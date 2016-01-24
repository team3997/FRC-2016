package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class Drive {

	double xValOP;
	double yValOP;
	public AnalogGyro gyro;
	public Encoder leftEncoder;
	public Encoder rightEncoder;

	// http://www.vexrobotics.com/vexpro/motion/gearboxes/wcp-ss.html
	// gear ratio: 8inch wheels 54:20 + 14t

	private LogitechF310Gamepad gamePad = Hardware.kDriverGamepad;

	RobotDrive driveTrain;

	// init
	public Drive(int drivePin1, int drivePin2, int drivePin3, int drivePin4,
			Encoder leftEncoder, Encoder rightEncoder, AnalogGyro gyro) {

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
	public void setDrive(double y, double x, boolean squareInputs) {
		driveTrain.arcadeDrive(y, -x, squareInputs);
	}
	
	public void setDrive(double y, double x) {
		driveTrain.arcadeDrive(y, -x, false);
	}

	// Function that runs during teleop periodically
	public void runTeleOp() {

		// Get Joystick input from gamepad
		xValOP = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		yValOP = (gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED.getDouble());

		//Button to reset gyro
		if(gamePad.getBlueButton()){
			resetGyro();
		}
		
		// Drive at the given input magnitude
		setDrive(yValOP, xValOP, Params.SQUARE_INPUTS);
		
		// Print drive magnitudes if wanted
		if (Params.DASHBOARD_DRIVE_DEBUG) {
			Dashboard.put("joystick x: ", xValOP);
			Dashboard.put("joystick y: ", yValOP);

			Dashboard.put("Left Encoder", leftEncoder.get());
			// Dashboard.put("Right Encoder", rightEncoder.get());
		}
	}
	
	public double getGyroAngle(){
		return -gyro.getAngle();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
}
