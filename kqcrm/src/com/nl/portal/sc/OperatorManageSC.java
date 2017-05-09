package com.nl.portal.sc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.csp.bsf.pwm.service.impl.PasswordForMD5;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.actionForm.CompanyUserForm;
import com.nl.portal.actionForm.LoginForm;
import com.nl.portal.actionForm.OperatorForm;
import com.nl.portal.bc.OperatorManageDbMgr;
import com.nl.portal.dt.IscPriDataRel;
import com.nl.portal.dt.KmCompanyUser;
import com.nl.portal.dt.PriTreeDisplayCfgDt;
import com.nl.portal.dt.SysOperator;
import com.nl.portal.dt.SysOperatorRole;
import com.nl.util.GlobalConst;

public class OperatorManageSC extends AbstractDB
{
	String bossCodeStr = "";
	public OperatorManageSC(String bossCodeStr)
	{
		//构造方法实现
		this.bossCodeStr = bossCodeStr;
	}
	
	/**
	 * 查询操作员列表
	 * @param roleForm
	 * @return List
	 */
	public List queryOperatorList(OperatorForm operatorForm)
	{
		List list = new ArrayList();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			list = operatorDbMgr.queryOperatorList(operatorForm);
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return list;
	}
	
	/**
	 * 
	 * 
	 * @param loginForm
	 * @param companyUser
	 * @return 1为成功，2为用户不存在，3为用户密码错误
	 * @author sanjing
	 * @createdate Oct 23, 2016
	 * @version v1.0
	 */
	public int queryCompanyUser(LoginForm loginForm,KmCompanyUser companyUser){
		SqlMapClient smc = null;
		KmCompanyUser compuser = null;
		int nRet = 0;
		PasswordForMD5 md5 = new PasswordForMD5();
		try{
			loginForm.setMd5UserPwd(md5.encode(loginForm.getUserPwd()));
			
			smc = getSqlMapClient();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			
			compuser = operatorDbMgr.queryCompanyUser(loginForm);
			//传userid查询为空，用户不存在
			if(null==compuser){
				nRet = 2;
//				loginForm.setUserPwd("");
//				//传userid查询
//				compuser = operatorDbMgr.queryCompanyUser(loginForm);
//				//用户不存在，userid错误
//				if(null==compuser){
//					nRet = 2;
//				//用户存在，userpwd错误	
//				}else
//					nRet = 3;
			//传userid非空，用户存在
			}else{
				//密码正确
				if(md5.encode(loginForm.getUserPwd()).equalsIgnoreCase(compuser.getUser_pwd())){
					nRet = 1;
					//companyUser = new KmCompanyUser();
					//companyUser = compuser;
					companyUser.setCompany_user_id(compuser.getCompany_user_id());
					companyUser.setCompany_user_name(compuser.getCompany_user_name());
					companyUser.setUser_type(compuser.getUser_type());
					companyUser.setStart_date_cu(compuser.getStart_date_cu());
					companyUser.setEnd_date_cu(compuser.getEnd_date_cu());
					companyUser.setCreate_id(compuser.getCreate_id());
					companyUser.setCreate_date(compuser.getCreate_date());
					companyUser.setStatus(compuser.getStatus());
				}else
					nRet = 3;

			}
		}catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return nRet;
	}
	
	/**
	 * 
	 * 
	 * @param companyUser
	 * @return
	 * @author sanjing
	 * @createdate Oct 25, 2016
	 * @version v1.0
	 */
	public int changeCompanyPass(LoginForm loginForm){
		SqlMapClient smc = null;
		int nRet = 0;
		PasswordForMD5 md5 = new PasswordForMD5();
		loginForm.setMd5UserPwd(md5.encode(loginForm.getNewPwd()));
		try{
			smc = getSqlMapClient();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			
			nRet = operatorDbMgr.changeCompanyPass(loginForm);
			
		}catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return nRet;
		
	}
	
	public List<SysOperator> getDetpList()
	{
		List list = new ArrayList();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			list = operatorDbMgr.getDetpList();
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return list;
	}
	
	public int addOperator(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode = 0;
		SqlMapClient smc = null;
		PasswordForMD5 md5 = new PasswordForMD5();
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);

			operatorForm.setNewPassword1(md5.encode("123"));

			//新增工号
			retCode = operatorDbMgr.insertPortalUser(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增工号！::" + "debug：");
				return retCode;
			}
			
			//新增工号与部门关系
			retCode = operatorDbMgr.insertPortalUserDept(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增工号与部门关系！::" + "debug：");
				return retCode;
			}

			smc.commitTransaction();
		} catch(Exception e)
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
	
	public int updateOperator(OperatorForm operatorForm)
	{
		int retCode = 0;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			retCode = operatorDbMgr.updateOperator(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改操作员信息有错！::" + "debug：");
				return retCode;
			}
			
			retCode = operatorDbMgr.updateOperatorDept(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改操作员与部门关系有错！::" + "debug：");
				return retCode;
			}
			
			if(!operatorForm.getSno_dept_old().equals(operatorForm.getSno_dept())){
								
				retCode = operatorDbMgr.insertOperatorDept(operatorForm);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增操作员与部门关系有错！::" + "debug：");
					return retCode;
				}
			}
						
			smc.commitTransaction();
		} catch(Exception e)
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
	
	public List getOperatorById(String param){
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始获取用户信息：");
		List<SysOperator> list = new ArrayList<SysOperator>();
		SqlMapClient smc = null;
		
		try{
			smc = getSqlMapClient();
			OperatorManageDbMgr db = new OperatorManageDbMgr(smc);
			list = db.getOperatorById(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("获取用户信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::获取用户信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}
	
	
	/**
	 * 查询操作员详细信息
	 * @param roleForm
	 * @return List
	 */
	public int queryOperatorDetail(OperatorForm operatorForm)
	{
		int retCode = 0;
		SysOperator operator = new SysOperator();
		List<SysOperatorRole> sysOperatorRoleList;
		Map operRoleMap = new HashMap();
		int systemId[] = GlobalConst.Global_SYSTEM_ID;
		
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			operator = operatorDbMgr.queryOperatorDetail(operatorForm);
			
			for (int i = 0; i < systemId.length; i++)
			{
				sysOperatorRoleList = new ArrayList<SysOperatorRole>();
				sysOperatorRoleList = operatorDbMgr.queryOperRoleBySeq(operatorForm.getUserSeq(), systemId[i]);
				
				if(sysOperatorRoleList!=null&&sysOperatorRoleList.size()>0)
				{
					operRoleMap.put(String.valueOf(systemId[i]), sysOperatorRoleList);
				}
			}
			
			operatorForm.setOperator(operator);
			operatorForm.setOperRoleMap(operRoleMap);
			
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return retCode;
	}
	
	
	
	
	/**
	 * 查询可以为操作员分配的角色列表
	 * @param roleForm
	 * @return List
	 */
	public List queryUseRoleList(OperatorForm operatorForm)
	{
		List list = new ArrayList();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			list = operatorDbMgr.queryUseRoleList(operatorForm);
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		finally
		{
			this.endTransaction(smc);
		}
		return list;
	}
	
	
	public int updateOperRoleRel(OperatorForm operatorForm)
	{
		int retCode = 0;
		SysOperatorRole operatorRole = new SysOperatorRole();
		String chooseRole[] = operatorForm.getChooseRole();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			
			String[] orgtreeData = operatorForm.getOrgtreedata();
			
			//先删除对应的角色关系
			retCode = operatorDbMgr.deleteOperRoleRel(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除对应的角色关系出错！::" + "debug：");
				return retCode;
			}
			
			HashMap param = new HashMap();
			param.put("userSeq", operatorForm.getUserSeq());
			param.put("sysid", operatorForm.getSysId());
			
			retCode = operatorDbMgr.deleteUserRoleData(param);//删除原数据角色关系数据
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除原数据角色关系数据出错！::" + "debug：");
				return retCode;
			}
			
			//再增加角色关系
			if (chooseRole != null)
			{
				for (int k = 0; k < operatorForm.getChooseRole().length; k++)
				{					
					param.put("roleId", chooseRole[k]);
					param.put("dataType", operatorForm.getDataType());
					param.put("dataLevel", operatorForm.getDataOrgLevel());
					param.put("code", operatorForm.getDataOrgId()==null?"":operatorForm.getDataOrgId());
					param.put("operatorId", operatorForm.getOperatorId());
					retCode = operatorDbMgr.insertOperRoleRel(param);
					
					if(orgtreeData!=null&&orgtreeData.length>0)
					{
						for(int i=0;i<orgtreeData.length;i++)//添加新数据角色关系数据
						{
							String str = orgtreeData[i];
							if(str!=null&&!"".equals(str))
							{
								String[] s = str.split(",");
								for(int n=0;n<s.length;n++)
								{
									String[] temp = s[n].split("_");
									int dataType = Integer.valueOf(temp[0]);//数据类型
									int dataLevel = Integer.valueOf(temp[1]);//数据级别
									String code = temp[2];
									param = new HashMap();
									param.put("data_type", dataType);
									List<Integer> roleIds = operatorDbMgr.queryRoleidByDataType(param);//获取包含该数据类型的角色编号
									
									param.put("dataType", dataType);
									param.put("dataLevel", dataLevel);
									param.put("code", code);
									if(roleIds!=null&&roleIds.size()>0)
									{
										for(int j=0;j<roleIds.size();j++)
										{
											int roleId = roleIds.get(j);
											if(Long.valueOf(chooseRole[k])==roleId)
											{	
												param.put("roleId", roleId);
												retCode = operatorDbMgr.insertUserRoleData(param);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			smc.commitTransaction();
			if (retCode >= 0)
				retCode = 0;
		} catch(Exception e)
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
	 * 修改操作员密码
	 * @param roleForm
	 * @return int
	 */
	public int updateOperPassword(OperatorForm operatorForm)
	{
		int retCode = 0;
		SysOperator operator = new SysOperator();
		PasswordForMD5 md5 = new PasswordForMD5();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			//先查询操作员的详细信息
			operator = operatorDbMgr.queryOperatorDetail(operatorForm);
			
			//判断旧密码是否相同
			if (md5.encode(operatorForm.getOldPassword()).equalsIgnoreCase(operator.getUserPassword().trim()))
			{
				operatorForm.setNewPassword1(md5.encode(operatorForm.getNewPassword1()));
				//修改密码
				retCode = operatorDbMgr.updateOperPassword(operatorForm);
			}
			else 
			{
				retCode = -2;
			}
			smc.commitTransaction();
		} catch(Exception e)
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
	 * 重置密码，将密码修改成默认密码123
	 * @param operatorForm
	 * @returnintOct 28, 2011Administrator
	 */
	public int resetPassword(OperatorForm operatorForm){
		int retCode = 0;
		PasswordForMD5 md5 = new PasswordForMD5();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			operatorForm.setNewPassword1(md5.encode("123"));
			//修改密码
			retCode = operatorDbMgr.updateOperPassword(operatorForm);
			smc.commitTransaction();
		} catch(Exception e)
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
	 * 密码重置，并登记密码重置记录
	 * 
	 * @param loginForm
	 * @return
	 * @author cj
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public int resetPassword(LoginForm loginForm){
		int retCode = 0;
		PasswordForMD5 md5 = new PasswordForMD5();
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			loginForm.setNewPassword1(md5.encode("123"));
			//修改密码
			operatorDbMgr.updateOperPassword(loginForm);
			operatorDbMgr.insertResetPassRecord(loginForm);
			smc.commitTransaction();
		} catch(Exception e)
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
	 * 获取权限树展现配置
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
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			dtList = operatorDbMgr.getPriTreeCfg();
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		return dtList;
	}
	
	
	
	/**
	 * 获取某系统下该数据类对应的所有权限及角色信息
	 * @description 方法描述
	 * @param operatorForm
	 * @return
	 * @author xiaqt   
	 * @date Aug 21, 2012 3:03:53 PM
	 */
	public List<IscPriDataRel> queryPrivilegeInfo(OperatorForm operatorForm)
	{
		List<IscPriDataRel> list = new ArrayList<IscPriDataRel>();
		HashMap param = new HashMap();
		SqlMapClient smc = null;
		try
		{
			param.put("data_type", operatorForm.getDataType());
			param.put("sysid", operatorForm.getSysId());
//			sram.put("endNum", operatorForm.getEndNum());
			smc = getSqlMapClient();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);
			list = operatorDbMgr.queryPrivilegeInfo(param);
		} catch(Exception e)
		{
			getLogger(bossCodeStr,GlobalConst.ERROR).error(e.getMessage());
		}
		return list;
	}

		

	/**
	 *
	 * @author wangtt
	 * @creatdate May 12, 2014
	 */
	public int closeUser(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode = 0;
		SqlMapClient smc = null;
		PasswordForMD5 md5 = new PasswordForMD5();
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);

			//置工号失效
			retCode = operatorDbMgr.delUser(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::置工号失效！::" + "debug：");
				return retCode;
			}
			//删除对应角色
			retCode = operatorDbMgr.delUserRole(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除对应角色！::" + "debug：");
				return retCode;
			}

			smc.commitTransaction();
		} catch(Exception e)
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
	 *
	 * @author wangtt
	 * @creatdate May 12, 2014
	 */
	public int openUser(OperatorForm operatorForm) {
		// TODO Auto-generated method stub
		int retCode = 0;
		SqlMapClient smc = null;
		PasswordForMD5 md5 = new PasswordForMD5();
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			OperatorManageDbMgr operatorDbMgr = new OperatorManageDbMgr(smc);

			operatorForm.setNewPassword1(md5.encode("123"));
			retCode = operatorDbMgr.openUser(operatorForm);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::打开工号！::" + "debug：");
				return retCode;
			}
			smc.commitTransaction();
		} catch(Exception e)
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

	

	
	
	
}