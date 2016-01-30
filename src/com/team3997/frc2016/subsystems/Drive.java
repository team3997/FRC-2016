package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class Drive {

	double rightXVal;
	double leftXVal;
	double rightYVal;
	double leftYVal;
	public AnalogGyro gyro;
	public Encoder leftEncoder;
	public Encoder rightEncoder;

	// http://www.vexrobotics.com/vexpro/motion/gearboxes/wcp-ss.html
	// gear ratio: 8inch wheels 54:20 + 14t

	private LogitechF310Gamepad gamePad;

	RobotDrive driveTrain;

	// init
	public Drive(int drivePin1, int drivePin2, int drivePin3, int drivePin4,
			Encoder leftEncoder, Encoder rightEncoder, AnalogGyro gyro, LogitechF310Gamepad kGamePad) {

		gamePad = kGamePad;
		
		this.leftEncoder = leftEncoder;
		//this.rightMotorEnc = rightEncoder;
		this.gyro = gyro;
		this.gyro.initGyro();
		this.gyro.calibrate();
		
		resetGyro();
		
		driveTrain = new RobotDrive(drivePin1, drivePin2, drivePin3, drivePin4);
	}

	public void stop() {
		driveTrain.arcadeDrive(0, 0, false);
	}

	// easy to use drive function
	public void setArcadeDrive(double y, double x, boolean squareInputs) {
		driveTrain.arcadeDrive(y, -x, squareInputs);
	}
	
	public void setArcadeDrive(double y, double x) {
		driveTrain.arcadeDrive(y, -x, false);
	}
	
	public void setTankDrive(double lefty, double righty, boolean squareInputs) {
		driveTrain.tankDrive(lefty, righty, squareInputs);
	}
	
	public void setTankDrive(double lefty, double righty) {
		driveTrain.tankDrive(lefty, righty, false);
	}

	// Function that runs during teleop periodically
	public void runTeleOp() {

		// Get Joystick input from gamepad
		rightXVal = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED);
		leftXVal = (gamePad.getLeftX()) * (Params.DRIVE_MOTOR_SPEED);
		rightYVal = (gamePad.getRightY()) * (Params.DRIVE_MOTOR_SPEED);
		leftYVal = (gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED);

		//Button to reset gyro
		if(gamePad.getBlueButton()){
			resetGyro();
		}
		
		//If invert drive button is pressed, invert the drive values
		if(gamePad.getButton(Controls.INVERT_DRIVE)){
			rightXVal = -rightXVal;
			leftXVal = -leftXVal;
			rightYVal = -rightYVal;
			leftYVal = -leftYVal;
		}
		
		
		// Drive at the given input magnitude
		if(Params.ARCADE_DRIVE){
			setArcadeDrive(leftYVal, rightXVal, Params.SQUARE_INPUTS);
		}
		else{
			setTankDrive(leftYVal, rightYVal, Params.SQUARE_INPUTS);
		}
		
		// Print drive magnitudes if wanted
		if (Params.DASHBOARD_DRIVE_DEBUG) {

			Dashboard.put("Left Encoder", leftEncoder.get());
			//Dashboard.put("Right Encoder", rightEncoder.get());
		}
	}
	
	public double getGyroAngle(){
		return -gyro.getAngle();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
}
