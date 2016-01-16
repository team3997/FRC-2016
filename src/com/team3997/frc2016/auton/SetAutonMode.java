package com.team3997.frc2016.auton;

import com.team3997.frc2016.auton.AutonModes;
import com.team3997.frc2016.auton.modes.*;

public class SetAutonMode extends AutonModes{
	public static void run(int autonMode){
		//if(onePass){
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
		//onePass = false; //the onePass exits so this switch runs only once
	}
