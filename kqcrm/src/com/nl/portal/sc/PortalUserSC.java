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
		//���췽��ʵ��
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��Ա��Ϣ��");
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
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��Ա��Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��Ա��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("������Դ����У�飺");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("user_id", user_id);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalUserById(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("������Դ����У�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::������Դ����У���д�::" + "error��"+e.getMessage());
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
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ȡ�����д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼ������Ա��Ϣ");
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
			
			
			//������Ա
			retCode = db.addPortalUser(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::������Ա��Ϣ����::" + "debug��");
				return retCode;
			}
			
			//������Ա����֯������ϵ
			retCode = db.addPortalUserDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::������Ա����֯������ϵ����::" + "debug��");
				return retCode;
			}
			
			//������Ա�͸�λ��ϵ
			if(!"".equals(form.getPostCode())){
				retCode = db.addPortalUserPost(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::������Ա�͸�λ��ϵ����::" + "debug��");
					return retCode;
				}
			}
			
			//������Ա�������ϵ
			if(!"".equals(form.getTown_id())){
				retCode = db.addPortalUserTown(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::������Ա�������ϵ����::" + "debug��");
					return retCode;
				}
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("������Ա��Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::������Ա��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼɾ����Ա��Ϣ");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("user_id", form.getUser_id());
			
			//ɾ����Ա����֯������ϵ
			retCode = db.delPortalUserDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����Ա����֯������ϵ����::" + "debug��");
				return retCode;
			}
			
			//ɾ����Ա�͸�λ��ϵ
				retCode = db.delPortalUserPost(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����Ա�͸�λ��ϵ����::" + "debug��");
					return retCode;
				}
			
			//ɾ����Ա�������ϵ
				retCode = db.delPortalUserTown(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����Ա�������ϵ����::" + "debug��");
					return retCode;
				}
			//ɾ����Ա
			retCode = db.delPortalUser(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����Ա��Ϣ����::" + "debug��");
				return retCode;
			}
			
			
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("ɾ����Ա��Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::ɾ����Ա��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��Ա��Ϣ��");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("user_id", form.getUser_id());
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.detailPortalUser(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��Ա��Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��Ա��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼ�޸���Ա��Ϣ");
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
			
			
			//�޸���Ա
			retCode = db.editPortalUser(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա��Ϣ����::" + "debug��");
				return retCode;
			}
			
			//�޸���Ա����֯������ϵ
				//��У��������Ź�ϵ�Ƿ��иı䣬����޸Ĺ������Ź�ϵ����ԭ�й�ϵ��ΪʧЧ������ʱ��Ĭ��Ϊ��ǰ���ڵ�ǰһ�죬
//					������һ����ϵ���ݣ���ʼʱ��Ĭ��Ϊ��ǰ���ڣ�û�иı����ԭ�����ϸĶ�
			if(form.getSno_dept().equals(form.getRel_sno_dept())){
				retCode = db.editPortalUserDept(param);
			}else{
				retCode = db.delPortalUserDept(param);
				retCode = db.addPortalUserDept(param);
			}
			
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա����֯������ϵ����::" + "debug��");
				return retCode;
			}
			
			//�޸���Ա�͸�λ��ϵ
//			У���λ��Ϣ�Ƿ��иı䣬��������ı䣬��ԭ�й�ϵ��ΪʧЧ������ʱ��Ĭ��Ϊ��ǰ���ڵ�ǰһ�죬
//			������һ����ϵ���ݣ���ʼʱ��Ĭ��Ϊ��ǰ���ڣ����ֻ�ǿ�ʼ������ʱ�䷢���ı䣬��λû��Ļ�����ԭ�����ϸĶ�
			if(!"".equals(form.getPostCode())){
				if("1".equals(form.getPost_flag())){
					if(form.getPost_flag().equals(form.getRel_sno_post())){
						retCode = db.editPortalUserPost(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�͸�λ��ϵ����::" + "debug��");
							return retCode;
						}
					}else{
						retCode = db.delPortalUserPost(param);
						retCode = db.addPortalUserPost(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�͸�λ��ϵ����::" + "debug��");
							return retCode;
						}
					}
				}else{
					retCode = db.addPortalUserPost(param);
					if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
						getLogger(bossCodeStr, GlobalConst.EXIT).error("::������Ա�͸�λ��ϵ����::" + "debug��");
						return retCode;
					}
				}
				
			}else{
				retCode = db.delPortalUserPost(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�͸�λ��ϵ����::" + "debug��");
					return retCode;
				}
			}
			
			//�޸���Ա�������ϵ
//			У�����������Ϣ�Ƿ��иı䣬��������ı䣬��ԭ�й�ϵ��ΪʧЧ������ʱ��Ĭ��Ϊ��ǰ���ڵ�ǰһ�죬
//			������һ����ϵ���ݣ���ʼʱ��Ĭ��Ϊ��ǰ���ڣ����ֻ�ǿ�ʼ������ʱ�䷢���ı䣬����������Ϣû�з����ı�Ļ���ԭ�����ϸĶ�����
			if(!"".equals(form.getTown_id())){
				if("1".equals(form.getTown_flag())){
					if(form.getTown_id().equals(form.getRel_sno_town())){
						retCode = db.editPortalUserTown(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�������ϵ����::" + "debug��");
							return retCode;
						}
					}else{
						retCode = db.delPortalUserTown(param);
						retCode = db.addPortalUserTown(param);
						if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
							getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�������ϵ����::" + "debug��");
							return retCode;
						}
					}
				}else{
					retCode = db.addPortalUserTown(param);
					if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
						getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�������ϵ����::" + "debug��");
						return retCode;
					}
				}
			}else{
				retCode = db.delPortalUserTown(param);
				if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
					getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���Ա�������ϵ����::" + "debug��");
					return retCode;
				}
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("�޸���Ա��Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::�޸���Ա��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯��Ϣ��");
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
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯��Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��֯����У�飺");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("dept_name", dept_name);
		param.put("sno_dept", sno_dept);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalDeptByName(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��֯����У�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��֯����У���д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼ������֯������Ϣ");
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

			//��ȡ����֯������sno����
			String seq_sno_dept = db.getPortalDeptSno();
			
			param.put("seq_sno_dept", seq_sno_dept);	
			
			//������֯����
			retCode = db.addPortalDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::������֯������Ϣ����::" + "debug��");
				return retCode;
			}
			
			//������֯������ϵ
			retCode = db.addPortalDeptReal(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::������֯������ϵ����::" + "debug��");
				return retCode;
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("������֯������Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::������֯������Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯������Ϣ��");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("sno_dept", form.getSno_dept());
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.detailPortalDept(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯������Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯������Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼ�޸���֯������Ϣ");
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
			

			
			
			//�޸���֯����
			retCode = db.editPortalDpet(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���֯������Ϣ����::" + "debug��");
				return retCode;
			}
			
			//�޸���֯������ϵ
				//��У��������Ź�ϵ�Ƿ��иı䣬����޸Ĺ������Ź�ϵ����ԭ�й�ϵ��ΪʧЧ������ʱ��Ĭ��Ϊ��ǰ���ڵ�ǰһ�죬
//					������һ����ϵ���ݣ���ʼʱ��Ĭ��Ϊ��ǰ���ڣ�û�иı����ԭ�����ϸĶ�
			if(form.getSno_dept().equals(form.getRel_sno_dept())){
				retCode = db.editPortalDeptReal(param);
			}else{
				retCode = db.delPortalDeptReal(param);
				retCode = db.addPortalDeptReal(param);
			}
			
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::�޸���֯������ϵ����::" + "debug��");
				return retCode;
			}
			
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("�޸���֯������Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::�޸���֯������Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯������Ϣ��");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("sno_dept", sno_dept);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalDeptUser(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯������Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯������Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯������Ϣ��");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		HashMap param = new HashMap();
		param.put("sno_dept", sno_dept);
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.getPortalDeptSon(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯������Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯������Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼɾ����֯��Ϣ");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("sno_dept", formBean.getSno_dept());
			param.put("son_dept_sno", formBean.getSno_dept());
			
			//ɾ����֯������Ϣ
			retCode = db.doDelPortalDept(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����֯��Ϣ����::" + "debug��");
				return retCode;
			}
			//ɾ����֯��ϵ
			retCode = db.delPortalDeptReal(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����֯��Ϣ����::" + "debug��");
				return retCode;
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("ɾ����֯��Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::ɾ����֯��Ϣ�д�::" + "error��"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼɾ����֯��Ϣ");
		int retCode = 0;
		SqlMapClient smc = null;
				
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			smc.startTransaction();
			
			HashMap param = new HashMap();
			param.put("sno_dept", formBean.getSno_dept());
			
//			//ɾ����֯������Ϣ
//			retCode = db.doDelPortalDept(param);
//			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
//				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ����֯��Ϣ����::" + "debug��");
//				return retCode;
//			}
			//ɾ������֯���������µ���������֯����
			retCode = db.delPortalDeptSon(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ������֯�����µ���������֯��������::" + "debug��");
				return retCode;
			}
			//ɾ������֯�����º���������֯����֮��Ĺ�ϵ
			retCode = db.delPortalDeptRealAll(param);
			if(retCode == GlobalConst.GLOBAL_RESULT_FAIL){
				getLogger(bossCodeStr, GlobalConst.EXIT).error("::ɾ������֯�����º���������֯����֮��Ĺ�ϵ����::" + "debug��");
				return retCode;
			}
			
			smc.commitTransaction();
			getLogger(bossCodeStr,GlobalConst.EXIT).info("ɾ����֯��Ϣ������");
		}catch(Exception e){
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::ɾ����֯��Ϣ�д�::" + "error��"+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return retCode;
	}

	
	public List checkOutsourcing(HashMap param){
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ʼ����û��Ƿ���ڣ�");
		List<PortalUser> list = new ArrayList<PortalUser>();
		SqlMapClient smc = null;
		
		try{
			smc = getSqlMapClient();
			PortalUserDb db = new PortalUserDb(smc,bossCodeStr);
			list = db.checkOutsourcing(param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����û��Ƿ���ڽ�����");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����û��Ƿ�����д�::" + "error��"+e.getMessage());
		}finally{
			this.endTransaction(smc);
		}
		return list;
	}
}
