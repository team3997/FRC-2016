package com.team3997.frc2016;

import com.team3997.frc2016.util.F310;

/**
 * 
 * Human controls
 *
 */

public class Controls {
	// Driver:
	public final static int INVERT_DRIVE = F310.rightBumper;
	
	public final static int VISION_LINE_UP_X = F310.greenButton;
	
	public final static int GYRO_ADJUST = F310.redButton;
	
	
	// Operator:
	public final static int MANUAL_CONTROL_TOGGLE_BUTTON = F310.startButton;
	
	public final static int INTAKE_BUTTON = F310.rightBumper;
	
	public final static int FORCE_OUTTAKE_BUTTON = F310.leftBumper; // run outtake function on both intake and chicken run
	public final static int SOFT_OUTTAKE_BUTTON = F310.leftTrigger; // run outtake function only on intake, not chicken run
	
	public final static int INTAKE_EXTEND_BUTTON = F310.backButton;
	
	public final static int RUN_CRUN_TO_SHOOTER = F310.rightTrigger;
	
	public final static int CHANGE_CAMERA_FEED_TOGGLE_BUTTON = F310.rightStickClick;
	
	/*
	 * 
	 * The colored buttons on the F310 run the shooter motors.
	 * 
	 */
}

