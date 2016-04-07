package com.team3997.frc2016.auton.actions;

import edu.wpi.first.wpilibj.Timer;

public class IntakeAndCenterBallAction extends Action {
	double goal_time;
    double start_time;
    double intakeTime = 0.75;
    double indexingTime = 0;
    
	public IntakeAndCenterBallAction(double seconds) {
		goal_time = seconds;
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + goal_time);
    }

    @Override
    public void update() {
		if((Timer.getFPGATimestamp() < start_time + intakeTime)){
            intake.intake();
            cRun.intake();
    	}
        else if((Timer.getFPGATimestamp() >= start_time + intakeTime)){
        	if(!cRun.isIndexed()){
        		cRun.outtake();
        	}
        	else {
        		cRun.stop();
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
