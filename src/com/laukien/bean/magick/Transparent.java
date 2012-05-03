package com.laukien.bean.magick;

/**
 * Make this color transparent within the image.
 * 
 * The color is specified using the format described under the "-fill" option.
 * The color to use for image transparency in colormap image formats,
 * such as GIF. As a side effect, fully-opaque colors of this value may also become transparent,
 * depending on the format.
 * 
 * @author Stephan Laukien
 *
 */
public class Transparent extends CommandAbstract {
	private String gColor;

	public Transparent() {
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
		return "-transparent "+gColor;
	}

}
