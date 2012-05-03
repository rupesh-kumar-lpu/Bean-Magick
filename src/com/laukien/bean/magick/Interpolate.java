package com.laukien.bean.magick;


/**
 * The pixel color interpolation method.
 * 
 * Select the desired filter for the "-interpolate" filter option with the "-filter" option
 * and the desired level of sharpness with the "-support" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Interpolate extends CommandAbstract {
	public static final int NONE=-1;
	public static final int AVERAGE=1;
	public static final int BICUBIC=2;
	public static final int BILINEAR=3;
	public static final int FILTER=4;
	public static final int INTEGER=5;
	public static final int MESH=6;
	public static final int NEAREST_NEIGHBOR=7;
	
	private int gType;
	
	public Interpolate() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(pType<AVERAGE || pType>NEAREST_NEIGHBOR) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		String result="-interpolate ";
		
		switch(gType) {
		case AVERAGE:			result+="average"; break;
		case BICUBIC:			result+="bicubic"; break;
		case BILINEAR:			result+="bilinear"; break;
		case FILTER:			result+="filter"; break;
		case INTEGER:			result+="integer"; break;
		case MESH:				result+="mesh"; break;
		case NEAREST_NEIGHBOR:	result+="nearest_neightbor"; break;
		default:				result="";
		}
		
		return result;
	}
}
