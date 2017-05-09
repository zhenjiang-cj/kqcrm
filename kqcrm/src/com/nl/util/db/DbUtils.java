package com.nl.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Created by 江苏新大陆科技有限公司
 * User: zhouk
 * Date: 2009-3-12
 * Time: 13:37:05
 * package:com.nl.cns.utils.db
 * 描述：数据库工具类
 */
public class DbUtils {
    
    private static String sysdateType = "yyyy-mm-dd HH24:mi:ss";
    
    /**
     * 获得数据库当前时间
     *
     * @param model  格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @param offset 偏移量 1 表示 当前时间后一天 －2表示当前时间前2天 0.25表示当前时间后6小时
     * @return 结果字符串
     */
    public static String getDbTime(String model, int offset) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.Open();
            stmt = conn.prepareStatement("select to_char(sysdate+?,?) as sys_date from dual");
            stmt.setInt(1, offset);
            stmt.setString(2, model);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("sys_date");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt, conn);
        }
        return null;

    }
    
    /**
     * 获得数据库当前时间
     *
     * @param model  格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @param offset 偏移量 1 表示 当前时间后一天 －2表示当前时间前2天 0.25表示当前时间后6小时
     * @return 结果字符串
     */
    public static String getDbTime(String model, Double offset) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.Open();
            stmt = conn.prepareStatement("select to_char(sysdate+?,?) as sys_date from dual");
            stmt.setDouble(1, offset);
            stmt.setString(2, model);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("sys_date");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt, conn);
        }
        return null;

    }

    /**
     * 获得数据库当前时间
     *
     * @return 结果字符串 格式为：yyyy-mm-dd HH24:mi:ss
     */
    public static String getDbTime() {
        return getDbTime(sysdateType, 0);
    }

    /**
     * 获得数据库当前时间
     *
     * @param model 格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @return 结果字符串 格式为：yyyy-mm-dd HH24:mi:ss
     */
    public static String getDbTime(String model) {
        return getDbTime(model, 0);
    }
    
    /**
     * 支持传入连接的查询数据库(sysdate)时间的公共方法
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param conn
     *          数据库联接
     * @param offset 
     *          偏移量 1 表示 当前时间后一天 －2表示当前时间前2天 0.25表示当前时间后6小时
     * @param model  
     *          格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getDbTime(Connection conn, int offset, String model)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try
        {
            stmt = conn.prepareStatement("select to_char(sysdate+?,?) as sys_date from dual");
            stmt.setInt(1, offset);
            stmt.setString(2, model);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("sys_date");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBConnection.tryClose(rs, stmt, null);
        }
        return null;
    }
    
    /**
     * 支持传入连接的查询数据库(sysdate)时间的公共方法
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param conn
     *          数据库联接
     * @param model  
     *          格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getDbTime(Connection conn, String model)
    {
        return getDbTime(conn, 0, model);
    }
    
    /**
     * 支持传入连接的查询数据库(sysdate)时间的公共方法
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param conn
     *          数据库联接
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getDbTime(Connection conn)
    {
        return getDbTime(conn, 0, sysdateType);
    }
    
    /**
     * 支持传入iBatis连接的查询数据库(sysdate)时间的公共方法[]
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param smc
     *          iBatis数据库联接
     * @param offset 
     *          偏移量 1 表示 当前时间后一天 －2表示当前时间前2天 0.25表示当前时间后6小时
     * @param model  
     *          格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public static String getDbTime(SqlMapClient smc, int offset, String model)
    {
        try
        {
            Map paraMap = new HashMap();
            paraMap.put("OFFSET", offset == 000000 ? "0" : offset);
            paraMap.put("MODEL", model);
            return (String)smc.queryForObject("common.getDbTime", paraMap);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * 支持传入iBatis连接的查询数据库(sysdate)时间的公共方法
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param smc
     *          iBatis数据库联接
     * @param model  
     *          格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getDbTime(SqlMapClient smc, String model)
    {
        return getDbTime(smc, 000000, model);
    }
    
    /**
     * 支持传入iBatis连接的查询数据库(sysdate)时间的公共方法
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param smc
     *          iBatis数据库联接
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getDbTime(SqlMapClient smc)
    {
        return getDbTime(smc, 000000, sysdateType);
    }
    
    /**
     * 获取指定日期存储过程的执行状态 
     * @param processName   存储过程名称
     * @param stat_date     执行日期(yyyymmdd)
     * @return state        执行状态,0为成功
     * @exception/throws [违例类型] [违例说明]
     */
    public static int getProcessState(String processName,long stat_date) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int state = -1;
        try {
            conn = DBConnection.Open();
            stmt = conn.prepareStatement(" SELECT status FROM BHMS_PROCESS_CTRL WHERE PROCESS_NAME = TRIM(UPPER(?)) AND STAT_DATE = ? ");
            stmt.setString(1,processName);
            stmt.setLong(2,stat_date);
            rs = stmt.executeQuery();
            if(rs.next()){
            	state = rs.getInt("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt, conn);
        }
        return state;

    }
    
    /**
     * 获取当天存储过程的执行状态 
     * @param processName   存储过程名称
     * @return state       执行状态,0为成功
     * @exception/throws [违例类型] [违例说明]
     */
    public static int getProcessNowState(String processName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int state = -1;
        StringBuffer str = new StringBuffer(" SELECT status FROM BHMS_PROCESS_CTRL WHERE PROCESS_NAME = TRIM(UPPER(?)) ");
        str.append(" AND STAT_DATE = (select to_char(sysdate,'yyyymmdd') from dual) ");
        try {
            conn = DBConnection.Open();
            stmt = conn.prepareStatement(str.toString());
            stmt.setString(1,processName);
            rs = stmt.executeQuery();
            if(rs.next()){
            	state = rs.getInt("status");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt, conn);
        }
        return state;

    }
    
    /**
     * 获取系统数据库时间+指定SEQuence序列
     * Description: <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param model  
     *          格式字符串(如:"yyyy-mm-dd HH24:mi:ss")
     * @param seqName
     *                   
     *          
     * @return String
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getTimeAppSeq(String model, String seqName)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.Open();
            stmt = conn.prepareStatement("SELECT to_char(SYSDATE, ?) || "+seqName+".nextval AS timeseq FROM dual");
            stmt.setString(1, model);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("timeseq");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt, conn);
        }
        return null;
    }
    
}
