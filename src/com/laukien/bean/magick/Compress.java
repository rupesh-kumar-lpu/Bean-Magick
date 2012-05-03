package com.laukien.bean.magick;

/**
 * Use this type of pixel compression when writing the image.
 * Choices are: None, BZip, Fax, Group4, JPEG, JPEG2000, Lossless, LZW, RLE or Zip.
 * 
 * Specify "+compress" to store the binary image in an uncompressed format.
 * The default is the compression type of the specified image file.
 * 
 * If LZW compression is specified but LZW compression has not been enabled,
 * the image data will be written in an uncompressed LZW format that can be read by LZW decoders.
 * This may result in larger-than-expected GIF files.
 * 
 * Lossless refers to lossless JPEG,
 * which is only available if the JPEG library has been patched to support it.
 * Use of lossless JPEG is generally not recommended.
 * 
 * Use the "-quality" option to set the compression level
 * to be used by JPEG, PNG, MIFF, and MPEG encoders.
 * Use the "-sampling-factor" option to set the sampling factor
 * to be used by JPEG, MPEG, and YUV encoders for downsampling the chroma channels.
 *  
 * @author Stephan Laukien
 *
 */
public class Compress extends CommandAbstract {
	/**
	 * No compression.
	 */
	public static final int NONE=-1;
	public static final int BZIP=1;
	public static final int FAX=2;
	public static final int GROUP4=3;
	public static final int JPEG=4;
	public static final int JPEG2000=5;
	public static final int LOSSLESS=6;
	public static final int LZW=7;
	public static final int RLE=8;
	public static final int ZIP=9;
	
	private int gType;
	
	public Compress() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(pType<BZIP || pType>ZIP) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "+compress";
		else return "-compress "+getTypeAsString();
	}

	private String getTypeAsString() {
		switch(gType) {
		case BZIP: return "BZip";
		case FAX: return "Fax";
		case GROUP4: return "Group4";
		case JPEG: return "JPEG";
		case JPEG2000: return "JPEG2000";
		case LOSSLESS: return "Loosless";
		case LZW: return "LZW";
		case RLE: return "RLE";
		case ZIP: return "Zip";
		default: return null;
		}
	}

}
