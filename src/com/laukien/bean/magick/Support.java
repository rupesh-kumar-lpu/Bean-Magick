package com.laukien.bean.magick;

/**
 * The "-filter" setting can be further modified using a "-support" setting,
 * which will further sharpen or blur the filter before it is applied.
 * 
 * @author Stephan Laukien
 *
 */
public class Support extends CommandAbstract {
	private float gFactor;
	
	public Support() {
		super();
		gFactor=1.0F;
	}
	
	public void setFactor(float pFactor) {
		gFactor=pFactor;
	}
	
	public String getStatement() {
		if(gFactor==1.0F) return "";
		
		return "-support "+gFactor;
	}
}
