package com.team3997.frc2016.util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.components.Dashboard;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * 
 * Allow the driver to change camera input on button press
 * 
 * the camera name (example "cam0", "cam1" ) can be found through the roborio web server
 * @author Michael
 *
 */

public class CameraSwitcher{
	
	/*int currSession;
	int axisCam;
	int usbCam;
	
	Image frame;
	
	private Debounce toggleCamButton;
	//private Debounce toggleExpButton;
	
	private Joystick gamePad;
	
	//protected AxisCamera Axis;
	
	public CameraSwitcher(){
		//Axis = new AxisCamera(Params.CAMERA_AXIS_IP); //debug purposes
		gamePad = new Joystick(Params.DRIVER_JOYSTICK_USB);
		
		//allocate debounce objects for the toggle buttons
		toggleCamButton = new Debounce(gamePad, Params.OP_CAMERA_TOGGLE_BUTTON);
		//toggleExpButton = new Debounce(gamePad, Params.EXPOSURE_BUTTON);
		
		
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		axisCam = NIVision.IMAQdxOpenCamera(Params.CAMERA_AXIS, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		usbCam = NIVision.IMAQdxOpenCamera(Params.CAMERA_USB, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		
		currSession = axisCam;
		NIVision.IMAQdxConfigureGrab(currSession); 
	}
	
	
	//This function runs during periodically during teleop 
	public void run(){

		if(toggleCamButton.getFall()){
		    if(currSession == axisCam){
		        NIVision.IMAQdxStopAcquisition(currSession);
		        currSession = usbCam;
		        NIVision.IMAQdxConfigureGrab(currSession);
		    } else if(currSession == usbCam){
		        NIVision.IMAQdxStopAcquisition(currSession);
		        currSession = axisCam;
		        NIVision.IMAQdxConfigureGrab(currSession);
		    }
		}
		
		sendCameraInfoToDashboard();
		
		NIVision.IMAQdxGrab(currSession, frame, 1);
		CameraServer.getInstance().setImage(frame); //This is the function that sends the picture to the Dashboard
		
	}
	
	public void stopAcquistion(){
		NIVision.IMAQdxStopAcquisition(axisCam);
		NIVision.IMAQdxStopAcquisition(usbCam);
	}
	
	
	//if enabled, send camera settings to the dashboard
	private void sendCameraInfoToDashboard(){
		if(Params.DASHBOARD_CAMERA_DEBUG){
			// Axis Camera settings
			Dashboard.put("AXISCAM Brightness", Axis.getBrightness());
			Dashboard.put("AXISCAM Compresssion", Axis.getCompression());
			Dashboard.put("AXISCAM Max FPS", Axis.getMaxFPS());
			Dashboard.put("AXISCAM Color Level", Axis.getColorLevel());
			Dashboard.put("AXISCAM Exposure Priority", Axis.getExposurePriority());
			*/
			//Dashboard.put("USBCAM Brightness", USB.getBrightness());
			
			/*Dashboard.put("CAMSERVER Quality", CameraServer.getInstance().getQuality());
		}*/
	}
}
