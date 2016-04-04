package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.F310;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;


public class Intake {
	
	private F310 gamePad;
	double intakeMotorPower = Params.INTAKE_MOTOR_POWER;
	Talon intakeMotor;
	private Extender extender;
	private ChickenRun cRun;
	
	public Intake(Talon kIntakeMotor, DoubleSolenoid kIntakeExtenderSolenoid, 
			F310 kGamePad, ChickenRun kCRun){
		
		gamePad = kGamePad;
		
		extender = new Extender(kIntakeExtenderSolenoid);
		intakeMotor = kIntakeMotor;
		cRun = kCRun;
		
		//set motors to stop for safety
		extender.off();
		stopIntakeAndCRun();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//cRun and Intake Behaviour
    	if(cRun.isSendingToShooter()){
    		this.stop();
    		cRun.run(Params.CRUN_SHOOTING_MOTOR_POWER);
    	}
    	else if(gamePad.getButton(Controls.FORCE_OUTTAKE_BUTTON)){
    		this.outtake();
    		cRun.outtake();
    	}
    	else if(gamePad.getButton(Controls.SOFT_OUTTAKE_BUTTON)){
    		if(!cRun.isIndexed()){
    			this.outtake();
    			cRun.outtake();
    		}
    		else {
    			stopIntakeAndCRun();
    		}
    	}
    	else if(gamePad.getButton(Controls.INTAKE_BUTTON)){
    			this.intake();
    			cRun.intake();
    	}
    	
    	else {
    		stopIntakeAndCRun();
    	}
    	
    	//Extender Controls
    	if(gamePad.getPOVDown()){
    		extender.out();
    	}
    	else {
    		extender.in();
    	}
    }

    
    // Run the intake at default motor speed
    public void intake(){
    	intakeMotor.set(intakeMotorPower);
    }
    
    // Run intake at custom direction and speed
    public void intake(double speed){
    	intakeMotor.set(speed);
    }
    
    public void outtake(){
    	intakeMotor.set(-intakeMotorPower);
    }
    
    public void stop(){
    	intakeMotor.set(0.0);
    }
    
    // Stop intake motors AND ChickenRun motors;
    public void stopIntakeAndCRun(){
    	this.stop();
		cRun.stop();
    }

    
}

//Class for the intake extender
class Extender {
	DoubleSolenoid extenderSolenoid;
	
	Extender(DoubleSolenoid kExtenderSolenoid){
		extenderSolenoid = kExtenderSolenoid;
	}
	
	
	public void out(){
		extenderSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void in(){
		extenderSolenoid.set(DoubleSolenoid.Value.kForward);
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
