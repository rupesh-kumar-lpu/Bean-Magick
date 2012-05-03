package com.laukien.bean.magick.template;

import com.laukien.bean.magick.CommandAbstract;
import com.laukien.bean.magick.Identify;
import com.laukien.exception.ParameterException;

public class Thumbnail extends CommandAbstract {
	public static final int ALIGN_AUTO		=	0;
	public static final int ALIGN_WIDTH		=	1;
	public static final int ALIGN_HEIGHT	=	2;
	
	public static final int OPTION_AUTO		=	0;
	public static final int OPTION_CUT		=	1;
	public static final int OPTION_WIDTH	=	2;
	public static final int OPTION_HEIGHT	=	3;

	private int gWidth;
	private int gHeight;
	private int gDepth;
	private int gQuality;
	private int gAlign;
	private int gOption;

	public Thumbnail() {
		super();
		setImageIndex(0);
		gWidth=160;
		gHeight=120;
		gDepth=8;
		gQuality=75;
		gAlign=ALIGN_HEIGHT;
		gOption=OPTION_CUT;
	}

	public void setGeometry(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("Thumbnail.setGeometry: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("Tumbnail.setGeometry: Invalid format");
				}
			}
			if(pos>0) gWidth=Integer.parseInt(pGeometry.substring(0,pos));
			else gWidth=-1;
			if(pos<pGeometry.length()-1) gHeight=Integer.parseInt(pGeometry.substring(pos+1));
			else gHeight=-1;
		} catch(Exception e) {
			throw new ParameterException("Thumbnail.setGeometry: Invalid format");
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
	
	public void setDepth(int pDepth) {
		gDepth=pDepth;
	}
	
	public void setQuality(int pQuality) {
		gQuality=pQuality;
	}

	/**
	 * Sets the Alignment of the image.
	 * "ALIGN_AUTO" need to have an "inputFile" to get information about the image-dimensions.
	 * 
	 * @param pAlign
	 */
	public void setAlignment(int pAlign) {
		if(pAlign<ALIGN_AUTO || pAlign>ALIGN_HEIGHT) throw new ParameterException("Thumbnail.setAlignment: Invalid value");
		gAlign=pAlign;
	}

	/**
	 * Sets the Option for the image.
	 * "ALIGN_CUT" is default.
	 * 
	 * @param pOption
	 */
	public void setOption(int pOption) {
		if(pOption<OPTION_AUTO || pOption>OPTION_HEIGHT) throw new ParameterException("Thumbnail.setOption: Invalid value");
		gOption=pOption;
	}
	
	public String getStatement() {
		if(gAlign==ALIGN_AUTO && gInputFile!=null) {
			Identify identify=new Identify();
			try {
				//widthXheight[+Left+Top]
				String geometry=(String)identify.getProperties(gInputFile).get("page geometry");
				if(geometry==null) throw new Exception("");
				int idxStart, idxStop, width, height;

				idxStop=geometry.indexOf('+');
				if(idxStop==-1) idxStop=geometry.length();
				idxStart=geometry.indexOf('x');
				height=Integer.parseInt(geometry.substring(idxStart+1, idxStop));
				width=Integer.parseInt(geometry.substring(0,idxStart));
				if(width>height) gAlign=ALIGN_WIDTH;
				else gAlign=ALIGN_HEIGHT;
			} catch (Exception e) {
				gAlign=ALIGN_WIDTH;
			}
		}
		//convert -verbose old.png -trim -thumbnail x120 -resize 160x< -gravity Center +repage -crop 160x120 new.png
		String result="-depth "+gDepth+" -quality "+gQuality+ " -trim -thumbnail";
		switch(gOption) {
		case OPTION_WIDTH:
			result+=" "+gWidth+"x";
			break;
		case OPTION_HEIGHT:
			result+=" x"+gHeight;
			break;
		case OPTION_CUT:
		case OPTION_AUTO:
			if(gAlign==ALIGN_WIDTH) result+=" x"+gHeight+" -resize "+gWidth+"x<";
			else result+=" "+gWidth+"x -resize x"+gHeight+"<";
			if(gOption==OPTION_CUT) result+=" -gravity Center -repage "+gWidth+'x'+gHeight+" -crop "+gWidth+'x'+gHeight;
			break;
		}
		return result;
	}

}