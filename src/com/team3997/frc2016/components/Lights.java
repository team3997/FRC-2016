package com.team3997.frc2016.components;

import com.team3997.frc2016.Hardware;
import edu.wpi.first.wpilibj.I2C;

public class Lights {

	//create byte array to send and the I2C object "lights"
	public byte[] toSend = new byte[1];
	I2C lights = Hardware.kLights;
	
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
			System.out.println("ERROR! No Slave on the I2C Bus! Check Connection.");
	}
}
