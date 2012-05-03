package com.laukien.bean.magick;

/**
 * Create a mirror image.
 * 
 * Reflect the scanlines in the horizontal direction.
 * 
 * @author Stephan Laukien
 *
 */
public class Flop extends CommandAbstract {

	public Flop() {
		super();
	}
	
	public String getStatement() {
		return "-flop";
	}

}
