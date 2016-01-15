package com.team3997.frc2016.auton;

import edu.wpi.first.wpilibj.Timer;

import com.team3997.frc2016.auton.AutonModes;
import com.team3997.frc2016.auton.SetAutonMode;
import com.team3997.frc2016.auton.DashboardAutonPicker;
import com.team3997.frc2016.components.Dashboard;

public class Auton extends AutonModes{
	//NOTE: the map of the different autonomous functions is AutonModes.java
	
	
	public static int autonMode; //autonMode is the auton "program" the user selects to run
	static Timer timer = new Timer(); //Timer that keeps track of time during autonomous 

	public static void listOptions(){
		DashboardAutonPicker.listOptions(); //Print the different autonomous options to the dashboard
	}
	
	public static void init(){
		autonMode = DO_NOTHING; //set the default autnomous mode to do nothing
		autonMode = DashboardAutonPicker.pick(); //if the user selected an option, change the autonmode to that option
		Dashboard.put("AUTO autonMode" ,autonMode); //print the autonmode
	}
	
	public static void run(){
		SetAutonMode.run(autonMode); //run the selected autonomous mode
	}
	
}