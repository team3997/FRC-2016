package com.team3997.frc2016;

import com.team3997.frc2016.util.UpdateParameters;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDParams extends UpdateParameters {
	/*
	 * 
	 * Shooter RPM PID
	 * 
	 */
	
	public static Constant visionThreshold = new Constant("visionThreshold", 0.45);
	
	// Yellow Button
	public static Constant syGoalRPM = new Constant("yShooter_Goal_RPM", 4500);
	public static Constant syP = new Constant("yShooter_P", 0.030); //P
	public static Constant syI = new Constant("yShooter_I", 0.000); //I
	public static Constant syD = new Constant("yShooter_D", 0.000); //D
	
	public static Constant yChTop = new Constant("yChTop", 20);
	public static Constant yChLeft = new Constant("yChLeft", 170); 
	public static Constant yChHeight = new Constant("yChHeight", 50);
	public static Constant yChWidth = new Constant("yChWidth", 50); 
	
	// Red Button
	public static Constant srGoalRPM = new Constant("rShooter_Goal_RPM", 4800);
	public static Constant srP = new Constant("rShooter_P", 0.000); //P
	public static Constant srI = new Constant("rShooter_I", 0.000); //I
	public static Constant srD = new Constant("rShooter_D", 0.000); //D
	
	public static Constant rChTop = new Constant("rChTop", 45);
	public static Constant rChLeft = new Constant("rChLeft", 170); 
	public static Constant rChHeight = new Constant("rChHeight", 50);
	public static Constant rChWidth = new Constant("rChWidth", 50); 
	
	// Blue Button
	public static Constant sbGoalRPM = new Constant("bShooter_Goal_RPM", 4000);
	public static Constant sbP = new Constant("bShooter_P", 0.030); //P
	public static Constant sbI = new Constant("bShooter_I", 0.000); //I
	public static Constant sbD = new Constant("bShooter_D", 0.000); //D
	
	public static Constant bChTop = new Constant("bChTop", 45);
	public static Constant bChLeft = new Constant("bChLeft", 170); 
	public static Constant bChHeight = new Constant("bChHeight", 50);
	public static Constant bChWidth = new Constant("bChWidth", 50); 
	
	// Green Button
	public static Constant sgGoalRPM = new Constant("gShooter_Goal_RPM", 2000);
	public static Constant sgP = new Constant("gShooter_P", 0.030); //P
	public static Constant sgI = new Constant("gShooter_I", 0.000); //I
	public static Constant sgD = new Constant("gShooter_D", 0.000); //D
	
	public static Constant gChTop = new Constant("gChTop", 90);
	public static Constant gChLeft = new Constant("gChLeft", 170); 
	public static Constant gChHeight = new Constant("gChHeight", 35);
	public static Constant gChWidth = new Constant("gChWidth", 35); 
	
	public static final int sTolerance = 1; //tolerance in RPM
	public static final double sOutMin = 0.0; //minimum output value
	public static final double sOutMax = 1.0; //maximum output value
	public static final int sSamplesToAverage = 10; //Amount of samples to average
	public static final PIDSourceType sType = PIDSourceType.kRate;	
}
/*
yShooter_Goal_RPM 4800
yShooter_P 0.0
yShooter_I 0.000
yShooter_D 0.000
rShooter_Goal_RPM 4500
rShooter_P 0.015
rShooter_I 0.000
rShooter_D 0.060
bShooter_Goal_RPM 4500
bShooter_P 0.015
bShooter_I 0.000
bShooter_D 0.000
gShooter_Goal_RPM 3000
gShooter_P 0.015
gShooter_I 0.000
gShooter_D 0.060
*/