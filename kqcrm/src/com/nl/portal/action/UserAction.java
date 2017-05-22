package com.nl.portal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nl.base.BaseAppAction;
import com.nl.base.utils.GlobalFunc;
import com.nl.base.utils.GlobalRsDt;
import com.nl.portal.actionForm.*;
import com.nl.portal.dt.UserInfo;
import com.nl.portal.sc.*;
import com.nl.util.GlobalConst;

public class UserAction extends BaseAppAction {
	
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
	/**
	 * ���뻶ӭҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toUserManage(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			List<UserInfo> userlist = sc.queryUser(formBean);
			
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
			return mapping.findForward("usermanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toUserAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			//��ȡ�û�����
			formBean.setSno(sc.getSno());
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("useradd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public Boolean checkUser(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			if("".equals(formBean.getUser_id())){
				return false;
			}else{
				return true;
			}
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
	}
	 
	public Map provincesChange(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		Map<String,String> resultMap = new HashMap<String,String>();
		
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			

			List<UserInfo> userlist = sc.getCityByPro(formBean);
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String options = "<option value=''>---ȫ��---</option>";
			if(null!=userlist&&userlist.size()>0){ 
	             for(int i=0;i<userlist.size();i++){
	            	 UserInfo user = userlist.get(i);
	            	 String org_id = user.getOrg_id();
	            	 String org_name = user.getOrg_name();
	                 options = options+"<option value="+org_id+">"+org_name+"</option>"; 
	             }
			}
			resultMap.put("cityMap",options);
			
			return resultMap;
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
		
		
	}
	
	public Map cityChange(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		Map<String,String> resultMap = new HashMap<String,String>();
		
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			

			List<UserInfo> userlist = sc.getRegionByCity(formBean);
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String options = "<option value=''>---ȫ��---</option>";
			if(null!=userlist&&userlist.size()>0){ 
	             for(int i=0;i<userlist.size();i++){
	            	 UserInfo user = userlist.get(i);
	            	 String org_id = user.getOrg_id();
	            	 String org_name = user.getOrg_name();
	                 options = options+"<option value="+org_id+">"+org_name+"</option>"; 
	             }
			}
			resultMap.put("regionMap",options);
			
			return resultMap;
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
		
		
	}
	public void doUserAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			
			//�жϽ���ҳ�����չʾ�Ŀգ�����չʾ���Ѿ���д����Ϣ��
			//������Ž���û��Ҫչʾ����Ϣ��������Ϣ���Ѿ��й�����Ϣ�����ҹ����Ѿ��ᵥ��
			retCode = sc.doUserAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump1(0,url,"�����û�",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump1(-1,url,"�����û�",request,response);
			}
			
		}catch(Exception e){
//			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	
	public void doUserDel(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{

			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			retCode = sc.doUserDel(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);			

			getLogger(bosscodestr,GlobalConst.EXIT).info("ɾ���û���");
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump(0,url,"ɾ���û�",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump(-1,url,"ɾ���û�",request,response);
			}
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("ɾ���û�����:"+e.getMessage());
			throw new Exception();
		}
	}
	

	public ActionForward toUserEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();

			List<UserInfo> userlist = sc.queryUserListBysno(formBean);
			request.setAttribute("userlist", userlist);		
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			
			return mapping.findForward("useredit");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public void doUserEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			retCode = sc.doUserEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump1(0,url,"�޸��û�",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump1(-1,url,"�޸��û�",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	

	public ActionForward toUserRole(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			formBean.setSysid("99");
			
			List<UserInfo> userlist = sc.queryUserListBysno(formBean);
			request.setAttribute("userlist", userlist);		

			//��ȡ���н�ɫ
			
//			List<UserInfo> rolelist = sc.queryAllRolebysno(formBean);
			List<UserInfo> rolelist = sc.queryAllRole(formBean);
			request.setAttribute("rolelist", rolelist);		
			
			//��ȡ����û��µ����н�ɫ
			List<UserInfo> userrolelist = sc.queryRoleByUser(formBean);
			request.setAttribute("userrolelist", userrolelist);	
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			
			return mapping.findForward("userrole");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	public void doUserRole(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			retCode = sc.doUserRole(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump1(0,url,"�����û���ɫ",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toUserManage";
				doJump1(-1,url,"�����û���ɫ",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public ActionForward toRoleManage(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			List<UserInfo> userlist = sc.queryRole(formBean);
			
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
			return mapping.findForward("rolemanage");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public ActionForward toRoleAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			//��ȡ��ɫ����
			formBean.setRole_id(sc.getRole());
			//Ĭ�Ϲ���sysid=-1
			formBean.setSysid("");
			List<UserInfo> privilegeList = sc.getPrivilegeBySysid(formBean);
			
			request.setAttribute("privilegeList", privilegeList);
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("roleadd");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
	}
	 

	public void doRoleAdd(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			retCode = sc.doRoleAdd(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toRoleManage";
				doJump1(0,url,"������ɫ",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toRoleManage";
				doJump1(-1,url,"������ɫ",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	public void doRoleDel(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			retCode = sc.doRoleDel(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toRoleManage";
				doJump1(0,url,"ɾ����ɫ",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toRoleManage";
				doJump1(-1,url,"ɾ����ɫ",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}
	
	public ActionForward toRoleEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			//��ȡ��ɫ
			List<UserInfo> rolelist = sc.queryRoleList(formBean);
			request.setAttribute("rolelist", rolelist);
			
			if(rolelist!=null&&rolelist.size()>0){
				formBean.setSysid(rolelist.get(0).getSysid());
			}else{
				formBean.setSysid("");
			}
			List<UserInfo> privilegeList = sc.getPrivilegeBySysid(formBean);
			request.setAttribute("privilegeList", privilegeList);
			
			//��ȡ��ɫȨ��
			List<UserInfo> privrolelist = sc.getPrivilegeByRoleid(formBean);
			request.setAttribute("privrolelist", privrolelist);
			
			
			
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			//��¼��־
//			doLog(form,"���뻶ӭҳ��");
//			createLog(request,"","","���뻶ӭҳ��","1");
			getLogger(bosscodestr,GlobalConst.EXIT).info("���뻶ӭҳ�档");
			return mapping.findForward("roleedit");
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���뻶ӭҳ�����:"+e.getMessage());
			throw new Exception();
		}
	}
	
	public void doRoleEdit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bosscodestr = super.getBossCodeStr();
		int retCode = 0;
		try
		{
			UserForm formBean = (UserForm)form;
			UserSc sc = new UserSc();
			
			retCode = sc.doRoleEdit(formBean);
			request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
			
			String url ="";
			if(retCode == 0){
				url =request.getContextPath()+"/userAction.do?method=toRoleManage";
				doJump1(0,url,"�޸Ľ�ɫ",request,response);
			}else{
				url =request.getContextPath()+"/userAction.do?method=toRoleManage";
				doJump1(-1,url,"�޸Ľ�ɫ",request,response);
			}
//			return mapping.findForward("verifiinfo");
			
		}catch(Exception e){
			getLogger(bosscodestr,GlobalConst.ERROR).error("���������Ϣ����:"+e.getMessage());
//			throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
			throw new Exception();
		}
	}

}
