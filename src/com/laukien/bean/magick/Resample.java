package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Shift image pixels as defined by a displacement map.
 * 
 * With this option, composite image is used as a displacement map.
 * Black, within the displacement map, is a maximum positive displacement.
 * White is a maximum negative displacement and middle gray is neutral.
 * The displacement is scaled to determine the pixel shift.
 * By default, the displacement applies in both the horizontal and vertical directions.
 * However, if you specify mask, composite image is the horizontal X displacement
 * and mask the vertical Y displacement.
 * 
 * @author Stephan Laukien
 *
 */
public class Resample extends CommandAbstract {
	private int gHorizontal;
	private int gVertical;

	public Resample() {
		super();
		gHorizontal=-1;
		gVertical=-1;
	}
	
	public void setResolution(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Resample.setResolution: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Resample.setResolution: Invalid format");
				}
			}
			if(pos>0) gHorizontal=Integer.parseInt(pGeometry.substring(0,pos));
			else gHorizontal=-1;
			if(pos<pGeometry.length()-1) gVertical=Integer.parseInt(pGeometry.substring(pos+1));
			else gVertical=-1;
		} catch(Exception e) {
			throw new ParameterException("Resample.setResolution: Invalid format");
		}
	}

	public void setHorizontal(int pHorizontal) {
		gHorizontal=pHorizontal;
	}
	
	public void setVertical(int pVertical) {
		gVertical=pVertical;
	}

	public String getStatement() {
		if(getResolution()==null) return "";
		else return "-resample "+getResolution();
	}

	private String getResolution() {
		if(gHorizontal==-1 && gVertical==-1) return null;
		String result="x";
		if(gHorizontal>0) result=gHorizontal+result;
		if(gVertical>0) result+=gVertical;
		
		return result;
	}
}
