package com.nl.base.iBatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;

import com.nl.util.GlobalConst;
import com.nl.base.utils.Log;
import com.nl.base.utils.SystemTool;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 *
 * 数据库模块实现类
 *
 * @author MaYan
 *
 */
class BaseModule extends Module
{
	
	public final String ROLE = this.getClass().getName();
	
	private static HashMap		SqlMapPool	= new HashMap();

	//protected Logger			log;
	protected Log			log;

	protected SqlMapClient		sqlMap;

	protected SqlMapClientProxy	sqlMapProxy;

	protected String			dataSourceJNDI;

	BaseModule(String dataSourceJNDI) throws IOException
	{
		//this.log = Logger.getLogger(this.getClass());
		this.log = SystemTool.getLogger(GlobalConst.LOG_SERVER_APP);
		this.dataSourceJNDI = dataSourceJNDI;
		this.initSqlMap();
	}// --

	/**
	 * <p>
	 * 重新载入iBATIS配置文件
	 * </p>
	 *
	 * @return
	 */
	@Override
	final SqlMapClient reloadSqlMapConfig()
	{
		this.sqlMap = null;
		Properties p = new Properties();
		p.setProperty("DataSourceJNDI", this.dataSourceJNDI);
		log.debug("0000000........"+ this.dataSourceJNDI);
		try
		{
			log.info("prepare Reader SqlMapConfig.xml.........");
			Reader configFileReader = Resources
					.getResourceAsReader("com/nl/config/iBatis/SqlMapConfig.xml");
			log.info("1 prepare SqlMapClientBuilder buildSqlMapClient.xml.........");
			this.sqlMap = SqlMapClientBuilder.buildSqlMapClient(
					configFileReader, p);
//			this.sqlMap = SqlMapClientBuilder.buildSqlMapClient(
//							configFileReader);
			log.info("end SqlMapClientBuilder buildSqlMapClient.xml.........");
		}
		catch (IOException e)
		{
			log.error("载入iBATIS配置文件失败\n\t" + e.getMessage());
			this.sqlMap = null;
		}
		catch (Exception e)
		{
			log.error("111载入iBATIS配置文件异常\n\t" + e.getMessage());
			this.sqlMap = null;
		}

		this.sqlMapProxy = new SqlMapClientProxy(this);
		BaseModule.SqlMapPool.put(this.dataSourceJNDI, this.sqlMap);
		//log.debug(sqlMap);
		return this.sqlMap;
	}

	synchronized final private void initSqlMap()// throws IOException
	{
		Object sqlMapInHash = BaseModule.SqlMapPool.get(this.dataSourceJNDI);
		if ((sqlMapInHash != null) && (sqlMapInHash instanceof SqlMapClient))
		{
			this.sqlMap = (SqlMapClient) BaseModule.SqlMapPool
					.get(this.dataSourceJNDI);
			this.sqlMapProxy = new SqlMapClientProxy(this);
		}
		else
		{
			log.info("reloadSqlMapConfig.........");
			this.reloadSqlMapConfig();
		}
	}// --

	/**
	 * 获得标准iBATIS的SqlMapClient【未经SqlMapClientProxy包装】
	 *
	 * @return
	 */
	@Override
	SqlMapClient getSqlMapClient()
	{
		if (this.sqlMap == null) this.reloadSqlMapConfig();
		return this.sqlMap;
	}

	public SqlMapClientProxy getSqlMapClientProxy()
	{
		if (this.sqlMapProxy == null && this.sqlMap != null) this.sqlMapProxy = new SqlMapClientProxy(
				this);
		else this.reloadSqlMapConfig();
		return this.sqlMapProxy;
	}

}
