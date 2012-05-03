package com.laukien.bean.magick;

/**
 * Transform the image to black and white.
 * 
 * @author Stephan Laukien
 *
 */
public class Monochrome extends CommandAbstract {

	public Monochrome() {
		super();
	}
	
	public String getStatement() {
		return "-monochrome";
	}

}
