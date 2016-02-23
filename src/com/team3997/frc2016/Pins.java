package com.team3997.frc2016;

/**
 * 
 * All pin and port numbers go here.
 *
 */

//PORTS TAKEN:
/*
 *	PWM: 0 1 2 3 4 5 6 7 8 9
 *	     m m m m m m     m m 
 *				
 */

/*
 *	DIO: 0 1 2 3 4 5 6 7 8 9
 *	     e e e e     c i e e
 *		
 */

/*
 *	Analog: 0 1 2 3 
 *	        g
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
	public static final int SHOOTER_ENCODER_PINS[] = {8, 9};

	
	/*
	 * 
	 * Drive
	 */
	public static final int DRIVE_MOTOR_PINS[] = {0, 2, 1, 3}; // front left pin,
															     // right pin
	
	/*
	 * 
	 * ChickenRun
	 * 
	 */
	public static final int CRUN_MOTOR_PIN = 5;
	public static final int CRUN_INDEXER_PIN = 7;
	
	/*
	 * 
	 * Intake
	 */
	public static final int INTAKE_MOTOR_PIN = 4; // left, right
	public static final int INTAKE_EXTENDER_SOLE_PINS[] = {2, 3};

	/*
	 * 
	 * Shooter
	 */
	public static final int SHOOTER_MOTOR_PINS[] = {8, 9};
	
	/*
	 * 
	 * Climber
	 */

	/*
	 * 
	 * Camera LED
	 * 
	 */
	 public static final int GREEN_CAMERA_LED_PIN = 6;
}