package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;


public class LowBarHighGoal extends AutonMode{
	 @Override
	    protected void routine() throws AutonModeEndedException{

		 	waitForDrive(1.5, 0.8, 0.0); //Drive forward over defense
		 	waitForDrive(0.3, 0.0, 0.6); //Turn right towards goal
		 	centerAim(3.0); //Aim the X axis
		 	runShooter(7.0, 2.0, 1.0); //Shoot
	    }

	    @Override
	    public void prestart() {
	    	//Ball starts in robot already indexed.
	    	//Run compressor so the ball doesn't escape when going over defenses.
	  }
	
}