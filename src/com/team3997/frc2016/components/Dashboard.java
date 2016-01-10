package com.team3997.frc2016.components;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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