package com.nl.base.utils;

import org.apache.log4j.Logger;


/**
 *
 * @author sanjing
 * @creatdate Sep 2, 2011
 */
public class Log{
	
	private  Logger logger = null;
	protected String name = null;
	private String message = null;
//	private static Log log = null;
//	private static Log log2 = null;
//	private static String logName = null;
	
	public Log (String name){
//		this.name = name;
//		this.logName = name;
		this.message = null;
		this.logger = Logger.getLogger(name);
	}
	
	public Log (String name,String message){
//		this.logName = name;
		this.message = message;
		this.logger = Logger.getLogger(name); 
	}
	
	public static Log getLogger(String name) {
//		System.out.println("logName = "+logName +";name = "+name);
//		if(log == null||!logName.equals(name))
//			log = new Log(name);
//		return log;
		return new Log(name);
	}
	
	public static Log getLogger(String name,String message){
//		if(log2 == null||!logName.equals(name))
//			log2 = new Log(name,message);
//		return log2;
		return new Log(name,message);
	}
	
	public void info(String message){
		this.logger.info((this.message == null?"":this.message+"::")+message);
	}
	
	public void debug(String message){
		this.logger.debug((this.message == null?"":this.message+"::")+message);
	}
	
	public void error(String message){
		this.logger.error((this.message == null?"":this.message+"::")+message);
	}
	
}
