package com.team3997.frc2016.util.PID;

import com.team3997.frc2016.util.AMT103V_Encoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class PID {
	
	private double P = 0.1;
	private double I = 0.00;
	private double D = 0.00;
	
	private double tolerance;
	private int samplesToAverage;
	
	private double outMin;
	private double outMax;
	
	private double rotationScale;
	
	private Encoder encoder;
	private Spark outputMotor1;
	private Spark outputMotor2;
	
	private PIDSourceType sensingType;
	
	public ShooterMotorsPIDOutput pidShooterOutput;
	
	protected PIDController pidController;
	
	
	//Shooter PID constructor
	public PID(Encoder shooterEncoder, Spark kOutputMotor1, Spark kOutputMotor2,
			double kTolerance, double kOutMin, double kOutMax, int kSamplesToAverage, PIDSourceType kType){
		tolerance = kTolerance;
		samplesToAverage = kSamplesToAverage;
		outMin = kOutMin;
		outMax = kOutMax;
		encoder = shooterEncoder;
		outputMotor1 = kOutputMotor1;
		outputMotor2 = kOutputMotor2;
		sensingType = kType;
		
		encoder.setPIDSourceType(sensingType);
    	encoder.setSamplesToAverage(samplesToAverage);
    	
    	pidShooterOutput = new ShooterMotorsPIDOutput(outputMotor1, outputMotor2);
    	
		pidController = new PIDController(P, I, D, encoder, pidShooterOutput);
		
		pidController.setOutputRange(outMin, outMax);
		pidController.setPercentTolerance(tolerance);
	}
	
	public void enablePID(){		
		pidController.enable();
	}
	
	public void disablePID(){
		pidController.disable(); //this also sets output to zero
	}

	public void setSetpoint(double newSetpoint){
		pidController.setSetpoint(newSetpoint);
	}
	
	public boolean onTarget(){
		return pidController.onTarget();
	}
	
	public boolean isPIDEnabled(){
		return pidController.isEnabled();
	}
	
	public double getError(){
		return pidController.getError();
	}
	

	public double getSetpoint(){
		return pidController.getSetpoint();
	}
	
    public void changePID(double P, double I, double D){
    	pidController.setPID(P, I, D);
    }
}
