
package com.team3997.frc2016;

import com.team3997.frc2016.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	public static DriveSubsystem drive;
	Joystick gamePad;
	double xVal;
	double yVal;
	double rotVal;
	
    public void robotInit() {
    	
    	gamePad = new Joystick(Params.JOYSTICK_USB);
    	
    	
    	CameraServer.getInstance().startAutomaticCapture();
    }

    /**
     * This function is called once before the autonomous
     */
    public void autonomousInit() {
        
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called once before periodic operator control
     */
    public void teleopInit(){
    	
    }
    
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	xVal = gamePad.getX();
		yVal = gamePad.getY();
		rotVal = gamePad.getZ();
		drive.setDrive(gamePad.getX(), gamePad.getY(), Params.squareInputs);
    }
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
