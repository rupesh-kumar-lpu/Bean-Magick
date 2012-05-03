package com.laukien.bean.magick;

/**
 * Mirror the image along the images top-left to bottom-right diagonal
 * (i.e. mathematically transpose the pixel array).
 * Equivelent to the operations -flip -rotate 90.
 * 
 * @author Stephan Laukien
 *
 */
public class Transpose extends CommandAbstract {

	public Transpose() {
		super();
	}
	
	public String getStatement() {
		return "-transpose";
	}

}
