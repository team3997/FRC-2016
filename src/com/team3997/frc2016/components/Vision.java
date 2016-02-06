package com.team3997.frc2016.components;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera.ExposureControl;
import edu.wpi.first.wpilibj.vision.USBCamera;



public class Vision {

	private static CameraServer server;
	public static AxisCamera Axis;
	private static USBCamera usb;
	private static DigitalOutput targetLED;
	
	public Image frame1;
	public Image frame2;
	
	public Vision(DigitalOutput kTargetLED){
		targetLED = kTargetLED;
		
		frame1 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		frame2 = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		Axis = new AxisCamera("10.39.97.89");
		Axis.writeMaxFPS(15);
		
		Axis.writeExposureControl(ExposureControl.kAutomatic);
		
		/*usb = new USBCamera(Params.CAMERA_USB);
		
		usb.setFPS(15);
		usb.setExposureManual(100);
		usb.startCapture();*/


		server = CameraServer.getInstance();
	}
	
	public void runTeleOp(){
		if(Hardware.kDriverGamePad.getBackButton()){
			sendSubtractedImage();
		}
		else{
			grabImage(true, frame1);
			server.setImage(frame1);
		}
	}
	
	public void sendSubtractedImage(){
		grabImage(true, frame1);
		Timer.delay((0.0467));
		grabImage(false, frame2);
		

		NIVision.imaqSubtract(frame1, frame1, frame2);
		
		server.setImage(frame1);
	}
	
	public void grabImage(boolean ledState, Image frame){
		targetLED.set(ledState);
		Timer.delay(0.01);
		Axis.getImage(frame);
	}

}