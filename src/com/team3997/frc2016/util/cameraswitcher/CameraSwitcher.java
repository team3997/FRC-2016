package com.team3997.frc2016.util.cameraswitcher;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * 
 * Allow the driver to change camera input on button press
 * 
 * the camera name (example "cam0", "cam1" ) can be found through the roborio web server
 * @author Michael
 *
 */

public class CameraSwitcher extends Thread{
	private Thread m_thread = null;
	
	public Image imagergb;
	public Image imagehsl;
	
	private CameraServer server;
	
	private USBCamera USB;
	private AxisCamera Axis;
	
	private Debounce toggleCamButton;
	private Debounce toggleExpButton;
	private boolean toggleCam = false;
	private boolean toggleExp = false;
	
	private boolean toggleThread = true;
	
	private LogitechF310Gamepad gamePad;
	
	public CameraSwitcher(){
		
		gamePad = Hardware.kDriverGamepad;
		imagergb = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		imagehsl = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0);
		
		toggleCamButton = new Debounce(gamePad, Params.OP_CAMERA_TOGGLE_BUTTON);
		toggleExpButton = new Debounce(gamePad, 4);
		
		USB = new USBCamera(Params.CAMERA_USB);
		Axis = new AxisCamera(Params.CAMERA_AXIS_IP);
		
		server = CameraServer.getInstance();
		server.setQuality(50);
		
		//Axis.getImage(image);
	}
	
	public void run(){
		while(toggleThread){
			if(toggleCamButton.getFall()){ //if this doesn't work, try getRise
				toggleCam = !toggleCam; //Change the state of the toggle boolean
			}
			
			if(toggleExpButton.getFall()){ //if this doesn't work, try getRise
				toggleExp = !toggleExp;
				runExp();
			}
			
			sendCameraInfoToDashboard();
			runCam();
			if(toggleCam) server.setImage(imagehsl);
			else server.setImage(imagergb);
		}
	}
	
	private void runCam(){
		if(toggleCam){
			USB.stopCapture();
			Axis.getImage(imagehsl);
		} 
		else {
			USB.startCapture();
			USB.getImage(imagergb);
		}
	}
	
	private void runExp(){
		if(toggleExp){
			Axis.writeExposurePriority(0);
		} 
		else {
			Axis.writeExposurePriority(50);
		}
	}
	
	
	
	public void init(){
		
		USB.openCamera();
		USB.setFPS(15);
		USB.updateSettings();
		
		
		//Set default camera to automatically send default camera to dashboard
		USB.startCapture(); //start default camera (USB)
		
		toggleThread = true;
	}
	
	public void kill(){
		toggleThread = false;
	}
	
	private void sendCameraInfoToDashboard(){
		if(Params.DASHBOARD_CAMERA_DEBUG){
			// Axis Camera settings
			Dashboard.put("AXISCAM Brightness", Axis.getBrightness());
			Dashboard.put("AXISCAM Compresssion", Axis.getCompression());
			Dashboard.put("AXISCAM Max FPS", Axis.getMaxFPS());
			Dashboard.put("AXISCAM Color Level", Axis.getColorLevel());
			Dashboard.put("AXISCAM Exposure Priority", Axis.getExposurePriority());
			
			Dashboard.put("USBCAM Brightness", USB.getBrightness());
			
			Dashboard.put("CAMSERVER Quality", server.getQuality());
		}
	}
	
}