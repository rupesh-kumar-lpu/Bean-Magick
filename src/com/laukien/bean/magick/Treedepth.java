package com.laukien.bean.magick;

/**
 * Tree depth for the color reduction algorithm.
 * 
 * Normally, this integer value is zero or one.
 * A value of zero or one causes the use of an optimal tree depth for the color reduction algorithm.
 * 
 * An optimal depth generally allows the best representation of the source image
 * with the fastest computational speed and the least amount of memory.
 * However, the default depth is inappropriate for some images.
 * To assure the best representation, try values between 2 and 8 for this parameter.
 * Refer to the color reduction algorithm for more details.
 * 
 * The "-colors" or "-monochrome" option, or writing to an image format
 * which requires color reduction, is required for this option to take effect.
 * 
 * @author Stephan Laukien
 *
 */
public class Treedepth extends CommandAbstract {
	private int gValue;

	public Treedepth() {
		super();
		gValue=Integer.MIN_VALUE;
	}

	public void setValue(int pValue) {
		gValue=pValue;
	}
	
	public String getStatement() {
		if(gValue==Integer.MIN_VALUE ) return "";
		return "-treedepth "+gValue;
	}

}
