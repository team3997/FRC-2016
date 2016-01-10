package com.team3997.frc2016.components;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;


public class Vision {
	 public static NetworkTable roboRealm;
	
	public Vision() {
		roboRealm = NetworkTable.getTable("RoboRealm");
    }
	
	
	//Get's the value of the roborealm key string provided   (also see http://www.roborealm.com/help/Network_Tables.php for array retrieval)
	public static double getRemoteVal(String val){
		
		try { // try to get the value of the string provided
				double fetchedVal = roboRealm.getNumber(val, 0.0);
				return fetchedVal;
		}
		catch (TableKeyNotDefinedException ex){ // if it fails, throw an an undefined table key error, and return 0
				return 0;
		}
		
	}
	
}	