package com.team3997.frc2016.commands;


import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera.Resolution;
import edu.wpi.first.wpilibj.Timer;

public class Vision {
	public int session;
	public int buffer;
	//AxisCamera camera;
	//CameraServer server;
	Image frame;
	ColorRange colorRange;
    int targetCycler;
    boolean debug;
    
    NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
	
	public Vision() {
    	
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        

        // open the camera at the IP address assigned. This is the IP address that the camera
        // can be accessed through the web interface.
        //camera = new AxisCamera("10.39.97.10"); 
        colorRange=ColorRange.YellowToteRange();
        targetCycler=0;
        
        debug=false;
    }
	
	public void start(){
		NIVision.IMAQdxConfigureGrab(session); //session?
        NIVision.IMAQdxStartAcquisition(session);
	}
	
	public void grab(){
		buffer = NIVision.IMAQdxGrab(session, frame, 1);
		//NIVision.imaqColorThreshold(frame, frame, 0, ColorMode.HSL, new Range(0, 255), new Range(0, 255), new Range(128, 255));
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
        
        //if (camera.isFreshImage()){
			//camera.getImage(frame);
			CameraServer.getInstance().setImage(frame);
		//}
	}

	public void stop(){
		NIVision.IMAQdxStopAcquisition(session);
	}
	
}	

class ColorRange{
	
	NIVision.Range hue;
	NIVision.Range saturation;
	NIVision.Range luminance;
	
	public ColorRange(int hueMin, int hueMax, int satMin, int satMax, int lumMin, int lumMax){
		hue=new NIVision.Range(hueMin, hueMax);
		saturation=new NIVision.Range(satMin, satMax);
		luminance=new NIVision.Range(lumMin, lumMax);
	}
	
	public static ColorRange YellowToteRange(){
		return new ColorRange(30,60,90,255,0,250);
	}
	
	public static ColorRange GrayToteRange(){
		return new ColorRange(120,160,5,30,20,200);
	}
	
	public static ColorRange RecycleBinRange(){
		return new ColorRange(80,160,40,160,0,160);
	}
}