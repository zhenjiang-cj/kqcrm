package com.nl.portal.sc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.csp.bsf.pwm.service.impl.PasswordForMD5;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.actionForm.CrmForm;
import com.nl.portal.actionForm.UserForm;
import com.nl.portal.bc.CrmDb;
import com.nl.portal.bc.UserDbMgr;
import com.nl.portal.dt.CrmInfo;
import com.nl.portal.dt.UserInfo;
import com.nl.util.GlobalConst;

public class CrmSc extends AbstractDB {
	public List<CrmInfo> queryKh(CrmForm userform)
	{
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("org_ids", userform.getOrg_ids());
			param.put("kh_id", userform.getKh_id());
			param.put("kh_name", userform.getKh_name());
			param.put("page_num", userform.getPageNum());
			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryKh(param);
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

			CrmDb db = new CrmDb(smc);
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
	public int doKhAdd(CrmForm form) throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			CrmDb db = new CrmDb(smc);
			param.put("kh_id",form.getRegion()+form.getKh_id());
			param.put("kh_name",form.getKh_name());
			param.put("provinces",form.getProvinces()==null?"":form.getProvinces());
			param.put("city",form.getCity()==null?"":form.getCity());
			param.put("region",form.getRegion()==null?"":form.getRegion());
			
			param.put("kh_addr",form.getKh_addr());
			param.put("kh_phone1",form.getKh_phone1());
			param.put("kh_phone2",form.getKh_phone2());
			param.put("kh_card",form.getKh_card());
			param.put("introduce_name",form.getIntroduce_name());
			param.put("create_id",form.getOperatorId());
			
			retCode = db.doKhAdd(param);
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
	public int doKhDel(CrmForm form)  throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			CrmDb db = new CrmDb(smc);
//			param.put("create_id",form.getOperatorId());
			
			param.put("kh_id",form.getKh_id());
			
			retCode = db.doKhDel(param);
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
	public int doHtDel(CrmForm form)  throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			CrmDb db = new CrmDb(smc);
//			param.put("create_id",form.getOperatorId());
			
			param.put("ht_id",form.getHt_id());
			
			retCode = db.doHtDel(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL)return retCode;
			
			//删除回访记录
			retCode = db.doHfDel(param);
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
	public List<CrmInfo> queryKhListById(CrmForm userform)
	{
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("kh_id", userform.getKh_id());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryKhListById(param);
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
	public int doKhEdit(CrmForm form)  throws Exception  {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			//数据访问对象
			CrmDb db = new CrmDb(smc);
			param.put("kh_id",form.getKh_id());
			param.put("kh_name",form.getKh_name());
			param.put("provinces",form.getProvinces()==null?"":form.getProvinces());
			param.put("city",form.getCity()==null?"":form.getCity());
			param.put("region",form.getRegion()==null?"":form.getRegion());
			
			param.put("kh_addr",form.getKh_addr());
			param.put("kh_phone1",form.getKh_phone1());
			param.put("kh_phone2",form.getKh_phone2());
			param.put("kh_card",form.getKh_card());
			param.put("introduce_name",form.getIntroduce_name());
			param.put("create_id",form.getOperatorId());
			
			
			retCode = db.doKhEdit(param);
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public List<CrmInfo> queryHt(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("org_ids", userform.getOrg_ids());
			param.put("kh_id", userform.getKh_id());
			param.put("kh_name", userform.getKh_name()==null?"":userform.getKh_name());
			param.put("ht_begin_date", userform.getHt_begin_date());
			param.put("ht_end_date", userform.getHt_end_date());
			param.put("page_num", userform.getPageNum());
			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryHt(param);
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
	public List<CrmInfo> queryIntroduce(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("kh_id", userform.getKh_id());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryIntroduce(param);
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
	public int doHtAdd(CrmForm form)  throws Exception{
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			//数据访问对象
			CrmDb db = new CrmDb(smc);
			param.put("kh_id",form.getKh_id());
			param.put("ht_id",db.getht_id());
			param.put("ht_code",form.getHt_code());
			param.put("ht_date_first",form.getHt_date_first());
			param.put("ht_date_current",form.getHt_date_current());
			param.put("ht_pledge",form.getHt_pledge());
			param.put("ht_rent",form.getHt_rent());
			param.put("prod_name",form.getProd_name());
			param.put("prod_code",form.getProd_code());
			param.put("ht_year",form.getHt_year());
			param.put("remark",form.getRemark());
			param.put("create_id",form.getOperatorId());
			
			retCode = db.doHtAdd(param);
			//插入四条回访信息
			param.put("hf_id1",db.gethf_id());
			param.put("hf_id2",db.gethf_id());
			param.put("hf_id3",db.gethf_id());
			param.put("hf_id4",db.gethf_id());
			retCode = db.doHfAdd(param);
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public List<CrmInfo> queryIntroduceByht(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("ht_id", userform.getHt_id());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryIntroduceByht(param);
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
	public List<CrmInfo> queryhtByid(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("ht_id", userform.getHt_id());
//			param.put("page_num", userform.getPageNum());
//			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryhtByid(param);
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
	public int doHtEdit(CrmForm form)  throws Exception{
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			//数据访问对象
			CrmDb db = new CrmDb(smc);
			param.put("ht_id",form.getHt_id());
			param.put("ht_code",form.getHt_code());
			param.put("ht_date_first",form.getHt_date_first());
			param.put("ht_date_current",form.getHt_date_current());
			param.put("ht_pledge",form.getHt_pledge());
			param.put("ht_rent",form.getHt_rent());
			param.put("prod_name",form.getProd_name());
			param.put("prod_code",form.getProd_code());
			param.put("ht_year",form.getHt_year());
			param.put("remark",form.getRemark());
			param.put("create_id",form.getOperatorId());
			
			
			retCode = db.doHtEdit(param);
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}
	public List<CrmInfo> queryHt1(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("org_ids", userform.getOrg_ids());
			param.put("kh_name", userform.getKh_name());
			param.put("ht_begin_date", userform.getHt_begin_date());
			param.put("ht_end_date", userform.getHt_end_date());
			param.put("page_num", userform.getPageNum());
			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryHt1(param);
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
	public List<CrmInfo> queryOrgByUser(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("sno", userform.getOperatorId());
			userlist = db.queryOrgByUser(param);
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
	public List<CrmInfo> queryHf(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("org_ids", userform.getOrg_ids());
			param.put("kh_name", userform.getKh_name());
			param.put("hf_status", userform.getHf_status());
			param.put("hf_begin_date", userform.getHf_begin_date());
			param.put("hf_end_date", userform.getHf_end_date());
			
			
			
			param.put("page_num", userform.getPageNum());
			param.put("page_size", userform.getNumPerPage());
			userlist = db.queryHf(param);
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
	public List<CrmInfo> queryhfByid(CrmForm userform) {
		List<CrmInfo> userlist = null;
		SqlMapClient smc = null;
		try
		{
			smc = getSqlMapClient();
			smc.startTransaction();
			CrmDb db = new CrmDb(smc);
			HashMap param = new HashMap(); 
			param.put("ht_id", userform.getHt_id());
			userlist = db.queryhfByid(param);
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
	public int doHfEdit(CrmForm form) throws Exception {
		SqlMapClient smc = null;
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try {
			smc = getSqlMapClient();
			smc.startTransaction();
			Map<String, String> param = new HashMap<String, String>();
			//数据访问对象
			//数据访问对象
			CrmDb db = new CrmDb(smc);
			param.put("ht_id",form.getHt_id());
			param.put("hf_id1",form.getHf_id1());
			param.put("hf_id2",form.getHf_id2());
			param.put("hf_id3",form.getHf_id3());
			param.put("hf_id4",form.getHf_id4());

			param.put("hf_date_must1",form.getHf_date_must1());
			param.put("hf_date_must2",form.getHf_date_must2());
			param.put("hf_date_must3",form.getHf_date_must3());
			param.put("hf_date_must4",form.getHf_date_must4());
			param.put("hf_date_fact1",form.getHf_date_fact1());
			param.put("hf_date_fact2",form.getHf_date_fact2());
			param.put("hf_date_fact3",form.getHf_date_fact3());
			param.put("hf_date_fact4",form.getHf_date_fact4());
			param.put("hf_remark1",form.getHf_remark1());
			param.put("hf_remark2",form.getHf_remark2());
			param.put("hf_remark3",form.getHf_remark3());
			param.put("hf_remark4",form.getHf_remark4());
			param.put("hf_user_name1",form.getHf_user_name1());
			param.put("hf_user_name2",form.getHf_user_name2());
			param.put("hf_user_name3",form.getHf_user_name3());
			param.put("hf_user_name4",form.getHf_user_name4());
			
			retCode = db.doHfEdit1(param);
			retCode = db.doHfEdit2(param);
			retCode = db.doHfEdit3(param);
			retCode = db.doHfEdit4(param);
			
			smc.commitTransaction();
		} catch (Exception e) {
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		} finally {
			this.endTransaction(smc);
		}
		return retCode;
	}

}
