package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Cut out a rectangular region of the image.
 * 
 * See "-resize" for details about the geometry specification.
 * The width and height give the size of the image that remains after cropping,
 * and x and y are offsets that give the location of the top left corner of the cropped image
 * with respect to the original image. To specify the amount to be removed, use -shave instead.
 * 
 * If the x and y offsets are present, a single image is generated,
 * consisting of the pixels from the cropping region. The offsets specify the location
 * of the upper left corner of the cropping region measured downward and rightward
 * with respect to the upper left corner of the image. If the -gravity option is present
 * with NorthEast, East, or SouthEast gravity, it gives the distance leftward from the right edge
 * of the image to the right edge of the cropping region.
 * Similarly, if the "-gravity" option is present with SouthWest, South, or SouthEast gravity,
 * the distance is measured upward between the bottom edges.
 * 
 * If the x and y offsets are omitted, a set of tiles of the specified geometry,
 * covering the entire input image, is generated. The rightmost tiles
 * and the bottom tiles are smaller
 * if the specified geometry extends beyond the dimensions of the input image.
 * 
 * Append an exclamation point to the geometry to force the page size to exactly the size you specify.
 *  
 * @author Stephan Laukien
 *
 */
public class Crop extends CommandAbstract {
	private int gWidth;
	private int gHeight;
	private int gLeft;
	private int gTop;
	private int gValue;
	private boolean gIsPercent;

	public Crop() {
		super();
		gWidth=-1;
		gHeight=-1;
		gLeft=gTop=Integer.MIN_VALUE;
		gValue=Integer.MIN_VALUE;
		gIsPercent=false;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Crop.setGeometry: Invalid format");
		try {
			if(pGeometry.endsWith("%")) {
				setPercentage(Integer.parseInt(pGeometry.substring(0,pGeometry.length()-1)));
				return;
			}
			
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
	
	public void setPercentage(int pValue) {
		gIsPercent=true;
		gValue=pValue;
	}
	
	public void setPercent(boolean pIsPercent) {
		gIsPercent=pIsPercent;
	}
	
	public String getStatement() {
		String param=getParameter();
		if(param==null) return "";
		return "-crop "+param;
	}

	private String getParameter() {
		if(gIsPercent && gValue!=Integer.MIN_VALUE) return gValue+"%";
		
		if(gWidth<=0 && gHeight<=0 && gLeft==0 && gTop==0) return null;
		String result="x";
		if(gWidth>0) result=gWidth+(gIsPercent ? "%" : "")+result;
		if(gHeight>0) result+=gHeight+(gIsPercent ? "%" : "");
		
		return result+getOffsetAsString();
	}
	
	private String getOffsetAsString() {
		if(gLeft==0 && gTop==0) return "";
		return (gLeft>0 ? "+" : "")+gLeft+(gTop>0 ? "+" : "")+gTop;
	}
}
