package com.laukien.bean.magick;

/**
 * The background color.
 * 
 * The color is specified using the format described under the "-fill" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Background extends CommandAbstract {
	private String gColor;

	public Background() {
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
		return "-background "+gColor;
	}

}
