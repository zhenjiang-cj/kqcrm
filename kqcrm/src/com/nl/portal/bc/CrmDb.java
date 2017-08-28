package com.nl.portal.bc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.portal.dt.CrmInfo;
import com.nl.util.GlobalConst;

public class CrmDb extends AbstractDB {
	private final Logger logger = Logger.getLogger(this.getClass());
	private SqlMapClient smc;
	
	protected String bossCodeStr = null;
	
	public CrmDb(String bossCodeStr){
		this.bossCodeStr = bossCodeStr;
		this.getClass().getName();
		
	}

	public CrmDb(SqlMapClient smc)
	{
		this.smc = smc;
	}

	public List<CrmInfo> queryKh(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryKh", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<CrmInfo> queryExpKh(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryExpKh", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	public String getkh_id(String regionId) throws Exception {
		String flowSql = "";
		try{
			flowSql = smc.queryForObject("CrmSql.getKhid", regionId).toString();
					smc.insert("CrmSql.addkhid", regionId);//��������
		}catch(Exception e){
			getLogger("",GlobalConst.ERROR).error(
					"::��ȡ�����������д�::" + "error:"+e.getMessage());
			throw e;
		}
		return flowSql;	
	}
	public String getht_id() throws Exception {
		String flowSql = "";
		try{
			flowSql = smc.queryForObject("CrmSql.gethtid", null).toString();
					smc.insert("CrmSql.addhtid", null);//��������
		}catch(Exception e){
			getLogger("",GlobalConst.ERROR).error(
					"::��ȡ�����������д�::" + "error:"+e.getMessage());
			throw e;
		}
		return flowSql;	
	}
	public String gethf_id() throws Exception {
		String flowSql = "";
		try{
			flowSql = smc.queryForObject("CrmSql.gethfid", null).toString();
					smc.insert("CrmSql.addhfid", null);//��������
		}catch(Exception e){
			getLogger("",GlobalConst.ERROR).error(
					"::��ȡ�����������д�::" + "error:"+e.getMessage());
			throw e;
		}
		return flowSql;	
	}

	public int doKhAdd(Map<String, String> param) throws Exception { 
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doKhAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	
	public List<CrmInfo> queryYxkh(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�����û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryYxkh", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�����û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�����û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	
	public List<CrmInfo> queryYxkhExp(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryYxkhExp", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	
	public int doYxkhAdd(Map<String, String> param) throws Exception { 
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doYxkhAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public int doKhDel(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doKhDel", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	
	public int doYxkhDel(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("CrmSql.doYxkhDel", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	
	public int doModifyYxkh(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.update("CrmSql.doModifyYxkh", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	

	public int copyYxkhTokh(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.copyYxkhTokh", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	
	public int doHtDel(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHtDel", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doHfDel(Map<String, String> param) throws Exception  {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfDel", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public List<CrmInfo> queryKhListById(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryKhListById", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	
	public CrmInfo queryYxkhById(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�����û���");
		CrmInfo detail = null;
		
		try{
			detail = (CrmInfo)smc.queryForObject("CrmSql.queryYxkhById", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�����û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�����û��д�::" + "error:"+e.getMessage());
		}
		return detail;
	}
	
	public List<CrmInfo> queryKhIntroduce(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryKhIntroduce", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doKhEdit(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doKhEdit", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public List<CrmInfo> queryHt(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryHt", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doHtAdd(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHtAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doHfAdd(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}

	public List<CrmInfo> queryIntroduce(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryIntroduce", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<CrmInfo> queryIntroduceByht(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryIntroduceByht", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<CrmInfo> queryhtByid(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryhtByid", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<CrmInfo> queryhtBykh(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryhtBykh", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doHtEdit(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHtEdit", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public List<CrmInfo> queryHt1(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryHt1", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<CrmInfo> queryHtExp(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryHtExp", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<CrmInfo> queryOrgByUser(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryOrgByUser", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<CrmInfo> queryHf(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryHf", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	
	public List<CrmInfo> queryHfExp(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryHfExp", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public List<CrmInfo> queryhfByid(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryhfByid", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	public List<CrmInfo> queryhfBykh(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryhfBykh", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return list;
	}

	public int doHfEdit(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfEdit", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doHfEdit1(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfEdit1", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doHfEdit2(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfEdit2", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doHfEdit3(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfEdit3", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	public int doHfEdit4(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doHfEdit4", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	
	public CrmInfo queryKhById(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û���");
		CrmInfo detail = null;
		
		try{
			detail = (CrmInfo)smc.queryForObject("CrmSql.queryKhById", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û��д�::" + "error:"+e.getMessage());
		}
		return detail;
	}
	
	public int doRepairAdd(Map<String, String> param) throws Exception {
		int retCode = GlobalConst.GLOBAL_RESULT_SUCCESS;
		try
		{
			smc.insert("CrmSql.doRepairAdd", param);
		}catch(Exception e){
			retCode = GlobalConst.GLOBAL_RESULT_FAIL;
			throw e;
		}
		return retCode;
	}
	
	public List<CrmInfo> queryRepairRecord(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�û�ά����¼��");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryRepairRecord", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�û�ά����¼������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�û�ά����¼�д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	
	public List<CrmInfo> queryDeviceReportSum(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�豸ͳ�ƣ�");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryDeviceReportSum", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�豸ͳ�ƽ�����");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�豸ͳ���д�::" + "error:"+e.getMessage());
		}
		return list;
	}
	
	public List<CrmInfo> queryDeviceReportDetail(HashMap param) {
		getLogger(bossCodeStr,GlobalConst.ENTER).info("��ѯ�豸ͳ�Ƽ�¼��");
		List<CrmInfo> list = null;
		
		try{
			list = smc.queryForList("CrmSql.queryDeviceReportDetail", param);
			getLogger(bossCodeStr,GlobalConst.EXIT).info("��ѯ�豸ͳ�Ƽ�¼������");
		}catch(Exception e){
			getLogger(bossCodeStr,GlobalConst.ERROR).error("::��ѯ�豸ͳ�Ƽ�¼�д�::" + "error:"+e.getMessage());
		}
		return list;
	}

}
