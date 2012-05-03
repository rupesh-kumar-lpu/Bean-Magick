package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * width and height of the image.
 * 
 * Use this option to specify the width and height of raw images
 * whose dimensions are unknown such as GRAY, RGB, or CMYK.
 * In addition to width and height, use "-size" with an offset to skip any header information
 * in the image or tell the number of colors in a MAP image file, (e.g. "-size 640x512+256").

 * @author Stephan Laukien
 *
 */
public class Size extends CommandAbstract {
	private int gWidth;
	private int gHeight;
	private boolean gEnable;
	private int gOffset=Integer.MIN_VALUE;

	public Size() {
		super();
		gWidth=-1;
		gHeight=-1;
		gEnable=true;
		gOffset=Integer.MIN_VALUE;
	}
	
	public void setEnable(boolean pEnable) {
		gEnable=pEnable;
	}
	
	public void setOffset(int pOffset) {
		gOffset=pOffset;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Size.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) pos=pGeometry.indexOf('X');
			if(pos==-1) throw new ParameterException("Size.setGeometry: Invalid format");
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Size.setGeometry: Invalid format");
		}
	}
	
	/**
	 * Sets the geometry of this command.
	 * 
	 * @param pWidth width
	 * @param pHeight height
	 */
	public void setGeometry(int pWidth, int pHeight) {
		gWidth=pWidth;
		gHeight=pHeight;
	}

	public String getStatement() {
		if(getGeometry()==null) return "";
		else return (gEnable ? "-" : "+")+"size "+getGeometry()+getOffset();	//+ = disabled
	}

	private String getGeometry() {
		if(gWidth==-1 && gHeight==-1) return null;
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight;
		
		return result;
	}
	
	private String getOffset() {
		if(gOffset==Integer.MIN_VALUE) return "";
		if(gOffset>0) return "+"+gOffset;
		else return ""+gOffset;
	}
}