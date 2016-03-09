package com.team3997.frc2016;

import com.team3997.frc2016.util.UpdateParameters;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDParams extends UpdateParameters {
	/*
	 * 
	 * Shooter RPM PID
	 * 
	 */
	
	// Yellow Button
	public static Constant syGoalRPM = new Constant("yShooter_Goal_RPM", 5000);
	public static Constant syP = new Constant("yShooter_P", 0.030); //P
	public static Constant syI = new Constant("yShooter_I", 0.000); //I
	public static Constant syD = new Constant("yShooter_D", 0.000); //D
	
	// Red Button
	public static Constant srGoalRPM = new Constant("rShooter_Goal_RPM", 4500);
	public static Constant srP = new Constant("rShooter_P", 0.030); //P
	public static Constant srI = new Constant("rShooter_I", 0.000); //I
	public static Constant srD = new Constant("rShooter_D", 0.000); //D
	
	// Green Button
	public static Constant sgGoalRPM = new Constant("gShooter_Goal_RPM", 4100);
	public static Constant sgP = new Constant("gShooter_P", 0.030); //P
	public static Constant sgI = new Constant("gShooter_I", 0.000); //I
	public static Constant sgD = new Constant("gShooter_D", 0.000); //D
	
	// Blue Button
	public static Constant sbGoalRPM = new Constant("bShooter_Goal_RPM", 2000);
	public static Constant sbP = new Constant("bShooter_P", 0.030); //P
	public static Constant sbI = new Constant("bShooter_I", 0.000); //I
	public static Constant sbD = new Constant("bShooter_D", 0.000); //D
	
	public static final int sTolerance = 15; //tolerance in RPM
	public static final double sOutMin = 0.0; //minimum output value
	public static final double sOutMax = 1.0; //maximum output value
	public static final int sSamplesToAverage = 50; //Amount of samples to average
	public static final PIDSourceType sType = PIDSourceType.kRate;	
}
