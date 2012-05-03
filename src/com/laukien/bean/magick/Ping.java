package com.laukien.bean.magick;

/**
 * Efficiently determine image characteristics.
 * 
 * @author Stephan Laukien
 *
 */
public class Ping extends CommandAbstract {

	public Ping() {
		super();
	}
	
	public String getStatement() {
		return "-ping";
	}

}
