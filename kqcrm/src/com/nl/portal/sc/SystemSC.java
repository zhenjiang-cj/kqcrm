package com.nl.portal.sc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.base.bssp.BsspXmlMgr;
import com.nl.base.utils.BASTree;
import com.nl.portal.bc.SystemDbMgr;
import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.AdmUserLog;
import com.nl.portal.dt.KmDictCfg;
import com.nl.portal.dt.SysMenu;
import com.nl.portal.dt.SysOperator;
import com.nl.util.GlobalConst;
import com.nl.util.GlobalUtil;
import com.nl.util.SessionConst;
import com.nl.util.SessionData;


/**
 * @Description: 
 * @author CJ
 * @version 1.0
 * Oct 13, 2016
 * -------------------------------------------
 * @History:
 * 修订日期    修订人    版本    描述
 * 
 */
public class SystemSC extends AbstractDB
{
	private int[] systemId = GlobalConst.Global_SYSTEM_ID;
	String bossCodeStr = "";
	public SystemSC(String bossCodeStr)
	{
		//构造方法实现
		this.bossCodeStr = bossCodeStr;
	}
	
	public SystemSC(){
		
	}
	
	
	/*
	 * 公共方法：记录日志流水
	 * 参数说明：lgUserID,	--操作员代码
				lgOptrType,	--操作对象
				lgObjType,	--操作对象类型
				lgNotes,	--操作记录说明
				lgStatus,	--操作成功与否（成功、失败）
				lgDate,	--操作日期
				lgUserType,	--操作员类型
				lgpcIP,	--登录IP
				lgpcName --登录主机
	*/
	
	public void createSysOperatingLog(String lgUserID,
									  String lgOptrType,
									  String lgObjType,
									  String lgNotes,
									  String lgStatus,
									  String lgUserType,
									  String lgpcIP,
									  String lgpcName){
		
		AdmUserLog dt = new AdmUserLog();
		try{
			dt.setLgUserID(lgUserID);
			dt.setLgOptrType(lgOptrType);
			dt.setLgObjType(lgObjType);
			dt.setLgNotes(lgNotes);
			dt.setLgStatus(lgStatus);
			dt.setLgUserType(lgUserType);
			dt.setLgpcIP(lgpcIP);
			dt.setLgpcName(lgpcName);
			this.insertSysOperatingLog(dt);
		}catch(Exception e){
			getLogger("bosscodestr",GlobalConst.ERROR).error("error:"+e.getMessage());
		}
	}
	
	
	/**
	 * 向sys_operating_log表插入一条数据
	 * @param dt
	 * @returnint
	 */
	public int insertSysOperatingLog(AdmUserLog dt){
		int retCode = 0;
		SqlMapClient smc = null;
		try{
			smc = getSqlMapClient();
			SystemDbMgr db = new SystemDbMgr(smc);
			smc.startTransaction();
			
			retCode = db.insertSysOperatingLog(dt);

			smc.commitTransaction();
		} catch (Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
			retCode = -1;
		}
		finally
		{
			this.endTransaction(smc);
		}
		return retCode;
	}
	
	/**
	 * 公共方法：记录日志流水-简单
	 * 
	 * @param reques
	 * @param lgOptrType--操作对象
	 * @param lgObjType	--操作对象类型
	 * @param lgNotes	--操作记录说明
	 * @param lgStatus	--操作成功与否（成功、失败）
	 * @author sanjing
	 * @createdate Oct 17, 2016
	 * @version v1.0
	 */
	public void createSysOperatingLogSimple(HttpServletRequest request,
											String lgOptrType,
											String lgObjType,
											String lgNotes,
											String lgStatus){
		AdmUserLog dt = new AdmUserLog();
		SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
		if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null)
			sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
		String lgUserID = null;
		String lgUserType = null;
		if(sessionData == null){
			sessionData = new SessionData();
			AdmUserFc admuserdt = new AdmUserFc();
			admuserdt.setAuID("guest");
			admuserdt.setAuType("游客");
			sessionData.setAdmUser(admuserdt);
			lgUserID = sessionData.getAdmUser().getAuID();
			lgUserType = sessionData.getAdmUser().getAuType();
		}else{
			if(sessionData.getCompanyUser()!=null){
				lgUserID = sessionData.getCompanyUser().getCompany_user_id();
				lgUserType = sessionData.getCompanyUser().getUser_type();
			}
			if(sessionData.getAdmUser()!=null){
				lgUserID = sessionData.getAdmUser().getAuID();
				lgUserType = sessionData.getAdmUser().getAuType();
			}
			
		}
		
		try{
			dt.setLgUserID(lgUserID);
			dt.setLgOptrType(lgOptrType);
			dt.setLgObjType(lgObjType);
			dt.setLgNotes(lgNotes);
			dt.setLgStatus(lgStatus);
			dt.setLgUserType(lgUserType);
			dt.setLgpcIP(GlobalUtil.getIpAddr(request));
			dt.setLgpcName(GlobalUtil.getRequestName(request));
			this.insertSysOperatingLog(dt);
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("error:"+e.getMessage());
		}
		
	}
	
	/**
	 * 查询字典公共方法
	 * param:字典ID
	 */
	public List<KmDictCfg> queryDictCfgList(String dict_id)
	{
		List<KmDictCfg> dictCfgList = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			SystemDbMgr db = new SystemDbMgr(smc);
			dictCfgList = db.queryDictCfg(dict_id);
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return dictCfgList;
	}
	
	/**
	 * 
	 * 
	 * @param userid
	 * @param strtoken
	 * @return
	 * @author sanjing
	 * @createdate Oct 21, 2016
	 * @version v1.0
	 */
//	public boolean checkUser(StringBuffer userid,String strtoken,AdmUserFc dtUserInfo){
//		SystemXml sxml = new SystemXml(dtUserInfo);
//		boolean rsCheck = sxml.checkUser(userid, strtoken);
//		
//		return rsCheck;
//		//return true;
//	}
	
	public boolean checkUser(String userid,String strtoken){
		
		return true;
	}
	
	public boolean testCheckUser(StringBuffer userid,String strtoken,AdmUserFc dtUserInfo){
		
		SystemXml sxml = new SystemXml(dtUserInfo);
		boolean rsCheck = sxml.checkUser(userid, strtoken);
		
		return rsCheck;
	}
	
	/*
	 --科大应答报文
<?xml version="1.0" encoding="GBK"?>
<operation_out>
	<service_name>checkUserLogin</service_name>
	<sysfunc_id>10011909</sysfunc_id>
	<request_type>1001</request_type>
	<operator_id>test111</operator_id>
	<request_time>201610221830</request_time>
	--2016年10月23日增加开始
	<response>
  <resp_type>1</resp_type> 
  <resp_code>1</resp_code> 
  <resp_desc>成功</resp_desc> 
  </response>
  --2016年10月23日增加结束
	<content>
	  <Result>
		  <ResultCode>1</ResultCode> 
		  <ResultMessage>用户正常登陆</ErrorMessage> 
	 	</Result>
	 	<userid>
	 	1234566
	 	</userid>
	</content>
</operation_out>
	 */
	
	/**
	 * 返回用户相关信息list(0)为用户信息，list(1)为该用户的组织机构信息
	 * 
	 * @param userid
	 * @return
	 * @author sanjing
	 * @createdate Oct 21, 2016
	 * @version v1.0
	 */
	public List getUserInfos(String userid){
		List list = new ArrayList();
		
//		AdmUserFc userInfo = new AdmUserFc();
//		HtzjCodeBm deptInfo = new HtzjCodeBm();
//		String message = "";
//		String forwardStr = "";
//		String operatorId = "test";
//		String password = "123";
//		userInfo.setAuID("test");
//		userInfo.setAuType("type1");
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			SystemDbMgr db = new SystemDbMgr(smc);
			list = db.queryUserInfo(userid);
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}

//		list.add(userInfo);
//		list.add(deptInfo);
		
		return list;
	}
	/**
	 * 
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 * @author sanjing
	 * @createdate Oct 24, 2016
	 * @version v1.0
	 */
	public Color getRandColor(int fc,int bc){
		
		Random random = new Random();   
        if(fc>255) fc=255;   
        if(bc>255) bc=255;   
        int r=fc+random.nextInt(bc-fc);   
        int g=fc+random.nextInt(bc-fc);   
        int b=fc+random.nextInt(bc-fc);
        //Color co = new Color(r,g,b);
        return new Color(r,g,b);
	} 
	
	public SysOperator getOperatorById(String operatorId)
	{
		SysOperator operator = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			SystemDbMgr sysDbMgr = new SystemDbMgr(smc);
			operator = sysDbMgr.getOperatorById(operatorId);
		} catch (Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return operator;
	}
	/**
	 * 获取操作员的系统菜单
	 * @param sessionData
	 * @return int
	 */
	public int getOperMenu(SessionData sessionData)
	{
		int retCode = -1;
		List<SysMenu> menuList;
		BASTree tree = null;
		BASTree tTemp = null;
		String priv ="";
		
		SysMenu sysMenu;
		Map<String, BASTree> treeMap = new HashMap<String, BASTree>();
		List<String> privMap = new ArrayList<String>();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			SystemDbMgr sysDbMgr = new SystemDbMgr(smc);
			//循环查询操作员各个系统的菜单数据
			for (int i = 0; i < systemId.length; i++)
			{
				menuList = new ArrayList<SysMenu>();
				//查询操作员的系统菜单
				menuList = sysDbMgr.getOperMenuList(sessionData.getSno(), systemId[i]);
				
				tree = null;
				tTemp = null;
				
				//判断是否有菜单数据，没有则不添加到MAP中
				if (menuList != null && menuList.size() > 0)
				{
					for(int j=0;j<menuList.size();j++)
					{
						sysMenu = menuList.get(j);
//						if (StringUtils.isNotEmpty(sysMenu.getWebaddr())) {
//							sysMenu.setWebaddr(sysMenu.getWebaddr() + "&menuClick=true");
//						}
//						if (tree == null) 
//						{
//							tree = new BASTree(sysMenu.getName(), sysMenu.getWebaddr(), sysMenu.getPage_id(),sysMenu.getPage_desc());
//		                } else 
//		                {
//		                    tTemp = new BASTree(sysMenu.getName(), sysMenu.getWebaddr(), sysMenu.getPage_id(),sysMenu.getPage_desc());
//		                    tree.addTreeNode(tTemp, Integer.parseInt(sysMenu.getParent_id()));
//		                }
						privMap.add(String.valueOf(sysMenu.getPage_id()));
						
					}
//					treeMap.put(String.valueOf(systemId[i]), tree);
				}
				
			}//结束for循环
			
			//MAP赋值到sessiondate中
//			sessionData.setTreeMap(treeMap);
			sessionData.setPrivMap(privMap);
			
		} catch (Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return retCode;
	}
	
}
