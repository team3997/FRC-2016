package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;
import com.team3997.frc2016.Controls;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraFeed{

	private Thread m_thread = null;

	F310 gamePad;
	
	Debounce yellowToggle;
	Debounce redToggle;
	Debounce red2Toggle;
	Debounce blueToggle;
	Debounce greenToggle;
	Debounce cameraToggle;

	public int sessionFront;
	public int sessionBack;
	public int currSession;
	public int buffer;
	Image frame;

	NIVision.Rect emptyRect;
	NIVision.Rect yellowRect;
	NIVision.Rect redRect;
	NIVision.Rect red2Rect;
	NIVision.Rect blueRect;
	NIVision.Rect greenRect;

	NIVision.Rect activeRect = yellowRect; // default rectangle is close rectangle
	NIVision.Rect active2Rect = emptyRect;

	public CameraFeed(F310 kGamePad) {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		sessionFront = NIVision.IMAQdxOpenCamera(Params.CAMERA_AXIS,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		
		sessionBack = NIVision.IMAQdxOpenCamera(Params.CAMERA_USB, 
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		
		gamePad = kGamePad;

		currSession = sessionFront;
		//NIVision.IMAQdxConfigureGrab(currSession); 
		
		yellowToggle = new Debounce(gamePad, F310.yellowButton);
		redToggle = new Debounce(gamePad, F310.redButton);
		red2Toggle = new Debounce(gamePad, F310.redButton);
		blueToggle = new Debounce(gamePad, F310.blueButton);
		greenToggle = new Debounce(gamePad, F310.greenButton);
		cameraToggle = new Debounce(gamePad, F310.backButton);
		
		CameraServer.getInstance().setQuality(50);
	}
	
	public void initRect(){
		emptyRect = new NIVision.Rect(0, 0, 0, 0);
		yellowRect = new NIVision.Rect(PIDParams.yChTop.getInt(), PIDParams.yChLeft.getInt(), PIDParams.yChHeight.getInt(), PIDParams.yChWidth.getInt());
		redRect = new NIVision.Rect(PIDParams.rChTop.getInt(), PIDParams.rChLeft.getInt(), PIDParams.rChHeight.getInt(), PIDParams.rChWidth.getInt());
		red2Rect = new NIVision.Rect(0, 0, 0, 0);
		blueRect = new NIVision.Rect(PIDParams.bChTop.getInt(), PIDParams.bChLeft.getInt(), PIDParams.bChHeight.getInt(), PIDParams.bChWidth.getInt()); 
		greenRect = new NIVision.Rect(PIDParams.gChTop.getInt(), PIDParams.gChLeft.getInt(), PIDParams.gChHeight.getInt(), PIDParams.gChWidth.getInt());
		
		activeRect = yellowRect;
		active2Rect = emptyRect;
	}
	
	private void init(){
		NIVision.IMAQdxConfigureGrab(currSession);
	}

	public void stop() {
		NIVision.IMAQdxStopAcquisition(currSession);
		m_thread = null;
	}

	public void start() {
		init();

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
		
		if(cameraToggle.getRise()){
			if(currSession == sessionFront){
		        NIVision.IMAQdxStopAcquisition(currSession);
		        currSession = sessionBack;
		        NIVision.IMAQdxConfigureGrab(currSession);
		    } else if(currSession == sessionBack){
		        NIVision.IMAQdxStopAcquisition(currSession);
		        currSession = sessionFront;
		        NIVision.IMAQdxConfigureGrab(currSession);
		    }
		}
		
		grabImage();
		getRectFromButton();
		drawRect(activeRect);
		drawRect(active2Rect);
		pushImage();
	}

	private void grabImage() {
		try {
			NIVision.IMAQdxGrab(currSession, frame, 1);
		}
		catch (Exception e){
			System.out.println("Camera Disconnected. Reinitializing...");
			init();
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
		if (yellowToggle.getRise()) {
			activeRect = yellowRect;
			active2Rect = emptyRect;
		}

		if (redToggle.getRise()) {
			activeRect = redRect;
			active2Rect = red2Rect;
		}

		if (blueToggle.getRise()) {
			activeRect = blueRect;
			active2Rect = emptyRect;
		}

		if (greenToggle.getRise()) {
			activeRect = greenRect;
			active2Rect = emptyRect;
		}
	}
}