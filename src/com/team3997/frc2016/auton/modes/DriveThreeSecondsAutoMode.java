package com.team3997.frc2016.auton.modes;

import com.team3997.frc2016.auton.AutonMode;
import com.team3997.frc2016.auton.AutonModeEndedException;

public class DriveThreeSecondsAutoMode extends AutonMode {
    @Override
    protected void routine() throws AutonModeEndedException {
    	System.out.println("routine auto mode");
    }

    @Override
    public void prestart() {
    	System.out.println("prestart auto mode");
    }
}
