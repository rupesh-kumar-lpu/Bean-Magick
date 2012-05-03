package com.laukien.bean.magick;

/**
 * Flatten a sequence of images.
 * 
 * The sequence of images is replaced by a single image created by composing each image
 * after the first over the first image.
 * The sequence of images is terminated by the appearance of any option.
 * If the "-flatten" option appears after all of the input images, all images are flattened.
 * 
 * @author Stephan Laukien
 *
 */
public class Flatten extends CommandAbstract {

	public Flatten() {
		super();
	}
	
	public String getStatement() {
		return "-flatten";
	}

}
