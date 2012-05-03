package com.laukien.bean.magick;

/**
 * Apply the clipping path, if one is present.
 * 
 * If a clipping path is present, it will be applied to subsequent operations.
 * 
 * For example, if you type the following command:
 * "convert -clip -negate cockatoo.tif negated.tif"
 * only the pixels within the clipping path are negated.
 * 
 * The "-clip" feature requires the XML library.
 * If the XML library is not present, the option is ignored.
 * 
 * @author Stephan Laukien
 *
 */
public class Clip extends CommandAbstract {
	public static final int NONE=-1;
	
	/**
	 * Clip along a named path from the 8BIM profile.
	 */
	public static final int PATH=1;
	
	/**
	 * clip as defined by an image mask.
	 */
	public static final int MASK=2;
	
	private int gType;
	private int gPathId;
	
	public Clip() {
		super();
		gType=NONE;
		gPathId=-1;
	}
	
	public void setType(int pType) {
		if(pType<PATH || pType>MASK) gType=NONE;
		else gType=pType;
	}
	
	/**
	 * Sets the Id for "clip-path".
	 *  
	 * @param pPathId
	 */
	public void setPathId(int pPathId) {
		gPathId=pPathId;
	}
	
	public String getStatement() {
		if(gType==MASK) return "-clip-mask";
		else if(gType==PATH) return "clip-path "+gPathId;
		else return "-clip";
	}

}
