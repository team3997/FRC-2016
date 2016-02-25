package com.team3997.frc2016.util.PID;

import com.team3997.frc2016.subsystems.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;

public class GyroDriveAdjustment {
	
	private boolean m_enable = false;
	
	private double gyroThreshold[] = {2, 8};
	
	private double startAngle = 0;
	AnalogGyro gyro;
	Drive driveBase;
	
	public GyroDriveAdjustment(AnalogGyro kGyro, Drive kDriveBase){
		gyro = kGyro;
		driveBase = kDriveBase;
	}
	
	public void start(){
		startAngle = gyro.getAngle();
	}
	
	public void update(){
		if(m_enable){
			
			if(onTarget(gyroThreshold[0])){
				//do not set motor update
			}
			
			
			
			
		}
	}
	
	public boolean compareError(double compare){
		return error() > compare;
	}
	
	public boolean onTarget(double threshold){
		return (Math.abs(error()) <= threshold); //! Make sure this works for 360 numbers of range values
	}
	
	public double error(){
		return startAngle - gyro.getAngle();
	}

	public void disable(){
		m_enable = false;
	}
	
	public void enable(){
		m_enable = true;
	}
}
