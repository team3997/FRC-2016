package com.team3997.frc2016.auton;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.components.Vision;
import com.team3997.frc2016.subsystems.Climber;
import com.team3997.frc2016.subsystems.DriveSubsystem;
import com.team3997.frc2016.subsystems.Intake;
import com.team3997.frc2016.subsystems.Shooter;
import com.team3997.frc2016.util.LogitechDualGamepad;

/**
 * 
 * Different functions that autonomous programs may need to use
 * (driving, shooting, vision) 
 * @author Michael
 *
 */
public class AutonFunctions{

	static double goalTime = 0;
	public final static boolean parallel = true;
	
	static Shooter shooter = new Shooter();
	static Intake intake = new Intake();
	static Climber climber = new Climber();
	static Vision vision = new Vision();
	static DriveSubsystem driveTrain = new DriveSubsystem();
	static LogitechDualGamepad gamePad = new LogitechDualGamepad(Params.JOYSTICK_USB);
	
	//Function that returns the auton timer
	public static double getTime(){
		return Auton.autonTimer.get();
	}
	
	public static boolean isTime(){
		if(getTime() <= goalTime){
			return true;
		}
		return false;
	}
	
}