package com.nl.util.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nl.base.utils.Log;
import com.nl.base.utils.SystemTool;
import com.nl.util.GlobalConst;
import com.nl.util.db.DBConnection;

public class NoLimitUser {
	private static List<String> users = new ArrayList<String>();
	public static void init(){
		String funcName = "��ʼ����½�û�������";
		Log.getLogger("", GlobalConst.ENTER).debug(funcName);
		//���USERS
		users.clear();

		//��ѯ�������û�
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.Open();
			String sql = " SELECT USER_ID FROM ISC_USER_NO_LIMIT WHERE RANGE_CODE = 1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					users.add(rs.getString(1));
				}
			}
			Log.getLogger("", GlobalConst.EXIT).debug(funcName);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getLogger("", GlobalConst.ERROR).error("��ʼ����½������ʧ��"+e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				Log.getLogger("", GlobalConst.ERROR).error("��ʼ����½������ʧ��");
			}finally{
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e1) {}
			}
			
		}
	}
	
	public static boolean noLimit(String sno){
		if (StringUtils.isEmpty(sno)) {
			Log.getLogger("").error("�û����SNO");
		}
		
		//ѭ����֤�Ƿ�������û�
		if (users != null && users.size() > 0) {
			for (String user : users) {
				if (StringUtils.isNotEmpty(user) && user.equals(sno)) {
					return true;
				}
			}
		}
		return false;
	}

}
