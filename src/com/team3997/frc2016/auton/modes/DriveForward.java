package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;

public class DriveForward extends AutonMode{
	 @Override
	    protected void routine() throws AutonModeEndedException{
		 	waitForDrive(2.0, 0.5, 0.0);
	    }

	    @Override
	    public void prestart() {
	    	
	  }
	
}