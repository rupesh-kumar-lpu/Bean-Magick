package com.laukien.bean.magick;

/**
 * Implode image pixels about the center.
 * 
 * @author Stephan Laukien
 *
 */
public class Implode extends CommandAbstract {
	private float gFactor;
	
	public Implode() {
		super();
		gFactor=Float.MIN_VALUE;
	}
	
	public void setFactor(float pFactor) {
		gFactor=pFactor;
	}
	
	public String getStatement() {
		if(gFactor==Float.MIN_VALUE) return "";
		
		return "-implode "+gFactor;
	}
}
