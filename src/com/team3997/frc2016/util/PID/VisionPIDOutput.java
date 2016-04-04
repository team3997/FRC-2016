package com.team3997.frc2016.util.PID;

import com.team3997.frc2016.Hardware;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

public class VisionPIDOutput implements PIDOutput{

	private double PIDloopOutput = 0;
	public boolean disablePIDControl = false;
	RobotDrive driveTrain;
	
	VisionPIDOutput(RobotDrive kDriveTrain){
		driveTrain = kDriveTrain;
	}
	
	@Override
	public void pidWrite(double output) {
		PIDloopOutput = output;
		
		//allows for pid control for rotation, and joystick control for forward/backward movement
		driveTrain.arcadeDrive(-Hardware.kDrive.leftYVal, -output, false); 
		
	}
	
	public double getPIDLoopOutput(){
		return PIDloopOutput;
	}

}
