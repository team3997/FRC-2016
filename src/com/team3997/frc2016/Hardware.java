package com.team3997.frc2016;

import com.team3997.frc2016.components.Lights;
import com.team3997.frc2016.components.Vision;
import com.team3997.frc2016.subsystems.*;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.F310;
import com.team3997.frc2016.util.FrontCamera;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class Hardware {

	/*
	 * 
	 * Interfaces
	 */
	public static F310 kDriverGamePad = new F310(Pins.DRIVER_GAMEPAD_USB);
	public static F310 kOpGamePad = new F310(Pins.OP_GAMEPAD_USB);
	
	/*
	 * 
	 * Arduino
	 * 
	 */
	public static I2C kArduino = new I2C(I2C.Port.kOnboard, 168);
	public static Lights kLights = new Lights(kArduino);
	
	
	/*
	 * 
	 * Motor Controllers
	 */
	public static Talon kIntakeMotor = new Talon(Pins.INTAKE_MOTOR_PIN);
	public static Talon kCRunMotor = new Talon(Pins.CRUN_MOTOR_PIN);
	public static Spark kShooterMotor1 = new Spark(Pins.SHOOTER_MOTOR_PINS[0]);
	public static Spark kShooterMotor2 = new Spark(Pins.SHOOTER_MOTOR_PINS[1]);

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
	
	public static Encoder kFlyWheelEncoder = new Encoder(
			Pins.SHOOTER_ENCODER_PINS[0], Pins.SHOOTER_ENCODER_PINS[1], false, EncodingType.k4X);
	
	public static DigitalInput kCRunIndexSensor = new DigitalInput(Pins.CRUN_INDEXER_PIN);
	
	public static AnalogGyro kGyro = new AnalogGyro(Pins.GYRO_PIN);
	
	public static Accelerometer kAccel = new BuiltInAccelerometer();

	/*
	 * 
	 * Subsystems
	 */
	public static Drive kDrive = new Drive(Pins.DRIVE_MOTOR_PINS[0],
			Pins.DRIVE_MOTOR_PINS[1], Pins.DRIVE_MOTOR_PINS[2], Pins.DRIVE_MOTOR_PINS[3], 
			kDriveLeftEncoder, kDriveRightEncoder, kGyro, kDriverGamePad);
	
	public static ChickenRun kChickenRun = new ChickenRun(kCRunMotor, kCRunIndexSensor);
	
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
	public static FrontCamera kFrontCamera = new FrontCamera(kOpGamePad);
}
