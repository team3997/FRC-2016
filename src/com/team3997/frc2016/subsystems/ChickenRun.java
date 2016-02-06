package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class ChickenRun {

	private LogitechF310Gamepad gamePad;
	double cRunIntakeMotorPower;
	double cRunTransferMotorPower;
	Talon cRunIntakeMotor;
	Talon cRunTransferMotor;
	DigitalInput indexSignal;

	public ChickenRun(Talon kMotor1, Talon kMotor2, DigitalInput kIndexSensor,
			double kIntakeMotorPower, double kTransferMotorPower,
			LogitechF310Gamepad kGamePad) {

		gamePad = kGamePad;

		indexSignal = kIndexSensor;

		cRunIntakeMotor = kMotor1;
		cRunTransferMotor = kMotor2;
		cRunIntakeMotorPower = kIntakeMotorPower;
		cRunTransferMotorPower = kTransferMotorPower;
	}
	
	/*public void runTeleOP(){
		
	}*/
	
	protected boolean isIndexed(){
		return indexSignal.get();
	}

	protected void stopCRunIntake() {
		cRunIntakeMotor.set(0.0);
	}

	protected void stopCRunTransfer() {
		cRunTransferMotor.set(0.0);
	}

	protected void runCRunIntake(double motorPower, int direction) {
		cRunIntakeMotor.set(motorPower * direction);
	}

	protected void runCRunIntake() {
		cRunIntakeMotor.set(cRunIntakeMotorPower);
	}

	protected void runCRunIntake(int direction) {
		cRunIntakeMotor.set(cRunIntakeMotorPower * direction);
	}

	protected void runCRunTransfer(double motorPower, int direction) {
		cRunTransferMotor.set(motorPower * direction);
	}

	protected void runCRunTransfer() {
		cRunTransferMotor.set(cRunTransferMotorPower);
	}

	protected void runCRunTransfer(int direction) {
		cRunTransferMotor.set(cRunTransferMotorPower * direction);
	}

	public void stopCRunMotors() {
		stopCRunIntake();
		stopCRunTransfer();
	}
}
