package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;


public class HighGoal extends AutonMode{
	 @Override
	    protected void routine() throws AutonModeEndedException{
		 	//Drive forward set distance.
		 	waitForDrive(1.5, 0.8, 0.0);
		 	waitForDrive(0.3, 0.0, 0.6);
		 	centerAim(3.0);
		 	runShooter(7.0, 2.0, 1.0);
		 	//Use gyro w/ PID to make sure it's driving straight.
		 	//Drive over defense (gyro PID is still enabled)
		 	//Use accelerometer to detect when we have finished going over the defense
		 	//Turn off gyro PID 
		 	//Now that we are past the defense, turn until we have hit a certain angle to face the goal.
		 	//Use vision tracking to adjust rotation of the bot to line up
		 	//Shoot
	    }

	    @Override
	    public void prestart() {
	    	//Ball starts in robot already indexed.
	    	//Run compressor so the ball doesn't escape when going over defenses.
	  }
	
}