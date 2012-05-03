package com.laukien.bean.magick;

/**
 * Change this color to the fill color within the image.
 * The color is specified using the format described under the "-fill" option.
 * 
 * See "-fill" for more details.
 * 
 * @author Stephan Laukien
 *
 */
public class Opaque extends CommandAbstract {
	private String gColor;

	public Opaque() {
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
		return "-opaque "+gColor;
	}

}
