package com.nl.base.iBatis;

/**
 *
 * 数据库模块公开接口类
 *
 * @author MaYan
 *
 */
interface ModuleHandle extends java.io.Serializable
{
	public final static int	CONN_NORMAL	= 0;	// 普通连接

	public final static int	CONN_TX		= 1;	// 支持分布式事务处理的连接

	public final static int	CONN_XA		= 2;	// 支持分布式事务处理（支持跨数据库操作）的连接

	/**
	 * 获取标准iBATIS SqlMapClient 的Proxy模式实现接口
	 *
	 * @return
	 */
	public SqlMapClientProxy getSqlMapClientProxy();
}
