package com.team3997.frc2016.util;

import com.team3997.frc2016.Params;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;

public class AMT103V_Encoder {
	private Encoder encoder;
	
	EncodingType encodingType = Params.ENCODER_ENCODING_TYPE;
	final double distancePerPulse = Params.ENCODER_DISTANCE_PER_PULSE;

	public AMT103V_Encoder(final int aChannel, final int bChannel){
		encoder = new Encoder(aChannel, bChannel, false, encodingType);
		encoder.setDistancePerPulse((double)distancePerPulse);
	}
	
	public AMT103V_Encoder(final int aChannel, final int bChannel, boolean reverseDirection){
		encoder = new Encoder(aChannel, bChannel, reverseDirection, encodingType);
		encoder.setDistancePerPulse((double)distancePerPulse);
	}
	
	
	/**
	 * Gets the current count. Returns the current count on the Encoder. This
	 * method compensates for the decoding type.
	 *
	 * @return Current count from the Encoder adjusted for the 1x, 2x, or 4x scale
	 *         factor.
	 */
	public double getScaledCount(){
		return encoder.get();
	}
	
	
	/**
	 * Get the current rate of the encoder. Units are distance per second as
	 * scaled by the value from setDistancePerPulse().
	 *
	 * @return The current rate of the encoder.
	 */
	public double getRate(){
		return encoder.getRate();
	}
	
	
	/**
	 * Gets the raw value from the encoder. The raw value is the actual count
	 * unscaled by the 1x, 2x, or 4x scale factor.
	 *
	 * @return Current raw count from the encoder
	*/
	public double getRaw(){
		return encoder.getRaw();
	}
	
	
	/**
	 * Reset the Encoder distance to zero. Resets the current count to zero on the
	 * encoder.
	 */
	public void reset(){
		encoder.reset();
	}

	
	/**
	 * Get the WPI encoder object
	 * 
	 * @return The WPILIB Encoder ojbect
	 */
	public Encoder getEncoderObject(){
		return encoder;
	}
}
