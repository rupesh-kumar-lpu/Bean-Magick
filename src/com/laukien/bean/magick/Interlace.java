package com.laukien.bean.magick;


/**
 * The type of interlacing scheme.
 * 
 * This option is used to specify the type of interlacing scheme for raw image formats
 * such as RGB or YUV.
 * 
 * Use Line or Plane to create an interlaced PNG or GIF or progressive JPEG image.
 * 
 * @author Stephan Laukien
 *
 */
public class Interlace extends CommandAbstract {
	/**
	 * None means do not interlace (RGBRGBRGBRGBRGBRGB...).
	 */
	public static final int NONE=-1;
	
	/**
	 * Line uses scanline interlacing (RRR...GGG...BBB...RRR...GGG...BBB...).
	 */
	public static final int LINE=1;
	
	/**
	 * Plane uses plane interlacing (RRRRRR...GGGGGG...BBBBBB...).
	 */
	public static final int PLANE=2;

	/**
	 * Partition is like plane except the different planes are saved to individual files
	 * (e.g. image.R, image.G, and image.B).
	 */
	public static final int PARTITION=3;
	
	private int gType;
	
	public Interlace() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(pType<LINE || pType>PARTITION) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		String result="-interlace ";
		
		switch(gType) {
		case LINE:		result+="Line"; break;
		case PLANE:		result+="Plane"; break;
		case PARTITION:	result+="Partition"; break;
		default:		result+="None";
		}
		
		return result;
	}
}
