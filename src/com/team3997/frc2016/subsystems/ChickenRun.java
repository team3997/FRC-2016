package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Dashboard;
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
	
	public ChickenRun(Talon kMotor1, Talon kMotor2, DigitalInput kIndexSensor, double kMotorPower, LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		
		indexSignal = kIndexSensor;
		
		cRunIntakeMotor = kMotor1;
		cRunTransferMotor = kMotor2;
		cRunIntakeMotorPower = cRunTransferMotorPower = kMotorPower;
	}
	
	public ChickenRun(Talon kMotor1, Talon kMotor2, DigitalInput kIndexSensor, double kMotorPowerI, double kMotorPowerT, LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		
		indexSignal = kIndexSensor;
		
		cRunIntakeMotor = kMotor1;
		cRunTransferMotor = kMotor2;
		cRunIntakeMotorPower = kMotorPowerI;
		cRunTransferMotorPower = kMotorPowerT;
	}
/*
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	//if only intake button is pressed, then run intake motors in intake direction BLOCKED = HIGH 
    	if(gamePad.getButton(Controls.INTAKE_BUTTON) && !gamePad.getButton(Controls.OUTTAKE_BUTTON) && !indexSignal.get()){
    		runCRunIntake();
    	}
    	//if only outtake button is pressed, then outtake motors in outtake direction
    	else if(gamePad.getButton(Controls.OUTTAKE_BUTTON) && !gamePad.getButton(Controls.INTAKE_BUTTON)){
    		runCRunIntake(cRunIntakeMotorPower, -1);
    	}
    	else{
    		stopIntake();
    	}
    		
/*   	
		if(Params.DASHBOARD_INTAKE_DEBUG){
			Dashboard.put("INTAKE Left Motor: ", leftIntakeMotor.get());
			Dashboard.put("INTAKE Right Motor: ", rightIntakeMotor.get());
		}
	
    }
*/
	protected void stopIntake() {
		cRunIntakeMotor.set(0.0);		
	}
	
	protected void stopTransfer() {
		cRunTransferMotor.set(0.0);		
	}

	protected void runCRunIntake(double motorPower, int direction) {
		cRunIntakeMotor.set(motorPower * direction);		
	}

	protected void runCRunIntake() {
    	cRunIntakeMotor.set(cRunIntakeMotorPower);		
	}
	
	protected void runCRunIntake(int direction) {
    	cRunIntakeMotor.set(cRunIntakeMotorPower*direction);		
	}
	
	
	protected void runCRunTransfer(double motorPower, int direction) {
		cRunTransferMotor.set(motorPower * direction);		
	}

	protected void runCRunTransfer() {
    	cRunTransferMotor.set(cRunTransferMotorPower);		
	}
	
	protected void runCRunTransfer(int direction) {
    	cRunTransferMotor.set(cRunTransferMotorPower*direction);		
	}
}
