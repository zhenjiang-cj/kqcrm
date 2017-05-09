package com.nl.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.nl.base.utils.SystemTool;
import com.nl.portal.dt.SysOperatorRole;

/**
 * ���ڴ����¼�������Ϣ
 * @author daiqiang
 *
 */
public class SessionTool {
	private static ThreadLocal<SessionData> sessionLocal = new ThreadLocal<SessionData>();

	public static SessionData getSessionData() {
		return sessionLocal.get();
	}

	public static void setSessionData(SessionData sessionData) {
		sessionLocal.set(sessionData);
	}
	
	/**
	 * 
	 * @Title: checkPrivilege 
	 * @Description: У���¼���Ƿ����XXȨ��
	 * @author dq   
	 * @date 2014-4-30 ����10:00:08 
	 * @version V1.0  
	 * @param sysId
	 * @param functionId
	 * @return boolean   
	 * @throws
	 */
	public static boolean checkPrivilege(String sysId, String functionId){
		if (StringUtils.isEmpty(sysId)) {
			SystemTool.getLoggerForWebApp(
					SessionTool.getSessionData().getAdmUser().getAuID(), 
					GlobalConst.ERROR).error("ϵͳ��Ų�����sysId��Ϊ�գ�");
		}else if(StringUtils.isEmpty(functionId)){
			SystemTool.getLoggerForWebApp(
					SessionTool.getSessionData().getAdmUser().getAuID(), 
					GlobalConst.ERROR).error("Ȩ�ޱ�Ų�����functionId��Ϊ�գ�");
		}else {
			/*Map sysPrivilegeMap = SessionTool.getSessionData().getSysPrivilgeMap().get(sysId);
			if (sysPrivilegeMap == null || sysPrivilegeMap.get(functionId) == null || sysPrivilegeMap.get(functionId).equals("")) {
			}else{
				return true;
			}*/
		}
		return false;
	}
	
	/**
	 * 
	 * @Title: checkRole 
	 * @Description: У���¼���Ƿ����XX��ɫ
	 * @author dq   
	 * @date 2014-4-30 ����10:00:33 
	 * @version V1.0  
	 * @param sysId
	 * @param roleId 
	 * @return boolean   
	 * @throws
	 */
	public static boolean checkRole(String sysId, String roleId){
		if (StringUtils.isEmpty(sysId)) {
			SystemTool.getLoggerForWebApp(
					SessionTool.getSessionData().getAdmUser().getAuID(), 
					GlobalConst.ERROR).error("ϵͳ��Ų�����sysId��Ϊ�գ�");
		}else if(StringUtils.isEmpty(roleId)){
			SystemTool.getLoggerForWebApp(
					SessionTool.getSessionData().getAdmUser().getAuID(), 
					GlobalConst.ERROR).error("��ɫ��Ų�����roleId��Ϊ�գ�");
		}else {
			/*List<SysOperatorRole> roles = SessionTool.getSessionData().getSysOperatorRoleMap().get(sysId);
			if (roles == null) {
			} else {
				for (SysOperatorRole role : roles) {
					if (StringUtils.isNotEmpty(role.getRole_id()) && role.getRole_id().equals(roleId)) {
						return true;
					}
				}
			}*/
		}
		return false;
	}
}
