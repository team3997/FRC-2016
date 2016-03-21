package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.Timer;

public class CenterAimAction extends Action {
	double goal_time;
    double start_time;
    
	public CenterAimAction(double seconds) {
		goal_time = seconds;
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + goal_time);
    }

    @Override
    public void update() {
    	drive.visionAutoAimX(grip.getCenterX(), Params.LEFT_GOAL_X, PIDParams.visionThreshold.getDouble());
    	
    	System.out.println("running centering");
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
