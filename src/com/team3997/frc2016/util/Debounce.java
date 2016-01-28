package com.team3997.frc2016.util;
import edu.wpi.first.wpilibj.*;

/*********************************Debounce**************************************
 * Purpose:
 * 		The Debounce class allows for the creation if joystick button debounce
 * 		objects.  These objects automatically keep track in changes of button
 * 		state, so you can read falling and rising edges.  This can help keep you
 * 		from processing button pushes more than once for an individual task.
 ******************************************************************************/

public class Debounce {
	private LogitechF310Gamepad joystick;	//Joystick object
	private int button;			//Button number on the joystick
	private boolean lastState;	//Records the button's previous state
	
	
	/*****************************Debounce()************************************
	 * Purpose:
	 * 		Constructor for the Debounce class.  Saves the joystick and button
	 * 		information and records an initial state in lastState;
	 * Parameters:
	 * 		Joystick joystick:  The joystick object to read values from
	 * 		int button:  The number of the button on the joystick
	 * Returns:
	 * 		N/A
	 * Notes:
	 * 		The joystick and button variables are set to object memory from
	 * 		local parameters.  That is the purpose of the 'this' keyword.  It
	 * 		References the top-level scope variables instead of the local
	 * 		parameters of the same name, which normally override precedence in
	 * 		the local scope. 
	 **************************************************************************/
	public Debounce(LogitechF310Gamepad joystick, int button) {
		this.joystick = joystick;	//Scope is important, y'all. 
		this.button = button;
		
		lastState = joystick.getButton(button);

	}
	
	/*******************************getRise()***********************************
	 * Purpose:
	 * 		Returns whether the the button is seeing a rising edge.
	 * Parameters:
	 * 		N/A
	 * Returns:
	 * 		true:  If button went from low to high
	 * 		false:  If button did not go from low to high
	 * Notes: 
	 **************************************************************************/
	public boolean getRise() {
			if(joystick.getButton(button) && !lastState) {
				lastState = true;
				return true;
			}
		
			lastState = joystick.getButton(button);
			return false;
	}
	
	/*******************************getFall()***********************************
	 * Purpose:
	 * 		Returns whether the the button is seeing a falling edge.
	 * Parameters:
	 * 		N/A
	 * Returns:
	 * 		true:  If button went from high to low
	 * 		false:  If button did not go from high to low
	 * Notes: 
	 * 		In this case, the return value will be the opposite of the button's
	 * 		current value when you care about it.
	 **************************************************************************/
	public boolean getFall() {
			if(!joystick.getButton(button) && lastState) {
				lastState = false;
				return true;
			}
		
			lastState = joystick.getButton(button);
			return false;
	}
	
	/*****************************getChange()***********************************
	 * Purpose:
	 * 		Returns whether the button state has changed.
	 * Parameters:
	 * 		N/A
	 * Returns:
	 * 		true:  If button saw rising or falling edge
	 * 		false:  If button state has not changed
	 * Notes:
	 **************************************************************************/
	public boolean getChange() {
		if(this.getRise() || this.getFall())
			return true;
		return false;
	}
	
	/******************************getValue()***********************************
	 * Purpose:
	 * 		Updates the lastState variable and returns button's current value.
	 * Parameters:
	 * 		N/A
	 * Returns:
	 * 		true:  If the button's current value is true
	 * 		false:  If the button's current value is false
	 * Note:
	 * 		This method is a better way of reading the button's value than
	 * 		reading it directly from the joystick since it updates lastState in
	 * 		case you need to use debouncing again.
	 **************************************************************************/
	public boolean getValue() {
		lastState = joystick.getButton(button);
		
		return joystick.getButton(button);
	}
	
	/********************************update()***********************************
	 * Purpose:
	 * 		Updates the lastState variable in cases where you don't care about
	 * 		the button's state this loop but need the object to be kept current.
	 * Parameters:
	 * 		N/A
	 * Returns:
	 * 		N/A
	 * Note:
	 **************************************************************************/
	public void update() {
		lastState = joystick.getButton(button);
	}
}