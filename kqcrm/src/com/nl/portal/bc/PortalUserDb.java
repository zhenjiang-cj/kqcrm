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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录人员信息：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.queryQlInfoList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录人员信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录人员信息有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("人力资源工号校验：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalUserById", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("人力资源工号校验结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::人力资源工号校验有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("人力资源工号校验：");
		List<HashMap> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getTownList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("人力资源工号校验结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::人力资源工号校验有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表新增一条数据：");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表新增一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表新增一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表新增一条数据：");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUserDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表新增一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表新增一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表新增一条数据：");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUserPost", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表新增一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表新增一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表新增一条数据：");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalUserTown", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表新增一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表新增一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUserDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUserPost", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUserTown", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录人员信息：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.detailPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录人员信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录人员信息有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUserDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUserPost", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalUserTown", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织信息：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.queryPortalDeptList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织信息有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("组织机构校验：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalDeptByName", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("组织机构校验结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::组织机构校验有错！::" + "error:"+e.getMessage());
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
						"::获取任务实例序列有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向组织机构表新增一条数据：");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向组织机构表新增一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向组织机构表新增一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向组织机构关系表新增一条数据：");
		int retCode = 0;
		try
		{
			smc.insert("portalUserSql.addPortalDeptReal", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向组织机构关系表新增一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向组织机构关系表新增一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织机构信息：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.detailPortalDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织机构信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织机构信息有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalDpet", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.editPortalDeptReal", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalDeptReal", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织机构信息：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalDeptUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织机构信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织机构信息有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询通讯录组织机构信息：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.getPortalDeptSon", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询通讯录组织机构信息结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询通讯录组织机构信息有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.doDelPortalDept", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalDeptSon", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
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
		getLogger(bossCodeStr,GlobalConst.ENTER).info("向人员表删除一条数据：");
		int retCode = 0;
		try
		{
			smc.update("portalUserSql.delPortalDeptRealAll", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("向人员表删除一条数据结束。");
		} catch(Exception e)
		{
			retCode = -1;
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::向人员表删除一条数据有错！::" + "error:"+e.getMessage());
		}
		
		return retCode;
	}
	
	public List<PortalUser> checkOutsourcing(HashMap param){
		getLogger(bossCodeStr,GlobalConst.ENTER).info("检查用户是否存在：");
		List<PortalUser> list = null;
		
		try{
			list = smc.queryForList("portalUserSql.checkOutsourcing", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("检查用户是否存在结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::检查用户是否存在有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
}
