/*
 * FRC TEAM 3997. 2016 RR
 * 
 * Thanks to the following teams for sharing their code!: 
 * 	1477, 1899
 *
 */
package com.team3997.frc2016;

//import com.team3997.frc2016.commands.Vision;
import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.components.UpdateParameters;
import com.team3997.frc2016.components.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	DriveSubsystem drive;
	Shooter shooter;
	Intake intake;
	Climber climber;
	Vision vision;
	
	
    public void robotInit() {
    	drive = new DriveSubsystem();
    	shooter = new Shooter();
    	intake = new Intake();
    	climber = new Climber();
    	vision = new Vision();
    	Auton.listOptions();
    	
		UpdateParameters.update();
    }

    public void autonomousInit() {
    	UpdateParameters.update();
    	Auton.init();
    }
    
    public void autonomousPeriodic() {
    	Auton.run();
    }

    public void teleopInit(){
    	UpdateParameters.update();
    }
    
    public void teleopPeriodic() {
		drive.runTeleOp();
    	intake.runTeleOp();
    	shooter.runTeleOp();
    	climber.runTeleOp();
    }
    
    public void disabledInit() {
    	Auton.listOptions();
        UpdateParameters.update();
    }
    
    public void disabledPeriodic() {

    }
    
    public void testInit() {
    	UpdateParameters.update();
    }
    
    public void testPeriodic() {
    
    }
    
}

