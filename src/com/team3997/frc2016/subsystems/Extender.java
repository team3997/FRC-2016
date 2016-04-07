package com.team3997.frc2016.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

//Class for the intake extender
public class Extender {
	DoubleSolenoid extenderSolenoid;
	
	Extender(DoubleSolenoid kExtenderSolenoid){
		extenderSolenoid = kExtenderSolenoid;
	}
	
	
	public void out(){
		extenderSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void in(){
		extenderSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void set(Value value){
		extenderSolenoid.set(value);
	}
	
	public Value get(){
		return extenderSolenoid.get();
	}
	
	public void off(){
		extenderSolenoid.set(DoubleSolenoid.Value.kOff);
	}
}