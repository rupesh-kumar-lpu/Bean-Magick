package com.laukien.bean.magick;

/**
 * Measure differences between images with this metric.
 * 
 * Choose from
 * AE (absolute),
 * MAE (mean absolute),
 * MSE (mean squared),
 * PSE (peak absolute),
 * PSNR (peak signal to noise ratio), or
 * RMSE (root mean squared).
 * 
 * @author Stephan Laukien
 *
 */
public class Metric extends CommandAbstract {
	public static final int NONE = -1;
	public static final int AE = 1;
	public static final int MAE = 2;
	public static final int MSE = 3;
	public static final int PSE = 4;
	public static final int PSNR = 5;
	public static final int RMSE = 6;
	
	private int gType;

	public Metric() {
		super();
		gType=NONE;
	}
	
	public void setType(int pType) {
		if(gType<AE || gType>RMSE) gType=NONE;
		else gType=pType;
	}
	
	public String getStatement() {
		if(gType==NONE) return "";
		String result;
		
		switch(gType) {
		case AE:		result="AE"; break;
		case MAE:		result="MAE"; break;
		case MSE:		result="MSE"; break;
		case PSE:		result="PSE"; break;
		case PSNR:		result="PSNR"; break;
		case RMSE:		result="RMSE"; break;
		default: return "";
		}
		return "-metric "+result;
	}

}
