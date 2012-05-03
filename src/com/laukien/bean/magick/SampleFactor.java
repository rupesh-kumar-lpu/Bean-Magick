package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Sampling factors used by JPEG or MPEG-2 encoder and YUV decoder/encoder.
 * 
 * This option specifies the sampling factors to be used by the JPEG encoder for chroma downsampling.
 * If this option is omitted, the JPEG library will use its own default values.
 * When reading or writing the YUV format and when writing the M2V (MPEG-2) format,
 * use "-sampling-factor 2x1" or "-sampling-factor 4:2:2" to specify the 4:2:2 downsampling method.
 * 
 * @author Stephan Laukien
 *
 */
public class SampleFactor extends CommandAbstract {
	private int gHorizontal;
	private int gVertical;

	public SampleFactor() {
		super();
		gHorizontal=-1;
		gVertical=-1;
	}
	
	public void setFactor(String pGeometry) {
		if(com.laukien.string.String.isEmpty(pGeometry)) throw new ParameterException("SampleFactor.setFactor: Invalid format");
		try {
			int pos=pGeometry.indexOf('x');
			if(pos==-1) {
				pos=pGeometry.indexOf('X');
				if(pos==-1) {
					pos=pGeometry.indexOf(',');
					if(pos==-1) throw new ParameterException("SampleFactor.setFactor: Invalid format");
				}
			}
			if(pos>0) gHorizontal=Integer.parseInt(pGeometry.substring(0,pos));
			else gHorizontal=-1;
			if(pos<pGeometry.length()-1) gVertical=Integer.parseInt(pGeometry.substring(pos+1));
			else gVertical=-1;
		} catch(Exception e) {
			throw new ParameterException("SampleFactor.setFactor: Invalid format");
		}
	}

	public void setHorizontal(int pHorizontal) {
		gHorizontal=pHorizontal;
	}
	
	public void setVertical(int pVertical) {
		gVertical=pVertical;
	}

	public String getStatement() {
		if(getFactor()==null) return "";
		else return "-displace "+getFactor();
	}

	private String getFactor() {
		if(gHorizontal==-1 && gVertical==-1) return null;
		String result="x";
		if(gHorizontal>0) result=gHorizontal+result;
		if(gVertical>0) result+=gVertical;
		
		return result;
	}
}
