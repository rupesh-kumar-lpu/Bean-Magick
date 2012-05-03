package com.laukien.bean.magick;

/**
 * Depth of the image.
 * 
 * This is the number of bits in a color sample within a pixel.
 * Use this option to specify the depth of raw images whose depth is unknown
 * such as GRAY, RGB, or CMYK, or to change the depth of any image after it has been read.
 * 
 * @author Stephan Laukien
 *
 */
public class Depth extends CommandAbstract {
	private int gValue;

	public Depth() {
		super();
		gValue=Integer.MIN_VALUE;
	}

	public void setValue(int pValue) {
		gValue=pValue;
	}
	
	public String getStatement() {
		if(gValue==Integer.MIN_VALUE ) return "";
		return "-depth "+gValue;
	}

}
