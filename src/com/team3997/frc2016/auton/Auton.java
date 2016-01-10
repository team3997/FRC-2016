package com.team3997.frc2016.auton;

import edu.wpi.first.wpilibj.Timer;

import com.team3997.frc2016.auton.AutonModes;
import com.team3997.frc2016.auton.SetAutonMode;
import com.team3997.frc2016.auton.DashboardAutonPicker;
import com.team3997.frc2016.components.Dashboard;

public class Auton extends AutonModes{
	
	public static int autonMode;
	static Timer timer = new Timer();

	public static void listOptions(){
		DashboardAutonPicker.listOptions();
	}
	
	public static void init(){
		autonMode = DO_NOTHING;
		autonMode = DashboardAutonPicker.pick();
		Dashboard.put("AUTO autonMode" ,autonMode);
	}
	
	public static void run(){
		SetAutonMode.set(autonMode);
	}
	
}