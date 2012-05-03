package com.laukien.bean.magick;

/**
 * Strip the image of any profiles or comments.
 * 
 * @author Stephan Laukien
 *
 */
public class Strip extends CommandAbstract {

	public Strip() {
		super();
	}
	
	public String getStatement() {
		return "-strip";
	}

}
