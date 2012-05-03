package com.laukien.bean.magick;

import java.io.File;

/**
 * The font.
 * 
 * @author Stephan Laukien
 *
 */
public class Font extends CommandAbstract {
	private String gName;
	private File gFile;

	public Font() {
		super();
		gName=null;
		gFile=null;
	}

	public void setType(Identify.Type pType) {
		gName=pType.getName();
	}
	
	public void setType(String pName) {
		gName=pName;
		gFile=null;
	}
	
	public void setType(File pFile) {
		gFile=pFile;
		gName=null;
	}
	
	public String getStatement() {
		if(gName==null && gFile==null) return "";
		if(gName!=null) return "-font "+gName;
		else return "-font @"+gFile.getAbsolutePath();	//@ for the file
	}

}
