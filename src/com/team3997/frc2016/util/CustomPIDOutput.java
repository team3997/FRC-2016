package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class CustomPIDOutput implements PIDOutput{

	private double loopOutput = 0;
	private static boolean isOutputtingMotor = false;
	private Talon outputMotor;
	
	CustomPIDOutput(){
		
	}
	
	CustomPIDOutput(Talon kOutputMotor){
		outputMotor = kOutputMotor;
		isOutputtingMotor = true;
	}
	
	@Override
	public void pidWrite(double output) {
		loopOutput = output;
		if(isOutputtingMotor){
			outputMotor.set(output);
		}
	}
	
	public double getLoopOutput(){
		return loopOutput;
	}

}
