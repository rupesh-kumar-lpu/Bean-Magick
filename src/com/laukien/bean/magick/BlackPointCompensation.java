package com.laukien.bean.magick;

/**
 * Use black point compensation.
 * 
 * @author Stephan Laukien
 *
 */
public class BlackPointCompensation extends CommandAbstract {

	public BlackPointCompensation() {
		super();
	}
	public String getStatement() {
		return "-black-point-compensation";
	}

}
