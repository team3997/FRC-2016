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
	
	
	// Operator:
	
	public static int MANUAL_CONTROL_TOGGLE_BUTTON = 8;
	
	public static int CAMERAFEED_TOGGLE_BUTTON = 7;
	
	public static int INTAKE_BUTTON = 6;
	public static int OUTTAKE_BUTTON = 5;
	
	public static int INTAKE_EXTEND_BUTTON = 3;
	
	public static int SHOOTER_ENABLE_TOGGLE_BUTTON = 4;
	public static int SHOOTER_ADJUSTER_TOGGLE_BUTTON = 1;
	
	
	
}