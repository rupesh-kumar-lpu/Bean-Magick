package com.laukien.bean.magick;

/**
 * Average a set of images.
 * The set of images is terminated by the appearance of any option.
 * If the "-average" option appears after all of the input images,
 * all images are averaged.
 * 
 * @author Stephan Laukien
 *
 */
public class Average extends CommandAbstract {

	public Average() {
		super();
	}
	
	public String getStatement() {
		return "-average";
	}

}
