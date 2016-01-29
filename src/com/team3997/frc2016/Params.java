package com.team3997.frc2016;

import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.UpdateParameters;

/**
 * 
 * All parameters go in this class.
 *
 */

public class Params extends UpdateParameters {

	/*
	 * 
	 * Other Params
	 */
	public static final String CONSTANTS_FILE = "/home/admin/params.txt";
	public static final String CAMERA_USB = "cam1";
	public static final String CAMERA_AXIS = "cam0";
	public static final String CAMERA_AXIS_IP = "10.39.97.89";
	public static final boolean DASHBOARD_CAMERA_DEBUG = false;
	
	/*
	 * 
	 * Drive Parameters
	 */
	public static final boolean DASHBOARD_DRIVE_DEBUG = true;
	public static final double DRIVE_MOTOR_SPEED = 1.0;
	public static final boolean SQUARE_INPUTS = true;
	
	/*
	 * 
	 * ChickenRun
	 * 
	 */
	public static final double CRUN_MOTOR_POWER = 0.85;
	
	/*
	 * 
	 * Intake Parameters
	 */
	public static final double INTAKE_MOTOR_POWER = 0.85;
	public static final boolean DASHBOARD_INTAKE_DEBUG = true;

	/*
	 * 
	 * Shooter Parameters
	 */
	public static final double FLYWHEEL_MOTOR_POWER = 0.85;
	public static final boolean ADJUSTER_DEFAULT_POSITION = false; //true is up, false is down
	
	/*
	 * 
	 * Climber Parameters
	 */
	


}