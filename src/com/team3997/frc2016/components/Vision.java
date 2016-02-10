package com.team3997.frc2016.components;

import com.team3997.frc2016.util.Dashboard;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Vision {
	static NetworkTable contours;
	static double[] areas;
	
	/*public Vision() {
		contours = NetworkTable.getTable("GRIP/myContoursReport");
		areas = contours.getNumberArray("area", defaultValue);
	}
	
	public static void getContours(){
		//areas = contours.getNumberArray("area", defaultValue);
		
		if(areas != null){
			Dashboard.put("area", areas[0]);
		}
		else{
			Dashboard.put("area", 999);
		}
	}*/
}