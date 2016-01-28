package com.team3997.frc2016;

import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.UpdateParameters;

/**
 * 
 * All pin and port numbers go here.
 *
 */

public class Pins {
	
	/*
	 * 
	 * Gamepads
	 */
	public static final int DRIVER_GAMEPAD_USB = 0;
	public static final int OP_GAMEPAD_USB = 1;
	
	/*
	 * 
	 * Gyro
	 * 
	 */
	public static final int GYRO_PIN = 0;
	
	/*
	 * 
	 * Encoders
	 * 
	 */
	public static final int LEFT_DRIVE_ENCODER_PINS[] = {0, 1};
	public static final int RIGHT_DRIVE_ENCODER_PINS[] = {2, 3};
	

	
	/*
	 * 
	 * Drive
	 */
	public static final int DRIVE_MOTOR_PINS[] = { 8, 9, 7, 6 }; // front left pin,
															// right pin
	
	/*
	 * 
	 * ChickenRun
	 * 
	 */
	public static final int CRUN_MOTOR_PIN = 3;
	public static final int CRUN_BREAKBEAM_PIN = 4;
	
	/*
	 * 
	 * Intake
	 */
	public static final int INTAKE_MOTOR_PINS[] = { 0, 1 }; // left, right
	public static final int INTAKE_EXTENDER_SOLE_PINS[] = { 0, 1 };

	/*
	 * 
	 * Shooter
	 */
	public static final int FLYWHEEL_MOTOR_PIN = 2;
	public static final int SHOOTER_ADJUSTER_SOLE_PIN = 3;
	
	/*
	 * 
	 * Climber
	 */


}