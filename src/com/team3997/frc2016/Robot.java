/*
 * FRC TEAM 3997. 2016 
 * 
 * Programming Team: 
 * -Damir Gluhak
 * -Lucy Zhao
 * -Michael Chacko
 * 
 * 
 * Thanks to the following teams for sharing their code: 
 * 	1477, 254!
 *
 */
package com.team3997.frc2016;

import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.components.*;
import com.team3997.frc2016.util.CameraSwitcher;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.UpdateParameters;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	LogitechDualGamepad driverGamepad = Hardware.kDriverGamepad;
	Drive drive = Hardware.kDrive;
	Shooter shooter = Hardware.kShooter;
	Intake intake = Hardware.kIntake;
	Climber climber = Hardware.kClimber;
	CVVision vision = Hardware.kVision;
	CameraSwitcher cameraSwitcher = Hardware.kCameraSwitcher;

	public static Auton auton;

	@Override
	public void robotInit() {
		System.out.println("Start robotInit()");

		auton = new Auton();
		auton.listOptions();

		// Update parameters from text file
		UpdateParameters.update();
	}

	@Override
	public void autonomousInit() {
		System.out.println("Start autonomousInit()");

		UpdateParameters.update();
		auton.start();
	}

	@Override
	public void autonomousPeriodic() {
		Dashboard.put("Auto mode running", auton.autonMode.m_active);
	}

	@Override
	public void teleopInit() {
		System.out.println("Start teleopInit()");

		auton.stop();

		cameraSwitcher.init();
		cameraSwitcher.start(); // Start camerSwitcher Thread

		UpdateParameters.update();
	}

	@Override
	public void teleopPeriodic() {
		drive.runTeleOp();
		shooter.runTeleOp();
		intake.runTeleOp();
		climber.runTeleOp();
		// vision.runTeleOp();
	}

	@Override
	public void disabledInit() {
		System.out.println("Start disabledInit()");

		cameraSwitcher.kill(); // fix thread stuff

		// Stop auto mode
		auton.stop();

		// cameraSwitcher.end();
		UpdateParameters.update();
	}

	@Override
	public void disabledPeriodic() {
		// cameraSwitcher.runTeleOp(); //debug
	}

	@Override
	public void testInit() {
		System.out.println("Start testInit()");

		UpdateParameters.update();
	}

	@Override
	public void testPeriodic() {

	}

}
