package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Green chromaticity primary point.
 * 
 * @author Stephan Laukien
 *
 */
public class GreenPrimary extends CommandAbstract {
	private int gLeft;
	private int gTop;

	public GreenPrimary() {
		super();
		gLeft=-1;
		gTop=-1;
	}
	
	public void setPoint(String pPoint) {
		if(com.laukien.string.String.isEmpty(pPoint)) throw new ParameterException("GreenPrimary.setPoint: Invalid format");
		try {
			int pos=pPoint.indexOf('x');
			if(pos==-1) {
				pos=pPoint.indexOf('X');
				if(pos==-1) {
					pos=pPoint.indexOf(',');
					if(pos==-1) throw new ParameterException("GreenPrimary.setPoint: Invalid format");
				}
			}
			if(pos>0) gLeft=Integer.parseInt(pPoint.substring(0,pos));
			else gLeft=-1;
			if(pos<pPoint.length()-1) gTop=Integer.parseInt(pPoint.substring(pos+1));
			else gTop=-1;
		} catch(Exception e) {
			throw new ParameterException("GreenPrimary.setPoint: Invalid format");
		}
	}
	
	/**
	 * Sets the point of this command.
	 * 
	 * @param pLeft x
	 * @param pTop y
	 */
	public void setPoint(int pLeft, int pTop) {
		gLeft=pLeft;
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
		else return "-green-primary "+getPoint();
	}

	public String getPoint() {
		if(gLeft==-1 && gTop==-1) return null;
		String result=",";
		if(gLeft>0) result=gLeft+result;
		if(gTop>0) result+=gTop;
		
		return result;
	}
}
