package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Blur the image with a Gaussian operator.
 * 
 * Use the given radius and standard deviation (sigma).
 * 
 * @author Stephan Laukien
 *
 */
public class Gaussian extends CommandAbstract {
	private float gRadius;
	private float gSigma;
	
	public Gaussian() {
		super();
		gRadius=-1;
		gSigma=-1;
	}
	
	/**
	 * Use a Gaussian operator of the given radius.
	 * @param pRadius
	 */
	public void setRadius(float pRadius) {
		if(pRadius<0) throw new ParameterException("Gaussian.setRadius: Invalid size");
		gRadius=pRadius;
	}
	
	/**
	 * Use a Gaussian operator of the given standard deviation
	 * 
	 * @param pSigma
	 */
	public void setSigma(float pSigma) {
		if(pSigma<0) throw new ParameterException("Gaussian.setSigma: Invalid size");
		gSigma=pSigma;
	}
	
	public String getStatement() {
		if(getParameter()==null) return "";
		
		return "-gaussian "+getParameter();
	}

	private String getParameter() {
		if(gRadius<=0 && gSigma<=0) return null;
		
		String result="";
		if(gRadius>=0) {
			result+=gRadius;
			if(gSigma>=0) result+="x"+gSigma;
		} else result+="x"+gSigma;
		
		return result;
	}
}