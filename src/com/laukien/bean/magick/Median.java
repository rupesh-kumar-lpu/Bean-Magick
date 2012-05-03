package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Apply a median filter to the image.
 * 
 * @author Stephan Laukien
 *
 */
public class Median extends CommandAbstract {
	private float gRadius;
	
	public Median() {
		super();
		gRadius=-1;
	}
	
	public void setRadius(float pRadius) {
		if(pRadius<0) throw new ParameterException("Median.setRadius: Invalid size");
		gRadius=pRadius;
	}
	
	public String getStatement() {
		if(gRadius<0) return "";
		
		return "-median "+gRadius;
	}
}