
package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechF310Gamepad {
    private Joystick joystick;
    private final double triggerEnableThreshold = 0.7;
    
    public LogitechF310Gamepad(int portNum) {
        this.joystick = new Joystick(portNum);
    }
    
    public double getLeftX() {
        return this.joystick.getRawAxis(0);
    }
    
    public double getLeftY() {
        return -this.joystick.getRawAxis(1);
    }
    
    public double getLeftTriggerAxis() {
    	return this.joystick.getRawAxis(2);
    }
    
    public double getRightTriggerAxis(){
    	return this.joystick.getRawAxis(3);
    }
    
    public boolean getLeftTrigger() {
    	return this.joystick.getRawAxis(2) >= triggerEnableThreshold;
    }
    
    public boolean getRightTrigger(){
    	return this.joystick.getRawAxis(3) >= triggerEnableThreshold;
    }
    
    public double getRightX() {
        return this.joystick.getRawAxis(4);
    }
    
    public double getRightY() {
        return -this.joystick.getRawAxis(5);
    }
    
    public boolean getButton(int btnNum) {
        return this.joystick.getRawButton(btnNum);
    }
    
    public boolean getGreenButton() {
        return this.joystick.getRawButton(1);
    }
    
    public boolean getBlueButton() {
        return this.joystick.getRawButton(3);
    }
    
    public boolean getRedButton() {
        return this.joystick.getRawButton(2);
    }
    
    public boolean getYellowButton() {
        return this.joystick.getRawButton(4);
    }
    
    public boolean getBackButton() {
        return this.joystick.getRawButton(7);
    }
    
    public boolean getStartButton() {
        return this.joystick.getRawButton(8);
    }
    
    public boolean getLeftBumper() {
        return this.joystick.getRawButton(5);
    }
    
    public boolean getRightBumper() {
        return this.joystick.getRawButton(6);
    }
    
    public boolean getLeftStickClick() {
        return this.joystick.getRawButton(9);
    }
    
    public boolean getRightStickClick() {
        return this.joystick.getRawButton(10);
    }
    
    public int getPOVVal(){
    	return this.joystick.getPOV(0);
    }
    
    public boolean getPOVDown(){
    	return (this.joystick.getPOV(0) == 180);
    }
    
    public boolean getPOVRight(){
    	return (this.joystick.getPOV(0) == 90);
    }
    
    public boolean getPOVUp(){
    	return (this.joystick.getPOV(0) == 0);
    }
    
    public boolean getPOVLeft(){
    	return (this.joystick.getPOV(0) == 270);
    }
	
}
