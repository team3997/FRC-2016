package com.team3997.frc2016.util;


import edu.wpi.first.wpilibj.Talon;

/**
 * A class representing a Talon motor controller.
 * Controls ramping and motor inversion as well,
 * making it easy to change such behavior.
 */
public class TalonMotor {
    
    private final Talon talon;
    private final Ramp ramp;
    private final boolean inverted;
    
    /**
     * Constructs a TalonMotor on a pin.
     * 
     * @param motorPin The PWM pin the motor is attached to.
     * @param inv Whether or not the motor is inverted.
     */
    public TalonMotor(int motorPin, boolean inv) {
        talon = new Talon(motorPin);
        ramp = new Ramp(0.2);
        inverted = inv;
    }
    
    /**
     * Set a target value for this motor. This
     * value will be ramped.
     * 
     * @param val The target value, between -1.0 and 1.0 for
     *            full reverse and full forward respectively.
     */
    public void set(double val) {
        val = inverted ? -val : val;
        val = ramp.rampValues(val);
        talon.set(val);
    }
    
    /**
     * Stops the motor, without getting interrupted by ramping.
     */
    public void stop() {
        talon.stopMotor();
    }
}