package com.laukien.bean.magick;

/**
 * Pointsize of the PostScript, OPTION1, or TrueType font.
 * 
 * @author Stephan Laukien
 *
 */
public class Pointsize extends CommandAbstract {
	private int gSize;

	public Pointsize() {
		super();
		gSize=-1;
	}
	
	public void setSize(int pSize) {
		gSize=pSize;
	}
	
	public String getStatement() {
		if(gSize==-1) return "";
		return "-pointsize "+gSize;
	}

}
