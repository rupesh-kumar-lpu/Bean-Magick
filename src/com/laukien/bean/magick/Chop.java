package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Remove pixels from the interior of an image.
 * 
 * Width and height give the number of columns and rows to remove,
 * and x and y are offsets that give the location of the leftmost column and topmost row to remove.
 * 
 * The x offset normally specifies the leftmost column to remove.
 * If the "-gravity" option is present with NorthEast, East, or SouthEast gravity,
 * it gives the distance leftward from the right edge of the image to the rightmost column to remove.
 * Similarly, the y offset normally specifies the topmost row to remove,
 * but if the -gravity option is present with SouthWest, South, or SouthEast gravity,
 * it specifies the distance upward from the bottom edge of the image to the bottom row to remove.
 * 
 * The "-chopoption" removes entire rows and columns,
 * and moves the remaining corner blocks leftward and upward to close the gaps.
 * 
 * @author Stephan Laukien
 *
 */
public class Chop extends CommandAbstract {
	private int gWidth;
	private int gHeight;
	private int gLeft;
	private int gTop;
	private int gValue;
	private boolean gIsPercent;

	public Chop() {
		super();
		gWidth=-1;
		gHeight=-1;
		gLeft=gTop=Integer.MIN_VALUE;
		gValue=Integer.MIN_VALUE;
		gIsPercent=false;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Chop.setGeometry: Invalid format");
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
					if(pos==-1) throw new ParameterException("Chop.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Chop.setGeometry: Invalid format\n"+pGeometry);
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
		return "-chop "+param;
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
