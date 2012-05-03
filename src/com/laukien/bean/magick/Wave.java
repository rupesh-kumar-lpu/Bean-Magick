package com.laukien.bean.magick;

/**
 * Alter an image along a sine wave.
 * 
 * Specify amplitude and wavelength of the wave.
 * 
 * @author Stephan Laukien
 *
 */
public class Wave extends CommandAbstract {

	private int gAmplitude;
	private int gWavelength;

	public Wave() {
		super();
		gAmplitude=-1;
		gWavelength=-1;
	}
	
	public void setAmplitude(int pAmplitude) {
		gAmplitude=pAmplitude;
	}
	
	public void setWavelength(int pWaveLength) {
		gWavelength=pWaveLength;
	}

	public String getStatement() {
		if(getParameter()==null) return "";
		else return "-wave "+getParameter();
	}

	private String getParameter() {
		if(gAmplitude==-1 && gWavelength==-1) return null;
		String result="x";
		if(gAmplitude>0) result=gAmplitude+result;
		if(gWavelength>0) result+=gWavelength;
		
		return result;
	}
}
