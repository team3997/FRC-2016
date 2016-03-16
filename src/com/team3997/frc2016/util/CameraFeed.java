package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;
import com.team3997.frc2016.Controls;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraFeed{

	private Thread m_thread = null;

	F310 gamePad;
	Debounce yellowToggle;
	Debounce redToggle;
	Debounce red2Toggle;
	Debounce blueToggle;
	Debounce greenToggle;

	public int session;
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

		session = NIVision.IMAQdxOpenCamera(Params.CAMERA_AXIS,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);

		gamePad = kGamePad;

		yellowToggle = new Debounce(gamePad, F310.yellowButton);
		redToggle = new Debounce(gamePad, F310.redButton);
		red2Toggle = new Debounce(gamePad, F310.redButton);
		blueToggle = new Debounce(gamePad, F310.blueButton);
		greenToggle = new Debounce(gamePad, F310.greenButton);

		CameraServer.getInstance().setQuality(80);
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
		NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
	}

	public void stop() {
		NIVision.IMAQdxStopAcquisition(session);
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
		grabImage();
		getRectFromButton();
		drawRect(activeRect);
		drawRect(active2Rect);
		pushImage();
	}

	private void grabImage() {
		try {
			NIVision.IMAQdxGrab(session, frame, 1);
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