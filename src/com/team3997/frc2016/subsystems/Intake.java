package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.LogitechDualShockGamepad;
import com.team3997.frc2016.components.Dashboard;
import edu.wpi.first.wpilibj.Talon;


public class Intake{
	
	private LogitechDualShockGamepad gamePad;
	private int leftIntakeMotorPin = Params.INTAKE_PINS[0];
	private int rightIntakeMotorPin = Params.INTAKE_PINS[1];
	private double intakeMotorSpeed = Params.INTAKE_MOTOR_SPEED;
	
	Talon leftIntakeMotor;
	Talon rightIntakeMotor;
	
	public Intake(){
		gamePad = new LogitechDualShockGamepad(Params.JOYSTICK_USB);
		leftIntakeMotor = new Talon(leftIntakeMotorPin);
		rightIntakeMotor = new Talon(rightIntakeMotorPin);
		
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
    	}
    	
    	//if only outtake button is pressed, then outtake
    	else if(gamePad.getButton(Params.OUTTAKE_BUTTON)){
    		if(!gamePad.getButton(Params.INTAKE_BUTTON)){
    			runIntake(intakeMotorSpeed, -1);
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
    	leftIntakeMotor.set(intakeMotorSpeed);
		rightIntakeMotor.set(intakeMotorSpeed);
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
