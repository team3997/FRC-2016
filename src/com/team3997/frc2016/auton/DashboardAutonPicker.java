package com.team3997.frc2016.auton;

import java.util.ArrayList;

import com.team3997.frc2016.auton.modes.*;
import com.team3997.frc2016.util.Dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardAutonPicker {
	
	SendableChooser autonChooser;
	private ArrayList<AutonMode> autonModes = new ArrayList<AutonMode>();
	int selectedIndex = 0;

	public DashboardAutonPicker() {
		registerAutonomous(new DoNothing()); //default
		registerAutonomous(new DriveForward());
		registerAutonomous(new DriveMulti());
		registerAutonomous(new HighGoal());
	}
	
	//Function that sends options to the dashboard
	public void listOptions() {
		autonChooser = new SendableChooser();

		// This puts up a bunch of radio buttons to the smart dashboard to allow
		// the user to choose which auto program will run
		autonChooser.addDefault("Do nuthin (Default)", 0);
		autonChooser.addObject("Drive Forward (3s)", 1);
		autonChooser.addObject("Drive Multi", 2); 
		autonChooser.addObject("High Goal", 3); 
		
		// Put all these radio buttons in the smart dashboard
		SmartDashboard.putData("Autonomous Selector", autonChooser);
	}
	
	public AutonMode pick() {
		Dashboard.put("Autonchooser Value", (int)autonChooser.getSelected());
		
		//set the automode
		setAutonModeByIndex((int)autonChooser.getSelected());
		
		return getAutonMode();
	}

	public void registerAutonomous(AutonMode auto) {
		autonModes.add(auto);
	}
	
	public AutonMode getAutonMode() {
		return autonModes.get(selectedIndex);
	}
	
    private void setAutonModeByIndex(int input) {
        if (input < 0 || input >= autonModes.size()) {
        	input = 0;
        }
        selectedIndex = input;
    }




}