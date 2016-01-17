package com.team3997.frc2016.auton;

import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeExecuter;

public class AutonSystem {
	
	AutonModeExecuter autonModeRunner;
	AutonMode mode;
	

	public AutonSystem(){
		autonModeRunner = new AutonModeExecuter();
	}
	
	
	public void go(){
		mode = AutonModeSelector.getInstance().getAutoMode();
		autonModeRunner.setAutoMode(mode);
		
		// Prestart auto mode
        mode.prestart();

        // Start control loops
        autonModeRunner.start();
	}
}
