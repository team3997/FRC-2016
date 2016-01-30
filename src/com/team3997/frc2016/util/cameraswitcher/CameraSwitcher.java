package com.team3997.frc2016.util.cameraswitcher;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.CameraServer;
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
	
	public Image imagergb;
	public Image imagehsl;
	
	private CameraServer server;
	
	private USBCamera USB;
	private AxisCamera Axis;
	
	private Debounce toggleCamButton;
	private boolean toggleCam = false;
	
	private boolean toggleThread = true;
	
	private LogitechF310Gamepad gamePad;
	
	public CameraSwitcher(LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		imagergb = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		imagehsl = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0);
		
		toggleCamButton = new Debounce(gamePad, Controls.CAMERAFEED_TOGGLE_BUTTON);
		
		USB = new USBCamera(Params.CAMERA_USB);
		Axis = new AxisCamera(Params.CAMERA_AXIS_IP);
		
		server = CameraServer.getInstance();
		server.setQuality(50);
		
		//Axis.getImage(image);
	}
	
	public void run(){
		while(toggleThread){
			
			if(toggleCamButton.getFall()) //if this doesn't work, try getRise
				toggleCam = !toggleCam; //Change the state of the toggle boolean
			
			
			if(toggleCam){
				USB.stopCapture();
				Axis.getImage(imagehsl);
			} 
			else {
				USB.startCapture();
				USB.getImage(imagergb);
			}
			
			sendCameraInfoToDashboard();
			
			if(toggleCam) 
				server.setImage(imagehsl);
			else 
				server.setImage(imagergb);
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