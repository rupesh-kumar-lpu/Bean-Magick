package com.laukien.bean.magick;

/**
 * Negate all pixels above the threshold level.
 * 
 * Specify factor as the percent threshold of the intensity (0 - 99.9%).
 * 
 * This option produces a solarization effect seen when exposing a photographic film
 * to light during the development process.
 * 
 * @author Stephan Laukien
 *
 */
public class Solarize extends CommandAbstract {
	private float gPercentage;

	public Solarize() {
		super();
		gPercentage=Float.MIN_VALUE;
	}
	
	public void setPercentage(float pPercentage) {
		gPercentage=pPercentage;;
	}
	
	public String getStatement() {
		if(gPercentage==Float.MIN_VALUE) return "";
		else return "-solarize "+gPercentage+'%';
	}

}
