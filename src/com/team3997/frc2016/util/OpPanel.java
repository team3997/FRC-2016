package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * Easy to use class to access buttons of our operator panel (if we use one)
 * @author Michael
 *
 */

public class OpPanel{
    private Joystick op;
    
    public OpPanel(int portNum) {
        this.op = new Joystick(portNum);
    }
    
    public boolean getButton(int buttonNum) {
        return this.op.getRawButton(buttonNum);
    }

}
