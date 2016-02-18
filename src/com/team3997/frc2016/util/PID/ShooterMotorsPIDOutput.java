package com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;

public class ShooterMotorsPIDOutput implements PIDOutput{

	private double PIDloopOutput = 0;
	private Talon outputMotor1;
	private Talon outputMotor2;
	
	ShooterMotorsPIDOutput(Talon kOutputMotor1, Talon kOutputMotor2){
		outputMotor1 = kOutputMotor1;
		outputMotor2 = kOutputMotor2;
	}
	
	@Override
	public void pidWrite(double output) {
		PIDloopOutput = output;
		outputMotor1.set(output);
		outputMotor2.set(output);
	}
	
	public double getPIDLoopOutput(){
		return PIDloopOutput;
	}

}
