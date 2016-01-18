package com.team3997.frc2016.auton;

import com.team3997.frc2016.HardwareBase;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.auton.actions.*;
import com.team3997.frc2016.subsystems.Drive;
import com.team3997.frc2016.subsystems.Intake;

public abstract class AutonMode extends AutonModeBase {

	 protected Drive drive = new Drive();
	 //protected Intake intake = HardwareBase.kIntake;

    public void waitTime(double seconds) throws AutonModeEndedException {
        runAction(new TimeoutAction(seconds));
    }
    /*
    public void waitForDrive(double timeout) throws AutonModeEndedException {
        runAction(new WaitForDriveAction(timeout));
    }

    public void waitForGyroData(double timeout) throws AutonModeEndedException {
        runAction(new WaitForGyroDataAction(timeout));
    }
    */
}