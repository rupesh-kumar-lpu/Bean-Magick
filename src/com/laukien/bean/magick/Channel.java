package com.laukien.bean.magick;

/**
 * The type of channel.
 * Choose from: Red, Green, Blue, Alpha, Cyan, Magenta, Yellow, Black,
 * Opacity, Index, RGB, RGBA, CMYK, or CMYKA.
 * 
 * By default, ImageMagick applies operations all channels,
 * except the opacity channel, in an image. Use this option to apply an operation
 * to only select channels of an image. For example to only negate the alpha channel of an image,
 * use "-channel Alpha -negate".
 * 
 * @author Stephan Laukien
 *
 */
public class Channel extends CommandAbstract {
	public static final int NONE=-1;
	public static final int RED=1;
	public static final int GREEN=2;
	public static final int BLUE=3;
	public static final int ALPHA=4;
	public static final int CYAN=5;
	public static final int MAGENTA=6;
	public static final int YELLOW=7;
	public static final int BLACK=8;
	public static final int OPACITY=9;
	public static final int INDEX=10;
	public static final int RGB=11;
	public static final int RGBA=12;
	public static final int CMYK=13;
	public static final int CMYKA=14;
	
	private int gType;
	
	public Channel() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		else return "-channel "+getTypeAsString();
	}
	
	/**
	 * Returns the type as String.
	 * Is the type is <code>NONE</code>, <code>NULL</code> will be the result.
	 * 
	 * @return
	 */
	private String getTypeAsString() {
		switch(gType) {
		case RED: return "Red";
		case GREEN: return "Green";
		case BLUE: return "Blue";
		case ALPHA: return "Alpha";
		case CYAN: return "Cyan";
		case MAGENTA: return "Magenta";
		case YELLOW: return "Yellow";
		case BLACK: return "Black";
		case OPACITY: return "Opacity";
		case INDEX: return "Index";
		case RGB: return "RGB";
		case RGBA: return "RGBA";
		case CMYK: return "CMYK";
		case CMYKA: return "CMYKA";
		default: return null;
		}
		
	}

}
