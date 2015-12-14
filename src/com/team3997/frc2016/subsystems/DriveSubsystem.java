package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.TalonMotor;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;


public class DriveSubsystem{
	
	RobotDrive driveTrain = new RobotDrive(Params.DRIVE_PINS[0],Params.DRIVE_PINS[1],Params.DRIVE_PINS[2],Params.DRIVE_PINS[3]);
	
	/*TalonMotor leftB = new TalonMotor(Params.DRIVE_PINS[0], true);
    TalonMotor leftA = new TalonMotor(Params.DRIVE_PINS[1], true);
    TalonMotor rightB = new TalonMotor(Params.DRIVE_PINS[2], true);
    TalonMotor rightA = new TalonMotor(Params.DRIVE_PINS[3], true);*/
    
    public void setDrive(double x, double y, boolean squareInputs){
    	driveTrain.arcadeDrive(-x, -y, squareInputs);
    }
    
    /**
     * Drives straight, adjusting for curve values from gyro.
     * This code is for mecanum drive systems!!!
     * 
     * 
     * @param speed The speed to drive.
     * @param curve The value read from the gyro.
     */
    /*public void driveStraight(double speed, double curve) {
        curve = -curve;
        double leftOutput, rightOutput;

        if (curve < 0) {
            double value = Math.log(-curve);
            double ratio = (value - 0.5) / (value + 0.5);
            if (ratio == 0)
                ratio = .0000000001;
            leftOutput = speed / ratio;
            rightOutput = speed;
        } else if (curve > 0) {
            double value = Math.log(curve);
            double ratio = (value - 0.5) / (value + 0.5);
            if (ratio == 0)
                ratio = .0000000001;
            leftOutput = speed;
            rightOutput = speed / ratio;
        } else {
            leftOutput = speed;
            rightOutput = speed;
        }
        frontLeft.set(leftOutput);
        rearLeft.set(leftOutput);
        frontRight.set(rightOutput);
        rearRight.set(rightOutput);
    }*/
    
    
    /**
     * Force stops all of the drive motors.
     */
    /*public void stop() {
        rightB.stop();
        rightA.stop();
        leftB.stop();
        leftA.stop();
    }*/
    
}
