package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.PID.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import com.team3997.frc2016.Controls;


public class Shooter {
	
	private LogitechF310Gamepad gamePad;
	private PID shooterPID;
	private Talon flyWheelMotor;
	private Encoder flyWheelEncoder;
	public double goalRPM = 0;
	private boolean toggleEnableMotor = false;
	private Debounce shooterToggleButton;
	private ChickenRun cRun;
	
	public Shooter(Talon kFlyWheelMotor, Encoder kFlyWheelEncoder, LogitechF310Gamepad kGamePad, ChickenRun kCRun){
		
		gamePad = kGamePad;
		shooterToggleButton = new Debounce(gamePad, Controls.SHOOTER_ENABLE_TOGGLE_BUTTON);
		
		flyWheelMotor = kFlyWheelMotor;
		flyWheelEncoder = kFlyWheelEncoder;
		
		shooterPID = new PID(flyWheelEncoder, flyWheelMotor,
				PIDParams.sP.getDouble(), PIDParams.sI.getDouble(), PIDParams.sI.getDouble(), 
				PIDParams.sTolerance, PIDParams.sOutMin, PIDParams.sOutMax,  PIDParams.sEncoderRotationScale, 
				 PIDParams.sSamplesToAverage, PIDParams.sType);
		
		cRun = kCRun;
		
		shooterPID.setSetpoint(100);
		stopShooter();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    		
    	//if shooter button is pressed, toggle motor enable
    	if(shooterToggleButton.getRise()){
    		toggleEnableMotor = !toggleEnableMotor;
    		System.out.println("switch");
    	}
    	//Shooting:
        if(!Robot.manualMode){ //Automatic mode code:
        	if(toggleEnableMotor){
        		shooterPID.enablePID();
        		System.out.println("enable pid");
        	} 
        	else {
        		shooterPID.disablePID();
        	//if the flywheels are up to speed and motors are enabled, transfer the ball from the Chicken Run to the shooter
        	if(toggleEnableMotor && onTarget())
        		cRun.runCRunTransfer();
        	else
        		cRun.stopTransfer(); 
    	}

    		if(toggleEnableMotor) //manual control
    			flyWheelMotor.set(Params.FLYWHEEL_MOTOR_POWER);
    		else
    			stopShooter();
    	}  	
    	
    }

    public boolean onTarget(){
    	return shooterPID.onTarget();
    }
    
    public boolean isPIDEnabled(){
    	return shooterPID.isPIDEnabled();
    }
    
    public void setSetpoint(int newSetpoint){
    	shooterPID.setSetpoint(newSetpoint);
    }
    
    public double getSetpoint(){
    	return shooterPID.getSetpoint();
    }
    
    // Run the fly wheel at default motor speed
    public void runShooterAtDefaultMotorSpeed(){
    	flyWheelMotor.set(Params.FLYWHEEL_MOTOR_POWER);
    }
    
    // Stop fly wheel motor
    public void stopShooter(){
    	flyWheelMotor.set(0.0);
    }

    
}