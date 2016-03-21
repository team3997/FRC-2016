package com.team3997.frc2016.auton.actions;

import com.sun.glass.ui.Robot;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.Timer;

public class CenterAimAndShootAction extends Action {
	double goal_time;
    double start_time;
    double shooter_speed;
    double aimingTime;
    double shooterSpinUpTime = 0.85;
    double timeout = 4.0;
    boolean shooter_boolean = false;
    
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
    	grip.updateGripValues();
    	
    	if(Timer.getFPGATimestamp() <= start_time + aimingTime){
    		drive.visionAutoAimX(grip.getCenterX(), Params.LEFT_GOAL_X, PIDParams.visionThreshold.getDouble());
    		System.out.println("centerx " + grip.getCenterX());
    		//System.out.println("aiming");
    	}
    	
    	if((Timer.getFPGATimestamp() >= start_time + aimingTime)&&(Timer.getFPGATimestamp() <= start_time + aimingTime + shooterSpinUpTime)){
    		shooter.run(shooter_speed);
    		drive.stop();
    	}
    	
    	if((Timer.getFPGATimestamp() >= start_time + aimingTime + shooterSpinUpTime) && (Timer.getFPGATimestamp() <= start_time + aimingTime + timeout)){
    		shooter.run(shooter_speed);
    		//drive.leftGoalVisionAutoAimX(grip.getCenterX(), Params.LEFT_GOAL_X, PIDParams.visionThreshold.getDouble());
    		if(grip.onTarget()){
    			shooter_boolean = true;
    		}
    		if(shooter_boolean){
				cRun.run(Params.CRUN_SHOOTING_MOTOR_POWER);
			}
    		//System.out.println("onTarget" + grip.onTarget());
    	}
    	

    }

    @Override
    public void done(){
    	drive.stop();
    	shooter.stopShooter();
    	cRun.run(0.0);
    }

    @Override
    public void start() {
        start_time = Timer.getFPGATimestamp();
    }

}
