package com.laukien.bean.magick;

/**
 * Direction primitive gravitates to when annotating the image.
 *   
 * @author Stephan Laukien
 *
 */
public class Gravity extends CommandAbstract {
	/**
	 * No Gravity.
	 */
	public static final int NONE=-1;
	public static final int NORTH_WEST=1;
	public static final int NORTH=2;
	public static final int NORTH_EAST=3;
	public static final int WEST=4;
	public static final int CENTER=5;
	public static final int EAST=6;
	public static final int SOUTH_WEST=7;
	public static final int SOUTH=8;
	public static final int SOUTH_EAST=9;
	
	private int gType;
	
	public Gravity() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(pType<NORTH_WEST || pType>SOUTH_EAST) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		else return "-gravity "+getTypeAsString();
	}

	private String getTypeAsString() {
		switch(gType) {
		case NORTH_WEST:	return "NorthWest";
		case NORTH:			return "North";
		case NORTH_EAST:	return "NorthEast";
		case WEST:			return "West";
		case CENTER:		return "Center";
		case EAST:			return "East";
		case SOUTH_WEST:	return "SouthWest";
		case SOUTH:			return "South";
		case SOUTH_EAST:	return "SouthEast";
		default: return null;
		}
	}

}
