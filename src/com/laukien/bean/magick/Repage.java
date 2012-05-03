package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Adjust the canvas and offset information of the image.
 * 
 * This option is like -page but acts as an image operator rather than a setting.
 * If a ! flag is given the offset given is added to the existing offset to move
 * the image relative to its previous position. This is useful for animation sequences.
 * A geometry of 0x0 recalculates the canvas size so the image at that offset will appear
 * completely on that canvas (unless it is at a negative offset).
 *
 * Use "+repage" to eliminate page size and location data (no geometry).
 * 
 * @author Stephan Laukien
 *
 */
public class Repage extends CommandAbstract {
	private int gWidth;
	private int gHeight;

	public Repage() {
		super();
		gWidth=-1;
		gHeight=-1;
	}
	
	/**
	 * Sets the "new" geomtry. If it is not set all paging data will be eliminated.
	 * @param pGeometry
	 */
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Repage.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Repage.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Repage.setGeometry: Invalid format");
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
		if(getGeometry()==null) return "+repage";
		else return "-repage "+getGeometry();
	}

	private String getGeometry() {
		if(gWidth==-1 && gHeight==-1) return null;
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight;
		
		return result;
	}
}
