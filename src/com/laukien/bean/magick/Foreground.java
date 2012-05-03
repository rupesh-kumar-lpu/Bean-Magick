package com.laukien.bean.magick;

/**
 * Define the foreground color.
 * 
 * The color is specified using the format described under the "-fill" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Foreground extends CommandAbstract {
	private String gColor;

	public Foreground() {
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
		return "-foreground "+gColor;
	}

}
