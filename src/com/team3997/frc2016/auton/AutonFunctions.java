package com.team3997.frc2016.auton;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.components.Vision;
import com.team3997.frc2016.subsystems.Climber;
import com.team3997.frc2016.subsystems.DriveSubsystem;
import com.team3997.frc2016.subsystems.Intake;
import com.team3997.frc2016.subsystems.Shooter;
import com.team3997.frc2016.util.LogitechDualShockGamepad;

public class AutonFunctions{

	Shooter shooter = new Shooter();
	Intake intake = new Intake();
	Climber climber = new Climber();
	Vision vision = new Vision();
	DriveSubsystem drive = new DriveSubsystem();
	LogitechDualShockGamepad gamePad = new LogitechDualShockGamepad(Params.JOYSTICK_USB);
	
}