package com.team3997.frc2016.util;

import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * Allow the driver to change camera input on button press
 * 
 * the camera name (example "cam0", "cam1" ) can be found through the roborio web server
 * @author Michael
 *
 */

public class CameraSwitcher{
	private CameraServer USB;
	private CameraServer Axis;
	private Debounce toggleButton;
	private boolean toggleCam = false;
	
	private Joystick gamePad;
	
	public CameraSwitcher(){
		gamePad = new Joystick(Params.JOYSTICK_USB);
		toggleButton = new Debounce(gamePad, Params.CAMERA_TOGGLE_BUTTON);
		
		//USB Camera settings
		USB = CameraServer.getInstance();
		USB.setSize(0);
		USB.setQuality(50);
		
		//Axis Camera settings (try commenting out these settings if exposure settings get overwritten)
		Axis = CameraServer.getInstance();
		Axis.setSize(0);
		Axis.setQuality(50);
		
		//Set default camera to automatically send default camera to dashboard
		USB.startAutomaticCapture(Params.CAMERA_USB); //start default camera (USB)
	}
	
	//This function runs during periodically during teleop 
	public void runTeleOP(){
		if(toggleButton.getFall()){ //if this doesn't work, try getRise
			toggle(); 	//Activate the toggle function
			toggleCam = !toggleCam; //Change the state of the toggle boolean
		}
	}
	
	private void toggle(){
		if(!toggleCam){
			Axis.startAutomaticCapture(Params.CAMERA_AXIS);
		} 
		else {
			USB.startAutomaticCapture(Params.CAMERA_USB);
		}
	}
}
