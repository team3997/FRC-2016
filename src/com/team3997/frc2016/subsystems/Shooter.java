package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.components.Lights;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.Debounce;
import com.team3997.frc2016.util.LogitechF310Gamepad;
import com.team3997.frc2016.util.PID.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team3997.frc2016.Controls;

public class Shooter {

	private LogitechF310Gamepad gamePad;
	public PID shooterPID;
	private Talon shooterMotor1;
	private Talon shooterMotor2;
	private AMT103V_Encoder shooterEncoder;
	private Encoder wpiShooterEncoder;
	public double goalRPM = 0;
	private boolean enableShooterMotor = false;
	
	//private Debounce shooterToggleButton;
	private ChickenRun cRun;

	public Shooter(Talon kshooterMotor1, Talon kshooterMotor2, AMT103V_Encoder kshooterEncoder,
			LogitechF310Gamepad kGamePad, ChickenRun kCRun) {

		gamePad = kGamePad;
		//shooterToggleButton = new Debounce(gamePad, Controls.SHOOTER_RUN_MOTORS_BUTTON);

		shooterMotor1 = kshooterMotor1;
		shooterMotor2 = kshooterMotor2;
		shooterMotor1.setInverted(true);
		shooterMotor2.setInverted(true);

		shooterEncoder = kshooterEncoder;
		wpiShooterEncoder = shooterEncoder.getEncoderObject();

		shooterPID = new PID(shooterEncoder, shooterMotor1, shooterMotor2, PIDParams.sP.getDouble(),
				PIDParams.sI.getDouble(), PIDParams.sI.getDouble(), PIDParams.sTolerance, PIDParams.sOutMin,
				PIDParams.sOutMax, PIDParams.sSamplesToAverage, PIDParams.sType);

		cRun = kCRun;

		shooterPID.setSetpoint(PIDParams.sGoalRPM.getInt());
		stopShooter();
	}

	public void initTeleOp(){
		wpiShooterEncoder.reset();
		wpiShooterEncoder.setDistancePerPulse(1/2048);
		//wpiShooterEncoder.
	}
	
	// Function that runs during teleop periodically
	public void runTeleOp() {

		// if shooter button is pressed, toggle motor enable
		if (gamePad.getButton(Controls.SHOOTER_RUN_MOTORS_BUTTON))
			enableShooterMotor = true;
		else 
			enableShooterMotor = false;
			

		if (!Robot.isManualMode)
			runAuto();
		else
			runManual();
		
		
		SmartDashboard.putNumber("encoder raw scaled quadrature", wpiShooterEncoder.get());
    	SmartDashboard.putNumber("encoder rotations cummalative", wpiShooterEncoder.get()/2048);
    	SmartDashboard.putNumber("encoder wpi distance", wpiShooterEncoder.getDistance());
    	SmartDashboard.putNumber("encoder rotations rate", wpiShooterEncoder.getRate());
    	//System.out.println(getRate());

	}
	
	/*public int getRate(){
		int x=0;
		int y=0;
		int z=0;
		
		if(timer.get() > 1){
			timer.reset();
		}
		
		if(timer.get() <= 1){
			if(timer.get() < 0.1){
				x=wpiShooterEncoder.get()/2048;
			}
			if((timer.get() > 0.8) || timer.get() < 0.9){
				y=wpiShooterEncoder.get()/2048;
			}
		}
		
		z=y-x;
		return z*60;
	}*/

	public void runAuto() {
		if (enableShooterMotor){
			if(!Params.SHOOTER_USE_PID){
				this.runShooterAtDefaultSpeed();
				Dashboard.put("shooterPID", true);
			}
			else{
				shooterPID.enablePID();
				Dashboard.put("shooterPID", false);
			}
			
		}
		else {
			if(!Params.SHOOTER_USE_PID){
				stopShooter();
			}
			else{
				shooterPID.disablePID();
			}
		}

		// if the shooter button is pressed and shooter motors are up to speed,
		// transfer the ball from the Chicken Run to the shooter
		//if (gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER) && toggleEnableShooterMotor && this.onTargetRPM() && cRun.isIndexed()) { // !!Check if onTarget is correct
		if (gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER) && enableShooterMotor) {	
			cRun.setSendingToShooter(true);
		} else {
			cRun.setSendingToShooter(false);
		}
		
		if ((enableShooterMotor && !cRun.isIndexed()))
			Hardware.kLights.setColor(Lights.HALFYELLOW);
		else if ((!enableShooterMotor) && cRun.isIndexed())
			Hardware.kLights.setColor(Lights.FULLYELLOW);
		else
			Hardware.kLights.setColor(Lights.ORANGE);
	}

	public void runManual() {
		if (enableShooterMotor) {
			runShooterAtDefaultSpeed();

			if (gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER)) {
				cRun.setSendingToShooter(true);
				Dashboard.put("trigger boolean", true);
			} else {
				cRun.setSendingToShooter(false);
				Dashboard.put("trigger boolean", false);
			}
		} 
		else if (gamePad.getLeftTrigger()) {
		
		} 
		else {
			stopShooter();
		}
		
		Hardware.kLights.setColor(Lights.RAINBOW);
	}

	/**
	 * @return true if the shooter's RPM is within the tolerance of it's goal
	 *         RPM.
	 */
	public boolean onTargetRPM() {
		if (isPIDEnabled())
			return shooterPID.onTarget();
		else
			return false;
	}

	/**
	 * @return true if a PID loop to achieve a certain RPM is running
	 */
	public boolean isPIDEnabled() {
		return shooterPID.isPIDEnabled();
	}

	/**
	 * sets the goal RPM of the PID loop
	 */
	public void setRPMSetpoint(int newSetpoint) {
		shooterPID.setSetpoint(newSetpoint);
	}

	/**
	 * @return the goal RPM of the current PID loop
	 */
	public double getRPMSetpoint() {
		return shooterPID.getSetpoint();
	}

	/**
	 * Sets the shooter to run at a set motor speed
	 */
	public void runShooterAtDefaultSpeed() {
		shooterMotor1.set(Params.SHOOTER_MOTOR_POWER);
		shooterMotor2.set(Params.SHOOTER_MOTOR_POWER);
	}

	public void outtakeShooter() {
		shooterMotor1.set(-Params.SHOOTER_OUTTAKE_MOTOR_POWER);
		shooterMotor2.set(-Params.SHOOTER_OUTTAKE_MOTOR_POWER);
	}

	/**
	 * sets the shooter motor to 0.0
	 */
	public void stopShooter() {
		shooterMotor1.set(0.0);
		shooterMotor2.set(0.0);
	}

}