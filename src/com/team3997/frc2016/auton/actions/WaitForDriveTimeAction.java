package com.team3997.frc2016.auton.actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitForDriveTimeAction extends Action {
	double goal_time;
    double start_time;
    
    double x_drive;
    double y_drive;
    
	public WaitForDriveTimeAction(double seconds, double y, double x) {
		goal_time = seconds;
		x_drive = x;
		y_drive = y;
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + goal_time);
    }

    @Override
    public void update() {
        drive.setArcadeDrive(y_drive, x_drive, false);
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
