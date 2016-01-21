package com.team3997.frc2016.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Easy to use dashboard class. Just include this file and use:
 * Dashboard.put("Label", variable); 
 * to put a string, double, or boolean to the dashboard.
 */
public class Dashboard {
    
	//Set a dashboard string.
    public static void put(String key, String value) {
        SmartDashboard.putString(key, value);
    }

    // set a dashboard double
    public static void put(String key, double value) {
        SmartDashboard.putNumber(key, value);
    }

    //Set a dashboard boolean.
    public static void put(String key, boolean value) {
        SmartDashboard.putBoolean(key, value);
    }
    
    
}