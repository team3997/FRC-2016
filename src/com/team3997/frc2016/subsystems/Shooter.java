package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;

import edu.wpi.first.wpilibj.Talon;


public class Shooter{
	
	private LogitechDualGamepad gamePad;
	
	private Talon flyWheel;
	
	public Shooter(){
		gamePad = Robot.driverGamepad;
		flyWheel = new Talon(Params.FLYWHEEL_PIN);
		
		// set fly wheel to stop for safety
		flyWheel.set(0.0);
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//if right bumper is pressed, then fly wheel runs
    	if(gamePad.getRightBumper())
    		runShooter();
    	else
    		stopShooter();
    }
    
    // Run the fly wheel at default motor speed
    public void runShooter(){
    	flyWheel.set(Params.FLYWHEEL_MOTOR_SPEED);
    }
    
    // Run fly wheel at custom direction and speed
    public void runShooter(double speed, int direction){
    	flyWheel.set(direction * speed);
    }
    
    // Stop fly wheel motor
    public void stopShooter(){
    	flyWheel.set(0.0);
    }

    
}
