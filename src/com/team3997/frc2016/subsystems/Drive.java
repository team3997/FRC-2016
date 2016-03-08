package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.F310;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * @author mikechacko
 *
 */
/**
 * @author mikechacko
 *
 */
public class Drive{

	double rightXVal, leftXVal, rightYVal, leftYVal;
	public AnalogGyro gyro;
	public Encoder leftEncoder, rightEncoder;
	private F310 gamePad;
	RobotDrive driveTrain;

	public Drive(int drivePin1, int drivePin2, int drivePin3, int drivePin4, AMT103V_Encoder leftEncoder,
			AMT103V_Encoder rightEncoder, AnalogGyro gyro, F310 kGamePad) {

		gamePad = kGamePad;

		this.leftEncoder = leftEncoder.getEncoderObject();
		this.rightEncoder = rightEncoder.getEncoderObject();

		driveTrain = new RobotDrive(drivePin1, drivePin2, drivePin3, drivePin4);
	}

	public void runTeleOp() {
		// Get Joystick input from gamepad
		rightXVal = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED);
		leftXVal = (gamePad.getLeftX()) * (Params.DRIVE_MOTOR_SPEED);
		rightYVal = (gamePad.getRightY()) * (Params.DRIVE_MOTOR_SPEED);
		leftYVal = (-gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED);

		// If invert drive button is pressed, invert the drive values
		if (gamePad.getButton(Controls.INVERT_DRIVE)) {
			rightXVal = -rightXVal;
			leftXVal = -leftXVal;
			rightYVal = -rightYVal;
			leftYVal = -leftYVal;
		}

		// Drive at the given input magnitude
		if (Params.ARCADE_DRIVE)
			setArcadeDrive(leftYVal, rightXVal, Params.SQUARE_INPUTS);
		else
			setTankDrive(leftYVal, rightYVal, Params.SQUARE_INPUTS);
	}

	/**
	 * Stop the robot from driving
	 */
	public void stop() {
		driveTrain.arcadeDrive(0.00, 0.00, false);
	}

	/**
	 * Set drive motor output in arcade style
	 * 
	 * @param y
	 *            forward direction
	 * @param x
	 *            rotate direction
	 * @param squareInputs
	 *            whether to square the x and y inputs to change input
	 *            sensitivity
	 */
	public void setArcadeDrive(double y, double x, boolean squareInputs) {
		driveTrain.arcadeDrive(y, -x, squareInputs);
	}

	/**
	 * Set drive motor output in arcade style
	 * 
	 * @param y
	 *            forward direction
	 * @param x
	 *            rotate direction
	 */
	public void setArcadeDrive(double y, double x) {
		driveTrain.arcadeDrive(y, x, false);
	}

	/**
	 * Set drive motor output in tank drive style
	 * 
	 * @param lefty
	 *            left input
	 * @param righty
	 *            right input
	 * @param squareInputs
	 *            whether to square the inputs to change input sensitivity
	 */
	public void setTankDrive(double lefty, double righty, boolean squareInputs) {
		driveTrain.tankDrive(lefty, righty, squareInputs);
	}

	/**
	 * Set drive motor output in tank drive style
	 * 
	 * @param lefty
	 *            left input
	 * @param righty
	 *            right input
	 */
	public void setTankDrive(double lefty, double righty) {
		driveTrain.tankDrive(lefty, righty, false);
	}

	 /**
	   * Return the actual angle in degrees that the robot is currently facing.
	   *
	   * The angle is based on the current accumulator value corrected by the
	   * oversampling rate, the gyro type and the A/D calibration values. The angle
	   * is continuous, that is it will continue from 360 to 361 degrees. This
	   * allows algorithms that wouldn't want to see a discontinuity in the gyro
	   * output as it sweeps past from 360 to 0 on the second time around.
	   *
	   * @return the current heading of the robot in degrees. This heading is based
	   *         on integration of the returned rate from the gyro.
	   */
	public double getGyroAngle() {
		return -gyro.getAngle();
	}

	/**
	 * Resets the gyro to a heading of zero.
	 */
	public void resetGyro() {
		gyro.reset();
	}

}
