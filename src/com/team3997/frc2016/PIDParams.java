package com.team3997.frc2016;

import com.team3997.frc2016.util.UpdateParameters;
import com.team3997.frc2016.util.UpdateParameters.Constant;

import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDParams extends UpdateParameters {
	/*
	 * 
	 * Shooter RPM PID
	 * 
	 */
	public static Constant[] sP = new Constant[]{new Constant("Shooter_P", 0.004), new Constant("Shooter_P", 0.004), new Constant("Shooter_P", 0.004), new Constant("Shooter_P", 0.004)}; //P
	public static Constant[] sI = new Constant[]{new Constant("Shooter_I", 0.000), new Constant("Shooter_I", 0.000), new Constant("Shooter_I", 0.000), new Constant("Shooter_I", 0.000)}; //I
	public static Constant[] sD = new Constant[]{new Constant("Shooter_D", 0.002), new Constant("Shooter_D", 0.002), new Constant("Shooter_D", 0.002), new Constant("Shooter_D", 0.002)}; //D
	public static final int sRPM[] = {100, 200, 300, 400};
	public static final double sTolerance = 3;
	public static final double[] sOutMin = {-1, -1, -1, -1}; //minimum output value
	public static final double[] sOutMax = {1, 1, 1, 1}; //maximum output value
	public static final double sEncoderRotationScale = 0.06; //"distance" scale
	public static final int[] sSamplesToAverage = {50, 50, 50, 50}; //Amount of samples to average
	public static final PIDSourceType sType = PIDSourceType.kRate;
	
}
