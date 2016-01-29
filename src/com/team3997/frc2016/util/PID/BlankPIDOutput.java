package com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class BlankPIDOutput implements PIDOutput{

	private double loopOutput = 0;
	
	BlankPIDOutput(){
		
	}
	
	@Override
	public void pidWrite(double output) {
		loopOutput = output;
	}
	
	public double getLoopOutput(){
		return loopOutput;
	}

}
