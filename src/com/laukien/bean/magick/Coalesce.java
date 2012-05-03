package com.laukien.bean.magick;

/**
 * Iidefine the look of each frame of a sequence.
 * 
 * Overlay each image in an image sequence as defined by the "-disposal" setting.
 * 
 * @author Stephan Laukien
 *
 */
public class Coalesce extends CommandAbstract {

	public Coalesce() {
		super();
	}
	
	public String getStatement() {
		return "-coalesce";
	}

}
