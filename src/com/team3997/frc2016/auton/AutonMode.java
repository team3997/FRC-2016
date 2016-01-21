package com.team3997.frc2016.auton;

import com.team3997.frc2016.Robot;
import com.team3997.frc2016.auton.actions.*;
import com.team3997.frc2016.subsystems.*;

public abstract class AutonMode extends AutonBase{
	
	protected Drive drive = Robot.drive;
	protected Shooter shooter = Robot.shooter;
	protected Intake intake = Robot.intake;
	protected Climber climber = Robot.climber;
	
	public void waitTime(double seconds) throws AutonModeEndedException {
        runAction(new TimeoutAction(seconds));
    }
	
	public void waitForDrive(double seconds, double y, double x) throws AutonModeEndedException {
        runAction(new WaitForDriveTimeAction(seconds, y, x));
    }
	
}