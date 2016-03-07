
package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.Joystick;

public class F310 {
	public static final int greenButton = 1;
	public static final int blueButton = 3;
	public static final int redButton = 2;
	public static final int yellowButton = 4;
	public static final int backButton = 7;
	public static final int startButton = 8;
	public static final int leftBumper = 5;
	public static final int rightBumper = 6;
	public static final int leftStickClick = 9;
	public static final int rightStickClick = 10;
	public static final int leftTrigger = 11;
	public static final int rightTrigger = 12;
		
    private Joystick joystick;
    private final double triggerEnableThreshold = 0.78;
    
    public F310(int portNum) {
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
    	
    	//special trigger stuff
    	if((btnNum == 11) || (btnNum == 12))
    		return getTrigger(btnNum);
    	
        return this.joystick.getRawButton(btnNum);
    }
    
    public boolean getGreenButton() {
        return this.joystick.getRawButton(greenButton);
    }
    
    public boolean getBlueButton() {
        return this.joystick.getRawButton(blueButton);
    }
    
    public boolean getRedButton() {
        return this.joystick.getRawButton(redButton);
    }
    
    public boolean getYellowButton() {
        return this.joystick.getRawButton(yellowButton);
    }
    
    public boolean getBackButton() {
        return this.joystick.getRawButton(backButton);
    }
    
    public boolean getStartButton() {
        return this.joystick.getRawButton(startButton);
    }
    
    public boolean getLeftBumper() {
        return this.joystick.getRawButton(leftBumper);
    }
    
    public boolean getRightBumper() {
        return this.joystick.getRawButton(rightBumper);
    }
    
    public boolean getLeftStickClick() {
        return this.joystick.getRawButton(leftStickClick);
    }
    
    public boolean getRightStickClick() {
        return this.joystick.getRawButton(rightStickClick);
    }
    
    public boolean getTrigger(int num){
    	if(num == 11)
    		return getLeftTrigger();
    	else
    		return getRightTrigger();
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
