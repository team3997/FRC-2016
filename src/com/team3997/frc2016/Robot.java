/*
 * FRC TEAM 3997. 2016
 * 
 * Thanks to the following teams for sharing their code!: 
 * 	1477, 1899
 *
 */
package com.team3997.frc2016;

import com.team3997.frc2016.subsystems.DriveSubsystem;
import com.team3997.frc2016.components.Dashboard;
import com.team3997.frc2016.components.UpdateParameters;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Joystick gamePad;
	double xVal;
	double yVal;
	double zVal;
	//double motorSpeed;
	//Preferences prefs;
	Dashboard dashboard;
	public static DriveSubsystem drive;
	
	
    public void robotInit() {
    	drive = new DriveSubsystem();
    	dashboard = new Dashboard();
    	gamePad = new Joystick(Params.JOYSTICK_USB);
    	
    	//prefs = Preferences.getInstance();
		//motorSpeed = prefs.getDouble("motorSpeed", Params.MOTOR_SPEED);
    	
		UpdateParameters.update();
    	//CameraServer.getInstance().startAutomaticCapture();
    }

    public void autonomousInit() {
    	UpdateParameters.update();
    }
    
    public void autonomousPeriodic() {
    	
    }

    public void teleopInit(){
    	UpdateParameters.update();
    }
    
    public void teleopPeriodic() {
    	xVal = gamePad.getX() * Params.MOTOR_SPEED.getDouble();
		yVal = gamePad.getY() * Params.MOTOR_SPEED.getDouble();
		zVal = gamePad.getZ() * Params.MOTOR_SPEED.getDouble();
		
		drive.setDrive(yVal, xVal, Params.squareInputs);
		
		dashboard.put("x: ", xVal);
		dashboard.put("y: ", -yVal);
		dashboard.put("z: ", zVal);
    }
    
    public void testInit() {
    	UpdateParameters.update();
    }
    
    public void testPeriodic() {
    
    }
    
}

