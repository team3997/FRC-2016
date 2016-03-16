package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.Params;

import edu.wpi.first.wpilibj.Timer;

public class RunShooterWheelsAction extends Action {
	double goal_time;
    double start_time;
    double speed;
    double warmup;
    
	public RunShooterWheelsAction(double seconds, double warmupWheels, double kSpeed) {
		goal_time = seconds;
		speed = kSpeed;
		warmup = warmupWheels;
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + goal_time);
    }

    @Override
    public void update() {
        shooter.run(speed);
        
        if(Timer.getFPGATimestamp() >= start_time + warmup){
        	cRun.run(Params.CRUN_SHOOTING_MOTOR_POWER);
        }
    }

    @Override
    public void done(){
    	shooter.stopShooter();
    }

    @Override
    public void start() {
        start_time = Timer.getFPGATimestamp();
    }

}
