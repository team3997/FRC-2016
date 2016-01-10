package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.LogitechDualShockGamepad;
import com.team3997.frc2016.components.Dashboard;

import edu.wpi.first.wpilibj.RobotDrive;


public class DriveSubsystem{
	
	double xValOP;
	double yValOP;
	double zValOP;
	
	private LogitechDualShockGamepad gamePad;

	RobotDrive driveTrain;
	
	//int
	public DriveSubsystem() {
		gamePad = new LogitechDualShockGamepad(Params.JOYSTICK_USB);
		driveTrain = new RobotDrive(Params.DRIVE_PINS[0],Params.DRIVE_PINS[1],Params.DRIVE_PINS[2],Params.DRIVE_PINS[3]);
    }
	
	
	//easy to use drive function
    public void setDrive(double x, double y, boolean squareInputs){
    	driveTrain.arcadeDrive(-x, -y, squareInputs);
    }
    
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    	//Get Joystick input from gamepad
    	xValOP = (gamePad.getLeftX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		yValOP = (gamePad.getLeftY()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
		zValOP = (gamePad.getRightX()) * (Params.DRIVE_MOTOR_SPEED.getDouble());
    	
		//Drive at the given input magnitude
    	setDrive(yValOP, xValOP, Params.squareInputs);
    	
    	//Print drive magnitudes if wanted
		if(Params.printTeleOpDriveOuputs){
			Dashboard.put("x: ", xValOP);
			Dashboard.put("y: ", -yValOP);
			Dashboard.put("z: ", zValOP);
		}
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
