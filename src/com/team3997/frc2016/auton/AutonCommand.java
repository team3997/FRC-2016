package com.team3997.frc2016.auton;

import com.team3997.frc2016.auton.AutonFunctions;


public class AutonCommand extends AutonFunctions{
	
	public static void start(){
		goalTime = getTime(); //update goalTime to match the real time before the auton program starts
	}
	
	
	/*******************************wait()*************************************
	 * Purpose:
	 * 		Has the auton program cease progression for an amount of time
	 * Parameters:
	 * 		time: amount of time to stop auton program
	 **************************************************************************/
	public static void wait(double time){ 
		goalTime += time;
		while(isTime()){
			
		}
	}
	
	/*******************************drive()*************************************
	 * Purpose:
	 * 		Has the auton program drive, and only drive.
	 * Parameters:
	 * 		time: amount of time to stop auton program
	 * 		x: motor speed for x direction
	 * 		y: motor speed for y direction
	 **************************************************************************/
	//Drive function for auton programs
	public static void drive(double time, boolean parallel, double x, double y){
		goalTime += time;
		if(!parallel){
			while(isTime()){
				driveTrain.setDrive(x, y, false);
			}
		}
		else {
			driveTrain.setDrive(x, y, false);
		}
		
	}
	
	public static void stopDriving(){
		driveTrain.stop();
	}
	
}