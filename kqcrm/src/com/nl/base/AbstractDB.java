/**
 *
 */
package com.nl.base;

import java.sql.SQLException;

import com.nl.base.components.AbstractComponent;
import com.nl.base.utils.SystemTool;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author sanjing
 * @creatdate 2008-4-21
 */
public abstract class AbstractDB extends AbstractComponent{
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected SqlMapClient getSqlMapClient() throws Exception {
		SqlMapClient smc = SystemTool.getSqlMapClient();
		if (smc == null) {
			getLogger().debug("取得IBATIS,SqlMapClient出错。");
			throw new Exception("取得IBATIS,SqlMapClient出错。");
		}
		return smc;
	}
	
	/**
	 * 关闭事物
	 * @param smc 使用的参数
	 */
	public void endTransaction(SqlMapClient smc){
		try {
			smc.endTransaction();
		} catch (SQLException e) {
			getLogger().error(e.getMessage());
		}
	} 

}
