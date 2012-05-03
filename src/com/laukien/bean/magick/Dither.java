package com.laukien.bean.magick;

/**
 * Apply Floyd/Steinberg error diffusion to the image.
 * 
 * The basic strategy of dithering is to trade intensity resolution for spatial resolution by averaging
 * the intensities of several neighboring pixels.
 * Images which suffer from severe contouring when reducing colors can be improved with this option.
 * 
 * The "-colors" or "-monochrome" option is required for this option to take effect.
 * 
 * Use "+dither" to turn off dithering and to render PostScript without text or graphic aliasing.
 * Disabling dithering often (but not always) leads to decreased processing time.
 *  
 * @author Stephan Laukien
 *
 */
public class Dither extends CommandAbstract {
	public static final boolean ON=true;
	public static final boolean OFF=false;
	
	private boolean gType;
	
	public Dither() {
		super();
		gType=ON;
	}
	
	public void setType(boolean pType) {
		gType=pType;
	}
	
	public String getStatement() {
		return (gType ? "-" : "+")+"dither";
	}

}
