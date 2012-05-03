package com.laukien.bean.magick;

/**
 * Trim an image.
 * This option removes any edges that are exactly the same color as the corner pixels.
 * Use "-fuzz" to make "-trim" remove edges that are nearly the same color as the corner pixels.
 * 
 * The page or virtual canvas information of the image is preserved
 * allowing you to extract the result of the -trim operation from the image.
 * Use a "+repage" to remove if unwanted.
 * 
 * @author Stephan Laukien
 *
 */
public class Trim extends CommandAbstract {

	public Trim() {
		super();
	}
	
	public String getStatement() {
		return "-trim";
	}

}
