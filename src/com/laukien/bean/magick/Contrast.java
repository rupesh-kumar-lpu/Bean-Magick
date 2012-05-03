package com.laukien.bean.magick;

/**
 * Enhance or reduce the image contrast.
 * 
 * This option enhances the intensity differences between the lighter and darker elements of the image.
 * Use "-contrast" to enhance the image or "+contrast" to reduce the image contrast.
 * 
 * @author Stephan Laukien
 *
 */
public class Contrast extends CommandAbstract {
	public static final boolean ENHANCE=true;
	public static final boolean REDUCE=false;
	
	private boolean gType;
	
	public Contrast() {
		super();
		gType=ENHANCE;
	}
	
	public void setType(boolean pType) {
		gType=pType;
	}
	
	public String getStatement() {
		return (gType ? "-" : "+")+"contrast";
	}

}
