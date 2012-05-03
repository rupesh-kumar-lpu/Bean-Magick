package com.laukien.bean.magick;

/**
 * Dissolve an image into another by the given percent.
 * 
 * The opacity of the composite image is multiplied by the given percent,
 * then it is composited over the main image.
 * 
 * @author Stephan Laukien
 *
 */
public class Dissolve extends CommandAbstract {
	private int gValue;

	public Dissolve() {
		super();
		gValue=Integer.MIN_VALUE;
	}

	public void setPercentage(int pValue) {
		gValue=pValue;
	}
	
	public String getStatement() {
		if(gValue==Integer.MIN_VALUE ) return "";
		return "-dissolve "+gValue;
	}

}
