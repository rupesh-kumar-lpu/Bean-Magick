package com.laukien.bean.magick;

/**
 * Store matte channel if the image has one.
 * 
 * If the image does not have a matte channel, create an opaque one.
 * 
 * Use "+matte" to ignore the matte channel and to avoid writing a matte channel in the output file.
 *  
 * @author Stephan Laukien
 *
 */
public class Matte extends CommandAbstract {
	private boolean gIsIgnore;

	public Matte() {
		super();
		gIsIgnore=false;
	}
	
	public void setIgnore(boolean pIsIgnore) {
		gIsIgnore=pIsIgnore;
	}
	
	public String getStatement() {
		if(gIsIgnore) return "+matte";
		else return "-matte";
	}
}
