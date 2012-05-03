package com.laukien.bean.magick;

import java.io.File;

import com.laukien.exception.ExecutionException;
import com.laukien.exception.ParameterException;
import com.laukien.execute.External;

public abstract class CommandAbstract implements CommandInterface {

	protected Process gResult;
	protected String gResultString;
	protected File gInputFile;
	protected File gOutputFile;
	protected String gStatement;
	private External gExec;
	private int gImageIndex;

	protected CommandAbstract() {
		gResult=null;
		gResultString=null;
		gInputFile=null;
		gOutputFile=null;
		gStatement=null;
		gImageIndex=-1;
	}
	
	public String getResult() {
		if(gExec==null) return null;
		return (gExec.isError() ? gExec.getErrorString() : gExec.getOutputString());
	}
	
	public Image getImage() {
		if(gExec==null && gInputFile!=null) {
			gExec=new External();
			gExec.setTimeout(Config.getTimeout());
			try {
				gExec.execute(Config.getIdentify()+ ' '+gInputFile.getAbsolutePath());
			} catch (ExecutionException e) {
				gResultString="CommandAbstract.execute: Unable to run \"Identify\"\n"+e+'\n'+gExec.getErrorString();
			}
		}
		
		if(gExec==null || gExec.isError()) return null;
		String output=getResult()+' ';	//search for ' ' to find the end
		Image image=new Image();
		String str;
		int idxStart, idxEnd;
		
		//size
		try {
			idxStart=output.lastIndexOf("-bit ");
			if(idxStart==-1) throw new Exception();
			
			idxEnd=output.indexOf(' ',idxStart+5);
			str=output.substring(idxStart+5,idxEnd);
			if(str.indexOf('b')==-1) {
				image.setSize((int)(Float.parseFloat(str)));
			} else {
				float size=Float.parseFloat(str.substring(0,str.length()-2));
				if(str.indexOf('k')!=-1) image.setSize((int)(size*1024));
				else if(str.indexOf('m')!=-1) image.setSize((int)(size*1024*1024)); 
				else if(str.indexOf('g')!=-1) image.setSize((int)(size*1024*1024*1024));
				else image.setSize(-1);
			}
		} catch(Exception e) {
			idxStart=-1;
			image.setSize((int)gOutputFile.length());
		}
		
		//depth
		//check if the ImageMagick-Version is up-to-date (no bit/size information)
		if(idxStart!=-1) {
			idxEnd=idxStart;
			idxStart=output.lastIndexOf(' ',idxEnd);
			try {
				image.setDepth(Integer.parseInt(output.substring(idxStart+1,idxEnd)));
			} catch(Exception e) {
				//return null;
				//do nothing
			}
		}
		
		//geometry
		try {
			idxEnd=output.lastIndexOf('x');
			idxStart=output.lastIndexOf(' ',idxEnd)+1;
			image.setWidth(Integer.parseInt(output.substring(idxStart,idxEnd)));

			idxStart=idxEnd+1;
			idxEnd=output.indexOf('+',idxStart);
			if(idxEnd!=-1) {
				image.setHeight(Integer.parseInt(output.substring(idxStart,idxEnd)));

				idxStart=idxEnd+1;
				idxEnd=output.indexOf('+',idxStart);
				image.setLeft(Integer.parseInt(output.substring(idxStart,idxEnd)));

				idxStart=idxEnd+1;
				idxEnd=output.indexOf(' ',idxStart);
				image.setTop(Integer.parseInt(output.substring(idxStart,idxEnd)));
			} else {
				//old ImageMagick-Version
				idxEnd=output.indexOf(' ',idxStart);
				image.setHeight(Integer.parseInt(output.substring(idxStart,idxEnd)));

				image.setLeft(0);
				image.setTop(0);
			}
		} catch (Exception e) {
			//return null;
			//do nothing
		}
		
		//File/Name/Extension
		image.setFile(gOutputFile);

		return image;
	}

	public String getRuntimeCommand() {
		//gStatement is always != NULL
		if(gInputFile==null && gOutputFile==null) throw new ParameterException("CommandAbstract.getRuntimeCommand: InputFile/OutputFile not set");
		if(gOutputFile==null) gOutputFile=new File(gInputFile.getAbsolutePath());
		
		//convert InputFile[Index] Statement OutputFile
		return Config.getConvert()+' '+(gInputFile!=null ? gInputFile.getAbsolutePath()+(gImageIndex>-1 ? "["+gImageIndex+"]" : "") : "")+' '+getStatement()+' '+gOutputFile.getAbsolutePath();
	}
	
	public void setImageIndex(int pImageIndex) {
		gImageIndex=pImageIndex;
	}
	
	public void execute() {
		String command=getRuntimeCommand();
		gExec=new External();
		gExec.setTimeout(Config.getTimeout());
		try {
			gExec.execute(command);
		} catch (ExecutionException e) {
			gResultString="CommandAbstract.execute: Unable to run \""+command+"\"\n"+e;
		}
	}
	
//	protected void execute(String pCommand) throws ExecuteException {
//		try {
//			gResult=Runtime.getRuntime().exec(pCommand);
//		} catch(IOException e) {
//			gResult=null;
//			gResultString=null;
//			throw new ExecuteException("CommandAbstract.execute: Unable to execute \""+pCommand+"\"");
//		}
//	}
//	
//	public void execute() {
//		String command=getRuntimeCommand();
//		try {
//			execute(command);
//			if(gResult.waitFor()!=0) throw new RuntimeException("CommandAbstract.execute: External application was terminated abnormaly ("+command+"=>"+gResult.exitValue()+").");
//		} catch (Exception e) {
//			gError=e;
//			if(gResult!=null) {
//				StringBuffer sb=new StringBuffer();
//				byte[] buffer=new byte[128];
//				int count;
//				try {
//					while((count=gResult.getErrorStream().read(buffer,0,128))!=-1) {
//						sb.append(new String(buffer,0,count));
//					}
//					gResultString=sb.toString();
//				} catch (IOException e1) {
//					gResultString=e1.toString();
//				}
//			}
//		}
//	}

	public void setInputFile(File pInput) {
		gInputFile=pInput;
	}

	public void setOutputFile(File pOutput) {
		gOutputFile=pOutput;
	}

	/**
	 * Retruns if the execution-prcess was successfully or not. If no command was executed the result will be <code>false</code>.
	 * 
	 * @return <code>true</code> if the execution was NOT error-free.
	 */
	public boolean isError() {
		return gExec==null ? false : gExec.isError();
	}
}
