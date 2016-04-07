package com.team3997.frc2016.auton;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.auton.actions.*;
import com.team3997.frc2016.components.vision.GRIP;
import com.team3997.frc2016.subsystems.*;

public abstract class AutonMode extends AutonBase{
	
	protected Drive drive = Hardware.kDrive;
	protected Shooter shooter = Hardware.kShooter;
	protected Intake intake = Hardware.kIntake;
	protected ChickenRun cRun = Hardware.kChickenRun;
	protected Hanger hanger = Hardware.kHanger;
	protected GRIP grip = Hardware.kGrip;
	
	//Wait for a set amount of seconds
	public void waitTime(double seconds) throws AutonModeEndedException {
        runAction(new TimeoutAction(seconds));
    }
	
	//Drive for a set amount of seconds
	public void waitForDrive(double seconds, double y, double x) throws AutonModeEndedException {
        runAction(new WaitForDriveTimeAction(seconds, y, x));
    }
	
	public void waitForTankDrive(double seconds, double left, double right) throws AutonModeEndedException {
        runAction(new WaitForTankDriveTimeAction(seconds, left, right));
    }
	
	public void hitDriveStraightSetpoint(double inches, double speed) throws AutonModeEndedException {
		runAction(new HitDriveStraightSetpointAction(inches, speed));
	}
	
	public void centerAimAndShoot(double seconds, double kSpeed, double aimingTime) throws AutonModeEndedException {
		runAction(new CenterAimAndShootAction(seconds, kSpeed, aimingTime));
	}
	
	public void centerAim(double seconds) throws AutonModeEndedException {
        runAction(new CenterAimAction(seconds));
    }
	
	public void runShooter(double seconds, double warmup, double speed) throws AutonModeEndedException {
        runAction(new RunShooterWheelsAction(seconds, warmup, speed));
    }
}