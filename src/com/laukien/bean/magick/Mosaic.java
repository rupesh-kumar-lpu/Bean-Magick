package com.laukien.bean.magick;

/**
 * Create a mosaic from an image or an image sequence.
 * 
 * The "-page" option can be used to establish the dimensions of the mosaic
 * and to locate the images within the mosaic.
 * 
 * The sequence of images is terminated by the appearance of any option.
 * If the "-mosaic" option appears after all of the input images, all images are included in the mosaic.
 * 
 * @author Stephan Laukien
 *
 */
public class Mosaic extends CommandAbstract {

	public Mosaic() {
		super();
	}
	
	public String getStatement() {
		return "-mosaic";
	}

}
