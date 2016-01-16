package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * Easy to use class to access buttons of a logitech dual shock gamepad
 * 
 * @author Michael
 *
 */

public class LogitechDualGamepad {
	private Joystick joystick;

	public LogitechDualGamepad(int portNum) {
		this.joystick = new Joystick(portNum);
	}

	public double getLeftX() {
		return this.joystick.getRawAxis(0);
	}

	public double getLeftY() {
		return -this.joystick.getRawAxis(1);
	}

	public double getRightX() {
		return this.joystick.getRawAxis(2);
	}

	public double getRightY() {
		return -this.joystick.getRawAxis(3);
	}

	public boolean getButton(int buttonNum) {
		return this.joystick.getRawButton(buttonNum);
	}

	public boolean getLeftBumper() {
		return this.joystick.getRawButton(5);
	}

	public boolean getRightBumper() {
		return this.joystick.getRawButton(6);
	}

	public boolean getLeftStickClick() {
		return this.joystick.getRawButton(11);
	}

	public boolean getRightStickClick() {
		return this.joystick.getRawButton(12);
	}

	public int getPOVVal() {
		return this.joystick.getPOV(0);
	}

	public boolean getPOVDown() {
		return (this.joystick.getPOV(0) == 180);
	}

	public boolean getPOVRight() {
		return (this.joystick.getPOV(0) == 90);
	}

	public boolean getPOVUp() {
		return (this.joystick.getPOV(0) == 0);
	}

	public boolean getPOVLeft() {
		return (this.joystick.getPOV(0) == 270);
	}

}
