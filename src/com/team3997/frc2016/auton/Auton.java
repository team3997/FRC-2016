package com.team3997.frc2016.auton;

import edu.wpi.first.wpilibj.Timer;

import com.team3997.frc2016.auton.SetAutonMode;
import com.team3997.frc2016.auton.DashboardAutonPicker;

public class Auton {
	
	DashboardAutonPicker selector = new DashboardAutonPicker();
	SetAutonMode autonModeRunner = new SetAutonMode();
	public AutonMode autonMode;
	public static Timer autonTimer = new Timer();

	//List the dashboard options for the auton mode
	public void listOptions(){
		selector.listOptions();
	}
	
	public void start(){
		//Get the dashboard selection and set the auton mode
		autonMode = selector.pick();
		autonModeRunner.setAutoMode(autonMode);
		
		//run the prestart for the function
		autonMode.prestart();
		
		//Run the set autonmode
		autonTimer.start();
		autonModeRunner.start();
		//stop(); //!! Check if this is necessary!!
	}
	
	public void stop(){
		autonModeRunner.stop();
	}
	
}