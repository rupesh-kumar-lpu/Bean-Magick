package com.laukien.bean.magick;

public class Filter extends CommandAbstract {
	public static final int NONE = -1;
	public static final int CUBIC = 1;
	public static final int QUADRATIC = 2;
	public static final int GAUSSIAN = 3;
	public static final int BESSEL = 4;
	public static final int POINT = 5;
	public static final int BOX = 6;
	public static final int TRIANGLE = 7;
	public static final int HERMITE = 8;
	public static final int HANNING = 9;
	public static final int HAMMING = 10;
	public static final int BLACKMAN = 11;
	public static final int CATROOM = 12;
	public static final int MITCHELL = 13;
	public static final int LANCZOS = 14;
	public static final int SINC = 15;
	
	private int gType;

	public Filter() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(gType<CUBIC || gType>SINC) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		String result;
		
		switch(gType) {
		case CUBIC:		result="Cubic"; break;
		case QUADRATIC:	result="Quadratic"; break;
		case GAUSSIAN:	result="Gaussian"; break;
		case BESSEL:	result="Bessel"; break;
		case POINT:		result="Point"; break;
		case BOX:		result="Box"; break;
		case TRIANGLE:	result="Triangle"; break;
		case HERMITE:	result="Hermite"; break;
		case HANNING:	result="Hanning"; break;
		case HAMMING:	result="Hamming"; break;
		case BLACKMAN:	result="Blackman"; break;
		case CATROOM:	result="Catroom"; break;
		case MITCHELL:	result="Mitchell"; break;
		case LANCZOS:	result="Lanczos"; break;
		case SINC:		result="Sinc"; break;
		default: return "";
		}
		return "-filter "+result;
	}

}
