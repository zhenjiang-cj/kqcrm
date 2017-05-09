package com.nl.base.iBatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.ibatis.sqlmap.client.event.RowHandler;

/**
 *
 * <p>
 * 用Proxy模式封装SqlMapClient，当发生数据库连接等底层异常时重构SqlMapClient
 *
 * 如果数据库底层错误，只要重新target 连接池，如果不采用这种代理模式否则需要重启应用程序
 * </p>
 *
 * <p>
 * RELOAD_ERR_GATE Oracle_SQL错误号，发生这些错误时自动重载入配置文件，请谨慎修改该属性 ;
 *
 *
 * ----------------------------------------------------------------------------
 * 例如，使用如下方式：
 *
 * <pre>
 * try
 * {
 * 	ModuleFactory factory = new ModuleFactory(&quot;testJNDI&quot;);
 * 	Module module = factory.CreateModule();
 * 	SqlMapClient smc = null;
 * 	try
 * 	{
 * 		module.getSqlMapClientProxy().startTransaction();
 * 		//...
 * 		module.getSqlMapClientProxy().commitTransaction();
 * 	}
 * 	finally
 * 	{
 * 		module.getSqlMapClientProxy().endTransaction();
 * 	}
 * }
 * catch (Exception e)
 * {
 * 	//...
 * }
 * </pre>
 *
 * 此时如果重构全局SqlMapClient 以上程序会连接泄漏，所以只有必要的错误才能重构【入连接错误,普通sql异常无需重新载入配置】
 *
 * 理由： 重构后 module.getSqlMapClientProxy() 获取的是新构造的 SqlMapClient,老的引用被覆盖了
 * </p>
 *
 * ----------------------------------------------------------------------------
 *
 *
 *
 * <p>
 * 所以推荐的写法如下【采用这种写法即使重构SqlMapClient也不会泄漏连接】：
 * </p>
 *
 * <pre>
 * try
 * {
 * 	ModuleFactory factory = new ModuleFactory(&quot;testJNDI&quot;);
 * 	Module module = factory.CreateModule();
 * 	SqlMapClient smc = module.getSqlMapClientProxy();
 * 	try
 * 	{
 * 		smc.startTransaction();
 * 		//...
 * 		smc.commitTransaction();
 * 	}
 * 	finally
 * 	{
 * 		smc.endTransaction();
 * 	}
 * }
 * catch (Exception e)
 * {
 * 	//...
 * }
 * </pre>
 *
 * ----------------------------------------------------------------------------
 *
 * @author MaYan
 *
 */
final class SqlMapClientProxy implements SqlMapClient
{
	final private Module		module;

	/**
	 * -------- 必需重新载入iBATIS配置的SQL异常编号 ---------------------
	 *
	 * ORA-00020: maximum number of processes  exceeded
	 *
	 * ORA-01004 default username feature not supported; logon denied
	 *
	 * ORA-01005 null password given; logon denied
	 *
	 * ORA-01012 not logged on
	 *
	 * ORA-01014 ORACLE shutdown in progress
	 *
	 * ORA-01017 invalid username/password; logon denied
	 *
	 * ORA-01033 ORACLE initialization or shutdown in progress
	 *
	 * ORA-01040 invalid character in password; logon denied
	 *
	 * ORA-01045 user string lacks CREATE SESSION privilege; logon denied
	 *
	 * ORA-01076 multiple logons per process not yet supported
	 *
	 *
	 * -------- 必需重新载入iBATIS配置的SQL异常编号 ---------------------
	 */
	final static private int[]	RELOAD_ERR_GATE	= {20, 1004, 1005, 1012, 1014,
			1017, 1033, 1040, 1045, 1076		};

	/**
	 *
	 * @param module
	 */
	SqlMapClientProxy(Module module)
	{
		this.module = module;
	}

	/**
	 * <p>
	 * 根据SQL错误号判断是否需要重新载入iBATIS配置
	 * </p>
	 *
	 * @param e
	 * @throws SQLException
	 */
	final private void errHandle(SQLException e) throws SQLException
	{
		int errCode = e.getErrorCode();
		for (int i = 0; i < RELOAD_ERR_GATE.length; i++)
		{
			if (errCode == RELOAD_ERR_GATE[i])
			{
				this.module.reloadSqlMapConfig();
				throw e;
			}
		}
		/* Modified By Lampard Lee,Jul 4th,2007 */
		/* 描述: 解决公共接口数据库操作出错没有抛出异常的问题 */
	    /* 修改开始 */
		/* 注释旧代码 */
		/* 增加新代码 */
		e.printStackTrace();
		throw e;
		/* 修改结束 */
	}

	/**
	 * Flushes all data caches.
	 */
	public void flushDataCache()
	{
		module.getSqlMapClient().flushDataCache();
	}

	/**
	 * Flushes the data cache that matches the cache model ID provided.
	 *
	 * @param cacheId
	 *            The cache model to flush
	 */
	public void flushDataCache(String cacheId)
	{
		this.module.getSqlMapClient().flushDataCache(cacheId);
	}

	/**
	 * TODO : Deprecated and will be removed.
	 *
	 * @return A session (DEPRECATED)
	 * @deprecated Use openSession() instead. THIS METHOD WILL BE REMOVED BEFORE
	 *             FINAL RELEASE.
	 */
	@Deprecated
	public SqlMapSession getSession()
	{
		return this.module.getSqlMapClient().getSession();
	}

	/**
	 * Returns a single threaded SqlMapSession implementation for use by one
	 * user. Remember though, that SqlMapClient itself is a thread safe
	 * SqlMapSession implementation, so you can also just work directly with it.
	 * If you do get a session explicitly using this method <b>be sure to close
	 * it!</b> You can close a session using the sqlMapSession.close() method.
	 * <p/>
	 *
	 * @return An SqlMapSession instance.
	 */
	public SqlMapSession openSession()
	{
		return this.module.getSqlMapClient().openSession();
	}

	/**
	 * Returns a single threaded SqlMapSession implementation for use by one
	 * user. Remember though, that SqlMapClient itself is a thread safe
	 * SqlMapSession implementation, so you can also just work directly with it.
	 * If you do get a session explicitly using this method <b>be sure to close
	 * it!</b> You can close a session using the SqlMapSession.close() method.
	 * <p/> This particular implementation takes a user provided connection as a
	 * parameter. This connection will be used for executing statements, and
	 * therefore overrides any configured datasources. Using this approach
	 * allows the developer to easily use an externally supplied connection for
	 * executing statements. <p/> <b>Important:</b> Using a user supplied
	 * connection basically sidesteps the datasource so you are responsible for
	 * appropriately handling your connection lifecycle (i.e. closing). Here's a
	 * (very) simple example (throws SQLException):
	 *
	 * <pre>
	 * try
	 * {
	 * 	Connection connection = dataSource.getConnection();
	 * 	SqlMapSession session = sqlMap.openSession(connection);
	 * 	// do work
	 * 	connection.commit();
	 * }
	 * catch (SQLException e)
	 * {
	 * 	try
	 * 	{
	 * 		if (connection != null) commit.rollback();
	 * 	}
	 * 	catch (SQLException ignored)
	 * 	{
	 * 		// generally ignored
	 * 	}
	 * 	throw e; // rethrow the exception
	 * }
	 * finally
	 * {
	 * 	try
	 * 	{
	 * 		if (connection != null) connection.close();
	 * 	}
	 * 	catch (SQLException ignored)
	 * 	{
	 * 		// generally ignored
	 * 	}
	 * }
	 * </pre>
	 *
	 * @param conn -
	 *            the connection to use for the session
	 *
	 * @return An SqlMapSession instance.
	 */
	public SqlMapSession openSession(Connection conn)
	{
		return this.module.getSqlMapClient().openSession(conn);
	}

	/**
	 * Executes a mapped SQL DELETE statement. Delete returns the number of rows
	 * effected. <p/> The parameter object is generally used to supply the input
	 * data for the WHERE clause parameter(s) of the DELETE statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @return The number of rows effected.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public int delete(String id, Object parameterObject) throws SQLException
	{
		int ret = 0;
		try
		{
			ret = this.module.getSqlMapClient().delete(id, parameterObject);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}

		return ret;
	}

	/**
	 * Executes (flushes) all statements currently batched.
	 *
	 * @return the number of rows updated in the batch
	 * @throws java.sql.SQLException
	 *             If the batch could not be executed or if any of the
	 *             statements fails.
	 */
	public int executeBatch() throws SQLException
	{
		int ret = 0;
		try
		{
			ret = this.module.getSqlMapClient().executeBatch();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL INSERT statement. Insert is a bit different from
	 * other update methods, as it provides facilities for returning the primary
	 * key of the newly inserted row (rather than the effected rows). This
	 * functionality is of course optional. <p/> The parameter object is
	 * generally used to supply the input data for the INSERT values.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @return The primary key of the newly inserted row. This might be
	 *         automatically generated by the RDBMS, or selected from a sequence
	 *         table or other source.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public Object insert(String id, Object parameterObject) throws SQLException
	{
		Object ret = null;
		try
		{
			ret = this.module.getSqlMapClient().insert(id, parameterObject);
		}
		catch (SQLException e)
		{
			/* Modified By Lampard Lee,May 23th,2007*/
			/* 描述: 解决公共接口插入数据出错没有抛出异常的问题 */
			/* 修改开始 */
			/* 注释旧代码*/
			/*
			errHandle(e);
			*/
			/* 增加新代码 */
			e.printStackTrace();
			throw e;
			/* 修改结束 */
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects. <p/> The parameter object is generally used to
	 * supply the input data for the WHERE clause parameter(s) of the SELECT
	 * statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @return A List of result objects.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public List queryForList(String id, Object parameterObject)
			throws SQLException
	{
		List ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForList(id,
					parameterObject);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects within a certain range. <p/> The parameter
	 * object is generally used to supply the input data for the WHERE clause
	 * parameter(s) of the SELECT statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param skip
	 *            The number of results to ignore.
	 * @param max
	 *            The maximum number of results to return.
	 * @return A List of result objects.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public List queryForList(String id, Object parameterObject, int skip,
			int max) throws SQLException
	{
		List ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForList(id,
					parameterObject, skip, max);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects that will be keyed into a Map. <p/> The
	 * parameter object is generally used to supply the input data for the WHERE
	 * clause parameter(s) of the SELECT statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param keyProp
	 *            The property to be used as the key in the Map.
	 * @return A Map keyed by keyProp with values being the result object
	 *         instance.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public Map queryForMap(String id, Object parameterObject, String keyProp)
			throws SQLException
	{
		Map ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForMap(id,
					parameterObject, keyProp);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects from which one property will be keyed into a
	 * Map. <p/> The parameter object is generally used to supply the input data
	 * for the WHERE clause parameter(s) of the SELECT statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param keyProp
	 *            The property to be used as the key in the Map.
	 * @param valueProp
	 *            The property to be used as the value in the Map.
	 * @return A Map keyed by keyProp with values of valueProp.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public Map queryForMap(String id, Object parameterObject, String keyProp,
			String valueProp) throws SQLException
	{
		Map ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForMap(id,
					parameterObject, keyProp, valueProp);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * single object instance. <p/> The parameter object is generally used to
	 * supply the input data for the WHERE clause parameter(s) of the SELECT
	 * statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @return The single result object populated with the result set data, or
	 *         null if no result was found
	 * @throws java.sql.SQLException
	 *             If more than one result was found, or if any other error
	 *             occurs.
	 */
	public Object queryForObject(String id, Object parameterObject)
			throws SQLException
	{
		Object ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForObject(id,
					parameterObject);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate the
	 * supplied result object. <p/> The parameter object is generally used to
	 * supply the input data for the WHERE clause parameter(s) of the SELECT
	 * statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param resultObject
	 *            The result object instance that should be populated with
	 *            result data.
	 * @return The single result object as supplied by the resultObject
	 *         parameter, populated with the result set data, or null if no
	 *         result was found
	 * @throws java.sql.SQLException
	 *             If more than one result was found, or if any other error
	 *             occurs.
	 */
	public Object queryForObject(String id, Object parameterObject,
			Object resultObject) throws SQLException
	{
		Object ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForObject(id,
					parameterObject, resultObject);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects a page at a time. <p/> The parameter object is
	 * generally used to supply the input data for the WHERE clause parameter(s)
	 * of the SELECT statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param pageSize
	 *            The maximum number of result objects each page can hold.
	 * @return A PaginatedList of result objects.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public PaginatedList queryForPaginatedList(String id,
			Object parameterObject, int pageSize) throws SQLException
	{
		PaginatedList ret = null;
		try
		{
			ret = this.module.getSqlMapClient().queryForPaginatedList(id,
					parameterObject, pageSize);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Executes a mapped SQL SELECT statement that returns a number of result
	 * objects that will be handled one at a time by a RowHandler. <p/> This is
	 * generally a good approach to take when dealing with large sets of records
	 * (i.e. hundreds, thousands...) that need to be processed without eating up
	 * all of the system resources. <p/> The parameter object is generally used
	 * to supply the input data for the WHERE clause parameter(s) of the SELECT
	 * statement.
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param rowHandler
	 *            A RowHandler instance
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public void queryWithRowHandler(String id, Object parameterObject,
			RowHandler rowHandler) throws SQLException
	{
		try
		{
			this.module.getSqlMapClient().queryWithRowHandler(id,
					parameterObject, rowHandler);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
	}

	/**
	 * Starts a batch in which update statements will be cached before being
	 * sent to the database all at once. This can improve overall performance of
	 * updates update when dealing with numerous updates (e.g. inserting 1:M
	 * related data).
	 *
	 * @throws java.sql.SQLException
	 *             If the batch could not be started.
	 */
	public void startBatch() throws SQLException
	{
		try
		{
			this.module.getSqlMapClient().startBatch();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
	}

	/**
	 * Executes a mapped SQL UPDATE statement. Update can also be used for any
	 * other update statement type, such as inserts and deletes. Update returns
	 * the number of rows effected. <p/> The parameter object is generally used
	 * to supply the input data for the UPDATE values as well as the WHERE
	 * clause parameter(s).
	 *
	 * @param id
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @return The number of rows effected.
	 * @throws java.sql.SQLException
	 *             If an error occurs.
	 */
	public int update(String id, Object parameterObject) throws SQLException
	{
		int ret = 0;
		try
		{
			ret = this.module.getSqlMapClient().update(id, parameterObject);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Commits the currently started transaction.
	 *
	 * @throws SQLException
	 *             If an error occurs while committing the transaction, or the
	 *             transaction could not be committed.
	 */
	public void commitTransaction() throws SQLException
	{
		try
		{
			this.module.getSqlMapClient().commitTransaction();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
	}

	/**
	 * Ends a transaction and rolls back if necessary. If the transaction has
	 * been started, but not committed, it will be rolled back upon calling
	 * endTransaction().
	 *
	 * @throws SQLException
	 *             If an error occurs during rollback or the transaction could
	 *             not be ended.
	 */
	public void endTransaction() throws SQLException
	{
		try
		{
			this.module.getSqlMapClient().endTransaction();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
	}

	/**
	 * Returns the current connection in use. If no connection exists null will
	 * be returned. There may be no connection if no transaction has been
	 * started, and if no user provided connection has been set.
	 *
	 * @return The current connection or null.
	 * @throws SQLException
	 */
	public Connection getCurrentConnection() throws SQLException
	{
		Connection ret = null;
		try
		{
			ret = this.module.getSqlMapClient().getCurrentConnection();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Returns the current connection in use. If no connection exists null will
	 * be returned. There may be no connection if no transaction has been
	 * started, and if no user provided connection has been set.
	 *
	 * @return The current connection or null.
	 * @throws SQLException
	 */
	public DataSource getDataSource()
	{
		return this.module.getSqlMapClient().getDataSource();
	}

	/**
	 * Returns the current user supplied connection as set by
	 * setUserConnection(). <p/> TODO : DEPRECATED
	 *
	 * @return The current user supplied connection.
	 * @throws SQLException
	 * @deprecated Use getCurrentConnection() instead.
	 */
	@Deprecated
	public Connection getUserConnection() throws SQLException
	{
		Connection ret = null;
		try
		{
			ret = this.module.getSqlMapClient().getUserConnection();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
		return ret;
	}

	/**
	 * Allows the developer to easily use an externally supplied connection when
	 * executing statements. <p/> <b>Important:</b> Using a user supplied
	 * connection basically sidesteps the transaction manager, so you are
	 * responsible for appropriately. Here's a (very) simple example (throws
	 * SQLException):
	 *
	 * <pre>
	 * try
	 * {
	 * 	Connection connection = dataSource.getConnection();
	 * 	sqlMap.setUserConnection(connection);
	 * 	// do work
	 * 	connection.commit();
	 * }
	 * catch (SQLException e)
	 * {
	 * 	try
	 * 	{
	 * 		if (connection != null) commit.rollback();
	 * 	}
	 * 	catch (SQLException ignored)
	 * 	{
	 * 		// generally ignored
	 * 	}
	 * 	throw e; // rethrow the exception
	 * }
	 * finally
	 * {
	 * 	try
	 * 	{
	 * 		if (connection != null) connection.close();
	 * 	}
	 * 	catch (SQLException ignored)
	 * 	{
	 * 		// generally ignored
	 * 	}
	 * }
	 * </pre>
	 *
	 * @param connnection
	 * @throws SQLException
	 */
	public void setUserConnection(Connection connnection) throws SQLException
	{

		try
		{
			this.module.getSqlMapClient().setUserConnection(connnection);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
	}

	/**
	 * Demarcates the beginning of a transaction scope. Transactions must be
	 * properly committed or rolled back to be effective. Use the following
	 * pattern when working with transactions:
	 *
	 * <pre>
	 * try
	 * {
	 * 	sqlMap.startTransaction();
	 * 	// do work
	 * 	sqlMap.commitTransaction();
	 * }
	 * finally
	 * {
	 * 	sqlMap.endTransaction();
	 * }
	 * </pre>
	 *
	 * <p/> Always call endTransaction() once startTransaction() has been
	 * called.
	 *
	 * @throws java.sql.SQLException
	 *             If an error occurs while starting the transaction, or the
	 *             transaction could not be started.
	 */
	public void startTransaction() throws SQLException
	{
		try
		{
			this.module.getSqlMapClient().startTransaction();
		}
		catch (SQLException e)
		{
			errHandle(e);
		}
	}

	/**
	 * Demarcates the beginning of a transaction scope using the specified
	 * transaction isolation. Transactions must be properly committed or rolled
	 * back to be effective. Use the following pattern when working with
	 * transactions:
	 *
	 * <pre>
	 * try
	 * {
	 * 	sqlMap.startTransaction(Connection.TRANSACTION_REPEATABLE_READ);
	 * 	// do work
	 * 	sqlMap.commitTransaction();
	 * }
	 * finally
	 * {
	 * 	sqlMap.endTransaction();
	 * }
	 * </pre>
	 *
	 * <p/> Always call endTransaction() once startTransaction() has been
	 * called.
	 *
	 * @throws java.sql.SQLException
	 *             If an error occurs while starting the transaction, or the
	 *             transaction could not be started.
	 */
	public void startTransaction(int transactionIsolation) throws SQLException
	{
		try
		{
			this.module.getSqlMapClient()
					.startTransaction(transactionIsolation);
		}
		catch (SQLException e)
		{
			errHandle(e);
		}

	}

}
