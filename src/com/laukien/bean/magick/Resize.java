package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Resize an image.
 * By default, the width and height are maximum values.
 * That is, the image is expanded or contracted to fit the width and height value
 * while maintaining the aspect ratio of the image.
 * Append an exclamation point to the geometry to force the image size to exactly the size you specify.
 * For example, if you specify 640x480! the image width is set to 640 pixels and height to 480.
 * 
 * If only the width is specified, the width assumes the value and the height
 * is chosen to maintain the aspect ratio of the image.
 * Similarly, if only the height is specified
 * (e.g., "-resize x256", the width is chosen to maintain the aspect ratio.
 * 
 * To specify a percentage width or height instead, append %.
 * The image size is multiplied by the width and height percentages to obtain the final image dimensions.
 * To increase the size of an image, use a value greater than 100 (e.g. 125%).
 * To decrease an image's size, use a percentage less than 100.
 * 
 * Use @ to specify the maximum area in pixels of an image.
 * Use &gt; to change the dimensions of the image only if its width or height exceeds
 * the geometry specification.
 * &lt; resizes the image only if both of its dimensions are less than the geometry specification.
 * For example, if you specify 640x480&gh; and the image size is 256x256, the image size does not change.
 * However, if the image is 512x512 or 1024x1024, it is resized to 480x480.
 * Enclose the geometry specification in quotation marks to prevent the &lt; or &gt;
 * from being interpreted by your shell as a file redirection.
 * 
 * If the "-filter" option precedes the "-resize" option, the image is resized with the specified filter.
 * 
 * If the "-support" option precedes the "-resize" option, the image is resized with the specified support.
 * 
 * @author Stephan Laukien
 *
 */
public class Resize extends CommandAbstract {
	public static final int NONE=-1;
	
	/**
	 * Resize image with data dependent triangulation.
	 */
	public static final int ADAPTIVE=1;

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
	
	/**
	 * Use @ to specify the maximum area in pixels of an image. 
	 */
	public static final int PIXEL=12;
	
	/**
	 * Use &gt; to change the dimensions of the image only if its width or height exceeds the geometry specification. 
	 */
	public static final int BIGGER=13;
	
	/**
	 * &lt; resizes the image only if both of its dimensions are less than the geometry specification.
	 */
	public static final int SMALLER=14;
	
	
	private int gWidth;
	private int gHeight;
	private int gValue;

	private int gType;
	private int gOption;

	public Resize() {
		super();
		gWidth=-1;
		gHeight=-1;
		gValue=Integer.MIN_VALUE;
		gType=NONE;
		gOption=NONE;
	}
	
	public void setType(int pType) {
		if(pType<ADAPTIVE || pType>ADAPTIVE) gType=NONE;
		else gType=pType;
	}

	/**
	 * Sets additionally options to the resize-parameter (&lt;gt;!@%).
	 * 
	 * @param pOption
	 */
	public void setOption(int pOption) {
		if(pOption<FORCE || pOption>SMALLER) gOption=NONE;
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
	
	public void setPixel(int pValue) {
		setOption(PIXEL);
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

		String result;
		switch(gType) {
		case ADAPTIVE:	result="-adaptive-resize"; break;
		default:		result="-resize";
		}
		
		return result+' '+param;
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
		if(gOption==PIXEL && gValue!=Integer.MIN_VALUE) return gValue+"@";
		
		if(gWidth<=0 && gHeight<=0) return null;
		String result="x";
		if(gWidth>0) result=gWidth+(gOption==PERCENTAGE ? "%" : "")+result;
		if(gHeight>0) result+=gHeight+(gOption==PERCENTAGE ? "%" : "");
		
		//add the option
		switch(gOption) {
		case FORCE: if(gWidth>0 && gHeight>0) result+='!'; break;
		//Not percentage - it's added above
		//case PERCENTAGE: result+='%'; break;
		case PIXEL: result+='@'; break;
		case BIGGER: result+='>'; break;
		case SMALLER: result+='<'; break;
		}
		
		return result;
	}
}
