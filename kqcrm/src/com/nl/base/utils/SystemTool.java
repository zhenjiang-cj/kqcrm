/**
 * 
 */
package com.nl.base.utils;
import com.nl.util.GlobalConst;
import com.nl.base.utils.Log;

import com.ibatis.sqlmap.client.SqlMapClient;
/**
*
* @author sanjing}
* @creatdate 2008-4-14
*/
public class SystemTool {
	
//	public static Logger getLogger(String name) {
//		return Logger.getLogger(name);
//	}
	
	public static Log getLogger(String name){
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		int ix = 0;
//		int flag = 0;
        String cname = null;
        String cmethod = null;
        String logMessage = null;
        while (ix < stack.length) {
            StackTraceElement frame = stack[ix];
            cname = frame.getClassName();
            if(cname.equals("com.nl.base.BaseAppAction")){
            	frame = stack[ix+1];
            	cname = frame.getClassName();
                break;
            }
            if(cname.equals("com.nl.base.components.AbstractComponent")){
            	frame = stack[ix+1];
            	cname = frame.getClassName();          	
            	break;
            }

            ix++;
        }
        return Log.getLogger(cname+"::"+name);
		//return Log.getLogger(name);
	}
	/**
	 * 
	 * @param bossCode
	 * @param name
	 * @param message
	 * @return
	 */
//	public static Logger getLogger(String bossCode,String name) {
//		StackTraceElement stack[] = (new Throwable()).getStackTrace();
//		int ix = 0;
//		int flag = 0;
//        String cname = null;
//        String cmethod = null;
//        String logMessage = null;
//        while (ix < stack.length) {
//            StackTraceElement frame = stack[ix];
//            cname = frame.getClassName();
//            cmethod = frame.getMethodName();
//            if(cname.equals("base.BaseAppAction")){
//            	flag = ix;
//            }
//            System.out.println("+++++++++cname = "+cname);
//            System.out.println("+++++++++cmethod = "+cmethod);
//            if((flag+1) == ix){
//            	logMessage = GlobalConst.LOG_WEB_APP+" - " + bossCode+"::"+cname+"::"+cmethod+"(ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse)->"+name;
//            	break;
//            	
//            }
//            ix++;
//        }
//		return Logger.getLogger(logMessage);
//		
//	}
	
	public static Log getLogger(String bossCode,String name) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		int ix = 0;
//		int flag = 0;
        String cname = null;
        String cmethod = null;
        String logMessage = null;
        while (ix < stack.length) {
            StackTraceElement frame = stack[ix];
            cname = frame.getClassName();
            cmethod = frame.getMethodName();
//            System.out.println("=========cname = "+cname);
//            System.out.println("=========cmethod = "+cmethod);
            if(cname.equals("com.nl.base.BaseAppAction")){
            	frame = stack[ix+1];
            	cname = frame.getClassName();
                cmethod = frame.getMethodName();
            	logMessage = bossCode+"::"+cname+"::"+cmethod+"(ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse)->"+name;
            	
                break;
            }
            if(cname.equals("com.nl.base.components.AbstractComponent")){
            	frame = stack[ix+1];
            	cname = frame.getClassName();
                cmethod = frame.getMethodName();
            	logMessage = bossCode+"::"+cname+"::"+cmethod+"()->"+name;
            	
            	break;
            }
//            System.out.println("+++++++++cname = "+cname);
//            System.out.println("+++++++++cmethod = "+cmethod);
//            if((flag+1) == ix){
//            	//logMessage = GlobalConst.LOG_WEB_APP+" - " + bossCode+"::"+cname+"::"+cmethod+"(ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse)->"+name;
//            	logMessage = bossCode+"::"+cname+"::"+cmethod+"(ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse)->"+name;
//            	break;
//            	
//            }
            ix++;
        }
//        System.out.println("++++++++++++name = "+name);
//        System.out.println("++++++++++++logMessage = "+logMessage);
		return Log.getLogger(cname+"::"+GlobalConst.LOG_WEB_APP,logMessage);
		
	}
	
	/**
	 * 
	 * @return
	 */
//	public static Logger getLoggerForWebApp() {
//		return getLogger(GlobalConst.LOG_WEB_APP);
//	}
	
	public static Log getLoggerForWebApp(){
		return getLogger(GlobalConst.LOG_WEB_APP);
		
	}
	
	public static Log getLoggerForSerApp(){
		return getLogger(GlobalConst.LOG_SERVER_APP);
		
	}
	
	/**
	 * 
	 * @return
	 */
//	public static Logger getLoggerForWebApp(String bossCode,String name) {
//		return getLogger(bossCode,name);
//	}
	
	public static Log getLoggerForWebApp(String bossCode,String name) {
		return getLogger(bossCode,name);
	}
	
	public static Log getLoggerForWebApp(String bossCode) {
		return getLogger(bossCode,GlobalConst.LOG_WEB_APP);
	}
	
	/**
	 * 
	 * @return
	 */
	public static SqlMapClient getSqlMapClient(){
		return IbatisTools.getSqlMapClientProxy();
	}
}
