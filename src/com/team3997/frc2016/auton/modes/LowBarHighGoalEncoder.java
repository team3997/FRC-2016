package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;


public class LowBarHighGoalEncoder extends AutonMode{
	 	@Override
	    protected void routine() throws AutonModeEndedException{
		 	hitDriveStraightSetpoint(PIDParams.dtSetpoint.getDouble(), 0.4);
		 	waitForTankDrive(0.7, 0.75, -0.4);
		 	centerAimAndShoot(8.0, Params.SHOOTER_YELLOW_MOTOR_POWER, 4.0);
	    }
	 
	   @Override
	    public void prestart() {
	    	//Ball starts in robot already indexed.
	    	//Run compressor so the ball doesn't escape when going over defenses.
	  }
	
}