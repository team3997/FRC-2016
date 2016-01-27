package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;


public class Shooter {
	
	private LogitechF310Gamepad gamePad;
	private PID shooterPID;
	private Talon flyWheelMotor;
	private Encoder flyWheelEncoder;
	public double goalRPM = 0;
	
	public Shooter(Talon kFlyWheelMotor, Encoder kFlyWheelEncoder, LogitechF310Gamepad kGamePad){
		
		gamePad = kGamePad;
		flyWheelMotor = kFlyWheelMotor;
		flyWheelEncoder = kFlyWheelEncoder;
		
		shooterPID = new PID(flyWheelEncoder, flyWheelMotor,
				PIDParams.sP.getDouble(), PIDParams.sI.getDouble(), PIDParams.sI.getDouble(), 
				PIDParams.sTolerance, PIDParams.sOutMin, PIDParams.sOutMax,  PIDParams.sEncoderRotationScale, 
				 PIDParams.sSamplesToAverage, PIDParams.sType);
		
		
		shooterPID.setSetpoint(100);
		stop();
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	//Automatic mode code:
    	if(!Robot.manualMode){
    		//if right bumper is pressed, then fly wheel PID runs
    		if(gamePad.getTriggerRight()) //manual control
    			shooterPID.enablePID();
    		else
    			shooterPID.disablePID();
    	}
    	//manual mode code:
    	else {
    		
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
    public void runAtDefaultMotorSpeed(){
    	flyWheelMotor.set(Params.FLYWHEEL_MOTOR_POWER);
    }
    
    // Stop fly wheel motor
    public void stop(){
    	flyWheelMotor.set(0.0);
    }

    
}
