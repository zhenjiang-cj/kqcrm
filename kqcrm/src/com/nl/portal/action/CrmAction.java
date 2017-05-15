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
			remark = objString+"�ɹ�";	
			
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
			remark =  objString+"ʧ��";
			
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
			remark = objString+"�ɹ�";	
			
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
			remark =  objString+"ʧ��";
			
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
			//��ѯ�û���Ͻ������
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("khmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			//��ȡ�ͻ�����
			formBean.setKh_id(sc.getSno());
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("khadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
				doJump1(0,url,"�����ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(-1,url,"�����ͻ�",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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

			getLogger(bosscodestr,GlobalConst.EXIT).info("ɾ���ͻ���");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump(0,url,"ɾ���ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump(-1,url,"ɾ���ͻ�",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("ɾ���ͻ�����:"+e.getMessage());
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
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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
				doJump1(0,url,"�޸Ŀͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(-1,url,"�޸Ŀͻ�",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("�޸Ŀͻ���Ϣ����:"+e.getMessage());
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
			//��ѯ�û���Ͻ������
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("htmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			
			//��ѯ�ͻ���Ϣ
			List<CrmInfo> khlist = sc.queryKhListById(formBean);
			request.setAttribute("khlist", khlist);	
			//��ѯ��������Ϣ
			List<CrmInfo> introducelist = sc.queryKhIntroduce(formBean);
			request.setAttribute("introducelist", introducelist);	
			
			//������ͬ��ʱ�򣬲�ѯ��������ͻ������ܵ����пͻ���
			List<CrmInfo> userlist = sc.queryIntroduce(formBean);
			request.setAttribute("userlist", userlist);	
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("htadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
				doJump1(0,url,"������ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump1(-1,url,"������ͬ",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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
			
			//������ͬ��ʱ�򣬲�ѯ��������ͻ��� ���ܵ����пͻ���
			List<CrmInfo> userlist = sc.queryIntroduceByht(formBean);
			request.setAttribute("userlist", userlist);	
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("htedit");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			
			//������ͬ��ʱ�򣬲�ѯ��������ͻ�������һ����ͬ�ڼ��ڣ����ܵ����пͻ���
			List<CrmInfo> userlist = sc.queryIntroduceByht(formBean);
			request.setAttribute("userlist", userlist);	
			
			request.setAttribute("pager", formBean);	
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("htedit1");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
				doJump1(0,url,"�޸ĺ�ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump1(-1,url,"�޸ĺ�ͬ",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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
				doJump1(0,url,"�޸ĺ�ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump1(-1,url,"�޸ĺ�ͬ",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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

			getLogger(bosscodestr,GlobalConst.EXIT).info("ɾ����ͬ��");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump(0,url,"ɾ����ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump(-1,url,"ɾ����ͬ",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("ɾ����ͬ����:"+e.getMessage());
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

			getLogger(bosscodestr,GlobalConst.EXIT).info("ɾ����ͬ��");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump(0,url,"ɾ����ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump(-1,url,"ɾ����ͬ",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("ɾ����ͬ����:"+e.getMessage());
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
			//��ѯ�û���Ͻ������
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("htmanage1");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			//��ѯ�û���Ͻ������
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("hfmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("hfedit");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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

			getLogger(bosscodestr,GlobalConst.EXIT).info("�����طá�");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toHfManage";
				doJump(0,url,"�����ط�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHfManage";
				doJump(-1,url,"�����ط�",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("�����طó���:"+e.getMessage());
			throw new Exception();
		}
	}
	


}
