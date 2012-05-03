package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Splice the background color into the image.
 * 
 * See "-resize" for details about the geometry specification.
 * 
 * @author Stephan Laukien
 *
 */
public class Splice extends CommandAbstract {
	public static final int NONE=-1;
	public static final int PERCENTAGE=1;
	
	private int gWidth;
	private int gHeight;
	private int gValue;

	private int gOption;

	public Splice() {
		super();
		gWidth=-1;
		gHeight=-1;
		gValue=Integer.MIN_VALUE;
		gOption=NONE;
	}
	
	/**
	 * Sets additionally options to the splice-parameter (%).
	 * 
	 * @param pOption
	 */
	public void setOption(int pOption) {
		if(pOption!=PERCENTAGE) gOption=NONE;
		else gOption=pOption;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Sketch.setGeometry: Invalid format");
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
					if(pos==-1) throw new ParameterException("Sketch.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Sketch.setGeometry: Invalid format\n"+pGeometry);
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

		return "-splice "+param;
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
