package com.laukien.bean.magick;

/**
 * Display the image centered on a backdrop.
 * 
 * This backdrop covers the entire workstation screen and is useful for
 * hiding other X window activity while viewing the image.
 * The color of the backdrop is specified as the background color.
 * The color is specified using the format described under the "-fill" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Backdrop extends CommandAbstract {
	private String gColor;

	public Backdrop() {
		super();
		gColor=null;
	}

	public void setColor(Identify.Color pColor) {
		gColor=pColor.getName();
	}
	
	public void setColor(String pName) {
		gColor=pName;
	}
	
	public String getStatement() {
		if(gColor==null) return "";
		return "-backdrop "+gColor;
	}

}
