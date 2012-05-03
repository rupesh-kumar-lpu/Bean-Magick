package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * JPEG/MIFF/PNG compression level.
 * 
 * For the JPEG and MPEG image formats, quality is 0 (lowest image quality and highest compression)
 * to 100 (best quality but least effective compression).
 * The default is to use the estimate quality of your input image otherwise 75.
 * Use the "-sampling-factor" option to specify the factors for chroma downsampling.
 * 
 * For the MIFF image format, quality/10 is the zlib compression level,
 * which is 0 (worst but fastest compression) to 9 (best but slowest).
 * It has no effect on the image appearance, since the compression is always lossless.
 * 
 * For the JPEG-2000 image format, quality is mapped using a non-linear equation
 * to the compression ratio required by the Jasper library.
 * This non-linear equation is intended to loosely approximate the quality provided
 * by the JPEG v1 format. The default quality value 75 results in a request for 16:1 compression.
 * The quality value 100 results in a request for non-lossy compression.
 * 
 * For the MNG and PNG image formats, the quality value sets the zlib compression level (quality / 10)
 * and filter-type (quality % 10).
 * Compression levels range from 0 (fastest compression) to 100 (best but slowest).
 * For compression level 0, the Huffman-only strategy is used,
 * which is fastest but not necessarily the worst compression.
 * 
 * The default is quality is 75, which means nearly the best compression with adaptive filtering.
 * The quality setting has no effect on the appearance of PNG and MNG images,
 * since the compression is always lossless.
 * 
 * For further information, see the PNG specification.
 * When writing a JNG image with transparency, two quality values are required,
 * one for the main image and one for the grayscale image that conveys the alpha channel.
 * These are written as a single integer equal to the main image quality plus 1000 times
 * the opacity quality. For example, if you want to use quality 75 for the main image
 * and quality 90 to compress the opacity data, use "-quality 90075".
 * 
 * @author Stephan Laukien
 *
 */
public class Quality extends CommandAbstract {
	private int gImage;
	private int gOpacity;

	public Quality() {
		super();
		gImage=-1;
		gOpacity=-1;
	}
	
	/**
	 * Sets the quality for the image data from 0 to 100.
	 * If the quality-value is bigger the opacity quality will be set to.
	 * 
	 * @param pImage
	 */
	public void setImage(int pImage) {
		if(pImage<0 || pImage>100) throw new ParameterException("Quality.setImage: Invalid quality-value");
		gImage=pImage;
	}
	
	/**
	 * Sets the quality of the opacity data from 0 to 100.
	 * If the quality-value is bigger the opacity quality will be set to.
	 * 
	 * @param pOpacity
	 */
	public void setOpacity(int pOpacity) {
		if(pOpacity<0 || pOpacity>100) throw new ParameterException("Quality.setOpacity: Invalid quality-value");
		gOpacity=pOpacity;
	}
	
	
	public String getStatement() {
		if(gImage==-1) return "";
		if(gOpacity==-1) return "-quality "+gImage;
		else return "quality "+(gOpacity*1000)+gImage;
	}

}
