package com.laukien.bean.magick;

import java.io.File;

public class Image {
	private int gWidth;
	private int gHeight;
	private int gLeft;
	private int gTop;
	private int gSize;
	private File gFile;
	private String gName;
	private String gExtension;
	private int gDepth;
	
	public Image() {
		gWidth=gHeight=gLeft=gTop-1;
		gSize=-1;
		gFile=null;
		gName=null;
		gExtension=null;
		gDepth=-1;
	}
	
	protected void setSize(int pSize) {
		gSize=pSize;
	}

	public int getSize() {
		return gSize;
	}

	protected void setDepth(int pDepth) {
		gDepth=pDepth;
	}
	
	public int getDepth() {
		return gDepth;
	}

	protected void setWidth(int pWidth) {
		gWidth=pWidth;
	}
	
	public int getWidth() {
		return gWidth;
	}

	protected void setHeight(int pHeight) {
		gHeight=pHeight;
	}
	
	public int getHeight() {
		return gHeight;
	}

	protected void setLeft(int pLeft) {
		gLeft=pLeft;
	}
	
	public int getLeft() {
		return gLeft;
	}

	protected void setTop(int pTop) {
		gTop=pTop;
	}
	
	public int getTop() {
		return gTop;
	}
	
	protected void setFile(File pFile) {
		gFile=pFile;
		if(pFile==null) {
			gName=null;
			gExtension=null;
		} else {
			gName=pFile.getName();
			int idx=gName.lastIndexOf('.');
			if(idx==-1) gExtension=null;
			else {
				gExtension=gName.substring(idx+1).toLowerCase();
				gName=gName.substring(0,idx);
			}
		}
	}
	
	public File getFile() {
		return gFile;
	}
	
	public String getName() {
		return gName;
	}
	
	public String getExtension() {
		return gExtension;
	}
}
