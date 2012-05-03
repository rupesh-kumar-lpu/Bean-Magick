package com.laukien.bean.magick;

/**
 * Specify the color to be used with the "-frame" option.
 * 
 * The color is specified using the format described under the "-fill" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Mattecolor extends CommandAbstract {
	private String gColor;

	public Mattecolor() {
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
		if(gColor==null ) return "";
		return "-mattecolor "+gColor;
	}

}
