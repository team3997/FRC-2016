package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;

import edu.wpi.first.wpilibj.Talon;


public class Shooter{
	
	private LogitechDualGamepad gamePad;
	
	private Talon flyWheelMotor;
	
	public Shooter(Talon flyWheelMotor){
		gamePad = Hardware.kDriverGamepad;
		this.flyWheelMotor = flyWheelMotor;
		
		// set fly wheel to stop for safety
		stopShooter();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	//if right bumper is pressed, then fly wheel runs
    	if(gamePad.getRightBumper()) //manual control
    		runShooter();
    	else
    		stopShooter();
    }
    
    // Run the fly wheel at default motor speed
    public void runShooter(){
    	flyWheelMotor.set(Params.FLYWHEEL_MOTOR_POWER);
    }
    
    // Run fly wheel at custom direction and speed
    public void runShooter(double speed, int direction){
    	flyWheelMotor.set(direction * speed);
    }
    
    // Stop fly wheel motor
    public void stopShooter(){
    	flyWheelMotor.set(0.0);
    }

    
}
