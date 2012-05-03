package com.laukien.bean.magick;

/**
 * Reduce the speckles within an image.
 * 
 * @author Stephan Laukien
 *
 */
public class Despeckle extends CommandAbstract {

	public Despeckle() {
		super();
	}
	
	public String getStatement() {
		return "-despeckle";
	}

}
