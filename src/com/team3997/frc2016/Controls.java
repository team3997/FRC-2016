package com.team3997.frc2016;

import com.team3997.frc2016.util.LogitechF310Gamepad;

/**
 * 
 * All human controls go in this class. note that due to our setup, all trigger/axis related controls will not be here.
 *
 */

public class Controls {
	public static LogitechF310Gamepad driverGamePad = Hardware.kDriverGamePad;
	public static LogitechF310Gamepad opGamePad = Hardware.kOpGamePad;
	
	// Driver:
	public static int INVERT_DRIVE = F310.rightBumper;
	
	// Operator:
	
	public static int MANUAL_CONTROL_TOGGLE_BUTTON = F310.startButton;
	
	public static int CAMERAFEED_TOGGLE_BUTTON = F310.backButton;
	
	public static int INTAKE_BUTTON = F310.rightBumper;
	public static int OUTTAKE_BUTTON = F310.leftBumper;
	
	public static int INTAKE_EXTEND_BUTTON = F310.blueButton;
	
	public static int SHOOTER_ENABLE_TOGGLE_BUTTON = F310.yellowButton;
	
	
	
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
}