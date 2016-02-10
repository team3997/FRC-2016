package com.team3997.frc2016;

import com.team3997.frc2016.components.Vision;
import com.team3997.frc2016.subsystems.*;
//import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.vision.AxisCamera;

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
	 * Target LED
	 * 
	 */
	public static DigitalOutput kTargetLED = new DigitalOutput(9);
	
	
	/*
	 * 
	 * Arduino
	 * 
	 */
	public static I2C kLights = new I2C(I2C.Port.kOnboard, 168);
	
	
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
	 * Sensors (Encoders, gyro, breakbeam, switches etc..)
	 */
	public static Encoder kDriveLeftEncoder = new Encoder(
			Pins.LEFT_DRIVE_ENCODER_PINS[0], Pins.LEFT_DRIVE_ENCODER_PINS[1]);
	public static Encoder kDriveRightEncoder = new Encoder(
			Pins.RIGHT_DRIVE_ENCODER_PINS[0], Pins.RIGHT_DRIVE_ENCODER_PINS[1]);
	public static Encoder kFlyWheelEncoder = new Encoder(
			Pins.SHOOTER_ENCODER_PINS[0], Pins.SHOOTER_ENCODER_PINS[1]);
	
	public static DigitalInput kCRunBreakbeam = new DigitalInput(Pins.CRUN_BREAKBEAM_PIN);
	
	public static AnalogGyro kGyro = new AnalogGyro(Pins.GYRO_PIN);

	/*
	 * 
	 * Subsystems
	 */
	public static Drive kDrive = new Drive(Pins.DRIVE_MOTOR_PINS[0],
			Pins.DRIVE_MOTOR_PINS[1], Pins.DRIVE_MOTOR_PINS[2], Pins.DRIVE_MOTOR_PINS[3], 
			kDriveLeftEncoder, kDriveRightEncoder, kGyro, kDriverGamePad);
	
	public static ChickenRun kChickenRun = new ChickenRun(kCRunIntakeMotor, kCRunTransferMotor, 
			kCRunBreakbeam, Params.CRUN_INTAKE_MOTOR_POWER, Params.CRUN_TRANSFER_MOTOR_POWER, kOpGamePad);
	
	public static Shooter kShooter = new Shooter(kFlyWheelMotor, kFlyWheelEncoder, kOpGamePad, kChickenRun);
	public static Intake kIntake = new Intake(kLeftIntakeMotor,
			kRightIntakeMotor, Params.INTAKE_MOTOR_POWER, kIntakeExtenderSolenoid, kOpGamePad, kChickenRun);
	
	public static Climber kClimber = new Climber();
	
	/*
	 * 
	 * Vision
	 */
	public static AxisCamera kAxisCamera = new AxisCamera(Params.CAMERA_AXIS_IP);
	public static Vision kVision = new Vision();

	/*
	 * 
	 * Utilities
	 */
	//public static CameraSwitcher kCameraSwitcher = new CameraSwitcher();
}
