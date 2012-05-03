package com.laukien.bean.magick;

/**
 * Obtain image by descending window hierarchy.
 * 
 * @author Stephan Laukien
 *
 */
public class Descent extends CommandAbstract {

	public Descent() {
		super();

	}
	
	public String getStatement() {
		return "-descent";
	}

}
