package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class ChickenRun {

	private LogitechF310Gamepad gamePad;
	Talon cRunMotor;
	DigitalInput indexSignal;
	public double crunMotorPower = Params.CRUN_MOTOR_POWER;
	public boolean sendingToShooter = false;

	public ChickenRun(Talon kMotor1, DigitalInput kIndexSensor, LogitechF310Gamepad kGamePad) {

		gamePad = kGamePad;
		indexSignal = kIndexSensor;
		cRunMotor = kMotor1;
	}
	
	protected void setSendingToShooter(boolean state){
		sendingToShooter = state;
	}
	
	protected boolean isSendingToShooter(){
		return sendingToShooter;
	}
	
	protected boolean isIndexed(){
		return !indexSignal.get();
	}

	protected void stop() {
		cRunMotor.set(0.0);
	}
	
	protected void intake() {
		cRunMotor.set(crunMotorPower);
	}
	
	protected void outtake() {
		cRunMotor.set(-crunMotorPower);
	}
	
	protected void run(double speed) {
		cRunMotor.set(speed);
	}
}
