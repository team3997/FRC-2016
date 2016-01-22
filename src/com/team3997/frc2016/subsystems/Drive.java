package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;


public class Drive{
	
	double xValOP;
	double yValOP;
	public AnalogGyro gyro;
	public Encoder leftMotorEnc;
	public Encoder rightMotorEnc;
	
	// http://www.vexrobotics.com/vexpro/motion/gearboxes/wcp-ss.html
	// gear ratio: 8inch wheels 54:20 + 14t
	
	
	private LogitechDualGamepad gamePad;

	RobotDrive driveTrain;
	
	//init
	public Drive() {
		leftMotorEnc = new Encoder(0,1);
		//rightMotorEnc = new Encoder(1,2);
		//gyro = new AnalogGyro(Params.GYRO_PIN);
		gamePad = Robot.driverGamepad;
		driveTrain = new RobotDrive(Params.DRIVE_PINS[0],Params.DRIVE_PINS[1],Params.DRIVE_PINS[2],Params.DRIVE_PINS[3]);
    }
	
	 public void stop(){
	    	driveTrain.arcadeDrive(0, 0, false);
	 }
	
	//easy to use drive function
    public void setDrive(double y, double x, boolean squareInputs){
    	driveTrain.arcadeDrive(y, -x, squareInputs); 
    }
    
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//Get Joystick input from gamepad
    	xValOP = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		yValOP = (gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
    	
		//Drive at the given input magnitude
    	setDrive(yValOP, xValOP, Params.SQUARE_INPUTS);
    	
    	//Print drive magnitudes if wanted
		if(Params.DASHBOARD_DRIVE_DEBUG){
			Dashboard.put("joystick x: ", xValOP);
			Dashboard.put("joystick y: ", yValOP);
			
			Dashboard.put("Left Encoder", leftMotorEnc.get());
			//Dashboard.put("Right Encoder", rightMotorEnc.get());
		}
    }
}
