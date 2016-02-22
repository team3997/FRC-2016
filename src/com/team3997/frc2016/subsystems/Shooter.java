package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frcpackage com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.components.Lights;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.PID.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import com.team3997.frc2016.Controls;


public class Shooter {
	
	private LogitechF310Gamepad gamePad;
	public PID shooterPID;
	private Talon shooterMotor1;
	private Talon shooterMotor2;
	private Encoder shooterEncoder;
	public double goalRPM = 0;
	private boolean toggleEnableShooterMotor = false;
	private Debounce shooterToggleButton;
	private ChickenRun cRun;
	
	Lights lights = new Lights();
	
	public Shooter(Talon kshooterMotor1, Talon kshooterMotor2, Encoder kshooterEncoder, LogitechF310Gamepad kGamePad, ChickenRun kCRun){
		
		gamePad = kGamePad;
		shooterToggleButton = new Debounce(gamePad, Controls.SHOOTER_RUN_MOTORS_TOGGLE_BUTTON);
		
		shooterMotor1 = kshooterMotor1;
		shooterMotor2 = kshooterMotor2;
		shooterEncoder = kshooterEncoder;
		
		shooterPID = new PID(shooterEncoder, shooterMotor1, shooterMotor2,
				PIDParams.sP.getDouble(), PIDParams.sI.getDouble(), PIDParams.sI.getDouble(), 
				PIDParams.sTolerance, PIDParams.sOutMin, PIDParams.sOutMax,  PIDParams.sEncoderRotationScale, 
				 PIDParams.sSamplesToAverage, PIDParams.sType);
		
		cRun = kCRun;
		
		shooterPID.setSetpoint(PIDParams.sGoalRPM.getInt());
		stopShooter();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    		
    	//if shooter button is pressed, toggle motor enable
    	if(shooterToggleButton.getRise()){
    		toggleEnableShooterMotor = !toggleEnableShooterMotor;
    	}
    	
    	if((toggleEnableShooterMotor && !cRun.isIndexed()))
    		lights.setColor(Params.HALFYELLOW);
    	if((!toggleEnableShooterMotor) && cRun.isIndexed())
    		lights.setColor(Params.FULLYELLOW);
    	else
    		lights.setColor(Params.ORANGE);
    	
        if(!Robot.manualMode){ 
        	runAuto();
        }
        else { 
        	runManual();
        }
    }
    
    public void runAuto(){
    	if(toggleEnableShooterMotor){
    		shooterPID.enablePID();
        	//if the shooter button is pressed and shooter motors are up to speed, transfer the ball from the Chicken Run to the shooter
        	if(gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER) && onTargetRPM()){ //!!Check if onTarget is correct
        		cRun.setSendingToShooter(true);
        		cRun.intake();
        	}
        	else {
        		cRun.setSendingToShooter(false);
        		cRun.stop();
        	}
    		
    	} 
    	else {
    		shooterPID.disablePID();
    	}
    }
    
    public void runManual(){
    	if(toggleEnableShooterMotor){
    		runShooterAtDefaultSpeed();
    		
        	if(gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER)){
        		cRun.setSendingToShooter(true);
        		cRun.intake();
        	}
        	else {
        		cRun.setSendingToShooter(false);
        		cRun.stop();
        	}
    	}
   		else
   			stopShooter();
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
    	shooterMotor1.set(Params.SHOOTER_MOTOR_POWER);
    	shooterMotor2.set(Params.SHOOTER_MOTOR_POWER);
    }
    
    /**
     * stopShooter
     * 
     * sets the shooter motor to 0.0
     */
    public void stopShooter(){
    	shooterMotor1.set(0.0);
    	shooterMotor2.set(0.0);
    }

    
}2016.util.PID.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import com.team3997.frc2016.Controls;


public class Shooter {
	
	private LogitechF310Gamepad gamePad;
	public PID shooterPID;
	private Talon shooterMotor1;
	private Talon shooterMotor2;
	private Encoder shooterEncoder;
	public double goalRPM = 0;
	private boolean toggleEnableShooterMotor = false;
	private Debounce shooterToggleButton;
	private ChickenRun cRun;
	
	public Shooter(Talon kshooterMotor1, Talon kshooterMotor2, Encoder kshooterEncoder, LogitechF310Gamepad kGamePad, ChickenRun kCRun){
		
		gamePad = kGamePad;
		shooterToggleButton = new Debounce(gamePad, Controls.SHOOTER_RUN_MOTORS_TOGGLE_BUTTON);
		
		shooterMotor1 = kshooterMotor1;
		shooterMotor2 = kshooterMotor2;
		shooterEncoder = kshooterEncoder;
		
		shooterPID = new PID(shooterEncoder, shooterMotor1, shooterMotor2,
				PIDParams.sP.getDouble(), PIDParams.sI.getDouble(), PIDParams.sI.getDouble(), 
				PIDParams.sTolerance, PIDParams.sOutMin, PIDParams.sOutMax,  PIDParams.sEncoderRotationScale, 
				 PIDParams.sSamplesToAverage, PIDParams.sType);
		
		cRun = kCRun;
		
		shooterPID.setSetpoint(PIDParams.sGoalRPM.getInt());
		stopShooter();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    		
    	//if shooter button is pressed, toggle motor enable
    	if(shooterToggleButton.getRise()){
    		toggleEnableShooterMotor = !toggleEnableShooterMotor;
    	}
    	
        if(!Robot.manualMode){ 
        	runAuto();
        }
        else { 
        	runManual();
        }
    }
    
    public void runAuto(){
    	if(toggleEnableShooterMotor){
    		shooterPID.enablePID();
    		
        	//if the shooter button is pressed and shooter motors are up to speed, transfer the ball from the Chicken Run to the shooter
        	if(gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER) && onTargetRPM()){ //!!Check if onTarget is correct
        		cRun.setSendingToShooter(true);
        		cRun.intake();
        	}
        	else {
        		cRun.setSendingToShooter(false);
        		cRun.stop();
        	}
    		
    	} 
    	else {
    		shooterPID.disablePID();
    	}
    }
    
    public void runManual(){
    	if(toggleEnableShooterMotor){
    		runShooterAtDefaultSpeed();
    		
        	if(gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER)){
        		cRun.setSendingToShooter(true);
        		cRun.intake();
        	}
        	else {
        		cRun.setSendingToShooter(false);
        		cRun.stop();
        	}
    	}
   		else
   			stopShooter();
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
    	shooterMotor1.set(Params.SHOOTER_MOTOR_POWER);
    	shooterMotor2.set(Params.SHOOTER_MOTOR_POWER);
    }
    
    /**
     * stopShooter
     * 
     * sets the shooter motor to 0.0
     */
    public void stopShooter(){
    	shooterMotor1.set(0.0);
    	shooterMotor2.set(0.0);
    }

    
}
