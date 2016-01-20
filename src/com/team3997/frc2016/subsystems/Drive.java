package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.components.Dashboard;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;


public class Drive{
	
	double xValOP;
	double yValOP;
	
	public Encoder leftMotorEnc;
	public Encoder rightMotorEnc;
	
	private LogitechDualGamepad gamePad;

	RobotDrive driveTrain;
	
	//init
	public Drive() {
		leftMotorEnc = new Encoder(0,1);
		rightMotorEnc = new Encoder(2,3);
		
		gamePad = Robot.driverGamepad;
		driveTrain = new RobotDrive(Params.DRIVE_PINS[0],Params.DRIVE_PINS[1],Params.DRIVE_PINS[2],Params.DRIVE_PINS[3]);
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
		if(Params.DASHBOARD_DRIVE_DEBUG){
			Dashboard.put("joystick x: ", xValOP);
			Dashboard.put("joystick y: ", yValOP);
			
			Dashboard.put("Left Encoder", leftMotorEnc.getRaw());
			Dashboard.put("Right Encoder", rightMotorEnc.getRaw());
		}
    }
}
