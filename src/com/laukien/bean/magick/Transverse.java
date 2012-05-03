package com.laukien.bean.magick;

/**
 * Mirror the image along the images bottom-left to top-right diagonal.
 * Equivelent to the operations -flop -rotate 90.
 * 
 * @author Stephan Laukien
 *
 */
public class Transverse extends CommandAbstract {

	public Transverse() {
		super();
	}
	
	public String getStatement() {
		return "-transverse";
	}

}
