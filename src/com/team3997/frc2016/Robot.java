/*
 * FRC TEAM 3997. 2016 NI
 * 
 * Thanks to the following teams for sharing their code!: 
 * 	1477, 1899
 *
 */
package com.team3997.frc2016;

//import com.team3997.frc2016.commands.Vision;
import com.team3997.frc2016.subsystems.DriveSubsystem;
import com.team3997.frc2016.util.LogitechGamepad;
import com.team3997.frc2016.components.Dashboard;
import com.team3997.frc2016.components.UpdateParameters;
import com.team3997.frc2016.commands.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends IterativeRobot {
	
	LogitechGamepad gamePad;
	Vision vision;
	double xVal;
	double yVal;
	double zVal;
	
	private Dashboard dashboard;
	public static DriveSubsystem drive;
	
	
    public void robotInit() {
    	vision = new Vision();
    	drive = new DriveSubsystem();
    	dashboard = new Dashboard();
    	gamePad = new LogitechGamepad(Params.JOYSTICK_USB);
    	
		UpdateParameters.update();
		
		
    }

    public void autonomousInit() {
    	UpdateParameters.update();
    }
    
    public void autonomousPeriodic() {
    	
    }

    public void teleopInit(){
		vision.start();
    	
    	dashboard.put("x: ", xVal);
		dashboard.put("y: ", -yVal);
		dashboard.put("z: ", zVal);
		dashboard.put("Max MotorSpeed: ", Params.MOTOR_SPEED.getDouble());
    	
    	UpdateParameters.update();
    }
    
    public void teleopPeriodic() {
    	
    	vision.grab();
    	
    	xVal = (gamePad.getLeftX()) * (Params.MOTOR_SPEED.getDouble());
		yVal = (gamePad.getLeftY()) * (Params.MOTOR_SPEED.getDouble());
		zVal = (gamePad.getRightX()) * (Params.MOTOR_SPEED.getDouble());
		
		drive.setDrive(yVal, xVal, Params.squareInputs);
		
		dashboard.put("x: ", xVal);
		dashboard.put("y: ", -yVal);
		dashboard.put("z: ", zVal);
		
		dashboard.put("Vision Session ", vision.session);
		dashboard.put("Vision Buffer ", vision.buffer);
		
		/*dashboard.put("Camera Enabled?", Params.VISION);
        dashboard.put("Advanced Vision?", Params.VISION_ADVANCED);*/
    }
    
    public void disabledInit() {
        vision.stop();
    }
    
    public void disabledPeriodic() {
        
    }
    
    public void testInit() {
    	UpdateParameters.update();
    }
    
    public void testPeriodic() {
    
    }
    
}

