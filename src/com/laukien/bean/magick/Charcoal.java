package com.laukien.bean.magick;

/**
 * Simulate a charcoal drawing.
 * 
 * @author Stephan Laukien
 *
 */
public class Charcoal extends CommandAbstract {
	private float gFactor;
	
	public Charcoal() {
		super();
		gFactor=Float.MIN_VALUE;
	}
	
	public void setFactor(float pFactor) {
		gFactor=pFactor;
	}
	
	public String getStatement() {
		if(gFactor==Float.MIN_VALUE) return "";
		
		return "-charcoal "+gFactor;
	}
}
