package com.laukien.bean.magick;

/**
 * Perform histogram equalization to the image.
 * 
 * @author Stephan Laukien
 *
 */
public class Equalize extends CommandAbstract {

	public Equalize() {
		super();
	}
	
	public String getStatement() {
		return "-equalize";
	}

}
