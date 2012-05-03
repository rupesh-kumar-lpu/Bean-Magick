package com.laukien.bean.magick;

/**
 * Level of gamma correction.
 * 
 * The same color image displayed on two different workstations may look different
 * due to differences in the display monitor. Use gamma correction to adjust for this color difference.
 * Reasonable values extend from 0.8 to 2.3. Gamma less than 1.0 darkens the image
 * and gamma greater than 1.0 lightens it.
 * Large adjustments to image gamma may result in the loss of some image information
 * if the pixel quantum size is only eight bits (quantum range 0 to 255).
 * 
 * You can apply separate gamma values to the red, green, and blue channels
 * of the image with a gamma value list delimited with commas (e.g., 1.7,2.3,1.2).
 * 
 * Use "+gamma" value to set the image gamma level without actually adjusting the image pixels.
 * This option is useful if the image is of a known gamma but not set as an image attribute
 * (e.g. PNG images).
 *  
 * @author Stephan Laukien
 *
 */
public class Gamma extends CommandAbstract {
	private float gFactor;
	private boolean gIsAdjust;
	
	public Gamma() {
		super();
		gFactor=Float.MIN_VALUE;
		gIsAdjust=true;
	}
	
	/**
	 *  Set the image gamma level without actually adjusting the image pixels
	 *  
	 *  @param pAdjust <code>true</code> means adjustment (-); <code>false</code> means NO adjustment (+)
	 */
	public void setAdjustment(boolean pAdjust) {
		gIsAdjust=!pAdjust;
	}
	
	public void setFactor(float pFactor) {
		gFactor=pFactor;
	}
	
	public String getStatement() {
		if(gFactor==Float.MIN_VALUE) return "";
		
		return (gIsAdjust ? "-" : "+")+"gamma "+gFactor;
	}
}
