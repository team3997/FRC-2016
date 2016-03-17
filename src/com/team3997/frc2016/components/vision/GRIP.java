package com.team3997.frc2016.components.vision;

import com.team3997.frc2016.Params;
import com.team3997.frc2016.util.Dashboard;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class GRIP extends IterativeRobot {
	
	NetworkTable table;
	
	private double[] area;
	private double[] centerx;
	private double[] centery;
	private double[] height;
	private double[] width;
	
	double[] defaultValue = {0.0};
	
	public GRIP() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
    }
	
	public void runTeleOp(){
		updateGripValues();
		printGripValues();
	}
	
	/**
	 *  Grab all grip contour variables
	 */
    public void updateGripValues() {
		centerx = table.getNumberArray("centerX", defaultValue);
		centery = table.getNumberArray("centerY", defaultValue);
		area = table.getNumberArray("area", defaultValue);
		height = table.getNumberArray("height", defaultValue);
		width = table.getNumberArray("width", defaultValue);
    }
    
    public double getCenterX(){
    	if(centerx.length>0){
    		return centerx[0];
    	}
    	else {
    		return 0.0;
    	}
    }
    
    public boolean onTarget(){
    	if(getCenterX() > 0){
    		
    		if((getCenterX() >= Params.LEFT_GOAL_X - 2) || (getCenterX() <= Params.LEFT_GOAL_X + 2)){
    			return true;
    		}
    		else {
        		return false;
        	}
    		
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Print all incoming grip contour variables to the dashboard 
     */
    public void printGripValues(){
    	if(centerx.length > 0)
    		Dashboard.put("centerx", getCenterX());
    	if(centery.length > 0)
    		Dashboard.put("centery", centery[0]);
    	if(area.length > 0)
    		Dashboard.put("area", area[0]);
    	if(height.length > 0)
    		Dashboard.put("height", height[0]);
    	if(width.length > 0)
    		Dashboard.put("width", width[0]);
    	Dashboard.put("centerxarraysize", centerx.length);
    }
    
    /**
     * Return the largest value in a given double array
     * @param valueArray a double array
     * @return the value of the element with the largest value. (returns -1 if array is empty)
     */
    public double getLargestArrayValue(double[] valueArray){
    	double max = 0;
    	for(int i = 0; i < valueArray.length; i++){
    		if(valueArray[i] > max){
    			max = valueArray[i];
    		}
    	}
    	
    	if(valueArray.length > 0){
    		return max;
    	}
    	else
    		return -1.0;
    }
    
    /**
     * Return the position of the largest value in a given double array
     * @param valueArray a double array
     * @return the value of the element with the largest value. (returns -1 if array is empty)
     */
    public int getLargestArrayValueIndex(double[] valueArray){
    	int biggestIndex = 0;
    	double max = 0;
    	for(int i = 0; i < valueArray.length; i++){
    		if(valueArray[i] > max){
    			max = valueArray[i];
    			biggestIndex = i;
    		}
    	}
    	
    	if(valueArray.length > 0)
    		return biggestIndex;
    	else
    		return -1;
    }
}