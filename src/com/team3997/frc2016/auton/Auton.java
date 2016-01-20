package com.team3997.frc2016.auton;

import edu.wpi.first.wpilibj.Timer;

import com.team3997.frc2016.auton.SetAutonMode;
import com.team3997.frc2016.auton.DashboardAutonPicker;

public class Auton {
	
	DashboardAutonPicker selector = new DashboardAutonPicker();
	SetAutonMode autoModeRunner = new SetAutonMode();
	public AutonMode autonMode;
	public static Timer autoTimer = new Timer();

	//List the dashboard options for the auton mode
	public void listOptions(){
		selector.listOptions();
	}
	
	public void start(){
		//Get the dashboard selection and set the auton mode
		autonMode = selector.pick();
		autoModeRunner.setAutoMode(autonMode);
		
		//run the prestart for the function
		autonMode.prestart();
		
		//Run the set autonmode
		autoTimer.start();
		autoModeRunner.start();
		stop();
	}
	
	public void stop(){
		autoModeRunner.stop();
	}
	
}