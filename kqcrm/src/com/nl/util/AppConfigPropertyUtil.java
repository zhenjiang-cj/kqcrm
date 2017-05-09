package com.nl.util;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

 

public class AppConfigPropertyUtil {
	final private static Logger LOG = Logger.getLogger(AppConfigPropertyUtil.class);
	
	public static String getMsg(String filename,String key) throws Exception
	{
		 String returnStr=""; 
		 
		 MessageResourcesFactory factory = MessageResourcesFactory.createFactory();
	     MessageResources resources = factory.createResources(filename);//application.properties
	     returnStr=resources.getMessage(key);
		 if (returnStr==null)
		 {
			LOG.error("AppConfigPropertyUtil::getMsg::" + key + "::文件缺少对应配置");
			throw new Exception("AppConfigPropertyUtil::getMsg::" + key + "::文件缺少对应配置");
		 }
		 
		 return returnStr;
	}

}
