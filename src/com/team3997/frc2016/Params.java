package com.team3997.frc2016;

import com.team3997.frc2016.util.UpdateParameters;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

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
	 * Encoder Parameters
	 * 
	 */
	public static final EncodingType ENCODER_ENCODING_TYPE = EncodingType.k4X;
	public static final double ENCODER_DISTANCE_PER_PULSE = 1000;
	
	/*
	 * 
	 * Drive Parameters
	 */
	public static final boolean DASHBOARD_DRIVE_DEBUG = true;
	public static final double DRIVE_MOTOR_SPEED = 1.00;
	public static final boolean SQUARE_INPUTS = true;
	public static final boolean ARCADE_DRIVE = true; // arcade drive is true, tank is false
	
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
	public static final double SHOOTER_MOTOR_POWER = 1.00;
	public static final double SHOOTER_OUTTAKE_MOTOR_POWER = 0.60;
	
	/*
	 * 
	 * Climber Parameters
	 */
	


}