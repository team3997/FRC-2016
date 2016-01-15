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
import com.team3997.frc2016.util.CameraSwitcher;
import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.components.UpdateParameters;
import com.team3997.frc2016.components.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	DriveSubsystem drive;
	Shooter shooter;
	Intake intake;
	Climber climber;
	Vision vision;
	CameraSwitcher cameraSwitcher;
	
    public void robotInit() {
    	//Init all the robot functions
    	drive = new DriveSubsystem();
    	shooter = new Shooter();
    	intake = new Intake();
    	climber = new Climber();
    	vision = new Vision();
    	cameraSwitcher = new CameraSwitcher();
    	
    	//List autonomous options to the driver station
    	Auton.listOptions();
    	
    	//Update parameters from text file
		UpdateParameters.update();
		
		cameraSwitcher.init();
		 //server = CameraServer.getInstance();
	     //server.setQuality(50);
	     //the camera name (ex "cam0") can be found through the roborio web interface
	     //server.startAutomaticCapture("cam1");
		
		
		//vision.visionInit();
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
    	vision.visionPut();
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

