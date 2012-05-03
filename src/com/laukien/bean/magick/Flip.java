package com.laukien.bean.magick;

/**
 * Create a mirror image.
 * 
 * Reflect the scanlines in the vertical direction.
 * 
 * @author Stephan Laukien
 *
 */
public class Flip extends CommandAbstract {

	public Flip() {
		super();
	}
	
	public String getStatement() {
		return "-flip";
	}

}
