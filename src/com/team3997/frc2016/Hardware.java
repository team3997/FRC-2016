package com.team3997.frc2016;

import com.team3997.frc2016.components.CVVision;
import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.util.cameraswitcher.CameraSwitcher;
//import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Hardware {

	/*
	 * 
	 * Interfaces
	 */
	public static LogitechF310Gamepad kDriverGamePad = new LogitechF310Gamepad(
			Params.DRIVER_JOYSTICK_USB);
	public static LogitechF310Gamepad kOpGamePad = new LogitechF310Gamepad(
			Params.OP_JOYSTICK_USB);

	/*
	 * 
	 * Motors
	 */
	public static Talon kLeftIntakeMotor = new Talon(Params.INTAKE_MOTOR_PINS[0]);
	public static Talon kRightIntakeMotor = new Talon(Params.INTAKE_MOTOR_PINS[1]);

	public static Talon kFlyWheelMotor = new Talon(Params.FLYWHEEL_MOTOR_PIN);
	
	public static Talon kCRunMotor = new Talon(Params.CRUN_MOTOR_PIN);
	
	//public static Talon kFeederMotor = new Talon(Params.FEEDER_MOTOR_PIN);

	/*
	 * 
	 * Sensors (Encoders, gyro, breakbeam, switches)
	 */
	public static Encoder kDriveLeftEncoder = new Encoder(
			Params.LEFT_DRIVE_ENCODER[0], Params.LEFT_DRIVE_ENCODER[1]);
	public static Encoder kDriveRightEncoder = new Encoder(
			Params.RIGHT_DRIVE_ENCODER[0], Params.RIGHT_DRIVE_ENCODER[1]);
	public static Encoder kFlyWheelEncoder = new Encoder(
			Params.RIGHT_DRIVE_ENCODER[0], Params.RIGHT_DRIVE_ENCODER[1]);
	
	// as a team decide if we want to use a beam breaker https://www.adafruit.com/products/2168 to index the ball
	public static DigitalInput kCRunBreakbeam = new DigitalInput(Params.CRUN_BREAKBEAM_PIN);
	
	public static AnalogGyro kGyro = new AnalogGyro(Params.GYRO_PIN);

	/*
	 * 
	 * Subsystems
	 */
	public static Drive kDrive = new Drive(Params.DRIVE_MOTOR_PINS[0],
			Params.DRIVE_MOTOR_PINS[1], Params.DRIVE_MOTOR_PINS[2], Params.DRIVE_MOTOR_PINS[3], 
			kDriveLeftEncoder, kDriveRightEncoder, kGyro, kDriverGamePad);
	
	public static Shooter kShooter = new Shooter(kFlyWheelMotor, kFlyWheelEncoder, kOpGamePad);
	public static Intake kIntake = new Intake(kLeftIntakeMotor,
			kRightIntakeMotor, Params.INTAKE_MOTOR_POWER, kOpGamePad);
	
	public static Climber kClimber = new Climber();
	
	public static ChickenRun kChickenRun = new ChickenRun(kCRunMotor, kCRunBreakbeam, Params.CRUN_MOTOR_POWER, kOpGamePad);

	/*
	 * 
	 * Vision
	 */
	public static CVVision kVision = new CVVision();

	/*
	 * 
	 * Utilities
	 */
	//public static CameraSwitcher kCameraSwitcher = new CameraSwitcher();
}
