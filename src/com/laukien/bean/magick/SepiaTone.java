package com.laukien.bean.magick;

/**
 * Simulate a sepia-toned photo.
 * 
 * Specify threshold as the percent threshold of the intensity (0 - 99.9%).
 * This option applies a special effect to the image,
 * similar to the effect achieved in a photo darkroom by sepia toning.
 * Threshold ranges from 0 to QuantumRange and is a measure of the extent of the sepia toning.
 * A threshold of 80% is a good starting point for a reasonable tone.
 * 
 * @author Stephan Laukien
 *
 */
public class SepiaTone extends CommandAbstract {
	private float gPercentage;

	public SepiaTone() {
		super();
		gPercentage=Float.MIN_VALUE;
	}
	
	public void setPercentage(float pPercentage) {
		gPercentage=pPercentage;;
	}
	
	public String getStatement() {
		if(gPercentage==Float.MIN_VALUE) return "";
		else return "-sepia-tone "+gPercentage+'%';
	}

}
