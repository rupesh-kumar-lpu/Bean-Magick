package com.laukien.bean.magick;

/**
 * Remove pixel aliasing.
 * By default, objects are antialiased when drawn (e.g. lines, polygon, etc.).
 * Use +antialias to disable antialiasing.
 * Without antialiasing, you can avoid increasing the unique colors in an image,
 * draw fixed width lines, or improve the rendering speed.
 * 
 * @author Stephan Laukien
 *
 */
public class Antialias extends CommandAbstract {
	private boolean gEnable;

	public Antialias() {
		super();
		gEnable=true;
	}
	
	public void setEnable(boolean pEnable) {
		gEnable=pEnable;
	}
	
	public String getStatement() {
		if(gEnable) return "-antialias";
		else return "+antialias";
	}

}
