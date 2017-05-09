package com.nl.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Created by �����´�½�Ƽ����޹�˾
 * User: zhouk
 * Date: 2009-3-12
 * Time: 13:37:05
 * package:com.nl.cns.utils.db
 * ���������ݿ⹤����
 */
public class DbUtils {
    
    private static String sysdateType = "yyyy-mm-dd HH24:mi:ss";
    
    /**
     * ������ݿ⵱ǰʱ��
     *
     * @param model  ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @param offset ƫ���� 1 ��ʾ ��ǰʱ���һ�� ��2��ʾ��ǰʱ��ǰ2�� 0.25��ʾ��ǰʱ���6Сʱ
     * @return ����ַ���
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
     * ������ݿ⵱ǰʱ��
     *
     * @param model  ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @param offset ƫ���� 1 ��ʾ ��ǰʱ���һ�� ��2��ʾ��ǰʱ��ǰ2�� 0.25��ʾ��ǰʱ���6Сʱ
     * @return ����ַ���
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
     * ������ݿ⵱ǰʱ��
     *
     * @return ����ַ��� ��ʽΪ��yyyy-mm-dd HH24:mi:ss
     */
    public static String getDbTime() {
        return getDbTime(sysdateType, 0);
    }

    /**
     * ������ݿ⵱ǰʱ��
     *
     * @param model ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @return ����ַ��� ��ʽΪ��yyyy-mm-dd HH24:mi:ss
     */
    public static String getDbTime(String model) {
        return getDbTime(model, 0);
    }
    
    /**
     * ֧�ִ������ӵĲ�ѯ���ݿ�(sysdate)ʱ��Ĺ�������
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param conn
     *          ���ݿ�����
     * @param offset 
     *          ƫ���� 1 ��ʾ ��ǰʱ���һ�� ��2��ʾ��ǰʱ��ǰ2�� 0.25��ʾ��ǰʱ���6Сʱ
     * @param model  
     *          ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ֧�ִ������ӵĲ�ѯ���ݿ�(sysdate)ʱ��Ĺ�������
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param conn
     *          ���ݿ�����
     * @param model  
     *          ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String getDbTime(Connection conn, String model)
    {
        return getDbTime(conn, 0, model);
    }
    
    /**
     * ֧�ִ������ӵĲ�ѯ���ݿ�(sysdate)ʱ��Ĺ�������
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param conn
     *          ���ݿ�����
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String getDbTime(Connection conn)
    {
        return getDbTime(conn, 0, sysdateType);
    }
    
    /**
     * ֧�ִ���iBatis���ӵĲ�ѯ���ݿ�(sysdate)ʱ��Ĺ�������[]
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param smc
     *          iBatis���ݿ�����
     * @param offset 
     *          ƫ���� 1 ��ʾ ��ǰʱ���һ�� ��2��ʾ��ǰʱ��ǰ2�� 0.25��ʾ��ǰʱ���6Сʱ
     * @param model  
     *          ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ֧�ִ���iBatis���ӵĲ�ѯ���ݿ�(sysdate)ʱ��Ĺ�������
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param smc
     *          iBatis���ݿ�����
     * @param model  
     *          ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String getDbTime(SqlMapClient smc, String model)
    {
        return getDbTime(smc, 000000, model);
    }
    
    /**
     * ֧�ִ���iBatis���ӵĲ�ѯ���ݿ�(sysdate)ʱ��Ĺ�������
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param smc
     *          iBatis���ݿ�����
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String getDbTime(SqlMapClient smc)
    {
        return getDbTime(smc, 000000, sysdateType);
    }
    
    /**
     * ��ȡָ�����ڴ洢���̵�ִ��״̬ 
     * @param processName   �洢��������
     * @param stat_date     ִ������(yyyymmdd)
     * @return state        ִ��״̬,0Ϊ�ɹ�
     * @exception/throws [Υ������] [Υ��˵��]
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
     * ��ȡ����洢���̵�ִ��״̬ 
     * @param processName   �洢��������
     * @return state       ִ��״̬,0Ϊ�ɹ�
     * @exception/throws [Υ������] [Υ��˵��]
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
     * ��ȡϵͳ���ݿ�ʱ��+ָ��SEQuence����
     * Description: <br>
     * Implement: <br>
     * 1���� <br>
     * 2���� <br>
     * [�����б�˵��ÿ��������;]
     * 
     * @param model  
     *          ��ʽ�ַ���(��:"yyyy-mm-dd HH24:mi:ss")
     * @param seqName
     *                   
     *          
     * @return String
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
