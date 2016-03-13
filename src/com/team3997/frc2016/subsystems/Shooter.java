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
	private boolean shooterIsSpinning = false;
	
	private ChickenRun cRun;

	public Shooter(Spark kShooterMotor1, Spark kShooterMotor2, Encoder kShooterEncoder,
			F310 kGamePad, ChickenRun kCRun) {

		gamePad = kGamePad;

		shooterMotor1 = kShooterMotor1;
		shooterMotor2 = kShooterMotor2;
		shooterMotor1.setInverted(true);
		shooterMotor2.setInverted(true);
		
		cRun = kCRun;

		shooterEncoder = kShooterEncoder;
		shooterEncoder.setDistancePerPulse(Params.ENCODER_DISTANCE_PER_PULSE); //Try directly inputting value if it doens't work

		shooterPID = new PID(shooterEncoder, shooterMotor1, shooterMotor2, PIDParams.sTolerance, PIDParams.sOutMin,
				PIDParams.sOutMax, PIDParams.sSamplesToAverage, PIDParams.sType);
		
		setRPMSetpoint(0);
		
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
		
		Dashboard.put("encoder pulses raw scaled", shooterEncoder.get());
    	Dashboard.put("encoder RPM rate", (shooterEncoder.getRate() * 60));
    	Dashboard.put("Shooter RPM graph", (shooterEncoder.getRate() * 60));
    	Dashboard.put("encoder total distance (total rotations)", shooterEncoder.getDistance());
	}


	public void runAuto() {
		if(Params.SHOOTER_USE_PID){
			if(gamePad.getYellowButton()){
				shooterPID.changePID(PIDParams.syP.getDouble(), PIDParams.syI.getDouble(), PIDParams.syD.getDouble());
				
				if(getRPMSetpoint() != PIDParams.syGoalRPM.getInt()){
					setRPMSetpoint(PIDParams.syGoalRPM.getInt());
					Dashboard.put("SHOOTER_PID", "yellow");
				}

				shooterPID.enablePID();
			}
			else if(gamePad.getRedButton()){ 
				shooterPID.changePID(PIDParams.srP.getDouble(), PIDParams.srI.getDouble(), PIDParams.srD.getDouble());
				
				if(getRPMSetpoint() != PIDParams.srGoalRPM.getInt()){
					setRPMSetpoint(PIDParams.srGoalRPM.getInt());
					Dashboard.put("SHOOTER_PID", "red");
				}
				
				shooterPID.enablePID();
			}
			else if(gamePad.getBlueButton()){ 
				shooterPID.changePID(PIDParams.sbP.getDouble(), PIDParams.sbI.getDouble(), PIDParams.sbD.getDouble());
				
				if(getRPMSetpoint() != PIDParams.sbGoalRPM.getInt()){
					setRPMSetpoint(PIDParams.sbGoalRPM.getInt());
					Dashboard.put("SHOOTER_PID", "blue");
				}
				
				shooterPID.enablePID();
			}
			else if(gamePad.getGreenButton()){ 
				shooterPID.changePID(PIDParams.sgP.getDouble(), PIDParams.sgI.getDouble(), PIDParams.sgD.getDouble());
				
				if(getRPMSetpoint() != PIDParams.sgGoalRPM.getInt()){
					setRPMSetpoint(PIDParams.sgGoalRPM.getInt());
					Dashboard.put("SHOOTER_PID", "green");
				}
				
				shooterPID.enablePID();
			}
			else if(gamePad.getLeftTrigger()) {
				outtakeShooter();
			}
			else {
				setRPMSetpoint(0);
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
		else if(gamePad.getLeftTrigger()) {
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
	public void setRPMSetpoint(double rpm) {
		shooterPID.setSetpoint(rpm / 60);
	}

	/**
	 * @return the goal RPM of the current PID loop
	 */
	public double getRPMSetpoint() {
		return shooterPID.getSetpoint() * 60;
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