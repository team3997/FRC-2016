package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.Talon;


public class ChickenRun {
	
	private LogitechF310Gamepad gamePad;
	double cRunMotorPower;
	Talon cRunMotor;
	
	public ChickenRun(Talon cRunMotor, double cRunMotorPower){
		
		gamePad = Hardware.kDriverGamepad;
		
		this.cRunMotor = cRunMotor;
		this.cRunMotorPower = cRunMotorPower;
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    }
}
