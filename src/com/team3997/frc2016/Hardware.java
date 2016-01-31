package com.team3997.frc2016;

import com.team3997.frc2016.components.CVVision;
import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.util.cameraswitcher.CameraSwitcher;
//import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Hardware {

	/*
	 * 
	 * Interfaces
	 */
	public static LogitechF310Gamepad kDriverGamePad = new LogitechF310Gamepad(
			Pins.DRIVER_GAMEPAD_USB);
	public static LogitechF310Gamepad kOpGamePad = new LogitechF310Gamepad(
			Pins.OP_GAMEPAD_USB);

	/*
	 * 
	 * Motors
	 */
	public static Talon kLeftIntakeMotor = new Talon(Pins.INTAKE_MOTOR_PINS[0]);
	public static Talon kRightIntakeMotor = new Talon(Pins.INTAKE_MOTOR_PINS[1]);

	public static Talon kFlyWheelMotor = new Talon(Pins.FLYWHEEL_MOTOR_PIN);
	
	public static Talon kCRunIntakeMotor = new Talon(Pins.CRUN_MOTOR_PINS[0]);
	public static Talon kCRunTransferMotor = new Talon(Pins.CRUN_MOTOR_PINS[1]);
	
	/*
	 * 
	 * Pneumatics
	 * 
	 */
	
	private static DoubleSolenoid kIntakeExtenderSolenoid = new DoubleSolenoid(Pins.INTAKE_EXTENDER_SOLE_PINS[0], Pins.INTAKE_EXTENDER_SOLE_PINS[1]);
	 
	
	/*
	 * 
	 * Sensors (Encoders, gyro, breakbeam, switches)
	 */
	public static Encoder kDriveLeftEncoder = new Encoder(
			Pins.LEFT_DRIVE_ENCODER_PINS[0], Pins.LEFT_DRIVE_ENCODER_PINS[1]);
	public static Encoder kDriveRightEncoder = new Encoder(
			Pins.RIGHT_DRIVE_ENCODER_PINS[0], Pins.RIGHT_DRIVE_ENCODER_PINS[1]);
	public static Encoder kFlyWheelEncoder = new Encoder(
			Pins.SHOOTER_ENCODER_PINS[0], Pins.SHOOTER_ENCODER_PINS[1]);
	
	// as a team decide if we want to use a beam breaker https://www.adafruit.com/products/2168 to index the ball
	public static DigitalInput kCRunBreakbeam = new DigitalInput(Pins.CRUN_BREAKBEAM_PIN);
	
	public static AnalogGyro kGyro = new AnalogGyro(Pins.GYRO_PIN);

	/*
	 * 
	 * Subsystems
	 */
	public static Drive kDrive = new Drive(Pins.DRIVE_MOTOR_PINS[0],
			Pins.DRIVE_MOTOR_PINS[1], Pins.DRIVE_MOTOR_PINS[2], Pins.DRIVE_MOTOR_PINS[3], 
			kDriveLeftEncoder, kDriveRightEncoder, kGyro, kDriverGamePad);
	
	public static ChickenRun kChickenRun = new ChickenRun(kCRunIntakeMotor, kCRunTransferMotor, kCRunBreakbeam, Params.CRUN_MOTOR_POWER, kOpGamePad);
	
	public static Shooter kShooter = new Shooter(kFlyWheelMotor, kFlyWheelEncoder, kOpGamePad, kChickenRun);
	public static Intake kIntake = new Intake(kLeftIntakeMotor,
			kRightIntakeMotor, Params.INTAKE_MOTOR_POWER, kIntakeExtenderSolenoid, kOpGamePad, kChickenRun);
	
	public static Climber kClimber = new Climber();
	
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
