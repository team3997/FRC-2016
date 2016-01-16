package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team3997.frc2016.Params;

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
	private static CameraServer server;
	private static AxisCamera Axis;
	private static USBCamera USB;
	private Debounce toggleCamButton;
	private Debounce toggleExpButton;
	private boolean toggleCam = false;
	public Image image;
	
	private Joystick gamePad;
	
	public CameraSwitcher(){
		USB = new USBCamera(Params.CAMERA_USB);
		image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		gamePad = new Joystick(Params.JOYSTICK_USB);
		toggleCamButton = new Debounce(gamePad, Params.CAMERA_TOGGLE_BUTTON);
		toggleExpButton = new Debounce(gamePad, 5);
		
		Axis = new AxisCamera("10.39.97.89");
		Axis.writeExposurePriority(0);
		
		server = CameraServer.getInstance();
        server.setQuality(50);

	}
	
	
	//This function runs during periodically during teleop 
	public void runTeleOP(){
		if(toggleCamButton.getFall()){ //if this doesn't work, try getRise
			toggleCam = !toggleCam; //Change the state of the toggle boolean
		}
		runCam();
		server.setImage(image);
		
	}
	
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
	
	public static void init(){
		USB.openCamera();
		USB.setFPS(15);
		USB.updateSettings();
		
		
		//Set default camera to automatically send default camera to dashboard
		USB.startCapture(); //start default camera (USB)*/
	}
	
	public static void end(){
		USB.closeCamera();
	}
}
