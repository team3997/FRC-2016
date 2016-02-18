package com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;

public class MotorPIDOutput implements PIDOutput{

	private double loopOutput = 0;
	private Talon outputMotor;
	
	MotorPIDOutput(Talon kOutputMotor){
		outputMotor = kOutputMotor;
	}
	
	@Override
	public void pidWrite(double output) {
		loopOutput = output;
		outputMotor.set(output);
	}
	
	public double getLoopOutput(){
		return loopOutput;
	}

}