package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Chromaticity white point.
 * 
 * @author Stephan Laukien
 *
 */
public class WhitePoint extends CommandAbstract {
	private int gLeft;
	private int gTop;

	public WhitePoint() {
		super();
		gLeft=-1;
		gTop=-1;
	}
	
	public void setPoint(String pPoint) {
		if(com.laukien.string.String.isEmpty(pPoint)) throw new ParameterException("WhitePoint.setPoint: Invalid format");
		try {
			int pos=pPoint.indexOf('x');
			if(pos==-1) {
				pos=pPoint.indexOf('X');
				if(pos==-1) {
					pos=pPoint.indexOf(',');
					if(pos==-1) throw new ParameterException("WhitePoint.setPoint: Invalid format");
				}
			}
			if(pos>0) gLeft=Integer.parseInt(pPoint.substring(0,pos));
			else gLeft=-1;
			if(pos<pPoint.length()-1) gTop=Integer.parseInt(pPoint.substring(pos+1));
			else gTop=-1;
		} catch(Exception e) {
			throw new ParameterException("WhitePoint.setPoint: Invalid format");
		}
	}
	
	/**
	 * Sets the geometry of this command.
	 * 
	 * @param pLength x
	 * @param pTop y
	 */
	public void setPoint(int pLength, int pTop) {
		gLeft=pLength;
		gTop=pTop;
	}

	public void setLeft(int pLeft) {
		gLeft=pLeft;
	}
	
	public void setTop(int pTop) {
		gTop=pTop;
	}
	
	public String getStatement() {
		if(getPoint()==null) return "";
		else return "-white-point "+getPoint();
	}

	public String getPoint() {
		if(gLeft==-1 && gTop==-1) return null;
		String result=",";
		if(gLeft>0) result=gLeft+result;
		if(gTop>0) result+=gTop;
		
		return result;
	}
}
