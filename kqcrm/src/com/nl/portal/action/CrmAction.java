package com.nl.portal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nl.base.BaseAppAction;
import com.nl.base.utils.GlobalFunc;
import com.nl.base.utils.GlobalRsDt;
import com.nl.portal.actionForm.CrmForm;
import com.nl.portal.dt.CrmInfo;
import com.nl.portal.sc.CrmSc;

import com.nl.util.GlobalConst;
import com.nl.util.GlobalUtil;
import com.nl.util.SessionConst;
import com.nl.util.SessionData;

public class CrmAction extends BaseAppAction {
	public void doJump(int retCode,String url,String objString,HttpServletRequest request,HttpServletResponse response){
		GlobalRsDt rsDt = new GlobalRsDt();
		String remark = "";
		String jsonString = "";
		PrintWriter pWriter = null;
		
		if(retCode==0){
			remark = objString+"成功";	
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_SUCCESS);
			rsDt.setMessage(remark);
			rsDt.setCallbackType("forward");
			
//			rsDt.setNavTabId("userinfo");
//			rsDt.setForwardUrl(request.getContextPath()+"/approvalFlowAction.do?method=queryApplyInfo");
			rsDt.setForwardUrl(url);
			jsonString = GlobalFunc.getRsJson(rsDt);

			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);		

		}else{
			remark =  objString+"失败";
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_FAIL);
			rsDt.setMessage(remark);
			jsonString = GlobalFunc.getRsJson(rsDt);
			
			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);	
		}
	}
	public void doJumps(int retCode,String url,String rel,String objString,HttpServletRequest request,HttpServletResponse response){
		GlobalRsDt rsDt = new GlobalRsDt();
		String remark = "";
		String jsonString = "";
		PrintWriter pWriter = null;
		
		if(retCode==0){
			remark = objString+"成功";	
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_SUCCESS);
			rsDt.setMessage(remark);
			rsDt.setCallbackType("forward");
			
			rsDt.setNavTabId(rel);
//			rsDt.setForwardUrl(request.getContextPath()+"/approvalFlowAction.do?method=queryApplyInfo");
			rsDt.setForwardUrl(url);
			rsDt.setRel(rel);
			jsonString = GlobalFunc.getRsJson(rsDt);

			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);		

		}else{
			remark =  objString+"失败";
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_FAIL);
			rsDt.setMessage(remark);
			jsonString = GlobalFunc.getRsJson(rsDt);
			
			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);	
		}
	}
	public void doJump1(int retCode,String url,String objString,HttpServletRequest request,HttpServletResponse response){
		GlobalRsDt rsDt = new GlobalRsDt();
		String remark = "";
		String jsonString = "";
		PrintWriter pWriter = null;
		
		if(retCode==0){
			remark = objString+"成功";	
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_SUCCESS);
			rsDt.setMessage(remark);
			rsDt.setCallbackType("closeCurrent");
			
//			rsDt.setNavTabId("userinfo");
//			rsDt.setForwardUrl(request.getContextPath()+"/approvalFlowAction.do?method=queryApplyInfo");
			rsDt.setForwardUrl(url);
//			rsDt.setRel("ht");
			jsonString = GlobalFunc.getRsJson(rsDt);

			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);		

		}else{
			remark =  objString+"失败";
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_FAIL);
			rsDt.setMessage(remark);
			jsonString = GlobalFunc.getRsJson(rsDt);
			
			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);	
		}
	}
	public void doJump2(int retCode,String url,String rel,String objString,HttpServletRequest request,HttpServletResponse response){
		GlobalRsDt rsDt = new GlobalRsDt();
		String remark = "";
		String jsonString = "";
		PrintWriter pWriter = null;
		
		if(retCode==0){
			remark = objString+"成功";	
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_SUCCESS);
			rsDt.setMessage(remark);
			rsDt.setCallbackType("closeCurrent");
			
//			rsDt.setNavTabId("userinfo");
//			rsDt.setForwardUrl(request.getContextPath()+"/approvalFlowAction.do?method=queryApplyInfo");
			rsDt.setForwardUrl(url);
			rsDt.setRel(rel);
			jsonString = GlobalFunc.getRsJson(rsDt);

			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);		

		}else{
			remark =  objString+"失败";
			
			rsDt.setStatusCode(GlobalConst.STATUS_CODE_FAIL);
			rsDt.setMessage(remark);
			jsonString = GlobalFunc.getRsJson(rsDt);
			
			try {
				response.setContentType("text/html;charset=UTF-8");  
				pWriter = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pWriter.write(jsonString);	
		}
	}
	
	public ActionForward toKhManage(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryKh(formBean);
			
			request.setAttribute("userlist", userlist);	
			if(userlist.size()>0)
			{
				formBean.setTotalCount(Integer.parseInt(userlist.get(0).getTotalCount()));
			}else{
				formBean.setTotalCount(0);
			}
			
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("khmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public ActionForward toKhExp(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryExpKh(formBean);

			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String[] titles ={"编号","名称","身份证号","地址","号码1","号码2","转介绍人"};

			List alist = getExpList(userlist);
			
			GlobalUtil.AllDataToExcel("客户导出.xls","客户清单",titles,alist,response); 
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	private List getExpList(List<CrmInfo> list) {
		// TODO Auto-generated method stub
		List ls = new ArrayList();
    	List tmp_ls = null;
    	for(int i=0;i<list.size();i++){
    		tmp_ls = new ArrayList(); 
    		CrmInfo user =   list.get(i);
			
			tmp_ls.add(user.getKh_id());
			tmp_ls.add(user.getKh_name());
			tmp_ls.add(user.getKh_card());
			tmp_ls.add(user.getKh_addr());
			tmp_ls.add(user.getKh_phone1());
			tmp_ls.add(user.getKh_phone2());
			tmp_ls.add(user.getIntroduce_name());
         
			ls.add(tmp_ls);
    	}
    	 
		return ls;
	}
	public ActionForward toKhAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;

			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("khadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toYxkhManage(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
			String org_id="";
			if(orglist!=null&&orglist.size()>0){
				for(int i=0;i<orglist.size();i++){
					CrmInfo org = orglist.get(i);
					org_id = org_id+org.getOrg_id()+",";
				}
			}
			formBean.setOrg_ids(org_id);
			List<CrmInfo> userlist = sc.queryYxkh(formBean);
			
			request.setAttribute("userlist", userlist);	
			if(userlist.size()>0)
			{
				formBean.setTotalCount(Integer.parseInt(userlist.get(0).getTotalCount()));
			}else{
				formBean.setTotalCount(0);
			}
			
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("yxkhmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toYxkhAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();

		try
		{
			CrmForm formBean = (CrmForm)form;

			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志

			getLogger(bosscodestr,GlobalConst.EXIT).info("进入意向客户新增页面。");
			return mapping.findForward("yxkhadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入意向客户新增页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toYxkhExp(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());

			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryYxkhExp(formBean);
			

			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String[] titles ={"客户编号","名称","地址","号码1","号码2","转介绍人","是否已安装","渠道来源","备注"};

			List alist = getYxkhExpList(userlist);
			
			GlobalUtil.AllDataToExcel("意向客户导出.xls","意向客户清单",titles,alist,response); 
			
			
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	private List getYxkhExpList(List<CrmInfo> list) {
		// TODO Auto-generated method stub
		List ls = new ArrayList();
    	List tmp_ls = null;
    	for(int i=0;i<list.size();i++){
    		tmp_ls = new ArrayList(); 
    		CrmInfo user =   list.get(i);
    		tmp_ls.add(user.getKh_id());
			tmp_ls.add(user.getKh_name());
			tmp_ls.add(user.getKh_addr());
			tmp_ls.add(user.getKh_phone1());
			tmp_ls.add(user.getKh_phone2());
			tmp_ls.add(user.getIntroduce_name());
			tmp_ls.add("1".equals(user.getIs_install())?"已安装":"未安装");
			tmp_ls.add(user.getChannel_source());
			tmp_ls.add(user.getRemark());
         
			ls.add(tmp_ls);
    	}
    	 
		return ls;
	}
	
	public void doKhAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
			
			retCode = sc.doKhAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
//				doJump(0,url,"新增客户",request,response);
				doJumps(0,url,"user","新增客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJumps(-1,url,"user","新增客户",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public void doYxkhAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int retCode = 0;
		try
		{
			
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
			
			retCode = sc.doYxkhAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toYxkhAdd";
				doJumps(0,url,"toYxkhAdd","新增意向客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toYxkhAdd";
				doJumps(-1,url,"toYxkhAdd","新增意向客户",request,response);
			}
			
		}catch(Exception e){
			throw new Exception();
		}
	}
	
	public void doKhDel(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{

			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doKhDel(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			getLogger(bosscodestr,GlobalConst.EXIT).info("删除客户。");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJumps(0,url,"user","删除客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJumps(-1,url,"user","删除客户",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("删除客户出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public void doYxkhDel(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{

			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
			
			retCode = sc.doYxkhDel(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			getLogger(bosscodestr,GlobalConst.EXIT).info("删除意向客户。");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				doJumps(0,url,"user","删除意向客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				doJumps(-1,url,"user","删除意向客户",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("删除意向客户出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toKhView(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//展现用户基本信息、
			List<CrmInfo> userlist = sc.queryKhListById(formBean);
			request.setAttribute("userlist", userlist);		
//			System.out.println("%%%%%userlist"+userlist);
			//用户所有的合同信息
			List<CrmInfo> htlist = sc.queryhtBykh(formBean);
			request.setAttribute("htlist", htlist);	
//			System.out.println("%%%%%htlist"+htlist);
			//回访记录  
			List<CrmInfo> hflist = sc.queryhfBykh(formBean);
			request.setAttribute("hflist", hflist);	
//			System.out.println("%%%%%hflist"+hflist);
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			
			return mapping.findForward("khview");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	public ActionForward toKhEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();

			List<CrmInfo> userlist = sc.queryKhListById(formBean);
			request.setAttribute("userlist", userlist);		
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			
			return mapping.findForward("khedit");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public ActionForward toYxkhEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();

			List<CrmInfo> userlist = sc.queryYxkhById(formBean);
			request.setAttribute("userlist", userlist);		
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			
			return mapping.findForward("yxkhedit");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public void doKhEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doKhEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump2(0,url,"user","修改客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump2(-1,url,"user","修改客户",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("修改客户信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public void doYxkhEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doYxkhEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				//doJump2(0,url,"queryYxkh","修改意向客户",request,response);
				doJump1(0,url,"修改意向客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				doJump2(-1,url,"queryYxkh","修改意向客户",request,response);
			}

		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("修改意向客户信息出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toHtManage(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryHt(formBean);
			
			request.setAttribute("userlist", userlist);	
			if(userlist.size()>0)
			{
				formBean.setTotalCount(Integer.parseInt(userlist.get(0).getTotalCount()));
			}else{
				formBean.setTotalCount(0);
			}
			
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("htmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toHtAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			//查询客户信息
			List<CrmInfo> khlist = sc.queryKhListById(formBean);
			request.setAttribute("khlist", khlist);	
			//查询介绍人信息
			//List<CrmInfo> introducelist = sc.queryKhIntroduce(formBean);
			//request.setAttribute("introducelist", introducelist);	
			
			//新增合同的时候，查询出我这个客户，介绍的所有客户。
			List<CrmInfo> userlist = sc.queryIntroduce(formBean);
			request.setAttribute("userlist", userlist);	
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("htadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public ActionForward toHtAdd2(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			 
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("htadd2");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public void doHtAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHtAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(0,url,"新增合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(-1,url,"新增合同",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	public void doHtAdd2(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHtAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtAdd2";
				doJumps(0,url,"ht","新增合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtAdd2";
				doJumps(-1,url,"ht","新增合同",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public ActionForward toHtEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();

			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
			
			List<CrmInfo> htlist = sc.queryhtByid(formBean);
			request.setAttribute("htlist", htlist);	
			
			//新增合同的时候，查询出我这个客户， 介绍的所有客户。
			List<CrmInfo> userlist = sc.queryIntroduceByht(formBean);
			request.setAttribute("userlist", userlist);	
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("htedit");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public ActionForward toHtEdit1(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();

			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
			
			List<CrmInfo> htlist = sc.queryhtByid(formBean);
			request.setAttribute("htlist", htlist);	
			
			//新增合同的时候，查询出我这个客户，在上一个合同期间内，介绍的所有客户。
			List<CrmInfo> userlist = sc.queryIntroduceByht(formBean);
			request.setAttribute("userlist", userlist);	
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("htedit1");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public void doHtEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHtEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump2(0,url,"ht","修改合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump2(-1,url,"ht","修改合同",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}

	public void doHtEdit1(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHtEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump2(0,url,"ht","修改合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump2(-1,url,"ht","修改合同",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}

	public void doHtDel(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{

			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHtDel(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			getLogger(bosscodestr,GlobalConst.EXIT).info("删除合同。");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJumps(0,url,"ht","删除合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJumps(-1,url,"ht","删除合同",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("删除合同出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public void doHtDel1(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{

			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHtDel(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			getLogger(bosscodestr,GlobalConst.EXIT).info("删除合同。");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJumps(0,url,"ht","删除合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJumps(-1,url,"ht","删除合同",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("删除合同出错:"+e.getMessage());
			throw new Exception();
		}
	}

	public ActionForward toHtManage1(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryHt1(formBean);
			
			request.setAttribute("userlist", userlist);	
			if(userlist.size()>0)
			{
				formBean.setTotalCount(Integer.parseInt(userlist.get(0).getTotalCount()));
			}else{
				formBean.setTotalCount(0);
			}
			
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("htmanage1");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}

	public ActionForward toHtExp(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryHtExp(formBean);
			

			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String[] titles ={"客户名称","地址","首次签约日期","本次签约日期","押金(元)","租金(元)","产品名","签约年度","合同类别","备注"};

			List alist = getHtExpList(userlist);
			
			GlobalUtil.AllDataToExcel("合同导出.xls","合同清单",titles,alist,response); 
			
			
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}

	private List getHtExpList(List<CrmInfo> list) {
		// TODO Auto-generated method stub
		List ls = new ArrayList();
    	List tmp_ls = null;
    	for(int i=0;i<list.size();i++){
    		tmp_ls = new ArrayList(); 
    		CrmInfo user =   list.get(i);
			
			tmp_ls.add(user.getKh_name());
			tmp_ls.add(user.getKh_addr());
			tmp_ls.add(user.getHt_date_first());
			tmp_ls.add(user.getHt_date_current());
			tmp_ls.add(user.getHt_pledge());
			tmp_ls.add(user.getHt_rent());
			tmp_ls.add(user.getProd_name());
			tmp_ls.add(user.getHt_year());
			tmp_ls.add("1".equals(user.getHf_type())?"新增":"续约");
			tmp_ls.add(user.getRemark());
         
			ls.add(tmp_ls);
    	}
    	 
		return ls;
	}
	public ActionForward toHfManage(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try 
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
//			formBean.setOrg_ids(org_id);
			formBean.setOrg_ids(sessionData.getRegion());
			
			List<CrmInfo> userlist = sc.queryHf(formBean);
			
			request.setAttribute("userlist", userlist);	
			if(userlist.size()>0)
			{
				formBean.setTotalCount(Integer.parseInt(userlist.get(0).getTotalCount()));
			}else{
				formBean.setTotalCount(0);
			}
			
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("hfmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public ActionForward toHfExp(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//查询用户管辖的区域
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());
//			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
//			String org_id="";
//			if(orglist!=null&&orglist.size()>0){
//				for(int i=0;i<orglist.size();i++){
//					CrmInfo org = orglist.get(i);
//					org_id = org_id+org.getOrg_id()+",";
//				}
//			}
//			formBean.setOrg_ids(org_id);
			formBean.setOrg_ids(sessionData.getRegion());
			
			List<CrmInfo> userlist = sc.queryHfExp(formBean);
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String[] titles ={"客户编码","客户姓名","签约年度","身份证号","电话号码","地址","回访次数","回访状态","应访日期","实际日期","访问情况","回访人","客户签字"};

			List alist = getHfExpList(userlist);
			
			GlobalUtil.AllDataToExcel("回访导出.xls","回访清单",titles,alist,response); 
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}

	private List getHfExpList(List<CrmInfo> list) {
		// TODO Auto-generated method stub
		List ls = new ArrayList();
    	List tmp_ls = null;
    	for(int i=0;i<list.size();i++){
    		tmp_ls = new ArrayList(); 
    		CrmInfo user =   list.get(i);

			tmp_ls.add(user.getKh_id());
			tmp_ls.add(user.getKh_name());
			tmp_ls.add(user.getHt_year());
			tmp_ls.add(user.getKh_card());
			tmp_ls.add(user.getKh_phone1());
			tmp_ls.add(user.getKh_addr());
			tmp_ls.add(user.getHf_type());
			tmp_ls.add(user.getHf_status().equals("1")?"已回访":"未回访");
			tmp_ls.add(user.getHf_date_must());
			tmp_ls.add(user.getHf_date_fact());
			tmp_ls.add(user.getHf_remark());
			tmp_ls.add(user.getHf_user_name());
			tmp_ls.add("");
			ls.add(tmp_ls);
    	}
    	 
		return ls;
	}
	public ActionForward toHfEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();

			List<CrmInfo> hflist = sc.queryhfByid(formBean);
			request.setAttribute("hflist", hflist);	
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//记录日志
//			doLog(form,"进入欢迎页面");
//			createLog(request,"","","进入欢迎页面","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("进入欢迎页面。");
			return mapping.findForward("hfedit");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
			throw new Exception();
		}
	}
	public void doHfEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{

			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			
			retCode = sc.doHfEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			getLogger(bosscodestr,GlobalConst.EXIT).info("工作回访。");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHfManagePage";
				doJump2(0,url,"hf","工作回访",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHfManagePage";
				doJump2(-1,url,"hf","工作回访",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("工作回访出错:"+e.getMessage());
			throw new Exception();
		}
	}
	


}
