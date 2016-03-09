package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Controls;
import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.Robot;
import com.team3997.frc2016.components.Lights;
import com.team3997.frc2016.util.AMT103V_Encoder;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.F310;
import com.team3997.frc2016.util.PID.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter {

	private F310 gamePad;
	public PID shooterPID;
	private Spark shooterMotor1;
	private Spark shooterMotor2;
	private Encoder shooterEncoder;
	public double goalRPM = 0;
	private boolean shooterIsSpinning = false;
	
	private ChickenRun cRun;

	public Shooter(Spark kShooterMotor1, Spark kShooterMotor2, Encoder kShooterEncoder,
			F310 kGamePad, ChickenRun kCRun) {

		gamePad = kGamePad;

		shooterMotor1 = kShooterMotor1;
		shooterMotor2 = kShooterMotor2;
		shooterMotor1.setInverted(true);
		shooterMotor2.setInverted(true);

		shooterEncoder = kShooterEncoder;

		shooterPID = new PID(shooterEncoder, shooterMotor1, shooterMotor2, PIDParams.sP.getDouble(),
				PIDParams.sI.getDouble(), PIDParams.sI.getDouble(), PIDParams.sTolerance, PIDParams.sOutMin,
				PIDParams.sOutMax, PIDParams.sSamplesToAverage, PIDParams.sType);

		cRun = kCRun;
		shooterEncoder.setPIDSourceType(PIDSourceType.kRate);
		shooterEncoder.setDistancePerPulse((double)1/2048);

		shooterPID.setSetpoint(PIDParams.sGoalRPM.getInt()/60);
		stopShooter();
	}

	public void initTeleOp(){
		shooterEncoder.reset();
	}
	
	// Function that runs during teleop periodically
	public void runTeleOp() {

		if( gamePad.getYellowButton() || gamePad.getRedButton() || gamePad.getBlueButton() || gamePad.getGreenButton() )
			shooterIsSpinning = true;
		else
			shooterIsSpinning = false;
		
		
		if(gamePad.getButton(Controls.RUN_CRUN_TO_SHOOTER) && shooterIsSpinning)
			cRun.setSendingToShooter(true);
		else
			cRun.setSendingToShooter(false);

		
		if(!Robot.isManualMode)
			runAuto();
		else
			runManual();
		
		SmartDashboard.putNumber("encoder pulses raw scaled", shooterEncoder.get());
    	SmartDashboard.putNumber("encoder RPM rate", (shooterEncoder.getRate() * 60)); //rpm
    	SmartDashboard.putNumber("encoder total distance (total rotations)", shooterEncoder.getDistance());
	}


	public void runAuto() {
		if(Params.SHOOTER_USE_PID){
			if(gamePad.getYellowButton()){
				shooterPID.enablePID();
			}
			else if(gamePad.getRedButton()){ 
				shooterPID.enablePID();
			}
			else if(gamePad.getBlueButton()){ 
				shooterPID.enablePID();
			}
			else if(gamePad.getGreenButton()){ 
				shooterPID.enablePID();
			}
			else if (gamePad.getLeftTrigger()) {
				outtakeShooter();
			}
			else {
				shooterPID.disablePID();
			}
		}
		else {
			runManual();
		}
	}

	public void runManual() {
		if(gamePad.getYellowButton()){
			this.run(Params.SHOOTER_YELLOW_MOTOR_POWER);
		}
		else if(gamePad.getRedButton()){ 
			this.run(Params.SHOOTER_RED_MOTOR_POWER);
		}
		else if(gamePad.getBlueButton()){ 
			this.run(Params.SHOOTER_BLUE_MOTOR_POWER);
		}
		else if(gamePad.getGreenButton()){ 
			this.run(Params.SHOOTER_GREEN_MOTOR_POWER);
		}
		else if (gamePad.getLeftTrigger()) {
			outtakeShooter();
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

	public void outtakeShooter() {
		shooterMotor1.set(-Params.SHOOTER_OUTTAKE_MOTOR_POWER);
		shooterMotor2.set(-Params.SHOOTER_OUTTAKE_MOTOR_POWER);
	}
	
	public void run(double speed){
		shooterMotor1.set(speed);
		shooterMotor2.set(speed);
	}

	/**
	 * sets the shooter motor output to 0.00
	 */
	public void stopShooter() {
		shooterMotor1.set(0.00);
		shooterMotor2.set(0.00);
	}

}