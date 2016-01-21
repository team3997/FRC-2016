package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.util.Dashboard;


public class DriveForward extends AutonMode{
	 @Override
	    protected void routine(){
		 while(Auton.autonTimer.get() < 2){
			 drive.setDrive(0.25, 0, false);
		 }
	    }

	    @Override
	    public void prestart() {
	    	
	  }
	
}