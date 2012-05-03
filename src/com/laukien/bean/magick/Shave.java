package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Shave pixels from the image edges.
 * 
 * Specify the width of the region to be removed from both sides of the image and the height of the regions to be removed from top and bottom.
 * 
 * @author Stephan Laukien
 *
 */
public class Shave extends CommandAbstract {
	public static final int NONE=-1;
	public static final int PERCENTAGE=1;
	
	private int gWidth;
	private int gHeight;
	private int gValue;

	private int gOption;

	public Shave() {
		super();
		gWidth=-1;
		gHeight=-1;
		gValue=Integer.MIN_VALUE;
		gOption=NONE;
	}
	
	/**
	 * Sets additionally options to the shave (%).
	 * 
	 * @param pOption
	 */
	public void setOption(int pOption) {
		if(pOption!=PERCENTAGE) gOption=NONE;
		else gOption=pOption;
	}

	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Resize.setGeometry: Invalid format");
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
					if(pos==-1) throw new ParameterException("Resize.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Resize.setGeometry: Invalid format\n"+pGeometry);
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

	public void setPercentage(int pValue) {
		setOption(PERCENTAGE);
		gValue=pValue;
	}
	
	public void setWidth(int pWidth) {
		gWidth=pWidth;
	}
	
	public void setHeight(int pHeight) {
		gHeight=pHeight;
	}
	
	public String getStatement() {
		String param=getParameter();
		if(param==null) return "";

		 return "-shave "+param;
	}

	private String getParameter() {
		if(gOption==PERCENTAGE && gValue!=Integer.MIN_VALUE) return gValue+"%";
		
		if(gWidth<=0 && gHeight<=0) return null;
		String result="x";
		if(gWidth>0) result=gWidth+(gOption==PERCENTAGE ? "%" : "")+result;
		if(gHeight>0) result+=gHeight+(gOption==PERCENTAGE ? "%" : "");
		
		return result;
	}
}
