package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;


public class LowBarHighGoal extends AutonMode{
	 @Override
	    protected void routine() throws AutonModeEndedException{

		 	waitForDrive(1.5, 0.8, 0.0); //Drive forward over defense
		 	waitForDrive(0.3, 0.0, 0.6); //Turn right towards goal
		 	centerAimAndShoot(4.0, Params.SHOOTER_YELLOW_MOTOR_POWER, 3.0); //Aim the X axis
	    }

	    @Override
	    public void prestart() {
	    	//Ball starts in robot already indexed.
	    	//Run compressor so the ball doesn't escape when going over defenses.
	  }
	
}