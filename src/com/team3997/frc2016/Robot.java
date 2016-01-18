/*
 * FRC TEAM 3997. 2016 
 * 
 * Thanks to the following teams for sharing their code!: 
 * 	1477, 1899, 254
 *
 */
package com.team3997.frc2016;

import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.util.CameraSwitcher;
import com.team3997.frc2016.auton.AutonSystem;
import com.team3997.frc2016.components.GripVision;
import com.team3997.frc2016.components.UpdateParameters;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	
    //Drive drive = HardwareBase.kDrive;
    Drive drive;
	Shooter shooter;
	Intake intake;
	Climber climber;
	GripVision vision;
	CameraSwitcher cameraSwitcher;
	
	
	@Override
	public void robotInit() {
		
		// Init robot functions
		drive = new Drive();
    	shooter = new Shooter();
    	intake = new Intake();
    	climber = new Climber();
    	vision = new GripVision();
    	cameraSwitcher = new CameraSwitcher();
		
		cameraSwitcher = new CameraSwitcher();
		//autonSystem = new AutonSystem();
		
		// Update parameters from text file
		UpdateParameters.update();

		// vision.visionInit();
		
	}

	@Override
	public void autonomousInit() {
		UpdateParameters.update();
	
		//autonSystem.go();
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
		UpdateParameters.update();
		CameraSwitcher.init();
	}

	@Override
	public void teleopPeriodic() {
		drive.runTeleOp();
		shooter.runTeleOp();
		intake.runTeleOp();
		climber.runTeleOp();
		vision.runTeleOp();
		cameraSwitcher.runTeleOP();
	}

	@Override
	public void disabledInit() {
		CameraSwitcher.end();
		UpdateParameters.update();

	}

	@Override
	public void disabledPeriodic() {

	}

	@Override
	public void testInit() {
		UpdateParameters.update();
	}

	@Override
	public void testPeriodic() {

	}

}
