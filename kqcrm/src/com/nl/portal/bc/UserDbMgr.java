package com.nl.portal.bc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.actionForm.UserForm;
import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.HtzjCodeBm;
import com.nl.portal.dt.PortalUser;
import com.nl.portal.dt.UserInfo;
import com.nl.util.GlobalConst;

public class UserDbMgr extends AbstractDB{
	private final Logger logger = Logger.getLogger(this.getClass());
	private SqlMapClient smc;
	
	protected String bossCodeStr = null;
	
	public UserDbMgr(String bossCodeStr){
		this.bossCodeStr = bossCodeStr;
		this.getClass().getName();
		
	}

	public UserDbMgr(SqlMapClient smc)
	{
		this.smc = smc;
	}

	public List<UserInfo> queryUser(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询用户：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询用户结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询用户有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public String getSno() throws Exception {
		String flowSql = "";
		try{
			flowSql = smc.queryForObject("UserSql.getSno", null).toString();
					smc.insert("UserSql.addSno", null);//序列增长
		}catch(Exception e){
			getLogger("",GlobalConst.ERROR).error(
					"::获取工单流序列有错！::" + "error:"+e.getMessage());
			throw e;
		}
		return flowSql;	
	}
	public String getRole() throws Exception {
		String flowSql = "";
		try{
			flowSql = smc.queryForObject("UserSql.getRole", null).toString();
					smc.insert("UserSql.addRole", null);//序列增长
		}catch(Exception e){
			getLogger("",GlobalConst.ERROR).error(
					"::获取工单流序列有错！::" + "error:"+e.getMessage());
			throw e;
		}
		return flowSql;	
	}
	public List<UserInfo> getCityByPro(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询用户：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.getCityByPro", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询用户结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询用户有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<UserInfo> getprovinces(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询用户：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.getprovinces", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询用户结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询用户有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<UserInfo> getRegionByCity(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询用户：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.getRegionByCity", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询用户结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询用户有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public int doUserAdd(Map<String, String> param) throws Exception { 
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("UserSql.doUserAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int doUserRegionAdd(Map<String, String> param) throws Exception { 
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("UserSql.doUserRegionAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doUserRegionAdd1(Map<String, String> param) throws Exception { 
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("UserSql.doUserRegionAdd1", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doUserDel(Map<String, String> param) throws Exception { 
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("UserSql.doUserDel", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public List<UserInfo> queryUserListBysno(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询用户：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryUserListBysno", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询用户结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询用户有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doUserEdit(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.doUserEdit", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doUserRegionEdit(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
//			smc.update("UserSql.doUserRegionEdit", param);
			smc.update("UserSql.doUserRegionEdit1", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public List<UserInfo> queryAllRole(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryAllRole", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<UserInfo> queryAllRolebysno(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryAllRolebysno", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<UserInfo> queryRoleByUser(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryRoleByUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int delUserRole(Map<String, String> param) throws Exception{
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.delUserRole", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int addUserRole(Map<String, String> param) throws Exception{
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.addUserRole", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public List<UserInfo> queryRole(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryRole", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<UserInfo> queryAllprivilege(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryAllprivilege", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<UserInfo> getRegionTree(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryAllRegion", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<UserInfo> getRegionByUser(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.getRegionByUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doRoleAdd(Map<String, String> param)  throws Exception{
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.doRoleAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int addRolePriv(Map<String, String> param) throws Exception{
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.addRolePriv", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int doRoleDel(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.doRoleDel", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public List<UserInfo> queryRoleList(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.queryRoleList", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<UserInfo> getPrivilegeByRoleid(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("查询所有角色：");
		List<UserInfo> list = null;
		
		try{
			list = smc.queryForList("UserSql.getPrivilegeByRoleid", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("查询所有角色结束。");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::查询所有角色有错！::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doRoleEdit(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.doRoleEdit", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int editRolePriv(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.editRolePriv", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int delRolePriv(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("UserSql.delRolePriv", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public List<UserInfo> doUserRegioncheck(Map<String, String> param) throws Exception   {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		List<UserInfo> list = null;
		try
		{
			list = smc.queryForList("UserSql.doUserRegioncheck", param);
			//smc.update("UserSql.doUserRegioncheck", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return list;
	}

}
