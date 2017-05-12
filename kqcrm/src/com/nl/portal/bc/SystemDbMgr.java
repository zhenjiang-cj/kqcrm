package com.nl.portal.bc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.AdmUserLog;
import com.nl.portal.dt.HtzjCodeBm;
import com.nl.portal.dt.KmDictCfg;
import com.nl.portal.dt.SysMenu;
import com.nl.portal.dt.SysOperator;

public class SystemDbMgr extends AbstractDB
{
	private final Logger logger = Logger.getLogger(this.getClass());
	private SqlMapClient smc;
	
	protected String bossCodeStr = null;
	
	public SystemDbMgr(String bossCodeStr){
		this.bossCodeStr = bossCodeStr;
		this.getClass().getName();
		
	}

	public SystemDbMgr(SqlMapClient smc)
	{
		this.smc = smc;
	}
	
	public SystemDbMgr(SqlMapClient smc,String bossCodeStr)
	{
		this.smc = smc;
		this.bossCodeStr = bossCodeStr;
		this.getClass().getName();
	}
	
		
	/**
	 * 向sys_operating_log表插入一条数据
	 * @param dt
	 * @returnint
	 */
	public int insertSysOperatingLog(AdmUserLog dt){
		int retCode = 0;
		try{
			smc.insert("SystemSql.insertAdmUserLog", dt);
		}catch(SQLException e){
			logger.error(e.getMessage());
			retCode = -1;
		}
		return retCode;
	}
	
	public List<KmDictCfg> queryDictCfg(String dict_id)
	{
		List<KmDictCfg> dictCfgList = null;
		HashMap param = new HashMap<String,String>();
		param.put("dict_id", dict_id);

		try
		{
			dictCfgList = smc.queryForList("SystemSql.queryDictCfg",param);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return dictCfgList;
	}
	/**
	 * 
	 * 
	 * @param userid
	 * @return
	 * @author sanjing
	 * @createdate Oct 21, 2016
	 * @version v1.0
	 */
	public List queryUserInfo(String userid)
	{
		List list = new ArrayList();
		HashMap param = new HashMap();
		param.put("auID", userid);

		try
		{
			AdmUserFc userinfo = (AdmUserFc)smc.queryForObject("SystemSql.queryUserInfo",param);
			list.add(userinfo);
			param.clear();
			param.put("bmdm", userinfo.getAuOrgId());
			HtzjCodeBm userdept = (HtzjCodeBm)smc.queryForObject("SystemSql.queryUserDept",param);
			list.add(userdept);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}
	
	public SysOperator getOperatorById(String operatorId)
	{
		SysOperator operator = new SysOperator();
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("user_id", operatorId);
		try
		{
			operator = (SysOperator) smc.queryForObject("systemLoginSql.queryOperatorById",param);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return operator;
	}
	public List getOperMenuList(String sno,int systemId)
	{
		List<SysMenu> sysOperatorMenuList = new ArrayList<SysMenu>();
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("sno", sno);
		param.put("sysid", String.valueOf(systemId));
		try
		{
			sysOperatorMenuList = smc.queryForList("systemLoginSql.queryMenu",param);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return sysOperatorMenuList;
	}
}
