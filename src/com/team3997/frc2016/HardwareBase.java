package com.team3997.frc2016;

import com.team3997.frc2016.components.Vision;
import com.team3997.frc2016.subsystems.Climber;
import com.team3997.frc2016.subsystems.Drive;
import com.team3997.frc2016.subsystems.Intake;
import com.team3997.frc2016.subsystems.Shooter;



public class HardwareBase {
	
	  public static Drive kDrive = new Drive(
			  Params.DRIVE_PINS[0],Params.DRIVE_PINS[1],Params.DRIVE_PINS[2],Params.DRIVE_PINS[3]);
	  
	  public static Intake kIntake = new Intake(
			  Params.INTAKE_PINS[0], Params.INTAKE_PINS[1]);
	  
	  public static Shooter kShooter = new Shooter(
			  Params.FLYWHEEL_PIN);
	  
	  public static Climber kClimber = new Climber();

	  public static Vision kVision = new Vision();
}
