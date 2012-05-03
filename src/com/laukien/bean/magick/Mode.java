package com.laukien.bean.magick;

/**
 * Mode of operation.
 * 
 * Choose from these styles: Frame, Unframe, or Concatenate
 * 
 * @author Stephan Laukien
 *
 */
public class Mode extends CommandAbstract {
	public static final int NONE = -1;
	public static final int FRAME = 1;
	public static final int UNFRAME = 2;
	public static final int CONCATENATE = 3;
	
	private int gType;

	public Mode() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(gType<FRAME || gType>CONCATENATE) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		String result;
		
		switch(gType) {
		case FRAME:			result="Frame"; break;
		case UNFRAME:		result="Unframe"; break;
		case CONCATENATE:	result="Concatenate"; break;
		default: return "";
		}
		return "-metric "+result;
	}

}
