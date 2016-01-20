package com.team3997.frc2016.components;

import edu.wpi.first.wpilibj.networktables.NetworkTable;



public class GripVision {
	/*
	NetworkTable grip;
	double[] defaultValue = new double[0];
	double[] areas;
	double[] xs;
	double[] ys;
	int i = 0;
	
	double targetArea = -1.0;
	double targetX = 0.0;
	
	
	
	public GripVision() {
		//Specify the path of the GRIP values
		grip = NetworkTable.getTable("GRIP/myContoursReport");
		
		//Runtime.getRuntime().exec(new String[]{"/usr/local/frc/JRE/bin/java", "-jar", "grip.jar", filename});
	}
	
	public void runTeleOp(){
		/*areas = grip.getNumberArray("area", defaultValue); //Get the numbers from the array
		Dashboard.put("No. of Contours", areas.length); // Print the number of contours detected
		for(i=0; i < areas.length; i++){
			Dashboard.put("areas", areas[i]); //Bad way of printing the area of each contour
		}
		for(i=0; i < xs.length; i++){
			Dashboard.put("centerX", xs[i]);
		}
		for(i=0; i < ys.length; i++){
			Dashboard.put("centerY", ys[i]);
		}
	}
	
	/*public void autoShooter(){ //not that this is a constructor
		
		//this is just a pseudo code of how this may work
		
		//pick whatever target has the biggest area
		for(i=0; i < areas.length; i++){
			if(areas[i] > targetArea){
				targetArea = areas[i];
				targetX = xs[i];
			}
		}
		
		//If we didn't find a target, return control to the operator
		if(targetArea < 0.0){
			
		}
		
		//If the target is too far left, move left
		//If the target is too far right, move right
		if(true){ //target too far right
			//AdjustRight
		} 
		else if(!true){ //target too far left
			//AdjustLeft
		}
		else{
			//Shooter.shoot();
		}
	}*/
	
	
	
	
	
	
	
	 //public static NetworkTable roboRealm;
	/*public Vision() {
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
*/	
}