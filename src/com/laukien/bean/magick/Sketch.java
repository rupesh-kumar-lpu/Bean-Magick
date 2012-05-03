package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Simulate a pencil sketch.
 * 
 * Sketch with the given radius, standard deviation (sigma), and angle.
 * The angle given is the angle toward which the image is sketched.
 * That is the direction people would consider the object is coming from. 
 * 
 * @author Stephan Laukien
 *
 */
public class Sketch extends CommandAbstract {
	private float gRadius;
	private float gSigma;
	private float gAngle;
	
	public Sketch() {
		super();
		gRadius=-1;
		gSigma=-1;
		gAngle=Integer.MIN_VALUE;
	}
	
	public void setRadius(float pRadius) {
		if(pRadius<0) throw new ParameterException("Sketch.setRadius: Invalid size");
		gRadius=pRadius;
	}
	
	public void setSigma(float pSigma) {
		if(pSigma<0) throw new ParameterException("Sketch.setSigma: Invalid size");
		gSigma=pSigma;
	}
	
	public void setAngle(float pAngle) {
		if(pAngle<0 || pAngle>360) throw new ParameterException("Sketch.setAngle: Invlaid Angle (0°<Angle>360°)");
		gAngle=pAngle;
	}
	
	public String getStatement() {
		if(getParameter()==null) return "";
		
		
		return "-sketch "+getParameter();
	}

	private String getParameter() {
		if(gRadius<0 && gSigma<0) return null;
		
		String result="";
		if(gRadius>=0) {
			result+=gRadius;
			if(gSigma>=0) result+="x"+gSigma;
		} else result+="x"+gSigma;
		
		if(gAngle!=Integer.MIN_VALUE) result+="+"+gAngle;
		return result;
	}
}
