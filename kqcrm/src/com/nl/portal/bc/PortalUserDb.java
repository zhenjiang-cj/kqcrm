package com.nl.portal.bc;

import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.dt.PortalUser;
import com.nl.util.GlobalConst;

/**
 *
 * @author wangtt
 * @creatdate Mar 25, 2014
 */
public class PortalUserDb extends AbstractDB {
	protected String bossCodeStr = null;

	private SqlMapClient smc;
	
	public PortalUserDb(SqlMapClient smc,String bossCodeStr){
		this.smc = smc;
		this.bossCodeStr = bossCodeStr;
		this.getClass().getName();
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 25, 2014
	 */
	public List<PortalUser> queryPortalUserList(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��Ա��Ϣ��");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.queryQlInfoList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��Ա��Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��Ա��Ϣ�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 26, 2014
	 */
	public List<PortalUser> getPortalUserById(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("������Դ����У�飺");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalUserById", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("������Դ����У�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::������Դ����У���д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public List<HashMap> getTownList(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("������Դ����У�飺");
		List<HashMap> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getTownList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("������Դ����У�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::������Դ����У���д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int addPortalUser(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա������һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա������һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա������һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int addPortalUserDept(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա������һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUserDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա������һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա������һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int addPortalUserPost(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա������һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUserPost", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա������һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա������һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int addPortalUserTown(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա������һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUserTown", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա������һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա������һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int delPortalUserDept(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUserDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int delPortalUserPost(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUserPost", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int delPortalUserTown(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUserTown", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int delPortalUser(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public List<PortalUser> detailPortalUser(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��Ա��Ϣ��");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.detailPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��Ա��Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��Ա��Ϣ�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int editPortalUser(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int editPortalUserDept(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUserDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int editPortalUserPost(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUserPost", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Mar 27, 2014
	 */
	public int editPortalUserTown(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUserTown", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 11, 2014
	 */
	public List<PortalUser> queryPortalDeptList(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯��Ϣ��");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.queryPortalDeptList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯��Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯��Ϣ�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public List<PortalUser> getPortalDeptByName(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��֯����У�飺");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalDeptByName", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��֯����У�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��֯����У���д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public String getPortalDeptSno() throws Exception {
		// TODO Auto-generated method stub
		String flowSql = "";
			try{
				flowSql = smc.queryForObject("portalUserSql.getPortalDeptSno", null).toString();
			}catch(Exception e){
				getLogger(bossCodeStr,GlobalConst.ERROR).error(
						"::��ȡ����ʵ�������д�::" + "error:"+e.getMessage());
				throw e;
			}
			return flowSql;	
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int addPortalDept(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����֯����������һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����֯����������һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����֯����������һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int addPortalDeptReal(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����֯������ϵ������һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalDeptReal", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����֯������ϵ������һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����֯������ϵ������һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public List<PortalUser> detailPortalDept(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯������Ϣ��");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.detailPortalDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯������Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯������Ϣ�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int editPortalDpet(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalDpet", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int editPortalDeptReal(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalDeptReal", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 14, 2014
	 */
	public int delPortalDeptReal(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalDeptReal", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public List<PortalUser> getPortalDeptUser(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯������Ϣ��");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalDeptUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯������Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯������Ϣ�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public List<PortalUser> getPortalDeptSon(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯͨѶ¼��֯������Ϣ��");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalDeptSon", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯͨѶ¼��֯������Ϣ������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯͨѶ¼��֯������Ϣ�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public int doDelPortalDept(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.doDelPortalDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public int delPortalDeptSon(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalDeptSon", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}

	/**
	 *
	 * @author wangtt
	 * @creatdate Apr 15, 2014
	 */
	public int delPortalDeptRealAll(HashMap param) {
		// TODO Auto-generated method stub
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����Ա��ɾ��һ�����ݣ�");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalDeptRealAll", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����Ա��ɾ��һ�����ݽ�����");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����Ա��ɾ��һ�������д�::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}
	
	public List<PortalUser> checkOutsourcing(HashMap param){
		getLogger(bossCodeStr,GlobalConst.ENTER).info("����û��Ƿ���ڣ�");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.checkOutsourcing", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("����û��Ƿ���ڽ�����");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::����û��Ƿ�����д�::" + "error:"+e.getMessage());
		}
		return list;
	}
}
