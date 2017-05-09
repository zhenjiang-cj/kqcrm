package com.nl.portal.bc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.utils.GlobalFunc;
import com.nl.portal.actionForm.LoginForm;
import com.nl.portal.actionForm.OperatorForm;
import com.nl.portal.dt.IscPriDataRel;
import com.nl.portal.dt.KmCompanyUser;
import com.nl.portal.dt.PriTreeDisplayCfgDt;
import com.nl.portal.dt.SysOperator;
import com.nl.portal.dt.SysOperatorRole;

public class OperatorManageDbMgr
{
	protected String bossCodeStr = null;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SqlMapClient smc;

	public OperatorManageDbMgr(SqlMapClient smc)
	{
		this.smc = smc;
	}

	/**
	 * 查询操作员列表
	 * @param operatorForm
	 * @return List
	 */
	public List queryOperatorList(OperatorForm operatorForm)
	{
		List roleList = new ArrayList();
		HashMap param = new HashMap<String,String>();
		param.put("user_id", operatorForm.getUserId().trim());
		param.put("user_name", "%"+operatorForm.getUserName().trim()+"%");
		param.put("startNum", operatorForm.getStartNum());
		param.put("endNum", operatorForm.getEndNum());

		try
		{
			roleList = smc.queryForList("operatorManageSql.queryOperatorList",param);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return roleList;
	}
	
	/**
	 * 
	 * 查询企业系统操作用户
	 * @param companyuserForm
	 * @return
	 * @author sanjing
	 * @createdate Oct 23, 2016
	 * @version v1.0
	 */
	public KmCompanyUser queryCompanyUser(LoginForm loginForm){
		HashMap param = new HashMap();
		if(!"".equals(loginForm.getCompanyUserId()))
			param.put("company_user_id", loginForm.getCompanyUserId().trim());
//		if(!"".equals(loginForm.getUserPwd()))
//			param.put("user_pwd", loginForm.getMd5UserPwd().trim());
		KmCompanyUser compuser = null;
		try
		{
			compuser= (KmCompanyUser)smc.queryForObject("operatorManageSql.queryCompanyUser",param);
			
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}	
		
		return compuser;
	}
	
	/**
	 * 修改企业用户的密码
	 * 
	 * @param companyUser
	 * @return
	 * @author sanjing
	 * @createdate Oct 25, 2016
	 * @version v1.0
	 */
	public int changeCompanyPass(LoginForm loginForm){
		int nRet = 0;
		HashMap param = new HashMap();
		param.put("user_pwd", loginForm.getMd5UserPwd());
		param.put("company_user_id", loginForm.getCompanyUserId());
		try
		{
			nRet= smc.update("operatorManageSql.changeCompanyPass",param);
			
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}	
		
		return nRet;
		
	}
	
	/**
	 * 显示部门列表
	 *@Title: getDetpList 
	 *@Description: TODO
	 *@author CJ
	 *@date Feb 3, 2016 9:36:40 AM 
	 *@return
	 *@return List<SysOperator>
	 */
	public List<SysOperator> getDetpList()
	{
		List<SysOperator>  deptList = new ArrayList();

		try
		{
			deptList = smc.queryForList("operatorManageSql.getDetpList",null);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return deptList;
	}
	
	public List<SysOperator> getOperatorById(String param){
		List<SysOperator> list = null;
		
		try{
			list = smc.queryForList("operatorManageSql.getOperatorById", param);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 *新增操作员信息
	 * @author 
	 * @creatdate May 9, 2014
	 */
	public int insertPortalUser(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode =0;
		HashMap param = new HashMap<String,String>();
		param.put("user_id", operatorForm.getUserId().trim());
		param.put("user_name", operatorForm.getUserName().trim());
		param.put("msisdn", operatorForm.getMsisdn().trim());
		param.put("email", operatorForm.getEmail().trim());
		param.put("password", operatorForm.getNewPassword1().trim());
		try
		{
			smc.insert("operatorManageSql.insertPortalUser",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	/**
	 *新增操作员与部门关系
	 * @author 
	 * @creatdate May 9, 2014
	 */
	public int insertPortalUserDept(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode =0;
		HashMap param = new HashMap<String,String>();
		param.put("user_id", operatorForm.getUserId());
		param.put("dept_id", operatorForm.getSno_dept());
		param.put("begin_date", operatorForm.getBeginDate());
		param.put("end_date", GlobalFunc.initStr(operatorForm.getEndDate(),null));

		try
		{
			smc.insert("operatorManageSql.insertPortalUserDept",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	/**
	 * 查询操作员详细信息
	 * @param operatorForm
	 * @return List
	 */
	public SysOperator queryOperatorDetail(OperatorForm operatorForm)
	{
		SysOperator operator = new SysOperator();
		
		HashMap param = new HashMap<String,String>();
		param.put("userSeq", operatorForm.getUserSeq());
		param.put("snoDept", operatorForm.getSno_dept_old());
		
		System.out.println("snoDept>>>>>>>"+operatorForm.getSno_dept_old());
		try
		{
			operator = (SysOperator)smc.queryForObject("operatorManageSql.queryOperatorDetail",param);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return operator;
	}
	
	
	/**
	 * 根据操作员的工号获取角色信息
	 * @param sNo
	 * @param systemId
	 * @return List
	 */
	public List queryOperRoleBySeq(String sNo,int systemId)
	{
		List<SysOperatorRole> sysOperatorRoleList = new ArrayList<SysOperatorRole>();
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("userSeq", sNo);
		param.put("sysId", String.valueOf(systemId));
		try
		{
			sysOperatorRoleList = smc.queryForList("operatorManageSql.queryOperRoleBySeq",param);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return sysOperatorRoleList;
	}
	
	/**
	 * 修改操作员信息
	 * @param operatorForm
	 * @return List
	 */
	public int updateOperator(OperatorForm operatorForm)
	{
		SysOperator operator = new SysOperator();
		int retCode = 0;
		
		operator.setUser_seq(operatorForm.getUserSeq());
		operator.setUser_name(operatorForm.getUserName());
		operator.setMsisdn(operatorForm.getMsisdn());
		operator.setEmail(operatorForm.getEmail());

		try
		{
			smc.update("operatorManageSql.updateOperator",operator);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
			retCode = -1;
		}
		return retCode;
	}
		
	/**
	 * 修改员工与部门关系
	 *@Title: updateOperatorDept 
	 *@Description: TODO
	 *@author CJ
	 *@date Feb 3, 2016 3:12:29 PM 
	 *@param operatorForm
	 *@return
	 *@return int
	 */
	public int updateOperatorDept(OperatorForm operatorForm)
	{
		SysOperator operator = new SysOperator();
		int retCode = 0;
				
		operator.setHr_sno(operatorForm.getHr_sno());
		
		if(!operatorForm.getSno_dept_old().equals(operatorForm.getSno_dept())){
			operator.setEnd_date(operatorForm.getBeginDate());
			operator.setSno_dept(operatorForm.getSno_dept_old());
		}else{
			operator.setBegin_date(operatorForm.getBeginDate());
			operator.setEnd_date(GlobalFunc.initStr(operatorForm.getEndDate(),null));		
			operator.setSno_dept(operatorForm.getSno_dept_old());
		}
		try
		{
			if(!operatorForm.getSno_dept_old().equals(operatorForm.getSno_dept())){
				smc.update("operatorManageSql.endOperatorDept",operator);
			}
			else{
				smc.update("operatorManageSql.updateOperatorDept",operator);
			}

		} catch (SQLException e)
		{
			logger.error(e.getMessage());
			retCode = -1;
		}
		return retCode;
	}
	
	public int insertOperatorDept(OperatorForm operatorForm)
	{
		SysOperator operator = new SysOperator();
		int retCode =0;
		
		operator.setHr_sno(operatorForm.getHr_sno());
		operator.setSno_dept(operatorForm.getSno_dept());
		operator.setBegin_date(operatorForm.getBeginDate());
		operator.setEnd_date(GlobalFunc.initStr(operatorForm.getEndDate(),null));
		try
		{
			smc.insert("operatorManageSql.insertOperatorDept",operator);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	
	
	
	
	/**
	 * 查询可以为操作员分配的角色列表
	 * @param operatorForm
	 * @return List
	 */
	public List queryUseRoleList(OperatorForm operatorForm)
	{
		List list = new ArrayList();
		HashMap param = new HashMap<String,String>();
		param.put("sysid", operatorForm.getSysId());
		param.put("userSeq", operatorForm.getUserSeq());

		try
		{
			list = smc.queryForList("operatorManageSql.queryUseRole",param);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 删除为操作员角色关系
	 * @param operatorForm
	 * @return int
	 */
	public int deleteOperRoleRel(OperatorForm operatorForm)
	{
		int retCode =0;
		HashMap param = new HashMap<String,String>();
		param.put("sysid", operatorForm.getSysId());
		param.put("userSeq", operatorForm.getUserSeq());

		try
		{
			smc.delete("operatorManageSql.deleteOperRoleRel",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	/**
	 * 增加操作员角色关系
	 * @param operatorForm
	 * @return int
	 */
	public int insertOperRoleRel(HashMap param)
	{
		int retCode =0;
		
		try
		{
			smc.insert("operatorManageSql.insertOperRoleRel",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	public List<Integer> queryRoleidByDataType(HashMap param)
	{
		List<Integer> list = new ArrayList<Integer>();
		try
		{
			list = smc.queryForList("operatorManageSql.queryRoleidByDataType", param);
		}catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}
	
	public int insertUserRoleData(HashMap param)
	{
		int retCode = 0;
		try
		{
			smc.insert("operatorManageSql.insertUserRoleData", param);
		}catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	/**
	 * 修改操作员密码
	 * @param operatorForm
	 * @return int
	 */
	public int updateOperPassword(OperatorForm operatorForm)
	{
		int retCode =0;
		HashMap param = new HashMap();
		param.put("user_seq", operatorForm.getUserSeq());
		param.put("user_pswd", operatorForm.getNewPassword1());
		try
		{
			smc.update("operatorManageSql.updateOperPassword",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	/**
	 * 修改操作员密码
	 * 
	 * @param loginForm
	 * @return
	 * @author cj
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public int updateOperPassword(LoginForm loginForm)
	{
		int retCode =0;
		HashMap param = new HashMap();
		param.put("sno", loginForm.getUserNo());
		param.put("user_pswd", loginForm.getNewPassword1());
		try
		{
			smc.update("operatorManageSql.updateOperPassword",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	/**
	 * 登记密码重置记录
	 * 
	 * @param loginForm
	 * @return
	 * @author cj
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public int insertResetPassRecord(LoginForm loginForm)
	{
		int retCode =0;
		HashMap param = new HashMap();
		param.put("operatorId", loginForm.getUserNo());
		param.put("ip", loginForm.getIp());
		try
		{
			smc.insert("operatorManageSql.insertResetPassRecord",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
			
	
	public int deleteUserRoleData(HashMap param)
	{
		int retCode = 0;
		try
		{
			smc.delete("operatorManageSql.deleteUserRoleData", param);
		}catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	
	
	/**
	 * 获取权限树配置信息
	 * 
	 * @param dataType
	 * @return
	 * @author daihb
	 * @createdate Jan 22, 2013
	 * @version v1.0
	 */
	public List<PriTreeDisplayCfgDt> getPriTreeCfg()
	{
		List<PriTreeDisplayCfgDt> dtList = null;
		try
		{
			dtList = smc.queryForList("operatorManageSql.getPriTreeCfg", null);
		}catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return dtList;
	}
	
	/**
	 * 通过数据类型获取该数据类型对应的某个系统下所有权限及角色信息
	 * @description 方法描述
	 * @param param
	 * @return
	 * @author xiaqt   
	 * @date Aug 21, 2012 2:55:55 PM
	 */
	public List<IscPriDataRel> queryPrivilegeInfo(HashMap param)
	{
		List<IscPriDataRel> list = new ArrayList<IscPriDataRel>();
		try
		{
			list = smc.queryForList("operatorManageSql.queryPrivilegeInfo", param);
		}catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}
	
	
	/**
	 * 
	 *@Title: checkUser 
	 *@Description: 判断工号是否存在
	 *@author CJ
	 *@date Apr 9, 2014 2:12:05 PM 
	 *@param userId
	 *@return
	 *@return int
	 */
	public int checkUser(String userId)
	{
		int isExists = 0;
		try
		{
			isExists = Integer.parseInt(String.valueOf(smc.queryForObject("operatorManageSql.checkUser", userId)));
		}catch (SQLException e)
		{
			isExists = -1;
			logger.error(e.getMessage());
		}
		return isExists;
	}
		
	
	
	/**
	 *
	 * @author 
	 * @creatdate 
	 */
	public int delUser(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode =0;
		HashMap param = new HashMap<String,String>();
		param.put("userSeq", operatorForm.getUserSeq());
		try
		{
			smc.insert("operatorManageSql.delOperator",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}

	/**
	 *
	 * @author 
	 * @creatdate 
	 */
	public int delUserRole(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode =0;
		HashMap param = new HashMap<String,String>();
		param.put("userSeq", operatorForm.getUserSeq());
		try
		{
			smc.insert("operatorManageSql.delUserRole",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}


	/**
	 *
	 * @author 
	 * @creatdate 
	 */
	public int openUser(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode =0;
		HashMap param = new HashMap<String,String>();
		param.put("userSeq", operatorForm.getUserSeq());
		param.put("password", operatorForm.getNewPassword1().trim());
		try
		{
			smc.insert("operatorManageSql.openUser",param);
		} catch (SQLException e)
		{
			retCode = -1;
			logger.error(e.getMessage());
		}
		return retCode;
	}
	

}
