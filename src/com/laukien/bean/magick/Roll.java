package com.laukien.bean.magick;


/**
 * Roll an image vertically or horizontally.
 * 
 * See "-resize" for details the geometry specification.
 * The x and y offsets are not affected by the "-gravity" option.
 * 
 * A negative x offset rolls the image left-to-right.
 * A negative y offset rolls the image top-to-bottom.
 *  
 * @author Stephan Laukien
 *
 */
public class Roll extends CommandAbstract {
	private int gLeft;
	private int gTop;

	public Roll() {
		super();
		gLeft=gTop=Integer.MIN_VALUE;
	}

	public void setOffset(int pLeft, int pTop) {
		gLeft=pLeft;
		gTop=pTop;
	}
	
	public void setLeft(int pLeft) {
		gLeft=pLeft;
	}
	
	public void setTop(int pTop) {
		gTop=pTop;
	}
	
	public String getStatement() {
		String param=getParameter();
		if(param==null) return "";
		return "-roll "+param;
	}

	private String getParameter() {
		if(gLeft==0 && gTop==0) return null;
		else return (gLeft>0 ? "+" : "")+gLeft+(gTop>0 ? "+" : "")+gTop;
	}
}
