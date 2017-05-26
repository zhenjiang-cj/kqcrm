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
	public void doJumps(int retCode,String url,String rel,String objString,HttpServletRequest request,HttpServletResponse response){
		GlobalRsDt rsDt = new GlobalRsDt();
		String remark = "";
		String jsonString = "";
		PrintWriter pWriter = null;
		
		if(retCode==0){
			remark = objString+"�ɹ�";	
			
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
	public void doJump2(int retCode,String url,String rel,String objString,HttpServletRequest request,HttpServletResponse response){
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
			//��ѯ�û���Ͻ������
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
			
			String[] titles ={"���","����","���֤��","��ַ","����1","����2","ת������"};

			List alist = getExpList(userlist);
			
			GlobalUtil.AllDataToExcel("�ͻ�����.xls","�ͻ��嵥",titles,alist,response); 
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("yxkhmanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			
			//��¼��־

			getLogger(bosscodestr,GlobalConst.EXIT).info("��������ͻ�����ҳ�档");
			return mapping.findForward("yxkhadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("��������ͻ�����ҳ�����:"+e.getMessage());
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
			//��ѯ�û���Ͻ������
			SessionData sessionData =(SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			formBean.setOperatorId(sessionData.getSno());

			formBean.setOrg_ids(sessionData.getRegion());
			List<CrmInfo> userlist = sc.queryYxkhExp(formBean);
			

			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String[] titles ={"�ͻ����","����","��ַ","����1","����2","ת������","�Ƿ��Ѱ�װ","������Դ","��ע"};

			List alist = getYxkhExpList(userlist);
			
			GlobalUtil.AllDataToExcel("����ͻ�����.xls","����ͻ��嵥",titles,alist,response); 
			
			
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			tmp_ls.add("1".equals(user.getIs_install())?"�Ѱ�װ":"δ��װ");
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
//				doJump(0,url,"�����ͻ�",request,response);
				doJumps(0,url,"user","�����ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJumps(-1,url,"user","�����ͻ�",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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
				doJumps(0,url,"toYxkhAdd","��������ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toYxkhAdd";
				doJumps(-1,url,"toYxkhAdd","��������ͻ�",request,response);
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

			getLogger(bosscodestr,GlobalConst.EXIT).info("ɾ���ͻ���");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJumps(0,url,"user","ɾ���ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJumps(-1,url,"user","ɾ���ͻ�",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("ɾ���ͻ�����:"+e.getMessage());
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

			getLogger(bosscodestr,GlobalConst.EXIT).info("ɾ������ͻ���");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				doJumps(0,url,"user","ɾ������ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				doJumps(-1,url,"user","ɾ������ͻ�",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("ɾ������ͻ�����:"+e.getMessage());
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
			//չ���û�������Ϣ��
			List<CrmInfo> userlist = sc.queryKhListById(formBean);
			request.setAttribute("userlist", userlist);		
//			System.out.println("%%%%%userlist"+userlist);
			//�û����еĺ�ͬ��Ϣ
			List<CrmInfo> htlist = sc.queryhtBykh(formBean);
			request.setAttribute("htlist", htlist);	
//			System.out.println("%%%%%htlist"+htlist);
			//�طü�¼  
			List<CrmInfo> hflist = sc.queryhfBykh(formBean);
			request.setAttribute("hflist", hflist);	
//			System.out.println("%%%%%hflist"+hflist);
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			
			return mapping.findForward("khview");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
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
				doJump2(0,url,"user","�޸Ŀͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toKhManage";
				doJump2(-1,url,"user","�޸Ŀͻ�",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("�޸Ŀͻ���Ϣ����:"+e.getMessage());
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
				//doJump2(0,url,"queryYxkh","�޸�����ͻ�",request,response);
				doJump1(0,url,"�޸�����ͻ�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toYxkhManage";
				doJump2(-1,url,"queryYxkh","�޸�����ͻ�",request,response);
			}

		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("�޸�����ͻ���Ϣ����:"+e.getMessage());
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
			//List<CrmInfo> introducelist = sc.queryKhIntroduce(formBean);
			//request.setAttribute("introducelist", introducelist);	
			
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
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("htadd2");
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
				doJumps(0,url,"ht","������ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtAdd2";
				doJumps(-1,url,"ht","������ͬ",request,response);
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
				doJump2(0,url,"ht","�޸ĺ�ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJump2(-1,url,"ht","�޸ĺ�ͬ",request,response);
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
				doJump2(0,url,"ht","�޸ĺ�ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJump2(-1,url,"ht","�޸ĺ�ͬ",request,response);
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
				doJumps(0,url,"ht","ɾ����ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage";
				doJumps(-1,url,"ht","ɾ����ͬ",request,response);
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
				doJumps(0,url,"ht","ɾ����ͬ",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHtManage1";
				doJumps(-1,url,"ht","ɾ����ͬ",request,response);
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
			//��ѯ�û���Ͻ������
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
			
			String[] titles ={"�ͻ�����","��ַ","�״�ǩԼ����","����ǩԼ����","Ѻ��(Ԫ)","���(Ԫ)","��Ʒ��","ǩԼ���","��ͬ���","��ע"};

			List alist = getHtExpList(userlist);
			
			GlobalUtil.AllDataToExcel("��ͬ����.xls","��ͬ�嵥",titles,alist,response); 
			
			
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			tmp_ls.add("1".equals(user.getHf_type())?"����":"��Լ");
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
			//��ѯ�û���Ͻ������
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
			//��ѯ�û���Ͻ������
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
			
			String[] titles ={"�ͻ�����","�ͻ�����","ǩԼ���","���֤��","�绰����","��ַ","�طô���","�ط�״̬","Ӧ������","ʵ������","�������","�ط���","�ͻ�ǩ��"};

			List alist = getHfExpList(userlist);
			
			GlobalUtil.AllDataToExcel("�طõ���.xls","�ط��嵥",titles,alist,response); 
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return null;
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
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
			tmp_ls.add(user.getHf_status().equals("1")?"�ѻط�":"δ�ط�");
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
				url =request.getContextPath()+"/crmAction.do?method=toHfManagePage";
				doJump2(0,url,"hf","�����ط�",request,response);
			}else{
				url =request.getContextPath()+"/crmAction.do?method=toHfManagePage";
				doJump2(-1,url,"hf","�����ط�",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("�����طó���:"+e.getMessage());
			throw new Exception();
		}
	}
	


}
