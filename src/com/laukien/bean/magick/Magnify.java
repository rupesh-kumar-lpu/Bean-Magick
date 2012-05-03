package com.laukien.bean.magick;

/**
 * Magnify the image.
 * 
 * @author Stephan Laukien
 *
 */
public class Magnify extends CommandAbstract {
	private float gFactor;
	
	public Magnify() {
		super();
		gFactor=Float.MIN_VALUE;
	}
	
	public void setFactor(float pFactor) {
		gFactor=pFactor;
	}
	
	public String getStatement() {
		if(gFactor==Float.MIN_VALUE) return "";
		
		return "-magnify "+gFactor;
	}
}
