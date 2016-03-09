package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;
import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeed {

	private Thread m_thread = null;

	F310 gamePad;
	Debounce yellowToggle;
	Debounce redToggle;
	Debounce blueToggle;
	Debounce greenToggle;
	
	Debounce cameraToggleButton;
	boolean cameraToggleBoolean;
	
	boolean frontCamIsActive = true;
	boolean backCamIsActive = false;
	
	public int sessionFront;
	public int sessionBack;
	
	Image frame;

	NIVision.Rect yellowRect = new NIVision.Rect(20, 170, 50, 50);
	NIVision.Rect redRect = new NIVision.Rect(45, 170, 50, 50); 
	NIVision.Rect blueRect = new NIVision.Rect(100, 170, 10, 10); 
	NIVision.Rect greenRect = new NIVision.Rect(90, 170, 35, 35); 

	NIVision.Rect activeRect = yellowRect; // default rectangle is close rectangle

	public CameraFeed(F310 kGamePad) {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		sessionFront = NIVision.IMAQdxOpenCamera(Params.CAMERA_AXIS,NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		sessionBack = NIVision.IMAQdxOpenCamera(Params.CAMERA_USB,NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		
		gamePad = kGamePad;

		yellowToggle = new Debounce(gamePad, F310.yellowButton);
		redToggle = new Debounce(gamePad, F310.redButton);
		blueToggle = new Debounce(gamePad, F310.blueButton);
		greenToggle = new Debounce(gamePad, F310.greenButton);
		
		cameraToggleButton = new Debounce(gamePad, Controls.CHANGE_CAMERA_FEED_TOGGLE_BUTTON);

		CameraServer.getInstance().setQuality(80);
	}

	public void stopCamThread() {
		stopFrontCam();
		stopBackCam();
		m_thread = null;
	}

	public void start() {
		initFrontCam();

		if (m_thread == null) {
			m_thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (m_thread != null) {
						loop();
					}
				}
			});
			m_thread.start();
		}
	}
	
	public void loop() {
		//Switch camera toggle boolean, and init the opposite camera
		if(cameraToggleButton.getFall()){
			cameraToggleBoolean = !cameraToggleBoolean;
			
			if(frontCamIsActive)
				initBackCam();
			else if(backCamIsActive)
				initFrontCam();
		}
		
		
		if(frontCamIsActive){
			frontCamGrabImage();
			drawRect(activeRect);
		}
		else if(backCamIsActive) {
			backCamGrabImage();
		}
		
		getRectFromButton();
			
		pushImage();
	}

	private void frontCamGrabImage() {
		try {
			NIVision.IMAQdxGrab(sessionFront, frame, 1);
		}
		catch (Exception e){
			System.out.println("Front Camera Disconnected. Reinitializing...");
			initFrontCam();
		}
	}
	
	private void backCamGrabImage() {
		try {
			NIVision.IMAQdxGrab(sessionBack, frame, 1);
		}
		catch (Exception e){
			System.out.println("Back Camera Disconnected. Reinitializing...");
			initBackCam();
		}
	}

	private void pushImage() {
		CameraServer.getInstance().setImage(frame);
	}

	private void drawRect(NIVision.Rect rect) {
		NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_INVERT,
				ShapeMode.SHAPE_RECT, 0.0f);
	}

	public void getRectFromButton() {
		if (yellowToggle.getFall()) {
			activeRect = yellowRect;
		}

		if (redToggle.getFall()) {
			activeRect = redRect;
		}

		if (blueToggle.getFall()) {
			activeRect = blueRect;
		}

		if (greenToggle.getFall()) {
			activeRect = greenRect;
		}
	}
	
	private void initFrontCam(){
		stopBackCam();
		NIVision.IMAQdxConfigureGrab(sessionFront);
        NIVision.IMAQdxStartAcquisition(sessionFront);
        
        frontCamIsActive = true;
        backCamIsActive = false;
	}
	
	private void initBackCam(){
		stopFrontCam();
		NIVision.IMAQdxConfigureGrab(sessionBack);
        NIVision.IMAQdxStartAcquisition(sessionBack);
        
        frontCamIsActive = false;
        backCamIsActive = true;
	}
	
	private void stopFrontCam(){
		NIVision.IMAQdxUnconfigureAcquisition(sessionFront);
        NIVision.IMAQdxStopAcquisition(sessionFront);
	}
	
	private void stopBackCam(){
		NIVision.IMAQdxUnconfigureAcquisition(sessionBack);
        NIVision.IMAQdxStopAcquisition(sessionBack);
	}
}
