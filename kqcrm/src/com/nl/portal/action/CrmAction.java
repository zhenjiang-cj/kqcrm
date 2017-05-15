package com.nl.portal.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.nl.portal.actionForm.UserForm;
import com.nl.portal.dt.CrmInfo;
import com.nl.portal.dt.UserInfo;
import com.nl.portal.sc.CrmSc;
import com.nl.portal.sc.UserSc;
import com.nl.util.GlobalConst;
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
			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
			String org_id="";
			if(orglist!=null&&orglist.size()>0){
				for(int i=0;i<orglist.size();i++){
					CrmInfo org = orglist.get(i);
					org_id = org_id+org.getOrg_id()+",";
				}
			}
			formBean.setOrg_ids(org_id);
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
	public ActionForward toKhAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			CrmForm formBean = (CrmForm)form;
			CrmSc sc = new CrmSc();
			//获取客户序列
			formBean.setKh_id(sc.getSno());
			
			
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
			
			retCode = sc.doKhAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(0,url,"新增客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(-1,url,"新增客户",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("进入基本信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
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
				doJump(0,url,"删除客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump(-1,url,"删除客户",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("删除客户出错:"+e.getMessage());
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
				doJump1(0,url,"修改客户",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(-1,url,"修改客户",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("修改客户信息出错:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
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
			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
			String org_id="";
			if(orglist!=null&&orglist.size()>0){
				for(int i=0;i<orglist.size();i++){
					CrmInfo org = orglist.get(i);
					org_id = org_id+org.getOrg_id()+",";
				}
			}
			formBean.setOrg_ids(org_id);
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
			List<CrmInfo> introducelist = sc.queryKhIntroduce(formBean);
			request.setAttribute("introducelist", introducelist);	
			
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
				doJump1(0,url,"修改合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump1(-1,url,"修改合同",request,response);
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
				doJump1(0,url,"修改合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump1(-1,url,"修改合同",request,response);
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
				doJump(0,url,"删除合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump(-1,url,"删除合同",request,response);
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
				doJump(0,url,"删除合同",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump(-1,url,"删除合同",request,response);
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
			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
			String org_id="";
			if(orglist!=null&&orglist.size()>0){
				for(int i=0;i<orglist.size();i++){
					CrmInfo org = orglist.get(i);
					org_id = org_id+org.getOrg_id()+",";
				}
			}
			formBean.setOrg_ids(org_id);
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
			List<CrmInfo> orglist = sc.queryOrgByUser(formBean);
			String org_id="";
			if(orglist!=null&&orglist.size()>0){
				for(int i=0;i<orglist.size();i++){
					CrmInfo org = orglist.get(i);
					org_id = org_id+org.getOrg_id()+",";
				}
			}
			formBean.setOrg_ids(org_id);
			
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
				url =request.getContextPath()+"/crmAction.do?method=toHfManage";
				doJump(0,url,"工作回访",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHfManage";
				doJump(-1,url,"工作回访",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("工作回访出错:"+e.getMessage());
			throw new Exception();
		}
	}
	


}
