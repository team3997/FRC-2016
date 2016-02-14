
package org.usfirst.frc.team3997.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
	
	RobotDrive myDrive;
	Joystick op, driver;
	Talon cRun, intake;
    public void robotInit() {
    	op = new Joystick(1);
    	driver = new Joystick(0);
    	myDrive = new RobotDrive(6,7,0,1);
    	cRun = new Talon(9);
    	intake = new Talon(8);
    }
    
    public void autonomousInit() {

    }

    public void autonomousPeriodic() {
    	
    }
    
    public void teleopInit() {
    	
    }

    public void teleopPeriodic() {
    	
    	myDrive.arcadeDrive(-driver.getRawAxis(1)*0.7, -driver.getRawAxis(4)*0.85, true);
    	//myDrive.arcadeDrive(0, 0, true)
    	
    	if(op.getRawButton(6)){
    		intake.set(0.80);
    	}
    	else if(op.getRawButton(5)){
    		intake.set(-0.80);
    	}
    	else
    		intake.set(0.0);
    	
    	if(driver.getRawButton(4)){
    		cRun.set(0.8);
    	}
    	else if(driver.getRawButton(1)){
    		cRun.set(-0.8);
    	}
    	else
    		cRun.set(0.0);
    }
    public void testPeriodic() {
    
    }
    
}
