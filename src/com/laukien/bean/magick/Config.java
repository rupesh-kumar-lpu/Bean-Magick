package com.laukien.bean.magick;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static final int TIMEOUT=60;
	private static final String CONVERT="convert";
	private static final String IDENTIFY="identify";

	private static int gsTimeout;
	private static String gsConvert;
	private static String gsIdentify;

	static {
		init();
	}
	
	private synchronized static void init() {
		synchronized(Config.class) {
			gsTimeout=TIMEOUT;
			gsConvert=CONVERT;
			gsIdentify=IDENTIFY;			
		}
	}
	
	/**
	 * Reloads this class ant "Identify" too.
	 *
	 */
	public static void reload() {
		init();
		Identify.reload();
	}
	
	/**
	 * Loads an alternativ configuration, reloads all parameters and reloads "Indetify".
	 * 
	 * @param pFile propertie-file with a set of parameters
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public synchronized static void load(File pFile) throws FileNotFoundException, IOException {
		Properties prop=new Properties();
		prop.load(new FileInputStream(pFile));
		
		int timeout;
		String convert, identify;
		
		try {
			timeout=Integer.parseInt(prop.getProperty("timeout"));
		} catch(Exception e) {
			timeout=TIMEOUT;
		}
		
		convert=prop.getProperty("convert");
		identify=prop.getProperty("identify");
		
		synchronized(Config.class) {
			gsTimeout=timeout;
			gsConvert=convert==null ? CONVERT : convert;
			gsIdentify=identify==null ? IDENTIFY : identify;
		}
		Identify.reload();
	}
	
	public static int getTimeout() {
		return gsTimeout;
	}

	public static String getConvert() {
		return gsConvert+" -verbose";
	}

	public static String getIdentify() {
		return gsIdentify;
	}
}
