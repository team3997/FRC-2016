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
		shooterToggleButton = new Debounce(gamePad, Controls.SHOOTER_RUN_FLYWHEEL_TOGGLE_BUTTON);
		
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
        	if(gamePad.getButton(Controls.RUN_CRUN_TRANSFER_MOTOR) && toggleEnableMotor && onTargetRPM())
        		cRun.startCRun();
        	else
        		cRun.stopCRun(); 
    	}
 

    	if(toggleEnableMotor) //manual control
    		flyWheelMotor.set(Params.FLYWHEEL_MOTOR_POWER);
   		else
   			stopShooter();
        }  	
    }

    /**
     * onTargetRPM
     * @return true if the shooter's RPM is within the tolerance of it's goal RPM.
     */
    public boolean onTargetRPM(){
    	if(isPIDEnabled())
    		return shooterPID.onTarget();
    	else
    		return false;
    }
    
    /**
     * isPIDEnabled
     * @return true if a PID loop to achieve a certain RPM is running
     */
    public boolean isPIDEnabled(){
    	return shooterPID.isPIDEnabled();
    }
    
    /**
     * setRPMSetpoint
     * sets the goal RPM of the PID loop
     */
    public void setRPMSetpoint(int newSetpoint){
    	shooterPID.setSetpoint(newSetpoint);
    }
    
    /**
     * getRPMSetpoint
     * @return the goal RPM of the current PID loop
     */
    public double getRPMSetpoint(){
    	return shooterPID.getSetpoint();
    }
    
    /**
     * runShooterAtDefaultSpeed
     * 
     * sets the shooter to run at a set motor speed
     */
    public void runShooterAtDefaultSpeed(){
    	flyWheelMotor.set(Params.FLYWHEEL_MOTOR_POWER);
    }
    
    /**
     * stopShooter
     * 
     * sets the shooter motor to 0.0
     */
    public void stopShooter(){
    	flyWheelMotor.set(0.0);
    }

    
}