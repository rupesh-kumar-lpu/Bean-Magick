package com.laukien.bean.magick;

/**
 * The units of image resolution.
 * 
 * Choose from: Undefined, PixelsPerInch, or PixelsPerCentimeter.
 * This option is normally used in conjunction with the "-density" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Unit extends CommandAbstract {

	public static final int NONE=-1;
	public static final int UNDEFINED=1;
	public static final int PIXELS_PER_INCH=2;
	public static final int PIXELS_PER_CENTIMETER=3;
	
	private int gType;
	
	public Unit() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		else return "-unit "+getTypeAsString();
	}
	
	/**
	 * Returns the type as String.
	 * Is the type is <code>NONE</code>, <code>NULL</code> will be the result.
	 * 
	 * @return
	 */
	private String getTypeAsString() {
		switch(gType) {
		case UNDEFINED:					return "Undefined";
		case PIXELS_PER_INCH:			return "PixelsPerInch";
		case PIXELS_PER_CENTIMETER:		return "PixelsPerCentimeter";
		default:						return null;
		}
		
	}

}
