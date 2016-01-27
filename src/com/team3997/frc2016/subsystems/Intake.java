package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.Talon;


public class Intake{
	
	private LogitechF310Gamepad gamePad;
	double intakeMotorPower;
	Talon leftIntakeMotor;
	Talon rightIntakeMotor;
	
	public Intake(Talon leftMotor, Talon rightMotor,
			double intakeMotorPower, LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		
		leftIntakeMotor = leftMotor;
		rightIntakeMotor = rightMotor;
		
		this.intakeMotorPower = intakeMotorPower;
		
		// set motors to stop for safety
		stopIntake();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//if only intake button is pressed, then intake
    	if(gamePad.getButton(Params.INTAKE_BUTTON)){
    		if(!gamePad.getButton(Params.OUTTAKE_BUTTON)){
    			runIntake();
    		}
    		else{
    			stopIntake();
    		}
    		
    		if(Params.DASHBOARD_INTAKE_DEBUG){
    			Dashboard.put("INTAKE Left Motor: ", leftIntakeMotor.get());
    			Dashboard.put("INTAKE Right Motor: ", rightIntakeMotor.get());
    		}	
    	}
    	
    	//if only outtake button is pressed, then outtake
    	else if(gamePad.getButton(Params.OUTTAKE_BUTTON)){
    		if(!gamePad.getButton(Params.INTAKE_BUTTON)){
    			runIntake(intakeMotorPower, -1);
    		}
    		else{
    			stopIntake();
    		}
    	}
    	
    	// else stop
    	else{
    		stopIntake();
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
    
    // Stop intake motors
    public void stopIntake(){
    	leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
    }

    
}
