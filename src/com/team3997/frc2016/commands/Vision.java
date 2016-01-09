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
	public boolean isVisionEnabled;
	//AxisCamera camera;
	//CameraServer server;
	Image frame;
	ColorRange colorRange;
    int targetCycler;
    boolean debug;
    
    int imaqError;
    
    double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
    
    private final NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(24, 49);     //Default hue range for yellow tote
    private final NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(67, 255);    //Default saturation range for yellow tote
    private final NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(49, 255);    //Default value range for yellow tote
    
    NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
    
    NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
	
	public Vision() {
    	
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        

        colorRange=ColorRange.YellowToteRange();
        targetCycler=0;
        
        debug=false;
    }
	
	public void start(){
		NIVision.IMAQdxConfigureGrab(session); //session?
        NIVision.IMAQdxStartAcquisition(session);
        isVisionEnabled = true;
        
	}
	
	public void grab(){
		buffer = NIVision.IMAQdxGrab(session, frame, 1);
		/*NIVision.imaqColorThreshold(frame, frame, 255, NIVision.ColorMode.HSV, 
				TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_VAL_RANGE);*/
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
        		DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
       		//int numParticles = NIVision.imaqCountParticles(frame, 1);
       		//SmartDashboard.putNumber("Masked particles", numParticles);
        //if (camera.isFreshImage()){
			//camera.getImage(frame);
        
			CameraServer.getInstance().setImage(frame);
			
			//filter out small particles
			/*float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
			criteria[0].lower = areaMin;
			imaqError = NIVision.imaqParticleFilter4(frame, frame, criteria, filterOptions, null);

			//Send particle count after filtering to dashboard
			numParticles = NIVision.imaqCountParticles(frame, 1);
			SmartDashboard.putNumber("Filtered particles", numParticles);*/
		
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