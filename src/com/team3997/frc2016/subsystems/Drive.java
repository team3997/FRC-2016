package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.components.Dashboard;

import edu.wpi.first.wpilibj.RobotDrive;


public class Drive{
	
	double xValOP;
	double yValOP;
	
	private LogitechDualGamepad gamePad;

	RobotDrive driveTrain;
	
	//init
	public Drive(int pin1, int pin2, int pin3, int pin4) {
		gamePad = new LogitechDualGamepad(Params.DRIVER_JOYSTICK_USB);
		driveTrain = new RobotDrive(pin1, pin2, pin3, pin4);
    }
	
	 public void stop(){
	    	driveTrain.arcadeDrive(0, 0, false);
	 }
	
	//easy to use drive function
    public void setDrive(double x, double y, boolean squareInputs){
    	driveTrain.arcadeDrive(y, x, squareInputs); 
    }
    
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//Get Joystick input from gamepad
    	xValOP = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		yValOP = (gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
    	
		//Drive at the given input magnitude
    	setDrive(xValOP, yValOP, Params.SQUARE_INPUTS);
    	
    	//Print drive magnitudes if wanted
		if(Params.DASHBOARD_TELE_DRIVE){
			Dashboard.put("joystick x: ", xValOP);
			Dashboard.put("joystick y: ", yValOP);
		}
    }
}
