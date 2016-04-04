package com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;

public class DriveTrainPIDOutput implements PIDOutput{

	private double PIDloopOutput = 0;
	public boolean disablePIDControl = false;
	public boolean arcade = true;
	RobotDrive driveTrain;
	
	DriveTrainPIDOutput(RobotDrive kDriveTrain){
		driveTrain = kDriveTrain;
	}
	
	@Override
	public void pidWrite(double output) {
		PIDloopOutput = output;
		
		if(!disablePIDControl){
			if(arcade){
				System.out.println("arcade");
				driveTrain.arcadeDrive(output, 0.00, false);
			}
			else {
				System.out.println("Tanks");
				driveTrain.tankDrive(output, 0.00, false);
			}
		}
		else {
			disablePIDControl = true;
		}
	}
	
	public double getPIDLoopOutput(){
		return PIDloopOutput;
	}

}
