package com.laukien.bean.magick;

import com.laukien.exception.ParameterException;

/**
 * Swirl image pixels about the center.
 * Degrees defines the tightness of the swirl.
 * 
 * @author Stephan Laukien
 *
 */
public class Swirl extends CommandAbstract {
	private float gAngle;
	
	public Swirl() {
		super();
		gAngle=Integer.MIN_VALUE;
	}
	
	public void setAngle(float pAngle) {
		if(pAngle<0 || pAngle>360) throw new ParameterException("Swirl.setAngle: Invlaid Angle (0°<Angle>360°)");
		gAngle=pAngle;
	}
	
	public String getStatement() {
		if(gAngle==Integer.MIN_VALUE) return "";
		
		
		return "-swirl "+gAngle;
	}
}
