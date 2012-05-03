package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Horizontal and vertical resolution in pixels of the image.
 * 
 * This option specifies the image resolution to store while encoding a raster image
 * or the canvas resolution while rendering (reading) vector formats
 * such as Postscript, PDF, WMF, and SVG into a raster image.
 * Image resolution provides the unit of measure to apply when rendering to an output device
 * or raster image. The default unit of measure is in dots per inch (DPI).
 * The "-units" option may be used to select dots per centimeter instead.
 * 
 * The default resolution is 72 dots per inch,
 * which is equivalent to one point per pixel (Macintosh and Postscript standard).
 * Computer screens are normally 72 or 96 dots per inch while printers typically support
 * 150, 300, 600, or 1200 dots per inch. To determine the resolution of your display,
 * use a ruler to measure the width of your screen in inches,
 * and divide by the number of horizontal pixels (1024 on a 1024x768 display).
 * 
 * If the file format supports it, this option may be used to update the stored image resolution.
 * Note that Photoshop stores and obtains image resolution from a proprietary embedded profile.
 * If this profile is not stripped from the image, then Photoshop will continue to treat the image
 * using its former resolution, ignoring the image resolution specified in the standard file header.
 * 
 * The density option is an attribute and does not alter the underlying raster image.
 * It may be used to adjust the rendered size for desktop publishing purposes by adjusting
 * the scale applied to the pixels. To resize the image so that it is the same size
 * at a different resolution, use the -resample option.
 * 
 * @author Stephan Laukien
 *
 */
public class Density extends CommandAbstract {

	private int gWidth;
	private int gHeight;

	public Density() {
		super();
		gWidth=-1;
		gHeight=-1;
	}
	
	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Density.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Density.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Density.setGeometry: Invalid format");
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
		if(getGeometry()==null) return "";
		else return "-density "+getGeometry();
	}

	private String getGeometry() {
		if(gWidth==-1 && gHeight==-1) return null;
		String result="x";
		if(gWidth>0) result=gWidth+result;
		if(gHeight>0) result+=gHeight;
		
		return result;
	}
}
