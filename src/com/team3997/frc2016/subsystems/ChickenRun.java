package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.F310;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class ChickenRun {
	Talon cRunMotor;
	DigitalInput indexSignal;
	public double crunMotorPower = Params.CRUN_MOTOR_POWER;
	public boolean sendingToShooter = false;

	public ChickenRun(Talon kMotor1, DigitalInput kIndexSensor) {
		indexSignal = kIndexSensor;
		cRunMotor = kMotor1;
		cRunMotor.setInverted(true);
	}
	
	/**
	 * Set the state of whether the cRun should send the ball to the shooter. The intake class handles the actual act of sending it
	 * @param state 
	 */
	protected void setSendingToShooter(boolean state){
		sendingToShooter = state;
	}
	
	/**
	 * @return Boolean that is true if the cRun is in the process of sending the ball to the shooter
	 */
	protected boolean isSendingToShooter(){
		return sendingToShooter;
	}
	
	/**
	 * @return whether the ball is indexed in the bot
	 */
	public boolean isIndexed(){
		return !indexSignal.get();
	}

	public void stop() {
		cRunMotor.set(0.0);
	}
	
	public void intake() {
		cRunMotor.set(crunMotorPower);
	}
	
	public void outtake() {
		cRunMotor.set(-crunMotorPower);
	}
	
	public void run(double speed) {
		cRunMotor.set(speed);
	}
}
