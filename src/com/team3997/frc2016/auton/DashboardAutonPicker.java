package com.team3997.frc2016.auton;

import com.team3997.frc2016.auton.AutonModes;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardAutonPicker extends AutonModes{
	static SendableChooser autoChooser;
	
	public static void listOptions() {
		// This puts up a bunch of radio buttons to the smart dashboard to allow
		// the user to choose which auto program will run
		autoChooser.addDefault("Do nuthin (Default)", DO_NOTHING); // default option
		autoChooser.addObject("Drive Forward (3s)", DRIVE_FORWARD); //other possible options:
		// Put all these radio buttons in the smart dashboard
		SmartDashboard.putData("Autonomous Selector", autoChooser);

		// reads the radio button and sets the selected value to autoMode
		
	}

	public static int pick() {
		return (int)autoChooser.getSelected();
	}
}