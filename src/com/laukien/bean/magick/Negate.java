package com.laukien.bean.magick;

/**
 * Replace every pixel with its complementary color.
 * 
 * The red, green, and blue intensities of an image are negated.
 * White becomes black, yellow becomes blue, etc.
 * Use "+negate" to only negate the grayscale pixels of the image.
 * 
 * @author Stephan Laukien
 *
 */
public class Negate extends CommandAbstract {

	private boolean gIsGrayscale;

	public Negate() {
		super();
		gIsGrayscale=false;
	}
	
	public String getStatement() {
		if(gIsGrayscale) return "+negate";
		else return "-negate";
	}

	/**
	 * <code>true</code> only negate the grayscale pixels of the image.
	 * 
	 * @param pIsGrayscale
	 */
	public void setGrayscaleOnly(boolean pIsGrayscale) {
		gIsGrayscale=pIsGrayscale;
	}
}
