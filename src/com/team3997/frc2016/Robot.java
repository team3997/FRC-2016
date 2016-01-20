/*
 * FRC TEAM 3997. 2016 
 * 
 * Thanks to the following teams for sharing their code!: 
 * 	1477, 1899, 254
 *
 */
package com.team3997.frc2016;

import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.auton.SetAutonMode;
import com.team3997.frc2016.components.*;
import com.team3997.frc2016.util.CameraSwitcher;
<<<<<<< HEAD
import com.team3997.frc2016.components.GripVision;
import com.team3997.frc2016.components.UpdateParameters;
=======
import com.team3997.frc2016.util.LogitechDualGamepad;
>>>>>>> michael

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	
    //Drive drive = HardwareBase.kDrive;
	public static LogitechDualGamepad driverGamepad;
	public static Drive drive;
	public static Shooter shooter;
	public static Intake intake;
	public static Climber climber;
	public static GripVision vision;
	public static CameraSwitcher cameraSwitcher;
	public static Auton auton;
	
	@Override
	public void robotInit() {
		
		// Init robot functions
		driverGamepad = new LogitechDualGamepad(Params.DRIVER_JOYSTICK_USB);
		drive = new Drive();
    	shooter = new Shooter();
    	intake = new Intake();
    	climber = new Climber();
    	//vision = new GripVision();
    	cameraSwitcher = new CameraSwitcher();
    	auton = new Auton();
    	
		auton.listOptions();
		
		// Update parameters from text file
		UpdateParameters.update();
	}

	@Override
	public void autonomousInit() {
		UpdateParameters.update();
		auton.start();
	}

	@Override
	public void autonomousPeriodic() {
		Dashboard.put("Auto mode running", auton.autonMode.m_active);
	}

	@Override
	public void teleopInit() {
		auton.stop();
		
		
		cameraSwitcher.init();
		cameraSwitcher.start();
		UpdateParameters.update();
		
	}

	@Override
	public void teleopPeriodic() {
		drive.runTeleOp();
		shooter.runTeleOp();
		intake.runTeleOp();
		climber.runTeleOp();
		//vision.runTeleOp();

	}

	@Override
	public void disabledInit() {
		
		cameraSwitcher.kill();
		
		// Stop auto mode
        auton.stop();
        
		//cameraSwitcher.end();
		UpdateParameters.update();

	}

	@Override
	public void disabledPeriodic() {
		//cameraSwitcher.runTeleOp(); //debug
	}

	@Override
	public void testInit() {
		UpdateParameters.update();
	}

	@Override
	public void testPeriodic() {

	}

}
