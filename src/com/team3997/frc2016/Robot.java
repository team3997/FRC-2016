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
import com.team3997.frc2016.components.UpdateParameters;
import com.team3997.frc2016.components.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	
	public enum RobotState {
        DISABLED, AUTONOMOUS, TELEOP
    }
	
	public static RobotState s_robot_state = RobotState.DISABLED;

    public static RobotState getState() {
        return s_robot_state;
    }

    public static void setState(RobotState state) {
        s_robot_state = state;
    }
	
    Drive drive = HardwareBase.kDrive;
	Shooter shooter = HardwareBase.kShooter;
	Intake intake = HardwareBase.kIntake;
	Climber climber = HardwareBase.kClimber;
	Vision vision = HardwareBase.kVision;
	
	CameraSwitcher cameraSwitcher;
	AutonSystem autonSystem;
	
	
	@Override
	public void robotInit() {
		
		// Init robot functions
		cameraSwitcher = new CameraSwitcher();
		autonSystem = new AutonSystem();
		
		// Update parameters from text file
		UpdateParameters.update();

		// vision.visionInit();
		
	}

	@Override
	public void autonomousInit() {
		setState(RobotState.AUTONOMOUS);
		UpdateParameters.update();
	
		autonSystem.go();
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
		setState(RobotState.TELEOP);
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
		setState(RobotState.DISABLED);
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
