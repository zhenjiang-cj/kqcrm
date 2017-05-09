package com.nl.util.db;

import com.nl.base.utils.GlobalFunc;
import com.nl.util.GlobalConst;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.util.Properties;

/**
 * <p>
 * Copyright: Copyright (c管理类
 * </p>
 * <p>
 * Date : 2008-04-26
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * 
 * @author 周科
 * @version 0.1
 */
public class DBConnection {
	private static final String CLASSNAME = "DBConnection";
	public static int nNum = 0;// 链接打开数

	private static DataSource ds_db = null;
	private static InitialContext ctx = null;
	public static BasicDataSource ds = null;
	public static BasicDataSource dsCp = null;
	private static Log logger = LogFactory.getLog(CLASSNAME);

	//
	public DBConnection() {
	}

	public static Connection Open() throws SQLException {
		return Open(GlobalConst.dbpool_id);
	}

	/**
	 * 获取 SqlServer数据库链接
	 * 
	 * @param poolnum
	 *            扩充暂留
	 * @return 数据库
	 * @throws SQLException
	 *             异常
	 * 
	 * public static Connection OpenSqlServer(int poolnum) throws SQLException {
	 * Connection con = null; // String url =
	 * "jdbc:jtds:sqlserver://10.37.8.58:1433;DatabaseName=pubs";//jtds方式 String
	 * url = "jdbc:sqlserver://10.37.8.58:1433;DatabaseName=pubs";//ms jdbc方式
	 * String db_driver =
	 * "com.microsoft.sqlserver.jdbc.SQLServerDriver";//2005驱动 // String
	 * db_driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";//2000驱动 //
	 * String db_driver = "net.sourceforge.jtds.jdbc.Driver"; String sUserName =
	 * "sa", sPassword = "yzh";
	 * 
	 * try { nNUm++; Class.forName(db_driver); con =
	 * DriverManager.getConnection(url, sUserName, sPassword);
	 * logger.debug("===========open the " + nNUm + " Connection==========="); }
	 * catch (SQLException e1) { e1.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace(); } return con; }
	 */
	public static Connection Open(int poolnum) throws SQLException {
		Connection con = null;    

		/*
		 * ModuleFactory factory = new ModuleFactory(GlobalConst.JNDI_NAME);
		 * Module module; SqlMapClient smc = null; try { module =
		 * factory.CreateModule(); smc = module.getSqlMapClientProxy(); try{
		 * smc.startTransaction(); }catch(Exception e){ e.printStackTrace(); }
		 * con = smc.getCurrentConnection(); } catch (Exception e) {
		 * e.printStackTrace();
		 *  }
		 */

		try {
			String url = null;
			if ("jdbc".equals(GlobalConst.database_mode)) {
				switch (poolnum) {
				case GlobalConst.dbpool_id:
					url = GlobalConst.database_url;
					break;
				default:
					url = GlobalConst.database_url;
				}
				nNum++;
				//
				DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
				// DriverManager.registerDriver(new com.p6spy.engine.spy.P6SpyDriver());
				con = DriverManager.getConnection(url,GlobalConst.database_username,GlobalConst.database_password);
				logger.debug("===========open the  " + nNum + "  Connection===========");
			} else if ("dbcp".equals(GlobalConst.database_mode)) {
				try {
					if (null == ds) {

						ds = new BasicDataSource();
						ds.setDriverClassName(GlobalConst.database_drive);
						ds.setUrl(GlobalConst.database_url);
						ds.setUsername(GlobalConst.database_username);
						ds.setPassword(GlobalConst.database_password);
						ds.setInitialSize(GlobalFunc.initInt(
								GlobalConst.database_initialSize, 5));
						ds.setMaxActive(GlobalFunc.initInt(
								GlobalConst.database_maxActive, 6));
					}
					logger.debug("数据库连接url:" + GlobalConst.database_url);
					con = ds.getConnection();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if ("pool".equals(GlobalConst.database_mode)) {
				url = GlobalConst.database_jndi;
				if (null == ctx) {
					ctx = new InitialContext();
				}

				if (ds_db == null) {
					ds_db = (DataSource) ctx.lookup(url);
				}

				con = ds_db.getConnection();

				if (con == null) {
					logger.info("connection is null!");
				}
			} else if ("t3pool".equals(GlobalConst.database_mode)){

				String m_jndi = GlobalConst.database_jndi;
			    String m_ts_url=GlobalConst.t3_url;
			    String m_ts_user=GlobalConst.t3_user;
			    String m_ts_password=GlobalConst.t3_password;
			    Context context = null;
			    
                Properties properties = null;
                properties = new Properties();
                properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
                properties.put(Context.PROVIDER_URL, m_ts_url);
                if (m_ts_user != null) {
                    properties.put(Context.SECURITY_PRINCIPAL, m_ts_user);
                    properties.put(Context.SECURITY_CREDENTIALS, m_ts_password == null ? "" : m_ts_password);
                }
                if (context == null) {
                    context = new InitialContext(properties);
                }
                if (ds_db == null) {
            	    ds_db = (javax.sql.DataSource) context.lookup(m_jndi);
                }
                logger.debug("m_jndi" + m_jndi);
                logger.debug("m_ts_url" + m_ts_url);
                logger.debug("m_ts_user" + m_ts_user);
                logger.debug("m_ts_password" + m_ts_password);

                logger.debug("ds_db" + ds_db);
                con = ds_db.getConnection();
            }
			 
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	/**
	 * 释放数据库连接
	 * 
	 * @param con
	 *            Connection [in]
	 */
	public static void CloseConnection(Connection con) {
		// final String METHODNAME = "CloseConnection";
		// String strMsg;

		if (con == null) {
			logger.debug("connection is null!");
		}

		try {
			if (con != null) {
				con.setAutoCommit(true);
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			nNum--;
		}
	}

	private static String inferCaller() {
		// Get the stack trace.
		StackTraceElement[] stack = (new Throwable()).getStackTrace();
		// First, search back to a method in the Logger class.
		int ix = 0;
		String cname = null;
		while (ix < stack.length) {
			StackTraceElement frame = stack[ix];
			cname = frame.getClassName();
			if (cname.equals("newload.pub.ConnOracle")) {
				break;
			}
			ix++;
		}
		// Now search for the first frame before the "Logger" class.
		while (ix < stack.length) {
			StackTraceElement frame = stack[ix];
			cname = frame.getClassName();
			if (!cname.equals("newload.pub.ConnOracle")) {
				// We've found the relevant frame.
				return new StringBuffer().append(cname).append(".").append(
						frame.getMethodName()).toString();
			}
			ix++;
		}
		// We haven't found a suitable frame, so just punt. This is
		// OK as we are only commited to making a "best effort" here.
		return null;
	}

	public static void rollback(Connection conn) {

		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace(); // 异常日志打印
			}
		}
	}

	public static void tryClose(Statement stmt, Connection conn) {
		tryClose(null, stmt, conn);
	}

	public static void tryClose(Connection conn) {
		tryClose(null, null, conn);
	}

	public static void tryClose(Statement stmt) {
		tryClose(null, stmt, null);
	}

	public static void tryClose(ResultSet rs, Statement stmt) {
		tryClose(rs, stmt, null);
	}

	public static void tryClose(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				nNum--;
				// logger.debug(inferCaller() + " user dbconn close !num= " +
				// nNum);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 获取下一个序列
	 * 
	 * @param conn
	 *            数据库链接
	 * @param sSeqName
	 *            序列名称
	 * @return long
	 * @throws java.sql.SQLException
	 *             异常
	 */
	public static long getSeq(Connection conn, String sSeqName)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql.append("select ");
			sql.append(sSeqName);
			sql.append(".nextval val from dual");

			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getLong("val");

		} finally {
			tryClose(rs, stmt);
		}
	}

}
