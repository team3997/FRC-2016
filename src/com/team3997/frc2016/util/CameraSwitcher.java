package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * Allow the driver to change camera input on button press
 * 
 * the camera name (ex "cam0") can be found through the roborio web server
 * @author Michael
 *
 */

public class CameraSwitcher{
	CameraServer USB;
	CameraServer Axis;
	
	public void init(){
		USB = CameraServer.getInstance();
		USB.setSize(0);
		USB.setQuality(50);
		
		Axis = CameraServer.getInstance();
		Axis.setSize(0);
		Axis.setQuality(50);
		
		USB.startAutomaticCapture("cam1"); //start default camera
	}
	
	public void runTeleOP(){
		//Toggle Camera
	}
}
