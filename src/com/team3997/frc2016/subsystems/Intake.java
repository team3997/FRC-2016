package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;


public class Intake{
	
	private LogitechF310Gamepad gamePad;
	double intakeMotorPower;
	Talon intakeMotor;
	private Extender extender;
	private ChickenRun cRun;
	
	public Intake(Talon kIntakeMotor,
			double intakeMotorPower, DoubleSolenoid kIntakeExtenderSolenoid, LogitechF310Gamepad kGamePad, ChickenRun kCRun){
		
		gamePad = kGamePad;
		
		extender = new Extender(kIntakeExtenderSolenoid);
		intakeMotor = kIntakeMotor;
		this.intakeMotorPower = intakeMotorPower;
		cRun = kCRun;
		
		// set motors to stop for safety
		extender.off();
		stopIntakeAndCRun();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//if only intake button is pressed, then run intake
    	if(gamePad.getButton(Controls.INTAKE_BUTTON) && !gamePad.getButton(Controls.OUTTAKE_BUTTON)){
    		startIntake();
    		if(!cRun.isIndexed())
    			cRun.startCRun();
    		else {
    			cRun.stopCRun();
    		}
    	}
    	//if only outtake button is pressed, then outtake using intake wheels and crun
    	else if(gamePad.getButton(Controls.OUTTAKE_BUTTON) && !gamePad.getButton(Controls.INTAKE_BUTTON)){
    		startOuttake();
    		cRun.startCRunReverse();
    	}
		//stops the intake wheels, and BOTH SETS OF ChickenRun WHEELS
    	else{
    		stopIntakeAndCRun();
    	}
    	
    	//Extender stuff:
    	if(gamePad.getButton(Controls.INTAKE_EXTEND_BUTTON)){
    		extender.out();
    	}
    	else {
    		extender.in();
    	}
    	
		if(Params.DASHBOARD_INTAKE_DEBUG){
			Dashboard.put("INTAKE Left Motor: ", intakeMotor.get());
		}	
    }

    
    // Run the intake at default motor speed
    public void startIntake(){
    	intakeMotor.set(intakeMotorPower);
    }
    
    // Run intake at custom direction and speed
    public void startIntake(double speed, int direction){
    	intakeMotor.set(direction * speed);
    }
    
    public void startOuttake(){
    	intakeMotor.set(-intakeMotorPower);
    }
    
    // Stop intake motors AND ChickenRun motors;
    public void stopIntakeAndCRun(){
    	intakeMotor.set(0.0);
		cRun.stopCRun();
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
