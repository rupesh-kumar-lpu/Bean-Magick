package com.laukien.bean.magick;

/**
 * Create a thumbnail of the image.
 * 
 * This is exactly like -resize, except that any image profiles
 * present are also removed as they are of little importance to small image thumbnails.
 * 
 * @author Stephan Laukien
 *
 */
public class Thumbnail extends Resize {

	public Thumbnail() {
		super();
	}
	
	public String getStatement() {
		String param=getParameter();
		if(param==null) return "";

		return "-thumbnail "+param;
	}

}
