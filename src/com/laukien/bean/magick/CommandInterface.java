package com.laukien.bean.magick;

import java.io.File;

public interface CommandInterface {
	/**
	 * Returns the statment which is the result of the filter or convert-buildment.
	 * 
	 * @return result as String
	 */
	public String getStatement();
	
	/**
	 * Returns the output of the application.
	 * This is the answer from the external executable.
	 * 
	 * @return result as String
	 */
	public String getResult();
	
	/**
	 * Source-File to convert.
	 * 
	 * @param pInput inputfile
	 */
	public void setInputFile(File pInput);
	
	/**
	 * Destination-File which is the result of the Magick-call.
	 * 
	 * @param pOutput outputfile
	 */
	public void setOutputFile(File pOutput);
	
	/**
	 * In the case of more layers or/and a multiple output, the target layer/part could be choosen by the index.
	 * ("0" is the first image.)
	 *
	 *@param pImageIndex index of the image
	 */
	public void setImageIndex(int pImageIndex);
	
	/**
	 * Action-Command which starts the convert-process.
	 */
	public void execute();
}
