package com.team3997.frc2016;

import com.team3997.frc2016.components.UpdateParameters;

public class Params extends UpdateParameters {
	
	
	/*
	 * 
	 * Joystick
	 * 
	 */
    	public static final int JOYSTICK_USB = 0;
	
	
	/*
	 * 
	 * Drive Parameters
	 * 
	 */
    	public static final int DRIVE_PINS[] = {8,9,7,6}; //front left pin, right pin
    	public static final boolean printTeleOpDriveOuputs = true;
    	public static final Constant DRIVE_MOTOR_SPEED = new Constant("MOTOR_SPEED", 0.5);
    	public static final boolean squareInputs = true;
    	
    	
    /*
     * 
     * Intake Parameters
     * 
     */
    	public static final int INTAKE_PINS[] = {0, 0}; //left, right
    	public static final double INTAKE_MOTOR_SPEED = 0.85;
    
    
    /*
     * 
     * Shooter Parameters
     * 
     */
    	public static final int FLYWHEEL_PIN = 0;
    	public static final double FLYWHEEl_MOTOR_SPEED = 0.85;
    
    /*
     * 
     * Climber Parameters
     * 
     */
    
    

}