package com.laukien.bean.magick;

/**
 * Add or reduce noise in an image.
 * 
 * The principal function of noise peak elimination filter is to smooth the objects
 * within an image without losing edge information and without creating undesired structures.
 * The central idea of the algorithm is to replace a pixel with its next neighbor in value
 * within a pixel window, if this pixel has been found to be noise.
 * A pixel is defined as noise
 * if and only if this pixel is a maximum or minimum within the pixel window.
 * Use radius to specify the width of the neighborhood.
 * 
 * Use "+noise" followed by a noise type to add noise to an image.
 *  
 * @author Stephan Laukien
 *
 */
public class Noise extends CommandAbstract {
	/**
	 * No compression.
	 */
	public static final int NONE=-1;
	public static final int UNIFORM=1;
	public static final int GAUSSIAN=2;
	public static final int MULTIPLICATIVE=3;
	public static final int IMPULSE=4;
	public static final int LAPLACIAN=5;
	public static final int POISSON=6;
	
	private int gType;
	private float gRadius;
	
	public Noise() {
		super();
		gType=NONE;
		gRadius=-1;
	}
	
	public void setType(int pType) {
		if(pType<UNIFORM || pType>POISSON) gType=NONE;
		else gType=pType;
		gRadius=-1;
	}
	
	public void setRadius(float pRadius) {
		gRadius=pRadius;
		gType=NONE;
	}
	
	public String getStatement() {
		if(gType==NONE && gRadius==-1) return "";
		
		if(gType==NONE) return "-noise "+gRadius;
		else return "+noise "+getTypeAsString();
	}

	private String getTypeAsString() {
		switch(gType) {
		case UNIFORM:			return "Uniform";
		case GAUSSIAN:			return "Gaussian";
		case MULTIPLICATIVE:	return "Multiplcative";
		case IMPULSE:			return "Impulse";
		case LAPLACIAN:			return "Laplacian";
		case POISSON:			return "Poisson";
		default: return null;
		}
	}

}
