package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Blur the image with a Gaussian operator.
 * 
 * @author Stephan Laukien
 *
 */
public class Blur extends CommandAbstract {
	public static final int NONE=-1;
	
	/**
	 * See "-resize" for details about the geometry specification.
	 * "-adaptive-resize" ignores the "-filter" selection if the -filter option is present.
	 * Offsets, if present in the geometry string, are ignored, and the -gravity option has no effect.
	 */
	public static final int ADAPTIVE=1;
	
	/**
	 * Simulate motion blur.
	 * Blur with the given radius, standard deviation (sigma), and angle.
	 * The angle given is the angle toward which the image is blurred.
	 * That is the direction people would consider the object is coming from.
	 */
	public static final int MOTION=2;
	
	/**
	 * Radial blur the image.
	 */
	public static final int RADIAL=3;
	
	private int gType;
	private float gRadius;
	private float gSigma;
	private float gAngle;
	
	public Blur() {
		super();
		gType=NONE;
		gRadius=-1;
		gSigma=-1;
		gAngle=Integer.MIN_VALUE;
	}
	
	public void setType(int pType) {
		if(pType<ADAPTIVE || pType>MOTION) gType=NONE;
		else gType=pType;
	}
	
	/**
	 * Use a Gaussian operator of the given radius.
	 * @param pRadius
	 */
	public void setRadius(float pRadius) {
		if(pRadius<0) throw new ParameterException("Blur.setRadius: Invalid size");
		gRadius=pRadius;
	}
	
	/**
	 * Use a Gaussian operator of the given standard deviation
	 * 
	 * @param pSigma
	 */
	public void setSigma(float pSigma) {
		if(pSigma<0) throw new ParameterException("Blur.setSigma: Invalid size");
		gSigma=pSigma;
	}
	
	public void setAngle(float pAngle) {
		if(pAngle<0 || pAngle>360) throw new ParameterException("Blur.setAngle: Invlaid Angle (0°<Angle>360°)");
		gAngle=pAngle;
	}
	
	public String getStatement() {
		if(getParameter()==null) return "";
		
		String result;
		switch(gType) {
		case ADAPTIVE:	result="-adaptive-blur"; break;
		case MOTION:	result="-motion-blur"; break;
		case RADIAL:	return (gAngle!=Integer.MIN_VALUE ? "-radial-blur "+gAngle : "");
		default:		result="-blur";
		}
		
		return result+' '+getParameter();
	}

	private String getParameter() {
		if(gRadius<0 && gSigma<0) return null;
		
		String result="";
		if(gRadius>=0) {
			result+=gRadius;
			if(gSigma>=0) result+="x"+gSigma;
		} else result+="x"+gSigma;
		
		if(gType==MOTION && gAngle!=Integer.MIN_VALUE) result+="+"+gAngle;
		return result;
	}
}
