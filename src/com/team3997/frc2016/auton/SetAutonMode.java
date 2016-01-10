package com.team3997.frc2016.auton;

import com.team3997.frc2016.auton.AutonModes;
import com.team3997.frc2016.auton.modes.*;

public class SetAutonMode extends AutonModes{
	static int onePass = 0;
	
	public static void set(int autonMode){
		if(onePass==0){
			switch(autonMode){
				case DO_NOTHING:
					DoNothing.run();
					break;
				case DRIVE_FORWARD:
					DriveForward.run();
					break;
				default:
					DoNothing.run();
					break;
			}
		}
		onePass++;
	}
	
}