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
		setState(RobotState.AUTONOMOUS);
		UpdateParameters.update();
	
		//autonSystem.go();
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
