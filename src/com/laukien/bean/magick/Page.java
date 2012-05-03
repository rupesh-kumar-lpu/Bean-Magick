package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Size and location of an image canvas.
 * 
 * Use this option to specify the dimensions of the PostScript page in dots per inch
 * or a TEXT page in pixels. 
 * 
 * @author Stephan Laukien
 *
 */
public class Page extends CommandAbstract {
	public static final int NONE=-1;
	
	/**
	 * Append an exclamation point to the geometry
	 * to force the image size to exactly the size you specify (!).
	 */
	public static final int FORCE=10;
	
	/**
	 * To specify a percentage width or height instead, append %.
	 * The image size is multiplied by the width and height percentages
	 * to obtain the final image dimensions.
	 */
	public static final int PERCENTAGE=11;
	
	private int gWidth;
	private int gHeight;
	private int gValue;

	private int gOption;

	public Page() {
		super();
		gWidth=Integer.MIN_VALUE;
		gHeight=Integer.MIN_VALUE;
		gValue=Integer.MIN_VALUE;
		gOption=NONE;
	}
	
	/**
	 * Sets additionally options to the resize-parameter (!%).
	 * 
	 * @param pOption
	 */
	public void setOption(int pOption) {
		if(pOption<FORCE || pOption>PERCENTAGE) gOption=NONE;
		else gOption=pOption;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Page.setGeometry: Invalid format");
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
					if(pos==-1) throw new ParameterException("Page.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Page.setGeometry: Invalid format\n"+pGeometry);
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
		
		return "-page "+param;
	}

	/**
	 * Builds the Resize-Parameter.
	 * 
	 * This method is protected 'cause it will be extended.
	 * 
	 * @return
	 */
	protected String getParameter() {
		if(gOption==PERCENTAGE && gValue!=Integer.MIN_VALUE) return gValue+"%";
		
		if(gWidth==Integer.MIN_VALUE && gHeight==Integer.MIN_VALUE) return null;
		String result="x";
		if(gWidth>0) result=gWidth+(gOption==PERCENTAGE ? "%" : "")+result;
		if(gHeight>0) result+=gHeight+(gOption==PERCENTAGE ? "%" : "");
		
		//add the option
		if(gOption==FORCE) result+='!';
		
		return result;
	}
}
