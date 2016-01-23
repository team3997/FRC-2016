package com.team3997.frc2016.subsystems;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;
import com.team3997.frc2016.util.LogitechDualGamepad;
import com.team3997.frc2016.util.LogitechF310Gamepad;

import edu.wpi.first.wpilibj.Talon;


public class Feeder {
	
	private LogitechF310Gamepad gamePad;
	double feederMotorPower;
	Talon feederMotor;
	
	public Feeder(Talon feederMotor, double feederMotorPower){
		
		gamePad = Hardware.kDriverGamepad;
		
		this.feederMotor = feederMotor;
		this.feederMotorPower = feederMotorPower;
	}
	
	
    // Function that runs during teleop periodically
    public void runTeleOp(){
    	
    }
}
