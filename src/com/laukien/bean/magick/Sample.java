package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Resizes images without adding any new colors to the images.
 * The color uses are exact pixel colors. Images are shrink by removing
 * rows and columns of pixels, and enlarged by doulding rows and
 * columns of pixels.
 * Equivalent to "-filter Point -resize {arg}" but faster.
 * 
 * @author Stephan Laukien
 *
 */
public class Sample extends CommandAbstract {
	private int gWidth;
	private int gHeight;

	public Sample() {
		super();
		gWidth=-1;
		gHeight=-1;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Sample.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) pos=pGeometry.indexOf('X');
			if(pos==-1) throw new ParameterException("Sample.setGeometry: Invalid format");
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Sample.setGeometry: Invalid format");
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
		else return "-sample "+getGeometry();
	}

	private String getGeometry() {
		if(gWidth==-1 && gHeight==-1) return null;
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight;
		
		return result;
	}
}
