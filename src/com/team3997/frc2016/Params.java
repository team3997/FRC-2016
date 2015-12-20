package com.team3997.frc2016;

import com.ni.vision.NIVision.Range;
import com.team3997.frc2016.components.UpdateParameters;

public class Params extends UpdateParameters {
	public static final Constant MOTOR_SPEED = new Constant("MOTOR_SPEED", 0.5);
	public static final boolean squareInputs = true;
    public static final int JOYSTICK_USB = 0;
    public static final int DRIVE_PINS[] = {8,9,7,6}; //front left pin, right pin
    
    
    //VISION STUFF:
    public static double LOOP_DELAY = 0.01;
    
    public static String CAMERA = "cam1";
    
    /**
     * Receive camera input and log to the dashboard?
     */
    public static boolean VISION = true;

    /**
     * Set if we should interpret camera input and
     * detect bright objects in the image.
     */
    public static boolean VISION_ADVANCED = false;
    
    /**
     * RGB Red Range
     */
    public static Range VISION_RED = new Range(120, 250);
    
    /**
     * RGB Green Range
     */
    public static Range VISION_GREEN = new Range(170, 255);
    
    /**
     * RGB Blue Range
     */
    public static Range VISION_BLUE = new Range(235, 255);
}