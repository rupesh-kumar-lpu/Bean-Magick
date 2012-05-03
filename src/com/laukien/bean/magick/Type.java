package com.laukien.bean.magick;

/**
 * The image type.
 * 
 * Choose from: Bilevel, Grayscale, Palette, PaletteMatte, TrueColor, TrueColorMatte,
 * ColorSeparation, ColorSeparationMatte, or Optimize.
 * 
 * Normally, when a format supports different subformats such as grayscale and truecolor,
 * the encoder will try to choose an efficient subformat.
 * The "-type" option can be used to overrride this behavior.
 * For example, to prevent a JPEG from being written in grayscale format even though
 * only gray pixels are present, use.
 * 
 * @author Stephan Laukien
 *
 */
public class Type extends CommandAbstract {
	public static final int NONE=-1;
	public static final int BILEVEL=1;
	public static final int GRAYSCALE=2;
	public static final int PALETTE=3;
	public static final int PALETTE_MATTE=4;
	public static final int TRUE_COLOR=5;
	public static final int TRUE_COLOR_MATTE=6;
	public static final int COLOR_SEPARATION=7;
	public static final int COLOR_SEPARATION_MATTE=8;
	public static final int OPTIMIZE=9;
	
	private int gType;
	
	public Type() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		else return "-type "+getTypeAsString();
	}
	
	/**
	 * Returns the type as String.
	 * Is the type is <code>NONE</code>, <code>NULL</code> will be the result.
	 * 
	 * @return
	 */
	private String getTypeAsString() {
		switch(gType) {
		case BILEVEL:					return "Bilevel";
		case GRAYSCALE:					return "Grayscale";
		case PALETTE:					return "Palette";
		case PALETTE_MATTE:				return "PaletteMatte";
		case TRUE_COLOR:				return "TrueColor";
		case TRUE_COLOR_MATTE:			return "TrueColorMatte";
		case COLOR_SEPARATION:			return "ColorSeparation";
		case COLOR_SEPARATION_MATTE:	return "ColorSeparationMatte";
		case OPTIMIZE:					return "Optimize";
		default:						return null;
		}
		
	}

}
