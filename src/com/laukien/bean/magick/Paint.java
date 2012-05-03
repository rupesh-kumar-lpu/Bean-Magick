package com.laukien.bean.magick;

/**
 * Simulate an oil painting.
 * 
 * Each pixel is replaced by the most frequent color in a circular neighborhood
 * whose width is specified with radius.
 *  
 * @author Stephan Laukien
 *
 */
public class Paint extends CommandAbstract {
	private float gRadius;
	
	public Paint() {
		super();
		gRadius=-1;
	}
	
	public void setRadius(float pRadius) {
		gRadius=pRadius;
	}
	
	public String getStatement() {
		if(gRadius==-1) return "";
		else return "-noise "+gRadius;
	}
}
