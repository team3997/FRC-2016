package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.Dashboard;
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
	boolean manualDrive = true;
	boolean visionLineUpX = false;
	double visionXOutput = 0.0;
	
	boolean gyroAdjust = false;
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
		
		if(gamePad.getButton(Controls.VISION_LINE_UP_X)){
			manualDrive = false;
			visionLineUpX = true;
		}
		else{
			manualDrive = true;
			visionLineUpX = false;
		}
		
		if(gamePad.getButton(Controls.GYRO_ADJUST)){
			manualDrive = false;
			gyroAdjust = true;
		}
		else{
			manualDrive = true;
			gyroAdjust = false;
		}
		

		// Drive at the given input magnitude
		if(manualDrive){
			Dashboard.put("Driving", "Manual");
			if (Params.ARCADE_DRIVE)
				setArcadeDrive(leftYVal, rightXVal, Params.SQUARE_INPUTS);
			else
				setTankDrive(leftYVal, rightYVal, Params.SQUARE_INPUTS);
		}
		else{
			if(visionLineUpX){
				Dashboard.put("Driving", "Auto aiming X axis");
				//visionAutoAimX();
			}
			if(gyroAdjust){
				Dashboard.put("Driving", "Auto adjusting with gyro");
				//gyroAdjust();
			}
		}
	}
	
	
	public void visionAutoAimX(double currentTargetX, double goalTargetX){
		if(currentTargetX > 0){
			//adjust to the right
			if((currentTargetX < (0.7 * goalTargetX))){
				setArcadeDrive(leftYVal, 0.75);
			}
			else if((currentTargetX < (0.9 * goalTargetX))){
				setArcadeDrive(leftYVal, 0.5);
			}
			
			//adjust to the left
			if((currentTargetX > (1.3 * goalTargetX))){
				setArcadeDrive(leftYVal, -0.75);
			}
			else if((currentTargetX > (1.4 * goalTargetX))){
				setArcadeDrive(leftYVal, -0.5);
			}
		}
	}
	
	public void gyroAdjust(double targetAngle){
		double angle = getGyroAngle();
		angle /= 360;
		if(angle > 180) angle = angle - 360;
		else if (angle <-180) angle = angle + 360;
		
		double mo = -1 + (angle / targetAngle);
		
		if(mo>1) mo = 1; 
		else if(mo<-1) mo = -1;
		
		if(mo < 0.3 && mo >0) mo = 0.3;
		else if(mo > -0.3 && mo <=0) mo = -0.3;
		
		setArcadeDrive(0, mo);
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
