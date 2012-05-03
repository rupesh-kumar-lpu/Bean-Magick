package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Set the image extent. The pixels scale or location do not change.
 * If the image is enlarged, unfilled areas are set to black.
 * 
 * See "-resize" for details about the geometry specification.
 * 
 * @author Stephan Laukien
 *
 */
public class Extend extends CommandAbstract {
	private int gWidth;
	private int gHeight;

	public Extend() {
		super();
		gWidth=-1;
		gHeight=-1;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Extend.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Extend.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Extend.setGeometry: Invalid format");
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

	public void setWidth(int pWidth) {
		gWidth=pWidth;
	}
	
	public void setHeight(int pHeight) {
		gHeight=pHeight;
	}

	public String getStatement() {
		if(getGeometry()==null) return "";
		else return "-extend "+getGeometry();
	}

	private String getGeometry() {
		if(gWidth==-1 && gHeight==-1) return null;
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight;
		
		return result;
	}
}
