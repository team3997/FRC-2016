package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.components.Dashboard;

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

public class CameraSwitcher{
	private static CameraServer dashboardImage;
	
	private static AxisCamera Axis;
	private static USBCamera USB;
	
	private Debounce toggleCamButton;
	private Debounce toggleExpButton;
	
	private boolean toggleCam = false;
	
	public Image image;
	
	private Joystick gamePad;
	
	public CameraSwitcher(){
		//Allocate the camera objects
		USB = new USBCamera(Params.CAMERA_USB);
		Axis = new AxisCamera(Params.CAMERA_AXIS_IP);
		
		gamePad = new Joystick(Params.DRIVER_JOYSTICK_USB);
		
		//allocate debounce objects for the toggle buttons
		toggleCamButton = new Debounce(gamePad, Params.OP_CAMERA_TOGGLE_BUTTON);
		toggleExpButton = new Debounce(gamePad, Params.EXPOSURE_BUTTON);
		
		//define the image type that is being sent to the dashboard
		image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		//set camera settings (untested)
		Axis.writeResolution(AxisCamera.Resolution.k320x240);
		Axis.writeExposurePriority(100);
		
		dashboardImage = CameraServer.getInstance();
		dashboardImage.setQuality(50);
	}
	
	
	//This function runs during periodically during teleop 
	public void runTeleOP(){
		if(toggleCamButton.getFall()){ //if this doesn't work, try getRise
			toggleCam = !toggleCam; //Change the state of the toggle boolean
		}
		
		sendCameraInfoToDashboard();
		
		runCam();
		dashboardImage.setImage(image); //This is the function that sends the picture to the Dashboard
		
	}
	
	//Depending on the state of the toggleCam boolean, switch which camera is sending the images
	private void runCam(){
		if(toggleCam){ 
			USB.stopCapture();
			Axis.getImage(image);
		} 
		else {
			USB.startCapture();
			USB.getImage(image);
		}
	}
	
	//This runs in teleop init
	public static void init(){
		USB.openCamera();
		USB.setFPS(15);
		USB.updateSettings();
		
		
		//Set default camera to automatically send default camera to dashboard
		USB.startCapture(); //start default camera (USB)*/
	}
	
	//This runs in disabled init
	public static void end(){
		USB.closeCamera();
	}
	
	//if enabled, send camera settings to the dashboard
	private void sendCameraInfoToDashboard(){
		if(Params.DASHBOARD_CAMERA_SETTINGS){
			// Axis Camera settings
			Dashboard.put("AXISCAM Brightness", Axis.getBrightness());
			Dashboard.put("AXISCAM Compresssion", Axis.getCompression());
			Dashboard.put("AXISCAM Max FPS", Axis.getMaxFPS());
			Dashboard.put("AXISCAM Color Level", Axis.getColorLevel());
			Dashboard.put("AXISCAM Exposure Priority", Axis.getExposurePriority());
			
			Dashboard.put("USBCAM Brightness", USB.getBrightness());
			
			Dashboard.put("CAMSERVER Quality", dashboardImage.getQuality());
		}
	}
}
