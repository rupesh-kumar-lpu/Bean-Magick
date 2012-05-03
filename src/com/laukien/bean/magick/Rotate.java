package com.laukien.bean.magick;

/**
 * Apply Paeth image rotation to the image.
 * 
 * Use &gt; to rotate the image only if its width exceeds the height.
 * &lt; rotates the image only if its width is less than the height.
 * For example, if you specify "-rotate -90&gt;" and the image size is 480x640,
 * the image is not rotated.
 * However, if the image is 640x480, it is rotated by -90 degrees.
 * If you use &gt; or &lt;, enclose it in quotation marks to prevent
 * it from being misinterpreted as a file redirection.
 * 
 * Empty triangles left over from rotating the image are filled with the color defined as background.
 * The color is specified using the format described under the "-fill" option.
 * 
 * @author Stephan Laukien
 *
 */
public class Rotate extends CommandAbstract {
	public static final int NONE=-1;
	public static final int SMALLER=1;
	public static final int BIGGER=2;
	
	private int gAngle;
	private int gOption;
	
	public Rotate() {
		super();
		gAngle=Integer.MIN_VALUE;
		gOption=NONE;
	}
	
	public void setAngle(int pAngle) {
		gAngle=pAngle;
	}
	
	public void setOption(int pOption) {
		if(pOption<SMALLER && pOption>BIGGER) gOption=NONE;
		else gOption=pOption;
	}
	
	public String getStatement() {
		if(gAngle==Integer.MIN_VALUE) return "";
		String result="-rotate "+gAngle;
		
		if(gOption==SMALLER) return result+"<";
		else if(gOption==BIGGER) return result+">";
		else return result;
	}

}
