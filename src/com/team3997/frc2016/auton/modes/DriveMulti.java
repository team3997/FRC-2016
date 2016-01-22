package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;
import com.team3997.frc2016.util.Dashboard;


public class DriveMulti extends AutonMode{
	 @Override
	    protected void routine() throws AutonModeEndedException{
		 	waitForDrive(2, 0.3, 0);
		 	waitForDrive(2, -0.3, 0);
	    }

	    @Override
	    public void prestart() {
	    	
	  }
	
}