package com.team3997.frc2016.auton;

import com.team3997.frc2016.Robot;
import com.team3997.frc2016.components.GripVision;
import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.util.LogitechDualGamepad;

public abstract class AutonMode extends AutonBase{
	
	LogitechDualGamepad gamePad = Robot.driverGamepad;
	protected Drive drive = Robot.drive;
	Shooter shooter = Robot.shooter;
	Intake intake = Robot.intake;
	Climber climber = Robot.climber;
	GripVision vision = Robot.vision;
	
	
}