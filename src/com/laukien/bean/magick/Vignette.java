package com.laukien.bean.magick;

/**
 * Soften the edges of the image in vignette style.
 *  
 * @author Stephan Laukien
 *
 */
public class Vignette extends CommandAbstract {
	private float gRadius;
	private float gSigma;
	private int gLeft;
	private int gTop;
	private float gValue;
	private boolean gIsPercent;

	public Vignette() {
		super();
		gRadius=-1;
		gSigma=-1;
		gLeft=gTop=Integer.MIN_VALUE;
		gValue=Float.MIN_VALUE;
		gIsPercent=false;
	}
	
	public void setOffset(int pLeft, int pTop) {
		gLeft=pLeft;
		gTop=pTop;
	}
	
	public void setRadius(float pRadius) {
		gRadius=pRadius;
	}
	
	public void setSigma(float pSigma) {
		gSigma=pSigma;
	}
	
	public void setLeft(int pLeft) {
		gLeft=pLeft;
	}
	
	public void setTop(int pTop) {
		gTop=pTop;
	}
	
	public void setPercentage(float pValue) {
		gIsPercent=true;
		gValue=pValue;
	}
	
	public void setPercent(boolean pIsPercent) {
		gIsPercent=pIsPercent;
	}
	
	public String getStatement() {
		String param=getParameter();
		if(param==null) return "";
		return "-vignette "+param;
	}

	private String getParameter() {
		if(gIsPercent && gValue!=Integer.MIN_VALUE) return gValue+"%";
		
		if(gRadius<=0 && gSigma<=0 && gLeft==0 && gTop==0) return null;
		String result="x";
		if(gRadius>0) result=gRadius+(gIsPercent ? "%" : "")+result;
		if(gSigma>0) result+=gSigma+(gIsPercent ? "%" : "");
		
		return result+getOffsetAsString();
	}
	
	private String getOffsetAsString() {
		if(gLeft==0 && gTop==0) return "";
		return (gLeft>0 ? "+" : "")+gLeft+(gTop>0 ? "+" : "")+gTop;
	}
}
