package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.PIDParams;
import com.team3997.frc2016.util.Dashboard;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class HitDriveStraightSetpointAction extends Action {
	double encoder_setpoint;
	double start_time;
	Encoder leftEncoder = Hardware.kDriveLeftEncoder;
	Encoder rightEncoder = Hardware.kDriveRightEncoder;
	boolean reached_setpoint = false;
	double timeout = 4.0;
	double leftEncoderStartDistance = 0.00;
	double rightEncoderStartDistance = 0.00;
	
	double outMin = 0.0, outMax = 0.0;
	
	double drive_speed;
    
	public HitDriveStraightSetpointAction(double inches, double speed) {
		encoder_setpoint = inches;
		outMin = -speed;
		outMax = speed;
		
	}
    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp() >= start_time + timeout) || (reached_setpoint);
    }

    @Override
    public void update() {
    	Dashboard.put("drive PID error", (leftEncoderStartDistance + leftEncoder.getDistance()) - encoder_setpoint);
    }

    @Override
    public void done(){
    	drive.encoderPIDSetpoint.disablePID();
    	drive.stop();
    }

    @Override
    public void start() {
    	drive.leftEncoder.reset();
    	drive.rightEncoder.reset();
        start_time = Timer.getFPGATimestamp();
        leftEncoderStartDistance = leftEncoder.getDistance();
        rightEncoderStartDistance = rightEncoder.getDistance();
        drive.encoderPIDSetpoint.changeOutput(outMin, outMax);
        drive.encoderPIDSetpoint.changePID(PIDParams.dtP.getDouble(), PIDParams.dtI.getDouble(), PIDParams.dtD.getDouble());
    	drive.encoderPIDSetpoint.setSetpoint(encoder_setpoint + leftEncoderStartDistance);
    	System.out.println("pid drive setpoint: " + encoder_setpoint);
    	drive.encoderPIDSetpoint.enablePID();
    }

}
