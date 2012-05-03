package com.laukien.bean.magick;

/**
 * Monitor progress.
 * 
 * @author Stephan Laukien
 *
 */
public class Monitor extends CommandAbstract {

	public Monitor() {
		super();
	}
	
	public String getStatement() {
		return "-monitor";
	}

}
