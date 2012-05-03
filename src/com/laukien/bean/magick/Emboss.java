package com.laukien.bean.magick;

/**
 * Emboss an image.
 * 
 * @author Stephan Laukien
 *
 */
public class Emboss extends CommandAbstract {
	private int gValue;

	public Emboss() {
		super();
		gValue=Integer.MIN_VALUE;
	}

	public void Radius(int pValue) {
		gValue=pValue;
	}
	
	public String getStatement() {
		if(gValue==Integer.MIN_VALUE ) return "";
		return "-emboss "+gValue;
	}

}
