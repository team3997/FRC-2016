package com.team3997.frc2016.auton.actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitForDriveTimeAction extends Action{
	double goal_time;
    double start_time;
    
    double x;
    double y;
    
	public WaitForDriveTimeAction(double seconds, double y, double x) {
		goal_time = seconds;
	}
    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() >= start_time + goal_time;
    }

    @Override
    public void update() {
    	drive.setDrive(y, x, false);
    }

    @Override
    public void done() {
    	drive.stop();
    }

    @Override
    public void start() {
        start_time = Timer.getFPGATimestamp();
    }

}
