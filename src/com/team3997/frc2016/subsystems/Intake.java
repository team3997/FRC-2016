package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;


public class Intake{
	
	private LogitechF310Gamepad gamePad;
	double intakeMotorPower;
	Talon leftIntakeMotor;
	Talon rightIntakeMotor;
	private Extender extender;
	private ChickenRun cRun;
	
	public Intake(Talon leftMotor, Talon rightMotor,
			double intakeMotorPower, DoubleSolenoid kIntakeExtenderSolenoid, LogitechF310Gamepad kGamePad, ChickenRun kCRun){
		
		gamePad = kGamePad;
		
		extender = new Extender(kIntakeExtenderSolenoid);
		
		leftIntakeMotor = leftMotor;
		rightIntakeMotor = rightMotor;
		
		this.intakeMotorPower = intakeMotorPower;
		
		cRun = kCRun;
		
		// set motors to stop for safety
		stopIntake();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//if only intake button is pressed, then run intake
    	if(gamePad.getButton(Controls.INTAKE_BUTTON) && !gamePad.getButton(Controls.OUTTAKE_BUTTON)){
    		runIntake();
    		//if the ball has not triggered the intake, run ChickenRun intake wheels also
    		if(!cRun.indexSignal.get())
    			cRun.runCRunIntake();
    	}
    	//if only outtake button is pressed, then outtake using intake wheels, and BOTH SETS OF ChickenRun wheels
    	else if(gamePad.getButton(Controls.OUTTAKE_BUTTON) && !gamePad.getButton(Controls.INTAKE_BUTTON)){
    		runIntake(intakeMotorPower, -1);
    		cRun.runCRunIntake(-1);
    		cRun.runCRunTransfer(-1);
    	}
    	else{
    		//stops the intake wheels, and the intake cRun wheels
    		stopIntake();
    	}
    	
		if(Params.DASHBOARD_INTAKE_DEBUG){
			Dashboard.put("INTAKE Left Motor: ", leftIntakeMotor.get());
			Dashboard.put("INTAKE Right Motor: ", rightIntakeMotor.get());
		}	
    }
    	
    	
    
    // Run the intake at default motor speed
    public void runIntake(){
    	leftIntakeMotor.set(intakeMotorPower);
		rightIntakeMotor.set(intakeMotorPower);
    }
    
    // Run intake at custom direction and speed
    public void runIntake(double speed, int direction){
    	leftIntakeMotor.set(direction * speed);
		rightIntakeMotor.set(direction * speed);
    }
    
    // Stop intake motors AND ChickenRun motors;
    public void stopIntake(){
    	leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
		cRun.stopIntake();
//		cRun.stopTransfer();
    }

    
}

//Class for the intake extender
class Extender {
	DoubleSolenoid extenderSolenoid;
	
	Extender(DoubleSolenoid kExtenderSolenoid){
		extenderSolenoid = kExtenderSolenoid;
	}
	
	
	public void out(){
		extenderSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void in(){
		extenderSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void set(Value value){
		extenderSolenoid.set(value);
	}
	
	public Value get(){
		return extenderSolenoid.get();
	}
	
	public void off(){
		extenderSolenoid.set(DoubleSolenoid.Value.kOff);
	}
}
