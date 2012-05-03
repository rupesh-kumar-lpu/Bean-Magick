package com.laukien.bean.magick;

import java.util.Vector;

public class Magick extends CommandAbstract {
	Vector gCommands;

	public Magick() {
		super();
		gCommands=new Vector();
		gResult=null;
		gResultString=null;
		gStatement=null;
	}
	
	public void add(CommandInterface pCommand) {
		gCommands.add(pCommand);
	}

	/**
	 * Sets an alternativ statement.
	 * (Only the "convert"-parameters)
	 * 
	 * @param pStatement
	 */
	public void setStatement(String pStatement) {
		gStatement=pStatement;
	}
	
	public String getStatement() {
		if(gStatement!=null) return gStatement;
		
		int length=gCommands.size();
		String cmd="";

		for(int i=0; i<length; i++) {
			cmd+=((CommandInterface)gCommands.get(i)).getStatement()+' ';
		}
		
		gStatement=cmd;
		return cmd;
	}
}
