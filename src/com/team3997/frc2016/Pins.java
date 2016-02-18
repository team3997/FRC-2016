package com.team3997.frc2016;

/**
 * 
 * All pin and port numbers go here.
 *
 */

//PORTS TAKEN:
/*
 *	PWM: 0 1 2 3 4 5 6 7 8 9
 *					 x x x x 
 *					 m m m m
 */

/*
 *	DIO: 0 1 2 3 4 5 6 7 8 9
 *		 x x x x x x x     x
 *		 e e e e e e b     a
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
	public static final int GYRO_PIN = 1;
	
	/*
	 * 
	 * Encoders
	 * 
	 */
	public static final int LEFT_DRIVE_ENCODER_PINS[] = {0, 1};
	public static final int RIGHT_DRIVE_ENCODER_PINS[] = {2, 3};
	
	public static final int SHOOTER_ENCODER_PINS[] = {5, 6};

	
	/*
	 * 
	 * Drive
	 */
	public static final int DRIVE_MOTOR_PINS[] = { 6, 7, 0, 1 }; // front left pin,
															// right pin
	
	/*
	 * 
	 * ChickenRun
	 * 
	 */
	public static final int CRUN_MOTOR_PIN = 9;
	public static final int CRUN_BREAKBEAM_PIN = 7;
	
	/*
	 * 
	 * Intake
	 */
	public static final int INTAKE_MOTOR_PIN = 8; // left, right
	public static final int INTAKE_EXTENDER_SOLE_PINS[] = {2, 3};

	/*
	 * 
	 * Shooter
	 */
	public static final int FLYWHEEL_MOTOR_PIN = 2;
	
	/*
	 * 
	 * Climber
	 */


}