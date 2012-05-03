package com.laukien.bean.magick;

/**
 * Discard all but one of any pixel color.
 * 
 * @author Stephan Laukien
 *
 */
public class UniqueColors extends CommandAbstract {
	private String gColor;

	public UniqueColors() {
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
		return "-unique-colors "+gColor;
	}

}
