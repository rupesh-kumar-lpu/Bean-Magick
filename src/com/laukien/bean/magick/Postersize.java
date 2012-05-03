package com.laukien.bean.magick;

/**
 * Reduce the image to a limited number of color levels.
 * 
 * @author Stephan Laukien
 *
 */
public class Postersize extends CommandAbstract {
	private int gSize;

	public Postersize() {
		super();
		gSize=-1;
	}
	
	public void setSize(int pSize) {
		gSize=pSize;
	}
	
	public String getStatement() {
		if(gSize==-1) return "";
		return "-postersize "+gSize;
	}

}
