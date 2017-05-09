/**
 *
 */
package com.nl.base.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.nl.util.GlobalConst;
import com.nl.base.BaseAppAction;
import com.nl.base.iBatis.Module;
import com.nl.base.iBatis.ModuleFactory;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 *
 * @author sanjing}
 * @creatdate 2008-4-14
 */
public class IbatisTools {
	public static final String ROLE = BaseAppAction.class.getName();
	static Logger logger = Logger.getLogger(GlobalConst.LOG_SERVER_APP);
	private static SqlMapClient smc;
	static{
		try{
			ModuleFactory factory = new ModuleFactory(GlobalConst.APP_JNDI);
			Module module = factory.CreateModule();
			smc = module.getSqlMapClientProxy();
		}catch(IOException e){
			logger.error(e.getMessage());
		}
	}
	public static SqlMapClient getSqlMapClientProxy(){
		return smc;
	}
	
	public static SqlMapClient getDynamicSqlMapClient(SqlMapClient smc,String connFlag){
		
		ModuleFactory factory = new ModuleFactory(connFlag);
		try {
			Module module = factory.CreateModule();
			smc = module.getSqlMapClientProxy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                
        return smc;
	}
}
