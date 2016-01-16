package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.AutonFunctions;
import com.team3997.frc2016.auton.AutonCommand;


public class DriveForward extends AutonFunctions{
	
	public static void run(){
		AutonCommand.start();
		
		AutonCommand.drive(3.0, !parallel, 0.0, 0.85);
	}
	
}