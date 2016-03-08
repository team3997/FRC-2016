package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;
import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.CameraServer;

public class FrontCamera {

	private Thread m_thread = null;

	F310 gamePad;
	Debounce yellowToggle;
	Debounce redToggle;
	Debounce blueToggle;
	Debounce greenToggle;

	public int session;
	public int buffer;
	Image frame;

	NIVision.Rect closeRect = new NIVision.Rect(20, 170, 50, 50); //yellow button
	NIVision.Rect farRect = new NIVision.Rect(80, 170, 35, 35); //red button
	NIVision.Rect otherRect = new NIVision.Rect(90, 170, 35, 35); //green button
	NIVision.Rect smallRect = new NIVision.Rect(100, 170, 10, 10); //blue button

	NIVision.Rect activeRect = closeRect; // default rectangle is close rectangle

	public FrontCamera(F310 kGamePad) {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		session = NIVision.IMAQdxOpenCamera(Params.CAMERA_AXIS,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);

		gamePad = kGamePad;

		yellowToggle = new Debounce(gamePad, F310.yellowButton);
		redToggle = new Debounce(gamePad, F310.redButton);
		blueToggle = new Debounce(gamePad, F310.blueButton);
		greenToggle = new Debounce(gamePad, F310.greenButton);

		CameraServer.getInstance().setQuality(80);
	}

	public void stop() {
		NIVision.IMAQdxStopAcquisition(session);
		m_thread = null;
	}

	public void start() {
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);

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
		pushImage();
	}

	private void grabImage() {
		try {
			NIVision.IMAQdxGrab(session, frame, 1);
		}
		catch (Exception e){
			System.out.println("Camera Failed");
			
			NIVision.IMAQdxConfigureGrab(session);
	        NIVision.IMAQdxStartAcquisition(session);
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
			activeRect = closeRect;
		}

		if (redToggle.getFall()) {
			activeRect = farRect;
		}

		if (greenToggle.getFall()) {
			activeRect = otherRect;
		}

		if (blueToggle.getFall()) {
			activeRect = smallRect;
		}
	}
}
