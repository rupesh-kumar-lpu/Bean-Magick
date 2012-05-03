package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Sharpen the image with an unsharp mask operator.
 * 
 * The "-unsharp" option sharpens an image.
 * The image is convolved with a Gaussian operator of the given radius and standard deviation (sigma).
 * For reasonable results, radius should be larger than sigma.
 * Use a radius of 0 to have the method select a suitable radius.
 * 
 * @author Stephan Laukien
 *
 */
public class Unsharp extends CommandAbstract {
	private float gRadius;
	private float gSigma;
	private float gAmount;
	private float gThreshold;
	
	public Unsharp() {
		super();
		gRadius=0;
		gSigma=1.0F;
		gAmount=1.0F;
		gThreshold=0.05F;
	}
	
	/**
	 * The radius of the Gaussian, in pixels,  not counting the center pixel (default 0).
	 * 
	 * @param pRadius
	 */
	public void setRadius(float pRadius) {
		if(pRadius<0) throw new ParameterException("Unsharp.setRadius: Invalid size");
		gRadius=pRadius;
	}
	
	/**
	 * The standard deviation of the Gaussian, in pixels (default 1.0).
	 * 
	 * @param pSigma
	 */
	public void setSigma(float pSigma) {
		if(pSigma<0) throw new ParameterException("Unsharp.setSigma: Invalid size");
		gSigma=pSigma;
	}
	/**
	 * The percentage of the difference between the original and the blur
	 * image that is added back into the original (default 1.0).
	 * 
	 * @param pAmount
	 */
	public void setAmount(float pAmount) {
		if(pAmount<0) throw new ParameterException("Unsharp.setAmount: Invalid size");
		gAmount=pAmount;
	}
	
	/**
	 * The threshold, as a fraction of QuantumRange, needed to apply the
	 * difference amount (default 0.05).
	 * 
	 * @param pThreshold
	 */
	public void setThreshold(float pThreshold) {
		if(pThreshold<0) throw new ParameterException("Unsharp.setThreshold: Invalid size");
		gThreshold=pThreshold;
	}
	
	public String getStatement() {
		if(getParameter()==null) return "";
		
		return "-unsharp "+getParameter();
	}

	private String getParameter() {
		if(gRadius<0 && gSigma<0) return null;
		
		String result="";
		
		//different order - cause the last two parameters
		if(gThreshold!=0.05F) result="+"+gThreshold;
		if(gAmount!=1.0F || gThreshold!=0.05F) result="+"+gAmount+result;
		if(gSigma!=1.0F) result="x"+gSigma+result;
		if(gRadius!=0 || gSigma!=1.0F) result=""+gRadius+result;
		
		return result;
	}
}