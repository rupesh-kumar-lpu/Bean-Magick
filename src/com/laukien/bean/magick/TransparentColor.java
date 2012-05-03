package com.laukien.bean.magick;

/**
 * The transparent color.
 * 
 * @author Stephan Laukien
 *
 */
public class TransparentColor extends CommandAbstract {
	private String gColor;

	public TransparentColor() {
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
		return "-transparent-color "+gColor;
	}

}
