package com.nl.portal.sc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.actionForm.OperatorForm;
import com.nl.portal.actionForm.PortalUserForm;
import com.nl.portal.dt.PortalUser;
import com.nl.portal.bc.OperatorManageDbMgr;
import com.nl.portal.bc.PortalUserDb;
import com.nl.util.GlobalConst;

/**
 *
 * @author wangtt
 * @creatdate Mar 25, 2014
 */
public class PortalUserSC extends AbstractDB {
	private int[] systemId = GlobalConst.Global_SYSTEM_ID;
	String bossCodeStr = "";
	public PortalUserSC(String bossCodeStr)
	{
		//构造方法实现
		this.bossCodeStr = bossCodeStr;
	}
	
	public PortalUserSC(){
		
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 25, 2014
	 */
	public List<PortalUser> queryPortalUserList(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录人员信息：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("user_name", form.getUser_name());
		param.put("user_id", form.getUser_id());
		param.put("user_status", form.getUser_status());
		param.put("startNum", form.getStartNum());
		param.put("endNum", form.getEndNum());
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.queryPortalUserList(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录人员信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录人员信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 26, 2014
	 */
	public List<PortalUser> getPortalUserById(String user_id) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("人力资源工号校验：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("user_id", user_id);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalUserById(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("人力资源工号校验结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::人力资源工号校验有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}
	public   List<HashMap>  getTownList(String town_name,String county_name){
		List<HashMap> list = new ArrayList<HashMap>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("town_name", town_name);
		param.put("county_name", county_name);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getTownList(param);
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::获取乡镇有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int addPortalUser(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始新增人员信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("operatorId", form.getOperatorId());
			param.put("user_id", form.getUser_id());	
			param.put("user_name", form.getUser_name());	
			param.put("msisdn", form.getMsisdn());
			param.put("short_code", form.getShort_code());
			param.put("email", form.getEmail());
			param.put("operatorId", form.getOperatorId());
			param.put("borth_date", form.getBorth_date());
			param.put("sex", form.getSex());
			param.put("work_type", form.getWork_type());
			param.put("user_status", form.getUser_status());
			param.put("user_type", form.getUser_type());

			param.put("sno_dept", form.getSno_dept());
			param.put("dept_begin_date", form.getDept_begin_date());
			param.put("dept_end_date", form.getDept_end_date());

			param.put("postCode",form.getPostCode());
			param.put("post_begin_date",form.getPost_begin_date());
			param.put("post_end_date",form.getPost_end_date());

			param.put("town_id",form.getTown_id());
			param.put("town_begin_date",form.getTown_begin_date());
			param.put("town_end_date",form.getTown_end_date());
			
			
			//新增人员
			retCode = db.addPortalUser(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增人员信息出错！::" + "debug：");
				return retCode;
			}
			
			//新增人员和组织机构关系
			retCode = db.addPortalUserDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增人员和组织机构关系出错！::" + "debug：");
				return retCode;
			}
			
			//新增人员和岗位关系
			if(!"".equals(form.getPostCode())){
				retCode = db.addPortalUserPost(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增人员和岗位关系出错！::" + "debug：");
					return retCode;
				}
			}
			
			//新增人员和乡镇关系
			if(!"".equals(form.getTown_id())){
				retCode = db.addPortalUserTown(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增人员和乡镇关系出错！::" + "debug：");
					return retCode;
				}
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("新增人员信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::新增人员信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int doDelPortalUser(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始删除人员信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("user_id", form.getUser_id());
			
			//删除人员和组织机构关系
			retCode = db.delPortalUserDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除人员和组织机构关系出错！::" + "debug：");
				return retCode;
			}
			
			//删除人员和岗位关系
				retCode = db.delPortalUserPost(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除人员和岗位关系出错！::" + "debug：");
					return retCode;
				}
			
			//删除人员和乡镇关系
				retCode = db.delPortalUserTown(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除人员和乡镇关系出错！::" + "debug：");
					return retCode;
				}
			//删除人员
			retCode = db.delPortalUser(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除人员信息出错！::" + "debug：");
				return retCode;
			}
			
			
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("删除人员信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::删除人员信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public List<PortalUser> detailPortalUser(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录人员信息：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("user_id", form.getUser_id());
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.detailPortalUser(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录人员信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录人员信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int doModifyPortalUser(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始修改人员信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("operatorId", form.getOperatorId());
			
			param.put("user_id", form.getUser_id());	
			param.put("user_name", form.getUser_name());	
			param.put("msisdn", form.getMsisdn());
			param.put("short_code", form.getShort_code());
			param.put("email", form.getEmail());
			param.put("operatorId", form.getOperatorId());
			param.put("borth_date", form.getBorth_date());
			param.put("sex", form.getSex());
			param.put("work_type", form.getWork_type());
			param.put("user_status", form.getUser_status());
			
			param.put("sno_dept", form.getSno_dept());
			param.put("dept_begin_date", form.getDept_begin_date());
			param.put("dept_end_date", form.getDept_end_date());

			param.put("postCode",form.getPostCode());
			param.put("post_begin_date",form.getPost_begin_date());
			param.put("post_end_date",form.getPost_end_date());

			param.put("town_id",form.getTown_id());
			param.put("town_begin_date",form.getTown_begin_date());
			param.put("town_end_date",form.getTown_end_date());
			
			
			//修改人员
			retCode = db.editPortalUser(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员信息出错！::" + "debug：");
				return retCode;
			}
			
			//修改人员和组织机构关系
				//先校验归属部门关系是否有改变，如果修改归属部门关系，将原有关系置为失效，结束时间默认为当前日期的前一天，
//					并新增一条关系数据，开始时间默认为当前日期；没有改变就在原数据上改动
			if(form.getSno_dept().equals(form.getRel_sno_dept())){
				retCode = db.editPortalUserDept(param);
			}else{
				retCode = db.delPortalUserDept(param);
				retCode = db.addPortalUserDept(param);
			}
			
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和组织机构关系出错！::" + "debug：");
				return retCode;
			}
			
			//修改人员和岗位关系
//			校验岗位信息是否有改变，如果发生改变，将原有关系置为失效，结束时间默认为当前日期的前一天，
//			并新增一条关系数据，开始时间默认为当前日期；如果只是开始、结束时间发生改变，岗位没变的话就在原数据上改动
			if(!"".equals(form.getPostCode())){
				if("1".equals(form.getPost_flag())){
					if(form.getPost_flag().equals(form.getRel_sno_post())){
						retCode = db.editPortalUserPost(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和岗位关系出错！::" + "debug：");
							return retCode;
						}
					}else{
						retCode = db.delPortalUserPost(param);
						retCode = db.addPortalUserPost(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和岗位关系出错！::" + "debug：");
							return retCode;
						}
					}
				}else{
					retCode = db.addPortalUserPost(param);
					if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
						getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增人员和岗位关系出错！::" + "debug：");
						return retCode;
					}
				}
				
			}else{
				retCode = db.delPortalUserPost(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和岗位关系出错！::" + "debug：");
					return retCode;
				}
			}
			
			//修改人员和乡镇关系
//			校验归属乡镇信息是否有改变，如果发生改变，将原有关系置为失效，结束时间默认为当前日期的前一天，
//			并新增一条关系数据，开始时间默认为当前日期；如果只是开始、结束时间发生改变，归属乡镇信息没有发生改变的话在原数据上改动即可
			if(!"".equals(form.getTown_id())){
				if("1".equals(form.getTown_flag())){
					if(form.getTown_id().equals(form.getRel_sno_town())){
						retCode = db.editPortalUserTown(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和乡镇关系出错！::" + "debug：");
							return retCode;
						}
					}else{
						retCode = db.delPortalUserTown(param);
						retCode = db.addPortalUserTown(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和乡镇关系出错！::" + "debug：");
							return retCode;
						}
					}
				}else{
					retCode = db.addPortalUserTown(param);
					if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
						getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和乡镇关系出错！::" + "debug：");
						return retCode;
					}
				}
			}else{
				retCode = db.delPortalUserTown(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改人员和乡镇关系出错！::" + "debug：");
					return retCode;
				}
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("修改人员信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::修改人员信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 11, 2014
	 */
	public List<PortalUser> queryPortalDeptList(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织信息：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("dept_name", form.getDept_name());
		param.put("startNum", form.getStartNum());
		param.put("endNum", form.getEndNum());
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.queryPortalDeptList(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @param sno_dept 
	 * @creatdate Apr 14, 2014
	 */
	public List<PortalUser> getPortalDeptByName(String dept_name, String sno_dept) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("组织机构校验：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("dept_name", dept_name);
		param.put("sno_dept", sno_dept);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalDeptByName(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("组织机构校验结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::组织机构校验有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int addPortalDept(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始新增组织机构信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("operatorId", form.getOperatorId());
			param.put("son_dept_name", form.getSon_dept_name());	
			param.put("dept_name", form.getDept_name());	

			param.put("sno_dept", form.getSno_dept());
			param.put("dept_begin_date", form.getDept_begin_date());
			param.put("dept_end_date", form.getDept_end_date());

			//获取新组织机构的sno序列
			String seq_sno_dept = db.getPortalDeptSno();
			
			param.put("seq_sno_dept", seq_sno_dept);	
			
			//新增组织机构
			retCode = db.addPortalDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增组织机构信息出错！::" + "debug：");
				return retCode;
			}
			
			//新增组织机构关系
			retCode = db.addPortalDeptReal(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::新增组织机构关系出错！::" + "debug：");
				return retCode;
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("新增组织机构信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::新增组织机构信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public List<PortalUser> detailPortalDept(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织机构信息：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("sno_dept", form.getSno_dept());
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.detailPortalDept(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织机构信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织机构信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int doModifyPortalDept(PortalUserForm form) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始修改组织机构信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("operatorId", form.getOperatorId());
			
			param.put("son_dept_name", form.getSon_dept_name());	
			param.put("son_dept_sno", form.getSon_dept_sno());	
			param.put("seq_sno_dept", form.getSon_dept_sno());
			
			param.put("dept_name", form.getDept_name());	
			param.put("sno_dept", form.getSno_dept());
			param.put("dept_begin_date", form.getDept_begin_date());
			param.put("dept_end_date", form.getDept_end_date());
			

			
			
			//修改组织机构
			retCode = db.editPortalDpet(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改组织机构信息出错！::" + "debug：");
				return retCode;
			}
			
			//修改组织机构关系
				//先校验归属部门关系是否有改变，如果修改归属部门关系，将原有关系置为失效，结束时间默认为当前日期的前一天，
//					并新增一条关系数据，开始时间默认为当前日期；没有改变就在原数据上改动
			if(form.getSno_dept().equals(form.getRel_sno_dept())){
				retCode = db.editPortalDeptReal(param);
			}else{
				retCode = db.delPortalDeptReal(param);
				retCode = db.addPortalDeptReal(param);
			}
			
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::修改组织机构关系出错！::" + "debug：");
				return retCode;
			}
			
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("修改组织机构信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::修改组织机构信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public List<PortalUser> getPortalDeptUser(String sno_dept) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织机构信息：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("sno_dept", sno_dept);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalDeptUser(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织机构信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织机构信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public List<PortalUser> getPortalDeptSon(String sno_dept) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织机构信息：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("sno_dept", sno_dept);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalDeptSon(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织机构信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织机构信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public int doDelPortalDept(PortalUserForm formBean) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始删除组织信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("sno_dept", formBean.getSno_dept());
			param.put("son_dept_sno", formBean.getSno_dept());
			
			//删除组织机构信息
			retCode = db.doDelPortalDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除组织信息出错！::" + "debug：");
				return retCode;
			}
			//删除组织关系
			retCode = db.delPortalDeptReal(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除组织信息出错！::" + "debug：");
				return retCode;
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("删除组织信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::删除组织信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public int doDelPortalDeptAll(PortalUserForm formBean) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始删除组织信息");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("sno_dept", formBean.getSno_dept());
			
//			//删除组织机构信息
//			retCode = db.doDelPortalDept(param);
//			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
//				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除组织信息出错！::" + "debug：");
//				return retCode;
//			}
			//删除该组织机构和其下的所有子组织机构
			retCode = db.delPortalDeptSon(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除该组织机构下的所有子组织机构出错！::" + "debug：");
				return retCode;
			}
			//删除该组织机构下和所有子组织机构之间的关系
			retCode = db.delPortalDeptRealAll(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::删除该组织机构下和所有子组织机构之间的关系出错！::" + "debug：");
				return retCode;
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("删除组织信息结束。");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::删除组织信息有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	
	public List checkOutsourcing(HashMap param){
		getLogger(bossCodeStr,GlobalConst.ENTER).info("开始检查用户是否存在：");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.checkOutsourcing(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("检查用户是否存在结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::检查用户是否存在有错！::" + "error："+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}
}
