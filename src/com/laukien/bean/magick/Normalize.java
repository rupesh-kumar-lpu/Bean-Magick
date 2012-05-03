package com.laukien.bean.magick;

/**
 * Transform image to span the full range of color values.
 * 
 * The channels are normalized in concert.
 * Specify "-channel" to normalize the RGB channels independently.
 * 
 * @author Stephan Laukien
 *
 */
public class Normalize extends CommandAbstract {

	public Normalize() {
		super();
	}
	
	public String getStatement() {
		return "-normalize";
	}

}
