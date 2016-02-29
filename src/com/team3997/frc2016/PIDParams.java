package com.team3997.frc2016;

import com.team3997.frc2016.util.UpdateParameters;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDParams extends UpdateParameters {
	/*
	 * 
	 * Shooter RPM PID
	 * 
	 */
	public static Constant sGoalRPM = new Constant("Goal_RPM", 3000);
	public static Constant sP = new Constant("Shooter_P1", 0.1); //P
	public static Constant sI = new Constant("Shooter_I1", 0.000); //I
	public static Constant sD = new Constant("Shooter_D1", 0.000); //D
	public static final double sTolerance = 15; //tolerance in RPM
	public static final double sOutMin = 0; //minimum output value
	public static final double sOutMax = 1; //maximum output value
	public static final int sSamplesToAverage = 50; //Amount of samples to average
	public static final PIDSourceType sType = PIDSourceType.kRate;	
}
