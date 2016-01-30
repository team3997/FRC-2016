package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;


public class DriveMulti extends AutonMode{
	 @Override
	    protected void routine() throws AutonModeEndedException{
		 	waitForDrive(2.0, 0.3, 0, 5);
		 	waitTime(2);
		 	waitForDrive(2.0, -0.3, 0, 5);
	    }

	    @Override
	    public void prestart() {
	    	
	  }
	
}