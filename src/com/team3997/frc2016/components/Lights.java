package com.team3997.frc2016.components;

import com.team3997.frc2016.Hardware;
import edu.wpi.first.wpilibj.I2C;

public class Lights {

	public static final int INDIGO = 1;
	public static final int ORANGE = 2;
	public static final int HALFYELLOW = 3;
	public static final int FULLYELLOW = 4;
	public static final int RAINBOW = 5;
	public static final int PRIDE = 6;
	public static final int BULLET = 7;
	
	//create byte array to send and the I2C object "lights"
	public byte[] toSend = new byte[1];
	I2C lights;
	
	public Lights(I2C kArduino){
		lights = kArduino;
	}

	
	public void setColor(int mode){
		
		/*
		 * set the byte in the toSend array 
		 * based on the integer received
		 */
		
		switch(mode){
		case 1:
			toSend[0]=(byte)1;
			break;
		case 2:
			toSend[0]=(byte)2;
			break;
		case 3:
			toSend[0]=(byte)3;
			break;
		case 4:
			toSend[0]=(byte)4;
			break;
		case 5:
			toSend[0]=(byte)5;
			break;
		case 6:
			toSend[0]=(byte)6;
			break;
		case 7:
			toSend[0]=(byte)7;
			break;
		}
		
		if(lights.addressOnly())
			lights.transaction(toSend, 1, null, 0);
		else
			System.out.println("Arduino not found!");
	}
}