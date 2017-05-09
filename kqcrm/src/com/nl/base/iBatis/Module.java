package com.nl.base.iBatis;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * 数据库模块抽象类，提供了包内访问需要的公共方法
 * @author MaYan
 *
 */
abstract public class Module implements ModuleHandle
{
	/**
	 * <p>
	 * 重新载入iBATIS配置文件
	 * </p>
	 *
	 * @return
	 */
	abstract SqlMapClient reloadSqlMapConfig();

	/**
	 * 获得标准iBATIS的SqlMapClient【未经SqlMapClientProxy包装】
	 *
	 * @return
	 */
	abstract SqlMapClient getSqlMapClient();
}
