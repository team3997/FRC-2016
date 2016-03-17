package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.Timer;

public class CenterAimAndShootAction extends Action {
	double goal_time;
    double start_time;
    double shooter_speed;
    double aimingTime;
    
	public CenterAimAndShootAction(double seconds, double kSpeed, double kAimingTime) {
		goal_time = seconds;
		shooter_speed = kSpeed;
		aimingTime = kAimingTime;
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + goal_time);
    }

    @Override
    public void update() {
    
    	shooter.run(shooter_speed);
    	
    	if(Timer.getFPGATimestamp() <= start_time + aimingTime){
    		drive.leftGoalVisionAutoAimX(grip.getCenterX(), Params.LEFT_GOAL_X);
        	
        	if(grip.onTarget()){
            	cRun.run(Params.CRUN_SHOOTING_MOTOR_POWER);
            }
    	}
    	

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
