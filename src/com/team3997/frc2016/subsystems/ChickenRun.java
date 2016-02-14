package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class ChickenRun {

	private LogitechF310Gamepad gamePad;
	double cRunMotorPower;
	Talon cRunMotor;
	DigitalInput indexSignal;

	public ChickenRun(Talon kMotor1, DigitalInput kIndexSensor,
			double kIntakeMotorPower, double kTransferMotorPower,
			LogitechF310Gamepad kGamePad) {

		gamePad = kGamePad;

		indexSignal = kIndexSensor;

		cRunMotor = kMotor1;
		cRunMotorPower = kIntakeMotorPower;
	}
	
	/*public void runTeleOP(){
		
	}*/
	
	protected boolean isIndexed(){
		return indexSignal.get();
	}

	protected void stopCRun() {
		cRunMotor.set(0.0);
	}
	
	protected void runCRunIntake(int direction) {
		cRunMotor.set(cRunMotorPower * direction);
	}
}
