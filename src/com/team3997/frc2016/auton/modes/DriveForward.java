package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.AutonCommand;

public class DriveForward extends AutonCommand {

	public static void run() {
		drive(3.0, !parallel, 0.0, 0.85);
		
	}

}