package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Sharpen the image.
 * 
 * Use a Gaussian operator of the given radius and standard deviation (sigma).
 * 
 * @author Stephan Laukien
 *
 */
public class Sharpen extends CommandAbstract {
	public static final int NONE=-1;
	
	/**
	 * Adaptively sharpen pixels; increase effect near edges.
	 */
	public static final int ADAPTIVE=1;
	
	private int gType;
	private float gRadius;
	private float gSigma;
	
	public Sharpen() {
		super();
		gType=NONE;
		gRadius=-1;
		gSigma=-1;
	}
	
	public void setType(int pType) {
		if(pType<ADAPTIVE || pType>ADAPTIVE) gType=NONE;
		else gType=pType;
	}
	
	/**
	 * Use a Gaussian operator of the given radius.
	 * @param pRadius
	 */
	public void setRadius(float pRadius) {
		if(pRadius<0) throw new ParameterException("Sharpen.setRadius: Invalid size");
		gRadius=pRadius;
	}
	
	/**
	 * Use a Gaussian operator of the given standard deviation
	 * 
	 * @param pSigma
	 */
	public void setSigma(float pSigma) {
		if(pSigma<0) throw new ParameterException("Sharpen.setSigma: Invalid size");
		gSigma=pSigma;
	}
	
	public String getStatement() {
		if(getParameter()==null) return "";
		
		String result;
		switch(gType) {
		case ADAPTIVE:	result="-adaptive-sharpen"; break;
		default:		result="-sharpen";
		}
		
		return result+' '+getParameter();
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
