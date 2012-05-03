package com.laukien.bean.magick;

/**
 * The fill color.
 * 
 * @author Stephan Laukien
 *
 */
public class Fill extends CommandAbstract {
	private String gColor;

	public Fill() {
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
		return "-fill "+gColor;
	}

}
