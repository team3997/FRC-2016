package com.team3997.frc2016.auton;

import java.util.ArrayList;

import com.team3997.frc2016.auton.modes.*;
import com.team3997.frc2016.components.Dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardAutonPicker {
	
	SendableChooser autoChooser;
	private ArrayList<AutonMode> autoModes = new ArrayList<AutonMode>();
	int selectedIndex = 0;

	public DashboardAutonPicker() {
		registerAutonomous(new DoNothing()); //default
		registerAutonomous(new DriveForward());
	}
	
	//Function that sends options to the dashboard
	public void listOptions() {
		autoChooser = new SendableChooser();

		// This puts up a bunch of radio buttons to the smart dashboard to allow
		// the user to choose which auto program will run
		autoChooser.addDefault("Do nuthin (Default)", 0);
		autoChooser.addObject("Drive Forward (3s)", 1); 
		
		// Put all these radio buttons in the smart dashboard
		SmartDashboard.putData("Autonomous Selector", autoChooser);
	}
	
	public AutonMode pick() {
		Dashboard.put("autochooser value", (int)autoChooser.getSelected());
		
		//set the automode
		setAutoModeByIndex((int)autoChooser.getSelected());
		
		return getAutoMode();
	}

	public void registerAutonomous(AutonMode auto) {
		autoModes.add(auto);
	}
	
	public AutonMode getAutoMode() {
		return autoModes.get(selectedIndex);
	}
	
    private void setAutoModeByIndex(int which) {
        if (which < 0 || which >= autoModes.size()) {
            which = 0;
        }
        selectedIndex = which;
    }




}