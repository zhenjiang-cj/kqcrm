package com.nl.portal.sc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.actionForm.UserForm;
import com.nl.portal.bc.UserDbMgr;
import com.nl.portal.dt.*;
import com.nl.util.GlobalConst;

public class UserSc extends AbstractDB {
	public List<UserInfo> queryUser(UserForm userform)
	{
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("user_name", userform.getUser_name());
			param.put("page_num", userform.getPageNum());
			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryUser(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public String getSno() throws Exception {
		SqlMapClient smc = null;
		String sno="";
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			
			UserDbMgr db = new UserDbMgr(smc);
			sno = db.getSno();

			smc.commitTransaction();
		} catch (Exception e) {
			sno = "";
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return sno;
	}
	public String getRole() throws Exception {
		SqlMapClient smc = null;
		String sno="";
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			
			UserDbMgr db = new UserDbMgr(smc);
			sno = db.getRole();

			smc.commitTransaction();
		} catch (Exception e) {
			sno = "";
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return sno;
	}
	
	public List<UserInfo> getCityByPro(UserForm userform)
	{
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("provinces", userform.getProvinces());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.getCityByPro(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public List<UserInfo> getRegionByCity(UserForm userform)
	{
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("provinces", userform.getProvinces());
			param.put("city", userform.getCity());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.getCityByPro(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public int doUserAdd(UserForm form) throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//���ݷ��ʶ���
			UserDbMgr db = new UserDbMgr(smc);
			
			param.put("sno",form.getSno());
			param.put("user_id",form.getUser_id());
			param.put("user_name",form.getUser_name());
			param.put("user_pwsd",form.getUser_pswd());
			param.put("msisdn",form.getMsisdn());
			param.put("email",form.getEmail());
			
			
			
			param.put("create_id",form.getOperatorId());
			
			String level = "1";
			
			if(form.getRegion()!=null&&!"".equals(form.getRegion())&&!"null".equals(form.getRegion())){
				level ="3";
			}else if(form.getCity()!=null&&!"".equals(form.getCity())&&!"null".equals(form.getCity())){
				level ="2";
			}else{
				level ="1";
			}
			
			param.put("level",level);
			param.put("provinces",form.getProvinces()==null?"":form.getProvinces());
			param.put("city",form.getCity()==null?"":form.getCity());
			param.put("region",form.getRegion()==null?"":form.getRegion());
			
			retCode = db.doUserAdd(param);
			retCode = db.doUserRegionAdd(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL)return retCode;
			
			 
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public int doUserDel(UserForm form) throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//���ݷ��ʶ���
			UserDbMgr db = new UserDbMgr(smc);
//			param.put("create_id",form.getOperatorId());
			
			param.put("sno",form.getSno());
			
			 

			//��������Ĺ�����Ϣ (����ʵ����Ϣ�� FLOW_INSTANCE,����������Ϣ�� CENSUPPORT_WORKORDER_BASE_INFO)
			retCode = db.doUserDel(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL)return retCode;
			
			 
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public List<UserInfo> queryUserListBysno(UserForm userform)
	{
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("sno", userform.getSno());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryUserListBysno(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public int doUserEdit(UserForm form)  throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//���ݷ��ʶ���
			UserDbMgr db = new UserDbMgr(smc);

			param.put("sno",form.getSno());
			param.put("user_id",form.getUser_id());
			param.put("user_name",form.getUser_name());
			param.put("user_pwsd",form.getUser_pswd());
			param.put("msisdn",form.getMsisdn());
			param.put("email",form.getEmail());
			
			
			
			param.put("create_id",form.getOperatorId());
			
			String level = "1";
			
			if(form.getRegion()!=null&&!"".equals(form.getRegion())&&!"null".equals(form.getRegion())){
				level ="3";
			}else if(form.getCity()!=null&&!"".equals(form.getCity())&&!"null".equals(form.getCity())){
				level ="2";
			}else{
				level ="1";
			}
			
			param.put("level",level);
			param.put("provinces",form.getProvinces()==null?"":form.getProvinces());
			param.put("city",form.getCity()==null?"":form.getCity());
			param.put("region",form.getRegion()==null?"":form.getRegion());
			
			 

			//��������Ĺ�����Ϣ (����ʵ����Ϣ�� FLOW_INSTANCE,����������Ϣ�� CENSUPPORT_WORKORDER_BASE_INFO)
			retCode = db.doUserEdit(param);
			retCode = db.doUserRegionEdit(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL)return retCode;
			
			 
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public List<UserInfo> queryAllRole(UserForm userform) {
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
//			param.put("sno", userform.getSno());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryAllRole(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public List<UserInfo> queryRoleByUser(UserForm userform) {
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("sno", userform.getSno());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryRoleByUser(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public int doUserRole(UserForm form) throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//���ݷ��ʶ���
			UserDbMgr db = new UserDbMgr(smc);

			param.put("sno",form.getSno());
//			param.put("chooseRole",form.getChooseRole());
			param.put("sysid",form.getSysid());
			
			param.put("create_id",form.getOperatorId());
			
			 
			//��ɾ��ԭ�ȵĽ�ɫ��ϵ���������µĽ�ɫ��ϵ
			String[] roles = form.getChooseRole().split(",");
			if(roles!=null&&roles.length>0){
				for(int i=0;i<roles.length;i++){
					param.put("role_id",roles[i]);
					retCode = db.delUserRole(param);
					retCode = db.addUserRole(param);
					if(retCode == GlobalConst.GLOBAL_RESULT_FAIL)return retCode;
				}
			}
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public List<UserInfo> queryRole(UserForm userform) {
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("role_name", userform.getRole_name());
			param.put("page_num", userform.getPageNum());
			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryRole(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
	public List<UserInfo> getPrivilegeBySysid(UserForm formBean) {
		List<UserInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			UserDbMgr db = new UserDbMgr(smc);
			HashMap param = new HashMap(); 
			param.put("sysid", formBean.getSysid());
			userlist = db.queryAllprivilege(param);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.endTransaction(smc);
		}
		return userlist;
	}
}