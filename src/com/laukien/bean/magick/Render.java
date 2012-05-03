package com.laukien.bean.magick;

/**
 * Resample image to specified horizontal and vertical resolution.
 * Resize the image so that its rendered size remains the same as the original
 * at the specified target resolution.
 * For example, if a 300 DPI image renders at 3 inches by 2 inches on a 300 DPI device,
 * when the image has been resampled to 72 DPI,
 * it will render at 3 inches by 2 inches on a 72 DPI device.
 * Note that only a small number of image formats (e.g. JPEG, PNG, and TIFF) are
 * capable of storing the image resolution. For formats which do not support an image resolution,
 * the original resolution of the image must be specified via -density on the command line
 * prior to specifying the resample resolution.
 * 
 * Note that Photoshop stores and obtains image resolution from a proprietary embedded profile.
 * If this profile exists in the image, then Photoshop will continue to treat the image
 * using its former resolution, ignoring the image resolution specified in the standard file header.
 * 
 * @author Stephan Laukien
 *
 */
public class Render extends CommandAbstract {
	public static final boolean ON=true;
	public static final boolean OFF=false;
	
	private boolean gType;
	
	public Render() {
		super();
		gType=ON;
	}
	
	public void setType(boolean pType) {
		gType=pType;
	}
	
	public String getStatement() {
		return (gType ? "-" : "+")+"render";
	}

}
