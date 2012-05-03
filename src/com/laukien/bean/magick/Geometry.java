package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Preferred size and location of the image.
 * 
 * If the x is negative, the offset is measured leftward from the right edge
 * of the screen to the right edge of the image being displayed.
 * Similarly, negative y is measured between the bottom edges.
 * The offsets are not affected by %; they are always measured in pixels.
 *  
 * @author Stephan Laukien
 *
 */
public class Geometry extends CommandAbstract {
	private int gWidth;
	private int gHeight;
	private int gLeft;
	private int gTop;

	public Geometry() {
		super();
		gWidth=-1;
		gHeight=-1;
		gLeft=gTop=Integer.MIN_VALUE;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Crop.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Crop.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Crop.setGeometry: Invalid format\n"+pGeometry);
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

	public void setOffset(int pLeft, int pTop) {
		gLeft=pLeft;
		gTop=pTop;
	}
	
	public void setWidth(int pWidth) {
		gWidth=pWidth;
	}
	
	public void setHeight(int pHeight) {
		gHeight=pHeight;
	}
	
	public void setLeft(int pLeft) {
		gLeft=pLeft;
	}
	
	public void setTop(int pTop) {
		gTop=pTop;
	}
	
	public String getStatement() {
		String param=getParameter();
		if(param==null) return "";
		return "-crop "+param;
	}

	private String getParameter() {
		if(gWidth<=0 && gHeight<=0 && gLeft==0 && gTop==0) return null;
		
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight+"";
		return result+getOffsetAsString();
	}
	
	private String getOffsetAsString() {
		if(gLeft==0 && gTop==0) return "";
		return (gLeft>0 ? "+" : "")+gLeft+(gTop>0 ? "+" : "")+gTop;
	}
}