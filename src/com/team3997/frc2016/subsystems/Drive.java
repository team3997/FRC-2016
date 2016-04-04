package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.F310;
import com.team3997.frc2016.util.PID.PID;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author mikechacko
 *
 */
public class Drive {
	
	double rightXVal, leftXVal, rightYVal, leftYVal;
	//public AnalogGyro gyro;
	public Encoder leftEncoder, rightEncoder;
	private F310 gamePad;
	boolean manualDrive = true;
	
	public PID encoderPIDSetpoint;

	boolean middleGoalVisionLineUpX = false;
	boolean leftGoalVisionLineUpX = false;
	boolean rightGoalVisionLineUpX = false;
	boolean gyroAdjust = false;
	double gyro_center_value = 0.0;
	double visionXOutput = 0.0;
	RobotDrive driveTrain;

	public Drive(int drivePin1, int drivePin2, int drivePin3, int drivePin4,
			Encoder leftEncoder, Encoder rightEncoder, F310 kGamePad) {

		gamePad = kGamePad;

		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		/*this.gyro = gyro;
		gyro.initGyro();
		gyro.reset();*/
		//gyroAngle=0;
		
		leftEncoder.reset();
		rightEncoder.reset();
		
		leftEncoder.setDistancePerPulse(Params.DRIVE_TRAIN_ENCODERS_DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(Params.DRIVE_TRAIN_ENCODERS_DISTANCE_PER_PULSE);
		driveTrain = new RobotDrive(drivePin1, drivePin2, drivePin3, drivePin4);
		
		encoderPIDSetpoint = new PID(leftEncoder, rightEncoder, driveTrain);
	}

	public void runTeleOp() {

		//Dashboard.put("Gyro Angle", getGyroAngle());
		
		// Get Joystick input from gamepad
		if(gamePad.getBackButton()){
			leftEncoder.reset();
			rightEncoder.reset();
		}
		
		Dashboard.put("left encoder raw", leftEncoder.getRaw());
		Dashboard.put("right encoder raw", rightEncoder.getRaw());
		
		Dashboard.put("left encoder ", leftEncoder.getDistance());
		Dashboard.put("right encoder", rightEncoder.getDistance());
		
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
		
		if(gamePad.getButton(Controls.GYRO_ADJUST)){
			manualDrive = false;
			gyroAdjust = true;
		}
		else{
			manualDrive = true;
			gyroAdjust = false;
		}

		if (gamePad.getButton(Controls.LEFT_GOAL_VISION_LINE_UP_X)) {
			manualDrive = false;
			gyroAdjust = false;
			leftGoalVisionLineUpX = true;
			middleGoalVisionLineUpX = false;
			rightGoalVisionLineUpX = false;
		} else if (gamePad.getButton(Controls.MIDDLE_GOAL_VISION_LINE_UP_X)) {
			manualDrive = false;
			gyroAdjust = false;
			leftGoalVisionLineUpX = false;
			middleGoalVisionLineUpX = true;
			rightGoalVisionLineUpX = false;
		} else if (gamePad.getButton(Controls.RIGHT_GOAL_VISION_LINE_UP_X)) {
			manualDrive = false;
			gyroAdjust = false;
			leftGoalVisionLineUpX = false;
			middleGoalVisionLineUpX = false;
			rightGoalVisionLineUpX = true;
		} else if (gamePad.getButton(Controls.GYRO_ADJUST)){
			manualDrive = false;
			gyroAdjust = true;
			leftGoalVisionLineUpX = false;
			middleGoalVisionLineUpX = false;
			rightGoalVisionLineUpX = false;
		}
		else {
			manualDrive = true;
			gyroAdjust = false;
			leftGoalVisionLineUpX = false;
			middleGoalVisionLineUpX = false;
			rightGoalVisionLineUpX = false;
		}

		// Drive at the given input magnitude
		if (manualDrive) {
			Dashboard.put("Driving", "Manual");
			if (Params.ARCADE_DRIVE)
				setArcadeDrive(leftYVal, rightXVal, Params.SQUARE_INPUTS);
			else
				setTankDrive(leftYVal, rightYVal, Params.SQUARE_INPUTS);
		} 
		else if (leftGoalVisionLineUpX) {
			Dashboard.put("Driving", "Auto aiming X Left");
			visionAutoAimX(Hardware.kGrip.getCenterX(),
					Params.LEFT_GOAL_X, PIDParams.visionThreshold.getDouble());
		} 
		else if (middleGoalVisionLineUpX) {
			Dashboard.put("Driving", "Auto aiming X Middle");
			visionAutoAimX(Hardware.kGrip.getCenterX(),
					Params.MIDDLE_GOAL_X, PIDParams.visionThreshold.getDouble());
		} 
		else if (rightGoalVisionLineUpX) {
			Dashboard.put("Driving", "Auto aiming X Right");
			visionAutoAimX(Hardware.kGrip.getCenterX(),
					Params.RIGHT_GOAL_X, PIDParams.visionThreshold.getDouble());
		}
		else if (gyroAdjust){
			Dashboard.put("Driving", "Gyro centering to " + gyro_center_value);
			gyroAdjust(gyro_center_value);
		}
	}
	
	public void gyroAdjust(double targetAngle){
		//TODO: change angle to angle = gyro.getAngle();
		double angle = 0;
		angle %= 360;
		if(angle > 180) angle = angle - 360;
		else if (angle <-180) angle = angle + 360;
		
		double mo = angle / 180;
		
		if(mo < 0.3 && mo >0) mo = 0.3;
		else if(mo > -0.3 && mo <=0) mo = -0.3;
		
		setArcadeDrive(0, -mo);
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
		driveTrain.arcadeDrive(-y, -x, squareInputs);
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
		driveTrain.arcadeDrive(-y, -x, false);
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
	 * oversampling rate, the gyro type and the A/D calibration values. The
	 * angle is continuous, that is it will continue from 360 to 361 degrees.
	 * This allows algorithms that wouldn't want to see a discontinuity in the
	 * gyro output as it sweeps past from 360 to 0 on the second time around.
	 *
	 * @return the current heading of the robot in degrees. This heading is
	 *         based on integration of the returned rate from the gyro.
	 */
	//TODO: uncomment
	/*public double getGyroAngle() {
		return gyro.getAngle();
	}*/

	/**
	 * Resets the gyro to a heading of zero.
	 */
	/*public void resetGyro() {
		gyro.reset();
	}*/

	public void visionAutoAimX(double currentTargetX, int goalTargetX, double lowThreshold) {

		double ms = (1.0 - (currentTargetX / (goalTargetX)));
		// System.out.println("visionThreshold" + lowThreshold);

		if (currentTargetX > 0) {

			if (ms < lowThreshold && ms > 0) {
				ms = lowThreshold;
			} else if (Math.abs(ms) < lowThreshold) {
				ms = -lowThreshold;
			}

			if (ms > 0.7 && ms > 0) {
				ms = 0.7;
			} else if (Math.abs(ms) > 0.7) {
				ms = -0.7;
			}

			// if(Timer.getFPGATimestamp()){

			if ((ms > 0 || ms < 0) && Math.abs(goalTargetX - currentTargetX) > 5) {
				setArcadeDrive(leftYVal, -ms);
				System.out.println("ms" + ms);
			} else {
				setArcadeDrive(leftYVal, 0.0);
			}
		} else {
			setArcadeDrive(leftYVal, 0.0);
		}
	}

}
