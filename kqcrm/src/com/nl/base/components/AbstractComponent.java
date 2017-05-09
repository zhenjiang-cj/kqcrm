/**
 *
 */
package com.nl.base.components;

import com.nl.base.utils.Log;
import com.nl.base.utils.SystemTool;

/**
 * 本类为系统内与数据库相关处理的组件提供一个公共的接入口,为所有子类提供数据库连接
 * @author sanjing
 * @creatdate 2008-4-21
 */
public abstract class AbstractComponent {
//	private Logger logger = null ;
//	protected Logger getLogger() {
//		if(logger == null)
//			logger = SystemTool.getLoggerForWebApp();
//		return logger ;
//	}
	
	private Log logger = null ;
	protected Log getLogger() {
		//if(logger == null)
			logger = SystemTool.getLoggerForWebApp();
		return logger ;
	}
	
	protected Log getLogger(String bossCode,String name) {
		//if(logger == null)
			logger = SystemTool.getLoggerForWebApp(bossCode,name);
		return logger ;
	}
}
