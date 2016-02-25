package com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;

public class ShooterMotorsPIDOutput implements PIDOutput{

	private double PIDloopOutput = 0;
	private Talon outputMotor1;
	private Talon outputMotor2;
	public boolean disablePIDControl = false;
	
	ShooterMotorsPIDOutput(Talon kOutputMotor1, Talon kOutputMotor2){
		outputMotor1 = kOutputMotor1;
		outputMotor2 = kOutputMotor2;
	}
	
	public void setSoftDisable(boolean pidControl){
		disablePIDControl = pidControl;
	}
	
	@Override
	public void pidWrite(double output) {
		PIDloopOutput = output;
		
		if(!disablePIDControl){
			outputMotor1.set(output);
			outputMotor2.set(output);
		}
		else {
			disablePIDControl = true;
		}
	}
	
	public double getPIDLoopOutput(){
		return PIDloopOutput;
	}

}
