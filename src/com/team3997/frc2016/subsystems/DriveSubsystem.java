package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.LogitechDualShockGamepad;
import com.team3997.frc2016.components.Dashboard;

import edu.wpi.first.wpilibj.RobotDrive;


public class DriveSubsystem{
	
	double xValOP;
	double yValOP;
	double zValOP;
	
	private LogitechDualShockGamepad gamePad;

	RobotDrive driveTrain;
	
	//init
	public DriveSubsystem() {
		gamePad = new LogitechDualShockGamepad(Params.JOYSTICK_USB);
		driveTrain = new RobotDrive(Params.DRIVE_PINS[0],Params.DRIVE_PINS[1],Params.DRIVE_PINS[2],Params.DRIVE_PINS[3]);
    }
	
	 public void stop(){
	    	driveTrain.arcadeDrive(0, 0, false);
	 }
	
	//easy to use drive function
    public void setDrive(double x, double y, boolean squareInputs){
    	driveTrain.arcadeDrive(-x, -y, squareInputs);
    }
    
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//Get Joystick input from gamepad
    	xValOP = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		yValOP = (gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		//zValOP = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
    	
		//Drive at the given input magnitude
    	setDrive(xValOP, yValOP, Params.squareInputs);
    	
    	//Print drive magnitudes if wanted
		if(Params.printTeleOpDriveOuputs){
			Dashboard.put("joystick x: ", xValOP);
			Dashboard.put("joystick y: ", -yValOP);
		}
    }
}
