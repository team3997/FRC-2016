package com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;

public class PID {
	
	private double P;
	private double I;
	private double D;
	
	private double tolerance;
	private int samplesToAverage;
	
	private double outMin;
	private double outMax;
	
	private double rotationScale;
	
	private Encoder encoder;
	private Talon outputMotor1;
	private Talon outputMotor2;
	
	private PIDSourceType sensingType;
	
	private double setpoint = 0;
	
	private ShooterMotorsPIDOutput pidOutput;
	
	protected PIDController pidController;
	
	
	//Shooter PID constructor
	public PID(Encoder kSensor, Talon kOutputMotor1, Talon kOutputMotor2, double kP, double kI, double kD,
			double kTolerance, double kOutMin, double kOutMax, 
			double kRotationScale, int kSamplesToAverage, PIDSourceType kType){
		
		P = kP;
		I = kI;
		D = kD;
		tolerance = kTolerance;
		samplesToAverage = kSamplesToAverage;
		outMin = kOutMin;
		outMax = kOutMax;
		rotationScale = kRotationScale;
		encoder = kSensor;
		outputMotor1 = kOutputMotor1;
		outputMotor2 = kOutputMotor2;
		sensingType = kType;
		
		encoder.setPIDSourceType(sensingType);
    	encoder.setDistancePerPulse(rotationScale);
    	encoder.setSamplesToAverage(samplesToAverage);
    	
    	pidOutput = new ShooterMotorsPIDOutput(outputMotor1, outputMotor2);
    	
		pidController = new PIDController(P, I, D, encoder, pidOutput);
		
		pidController.setOutputRange(outMin, outMax);
		pidController.setAbsoluteTolerance(tolerance);
	}
	
	public void enablePID(){
		pidController.enable();
	}
	
	public void disablePID(){
		pidController.disable(); //this also sets output to zero
	}
	
	public void setSetpoint(int newSetpoint){
		setpoint = newSetpoint;
		
		pidController.setSetpoint(setpoint);
	}
	
	
	public double getLoopOutput(){
		return pidOutput.getPIDLoopOutput();
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
		return setpoint;
	}
	
    public void changePID(int goalRPM, double P, double I, double D){
    	pidController.setPID(P, I, D);
    	setSetpoint(goalRPM);
    }
}
