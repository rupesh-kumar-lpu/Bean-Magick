package com.laukien.bean.magick;

/**
 * Detect edges within an image.
 * 
 * @author Stephan Laukien
 *
 */
public class Edge extends CommandAbstract {
	private int gValue;

	public Edge() {
		super();
		gValue=Integer.MIN_VALUE;
	}

	public void Radius(int pValue) {
		gValue=pValue;
	}
	
	public String getStatement() {
		if(gValue==Integer.MIN_VALUE ) return "";
		return "-edge "+gValue;
	}

}
