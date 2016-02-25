package com.team3997.frc2016;

/**
 * 
 * All human controls go in this class.
 *
 */

public class Controls {

	// Driver:
	public final static int INVERT_DRIVE = F310.rightBumper;
	
	// Operator:
	public final static int MANUAL_CONTROL_TOGGLE_BUTTON = F310.startButton;
	
	public final static int CAMERAFEED_TOGGLE_BUTTON = F310.backButton;
	
	public final static int INTAKE_BUTTON = F310.rightBumper;
	public final static int OUTTAKE_BUTTON = F310.leftBumper;
	
	public final static int INTAKE_EXTEND_BUTTON = F310.blueButton;
	
	public final static int SHOOTER_RUN_MOTORS_BUTTON = F310.yellowButton;
	
	public final static int RUN_CRUN_TO_SHOOTER = F310.rightTrigger;
	
	
	
}

class F310 {
	public static final int greenButton = 1;
	public static final int blueButton = 3;
	public static final int redButton = 2;
	public static final int yellowButton = 4;
	public static final int backButton = 7;
	public static final int startButton = 8;
	public static final int leftBumper = 5;
	public static final int rightBumper = 6;
	public static final int leftStickClick = 9;
	public static final int rightStickClick = 10;
	public static final int leftTrigger = 11;
	public static final int rightTrigger = 12;
}