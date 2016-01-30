/*
 * FRC TEAM 3997. 2016 Robot Code
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
import com.team3997.frc2016.util.cameraswitcher.CameraSwitcher;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.UpdateParameters;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class Robot extends IterativeRobot {
	
	public static boolean manualMode = false;

	LogitechF310Gamepad driverGamePad = Hardware.kDriverGamePad;
	LogitechF310Gamepad opGamePad = Hardware.kOpGamePad;
	Drive drive = Hardware.kDrive;
	Shooter shooter = Hardware.kShooter;
	Intake intake = Hardware.kIntake;
	Climber climber = Hardware.kClimber;
	ChickenRun chickenRun = Hardware.kChickenRun;
	CVVision vision = Hardware.kVision;
	CameraSwitcher cameraSwitcher = new CameraSwitcher(Hardware.kOpGamePad);
	DigitalOutput switcher = new DigitalOutput(8);
	Debounce manualToggle = new Debounce(opGamePad, Controls.MANUAL_CONTROL_TOGGLE_BUTTON);
	public static Auton auton = new Auton();
	//AHRS accel = new AHRS();

	@Override
	public void robotInit() {
		System.out.println("Start robotInit()");
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
		
	}

	@Override
	public void teleopInit() {
		System.out.println("Start teleopInit()");

		auton.stop();

		cameraSwitcher.init();
		cameraSwitcher.start(); // Start camerSwitcher Thread

		UpdateParameters.update();
		//accel.reset();
	}

	@Override
	public void teleopPeriodic() {
		drive.runTeleOp();
		shooter.runTeleOp();
		intake.runTeleOp();
		climber.runTeleOp();
		chickenRun.runTeleOp();
		
		//Change between manual and automatic mode
		if(manualToggle.getFall()){
			manualMode = !manualMode;
		}
		switcher.enablePWM(0.5);
		switcher.set(true);
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
