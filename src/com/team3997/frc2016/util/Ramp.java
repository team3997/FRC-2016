package com.team3997.frc2016.util;

/**
 * A Ramp causes fast changes in input to be spread out
 * over time in the output, which puts less stress on
 * hardware components and can be used to make the robot
 * drive smoother, though the latter is rarely the intended
 * use case.
 */
public class Ramp {
    
    /** The value of the ramp from the previous iteration */
    private double oldValue = 0;
    
    /** How much to ramp by */
    private double rampSpeed;
    
    /**
     * Create a new ramp.
     * 
     * @param rampSpeed The amount to ramp by.
     */
    public Ramp(double rampSpeed) {
        this.rampSpeed = Math.abs(rampSpeed);
    }
    
    /**
     * Ramp a target value. This method must be called every iteration.
     * 
     * @param targetValue The raw input value.
     * @return The ramped value.
     */
    public double rampValues(double targetValue) {
    	double newValue = oldValue;
    	if(Math.abs(oldValue - targetValue) > rampSpeed)
    		newValue += oldValue < targetValue ? rampSpeed : -rampSpeed;
    	else
    		newValue = targetValue;
    	oldValue = newValue;
    	return newValue;
    }
}