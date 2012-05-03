package com.laukien.bean.magick;

/**
 * Apply a digital filter to enhance a noisy image.
 * 
 * @author Stephan Laukien
 *
 */
public class Enhance extends CommandAbstract {

	public Enhance() {
		super();
	}
	
	public String getStatement() {
		return "-enhance";
	}

}
