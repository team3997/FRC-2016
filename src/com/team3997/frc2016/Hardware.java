package com.team3997.frc2016;

import com.team3997.frc2016.components.Lights;
import com.team3997.frc2016.components.Vision;
import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Hardware {

	/*
	 * 
	 * Interfaces
	 */
	public static LogitechF310Gamepad kDriverGamePad = new LogitechF310Gamepad(Pins.DRIVER_GAMEPAD_USB);
	public static LogitechF310Gamepad kOpGamePad = new LogitechF310Gamepad(Pins.OP_GAMEPAD_USB);
	/*
	 * 
	 * Target LED
	 * 
	 */
	public static DigitalOutput kTargetLED = new DigitalOutput(Pins.GREEN_CAMERA_LED_PIN);
	
	
	/*
	 * 
	 * Arduino
	 * 
	 */
	public static I2C kArduino = new I2C(I2C.Port.kOnboard, 168);
	public static Lights kLights = new Lights(kArduino);
	
	
	/*
	 * 
	 * Motors
	 */
	public static Talon kIntakeMotor = new Talon(Pins.INTAKE_MOTOR_PIN);

	public static Talon kShooterMotor1 = new Talon(Pins.SHOOTER_MOTOR_PINS[0]);
	public static Talon kShooterMotor2 = new Talon(Pins.SHOOTER_MOTOR_PINS[1]);
	
	public static Talon kCRunMotor = new Talon(Pins.CRUN_MOTOR_PIN);
	/*
	 * 
	 * Pneumatics
	 * 
	 */
	
	private static DoubleSolenoid kIntakeExtenderSolenoid = new DoubleSolenoid(
			Pins.INTAKE_EXTENDER_SOLE_PINS[0], 
			Pins.INTAKE_EXTENDER_SOLE_PINS[1]);
	 
	
	/*
	 * 
	 * Sensors (Encoders, gyro, index sensor, switches etc..)
	 */
	public static AMT103V_Encoder kDriveLeftEncoder = new AMT103V_Encoder(
			Pins.LEFT_DRIVE_ENCODER_PINS[0], Pins.LEFT_DRIVE_ENCODER_PINS[1]);
	
	public static AMT103V_Encoder kDriveRightEncoder = new AMT103V_Encoder(
			Pins.RIGHT_DRIVE_ENCODER_PINS[0], Pins.RIGHT_DRIVE_ENCODER_PINS[1]);
	
	public static AMT103V_Encoder kFlyWheelEncoder = new AMT103V_Encoder(
			Pins.SHOOTER_ENCODER_PINS[0], Pins.SHOOTER_ENCODER_PINS[1]);
	
	public static DigitalInput kCRunIndexSensor = new DigitalInput(Pins.CRUN_INDEXER_PIN);
	
	public static AnalogGyro kGyro = new AnalogGyro(Pins.GYRO_PIN);
	
	public static Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);

	/*
	 * 
	 * Subsystems
	 */
	public static Drive kDrive = new Drive(Pins.DRIVE_MOTOR_PINS[0],
			Pins.DRIVE_MOTOR_PINS[1], Pins.DRIVE_MOTOR_PINS[2], Pins.DRIVE_MOTOR_PINS[3], 
			kDriveLeftEncoder, kDriveRightEncoder, kGyro, kDriverGamePad);
	
	public static ChickenRun kChickenRun = new ChickenRun(kCRunMotor, 
			kCRunIndexSensor, kOpGamePad);
	
	public static Shooter kShooter = new Shooter(kShooterMotor1, kShooterMotor2, kFlyWheelEncoder, kOpGamePad, kChickenRun);
	public static Intake kIntake = new Intake(kIntakeMotor, kIntakeExtenderSolenoid, kOpGamePad, kChickenRun);
	
	public static Hanger kHanger = new Hanger();
	
	/*
	 * 
	 * Vision
	 */
	public static Vision kVision = new Vision();

	/*
	 * 
	 * Utilities
	 */
	//public static CameraSwitcher kCameraSwitcher = new CameraSwitcher();
}
