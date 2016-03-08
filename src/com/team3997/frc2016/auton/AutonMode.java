package com.team3997.frc2016.auton;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.auton.actions.*;
import com.team3997.frc2016.subsystems.*;

public abstract class AutonMode extends AutonBase{
	
	protected Drive drive = Hardware.kDrive;
	protected Shooter shooter = Hardware.kShooter;
	protected Intake intake = Hardware.kIntake;
	protected Hanger hanger = Hardware.kHanger;
	
	//Wait for a set amount of seconds
	public void waitTime(double seconds) throws AutonModeEndedException {
        runAction(new TimeoutAction(seconds));
    }
	
	//Drive for a set amount of seconds
	public void waitForDrive(double seconds, double y, double x) throws AutonModeEndedException {
        runAction(new WaitForDriveTimeAction(seconds, y, x));
    }
}