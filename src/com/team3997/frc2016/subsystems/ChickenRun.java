package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;


public class ChickenRun {
	
	private LogitechF310Gamepad gamePad;
	double cRunMotorPowerFront;
	double cRunMotorPowerBack;
	Talon cRunFrontMotor;
	Talon cRunBackMotor;
	DigitalInput indexSignal;
	
	public ChickenRun(Talon kMotor1, Talon kMotor2, DigitalInput kIndexSensor, double kMotorPower, LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		
		indexSignal = kIndexSensor;
		
		cRunFrontMotor = kMotor1;
		cRunBackMotor = kMotor2;
		cRunMotorPowerFront = kMotorPower;
	}
	
	public ChickenRun(Talon kMotor1, Talon kMotor2, DigitalInput kIndexSensor, double kMotorPowerF, double kMotorPowerB, LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		
		indexSignal = kIndexSensor;
		
		cRunFrontMotor = kMotor1;
		cRunBackMotor = kMotor2;
		cRunMotorPowerFront = kMotorPowerF;
		cRunMotorPowerBack = kMotorPowerB;
	}
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    }
}
