package com.team3997.frc2016.auton.actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitForTankDriveTimeAction extends Action {
	double goal_time;
    double start_time;
    
    double left_drive;
    double right_drive;
    
	public WaitForTankDriveTimeAction(double seconds, double left, double right) {
		goal_time = seconds;
		left_drive = left;
		right_drive = right;
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + goal_time);
    }

    @Override
    public void update() {
        drive.setTankDrive(left_drive, right_drive, false);
    }

    @Override
    public void done(){
    	drive.stop();
    }

    @Override
    public void start() {
        start_time = Timer.getFPGATimestamp();
    }

}
