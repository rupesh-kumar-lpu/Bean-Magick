package com.laukien.bean.magick;

/**
 * Colors within this distance are considered equal.
 * 
 * A number of algorithms search for a target color. By default the color must be exact.
 * Use this option to match colors that are close to the target color in RGB space.
 * For example, if you want to automatically trim the edges of an image with "-trim"
 * but the image was scanned and the target background color may differ by a small amount.
 * This option can account for these differences.
 * 
 * The distance can be in absolute intensity units or, by appending % as a percentage
 * of the maximum possible intensity (255, 65535, or 4294967295).
 *  
 * @author Stephan Laukien
 *
 */
public class Fuzz extends CommandAbstract {
	private int gValue;
	private boolean gIsPercent;

	public Fuzz() {
		super();
		gValue=Integer.MIN_VALUE;
	}

	public void setPercentage(int pValue) {
		gIsPercent=true;
		gValue=pValue;
	}
	
	public void setDistance(int pValue) {
		gIsPercent=false;
		gValue=pValue;
	}
	
	public String getStatement() {
		if(gValue==Integer.MIN_VALUE ) return "";
		return "-fuzz "+gValue+(gIsPercent ? "%" : "");
	}

}
