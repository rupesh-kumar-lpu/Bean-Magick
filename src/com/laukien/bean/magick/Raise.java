package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Lighten or darken image edges.
 * 
 * This will create a 3-D effect.
 * See "-geometry" for details details about the geometry specification.
 * Offsets are not used.
 * 
 * Use "-raise" to create a raised effect, otherwise use "+raise".
 * 
 * @author Stephan Laukien
 *
 */
public class Raise extends CommandAbstract {
	private int gWidth;
	private int gHeight;
	private boolean gIsRaise;

	public Raise() {
		super();
		gWidth=-1;
		gHeight=-1;
		gIsRaise=true;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Raise.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Raise.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Raise.setGeometry: Invalid format");
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
	
	
	/**
	 * Sets the kind of the "raise".
	 * 
	 * @param pIsRaise <code>true</code> to create a raised effect, otherwise use <code>false</code>
	 */
	public void setRaise(boolean pIsRaise) {
		gIsRaise=pIsRaise;
	}

	public String getStatement() {
		if(getGeometry()==null) return "";
		else return (gIsRaise ? "-" : "+")+"raise "+getGeometry();
	}

	private String getGeometry() {
		if(gWidth==-1 && gHeight==-1) return null;
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight;
		
		return result;
	}
}
