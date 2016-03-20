/*
 * FRC TEAM 3997. 2016 Robot Code
 * 
 * Programming Team: 
 * -Damir Gluhak
 * -Lucy Zhao
 * -Michael Chacko
 * 
 * 
 * Thanks to the following teams for sharing their code: 
 * 	1477, 254!
 *
 */
package com.team3997.frc2016;

import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.auton.Auton;
import com.team3997.frc2016.components.*;
import com.team3997.frc2016.components.vision.GRIP;
import com.team3997.frc2016.components.vision.Vision;
import com.team3997.frc2016.util.CameraFeed;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.F310;
import com.team3997.frc2016.util.UpdateParameters;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static boolean isManualMode = false;
	
	F310 driverGamePad = Hardware.kDriverGamePad;
	F310 opGamePad = Hardware.kOpGamePad;
	Drive drive = Hardware.kDrive;
	Shooter shooter = Hardware.kShooter;
	Intake intake = Hardware.kIntake;
	Hanger hanger = Hardware.kHanger;
	GRIP grip = Hardware.kGrip;
	Vision vision = Hardware.kVision;
	Lights lights = Hardware.kLights;
	CameraFeed cameraFeed = Hardware.kCameraFeed;
	Debounce manualToggle = new Debounce(opGamePad, Controls.MANUAL_CONTROL_TOGGLE_BUTTON);
	Encoder rpmEncoder = Hardware.kFlyWheelEncoder;
	public static Auton auton = new Auton();

	@Override
	public void robotInit() {
		System.out.println("Start robotInit()");
		System.out.println("*****Running Main Code Base*****");
		auton.listOptions();
		cameraFeed.initRect();
		// Update parameters from text file
		UpdateParameters.update();
	}

	@Override
	public void autonomousInit() {
		System.out.println("Start autonomousInit()");
		UpdateParameters.update();
		auton.start();
		cameraFeed.start();
		cameraFeed.initRect();
	}
	
	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		System.out.println("Start teleopInit()");
		auton.stop();
		UpdateParameters.update();
		shooter.initTeleOp();
		cameraFeed.start();
		cameraFeed.initRect();
	}

	@Override
	public void teleopPeriodic() {
		drive.runTeleOp();
		shooter.runTeleOp();
		intake.runTeleOp();
		hanger.runTeleOp();
		grip.runTeleOp();
		//Change between manual and automatic mode
		Dashboard.put("Manual Mode", isManualMode);
		if(manualToggle.getFall()){
			isManualMode = !isManualMode;
			shooter.shooterPID.disablePID();
		}
		
		if(isManualMode){
			//set lights to manual mode color
		}
		
	}

	@Override
	public void disabledInit() {
		System.out.println("Start disabledInit()");
		auton.stop();
		UpdateParameters.update();
		cameraFeed.start();
		cameraFeed.initRect();
	}

	@Override
	public void disabledPeriodic() {
		lights.setColor(Lights.PRIDE);
		grip.runTeleOp();
		Dashboard.put("Gyro Angle", drive.getGyroAngle());
		Dashboard.put("encoder pulses raw scaled", rpmEncoder.get());
    	Dashboard.put("encoder RPM rate", (rpmEncoder.getRate() * 60)); //rpm
    	Dashboard.put("encoder total distance (total rotations)", rpmEncoder.getDistance());
	}
}
