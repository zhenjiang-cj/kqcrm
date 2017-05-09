package com.nl.portal.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nl.base.BaseAppAction;
import com.nl.portal.actionForm.LoginForm;
import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.AdmUserLog;
import com.nl.portal.dt.HtzjCodeBm;
import com.nl.portal.dt.KmCompanyUser;
import com.nl.portal.sc.OperatorManageSC;
import com.nl.portal.sc.SystemSC;
import com.nl.util.Base64;
import com.nl.util.GlobalConst;
import com.nl.util.SessionConst;
import com.nl.util.SessionData;

public class LoginAction extends BaseAppAction
{

	private final Logger logger = Logger.getLogger(this.getClass());

	public LoginAction()
	{
		super(1);
	}
	
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sanjing
	 * @createdate Oct 21, 2016
	 * @version v1.0
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		LoginForm loginForm = (LoginForm)form;
		//�Է�����userid����
		String userid = request.getParameter("userid");
		
		//�Է��������������
		String strtoken = request.getParameter("token");
		//���ʵ�ģ���ʶ
		String go = request.getParameter("go");
		request.getSession().setAttribute(GlobalConst.LOGIN_GO, go);

		ActionForward af = null;
		String goUrl = "";

		HttpSession session = request.getSession();
		SessionData sessionData = new SessionData();
		
		String lgOptrType = "";
		String lgObjType = "";
		String lgNotes = "";

		SystemSC sc = new SystemSC();
		
		int nRet = 0;
		
		//��ҵ�û���¼���ʣ���ת����¼����
		if("1".equals(go)){
			String sRand = null;
			String checkflag = null;
			try{
				sRand = session.getAttribute(SessionConst.COMPANY_AUTH_IMAGE).toString();
				checkflag = request.getParameter("checkflag");
			}catch(Exception e){
				checkflag = null;
			}
			//û��ͼƬ��֤����ת����¼ҳ��
			if(sRand == null||checkflag==null){
				goUrl = goUrl+"/coremain/portal/companylogin.jsp";
				af = new ActionForward(goUrl);
				return af;
			}
			
			String message = "";
			String forwardStr = "index";
			String loginStatus = "�ɹ�";
			
			if(loginForm.getCompanyUserId()!=null){
				super.setBossCodeStr(loginForm.getCompanyUserId().trim());
				lgOptrType = loginForm.getCompanyUserId().trim();
			}
			
			//�ж��Ƿ���Ҫ���������޸�
			//��Ҫ�����޸�
			if(1==loginForm.getChangePassFlag()){
				//��̨У��������������һ��
				if(loginForm.getNewPwd().equals(loginForm.getRenewPwd())){
					//������������һ��
					KmCompanyUser companyUser = ((SessionData)session.getAttribute(SessionConst.COMPANY_LOGIN_SESSION)).getCompanyUser();
					OperatorManageSC opersc = new OperatorManageSC(companyUser.getCompany_user_id());
					loginForm.setCompanyUserId(companyUser.getCompany_user_id());
					int rs = opersc.changeCompanyPass(loginForm);
					//�޸ĳɹ�
					if(1==rs){
						lgObjType = "�����޸�";
						lgNotes = "��ҵ�û���ʼ�����޸ĳɹ�";
						loginStatus = "�ɹ�";
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
						
						goUrl = goUrl+"/login.do?method=login&go=2";
						af = new ActionForward(goUrl);
						return af;
					}else{
						lgObjType = "�����޸�";
						lgNotes = "��ҵ�û���ʼ�����޸Ĵ���";
						loginStatus = "ʧ��";
						//�����޸�ʧ��
						nRet =8;
						forwardStr = "changePass";
						
					}
					
				}else{
					lgObjType = "�����޸�";
					lgNotes = "��ҵ�û���ʼ�����޸������������벻һ��";
					loginStatus = "ʧ��";
					//�����������벻һ��
					nRet =7;
					forwardStr = "changePass";
				}
				sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
			}else{
				//ͼƬ��֤����ȷ
				if(sRand.equals(loginForm.getValidPic())){
					
					KmCompanyUser companyUser = new KmCompanyUser();
					companyUser.setCompany_user_id(loginForm.getCompanyUserId());
					companyUser.setUser_pwd(loginForm.getUserPwd());
					sessionData.setCompanyUser(companyUser);
					session.setAttribute(SessionConst.COMPANY_LOGIN_SESSION, sessionData);
					try{
						
						//�û�������Ϊ��
						if("".equals(loginForm.getCompanyUserId())&&"".equals(loginForm.getUserPwd())){
							message = GlobalConst.LOGIN_USER_NOT_NULL;
							lgObjType = "��ҵ��¼";
							lgNotes = "��ҵ�û���¼�û�������Ϊ��";
							nRet = 4;
						}else{
							OperatorManageSC opersc = new OperatorManageSC(loginForm.getCompanyUserId());
							nRet = opersc.queryCompanyUser(loginForm,companyUser);
							//�û���������ȷ
							if(1==nRet){
								lgObjType = "��ҵ��¼";
								lgNotes = "��ҵ�û���¼�ɹ�";
								loginStatus = "�ɹ�";
								sessionData.setCompanyUser(companyUser);
								session.setAttribute(SessionConst.COMPANY_LOGIN_SESSION, sessionData);
								
								//�ж��Ƿ���Ĭ�����룬��Ĭ�����룬����ת�����޸�ҳ��
								if(("123").equals(loginForm.getUserPwd())){
									forwardStr = "changePass";

								}else{
									sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
									goUrl = goUrl+"/login.do?method=login&go=2";
									af = new ActionForward(goUrl);
									return af;
									
								}
							//�û�������	
							}else if(2==nRet){
								lgObjType = "��ҵ��¼";
								lgNotes = "��ҵ�û�������";
								loginStatus = "ʧ��";
							//�û����벻��ȷ	
							}else if(3==nRet){
								lgObjType = "��ҵ��¼";
								lgNotes = "��ҵ�û��������";
								loginStatus = "ʧ��";
							//�����쳣���󣬷���Ϊ0	
							}else{
								lgObjType = "��ҵ��¼";
								lgNotes = "��ҵ�û���¼�쳣";
								loginStatus = "ʧ��";
							}
						}
						//forwardStr = "index";
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
						
					}catch(Exception e){
						lgObjType = "��ҵ��¼";
						lgNotes = "��ҵ�û���¼�쳣";
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "ʧ��");
						//throw new Exception(GlobalConst.LOGIN_ERROR);
						//�����쳣����
						nRet = 6;
					}
				}else{
					lgObjType = "��ҵ��¼";
					lgNotes = "��ҵ�û���¼��֤�����";
					nRet =5;
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
				}
			}

			request.setAttribute("logincode", nRet);
			return mapping.findForward(forwardStr);
			
		}else{
			//��������
			userid = "wytest1";
			
			if(userid!=null){
				super.setBossCodeStr(userid);
				lgOptrType = userid;
			}else{
				//Ȩ������ǰ̨�οͷ���
				if("4".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "�û���¼";
						lgNotes = "�û���¼����Ȩ������";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "��ҵ��¼";
						lgNotes = "��ҵ��¼����Ȩ������";
					}
					else{
						lgObjType = "�ο͵�¼";
						lgNotes = "�ο͵�¼����Ȩ������";
						
					}
					
					//��¼��־
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "�ɹ�");
					goUrl = goUrl+"/qlsx.do?method=queryQlInfoList";
					af = new ActionForward(goUrl);
					return af;
				//��ҵ��������
				}else if("2".equals(go)){
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "��ҵ��¼";
						lgNotes = "��ҵ��¼������ҵ��������";
					}
					//��¼��־
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "�ɹ�");
					goUrl = goUrl+"/companyAction.do?method=toframe";
					af = new ActionForward(goUrl);
					return af;
				//˫��������ǰ̨�οͷ���	
				}else if ("6".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "�û���¼";
						lgNotes = "�û���¼����˫������";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "��ҵ��¼";
						lgNotes = "��ҵ��¼����˫������";
					}
					else{
						lgObjType = "�ο͵�¼";
						lgNotes = "�ο͵�¼����˫������";
						
					}
					
					//��¼��־
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "�ɹ�");
					goUrl = goUrl+"/coremain/drandom/drandomRsTable.jsp";
					af = new ActionForward(goUrl);
					return af;
				//��վǰ̨��ҵ���ʲ�ѯ		
				}else if ("9".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "�û���¼";
						lgNotes = "�û���¼������ҵ���ʲ�ѯ";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "��ҵ��¼";
						lgNotes = "��ҵ��¼������ҵ���ʲ�ѯ";
					}
					else{
						lgObjType = "�ο͵�¼";
						lgNotes = "�ο͵�¼������ҵ���ʲ�ѯ";
						
					}
					
					//��¼��־
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "�ɹ�");
					goUrl = goUrl+"/frontManageAction.do?method=queryExpireCert";
					af = new ActionForward(goUrl);
					return af;
				//��ͼǰ̨�οͷ���	
				}else if ("12".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "�û���¼";
						lgNotes = "�û���¼���ʵ�ͼǰ̨";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "��ҵ��¼";
						lgNotes = "��ҵ��¼���ʵ�ͼǰ̨";
					}
					else{
						lgObjType = "�ο͵�¼";
						lgNotes = "�ο͵�¼���ʵ�ͼǰ̨";
						
					}
					
					//��¼��־
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "�ɹ�");
					goUrl = goUrl+"";
					af = new ActionForward(goUrl);
					return af;
					
				}
					
			}
			try{

				boolean isValid =  sc.checkUser(userid,strtoken);
				//�û����󲻺Ϸ�����ת������ҳ��
				if(!isValid){
					lgObjType = "�û���¼";
					lgNotes = "�û�������ʷǷ�";
					//��¼��־
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "ʧ��");
					throw new Exception(GlobalConst.LOGIN_ILLEGAL);
				
				//����Ϸ��������û���Ϣ����֯������Ϣ
				}else{
					List list  = sc.getUserInfos(userid);
					if(null == list){
						lgObjType = "�û���¼";
						lgNotes = "�û��ͻ���������";
						//��¼��־
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "ʧ��");
						throw new Exception(GlobalConst.LOGIN_USER_NOT_FOUND);
					}
					//δ��ѯ����Ч�û���Ϣ����ת������ҳ��
					if(null==list.get(0)){
						lgObjType = "�û���¼";
						lgNotes = "δ��ѯ����Ч�û�";
						throw new Exception(GlobalConst.LOGIN_ERROR);
					}
					if(null==list.get(1)){
						lgObjType = "�û���¼";
						lgNotes = "δ��ѯ����Ч�û���֯������Ϣ";
						throw new Exception(GlobalConst.LOGIN_ERROR);
					}
					//���û�������Ϣ��װ��session��
					sessionData.setAdmUser((AdmUserFc)list.get(0));
					//���û���֯������Ϣ��װ��session�� 
					sessionData.setHtzjCodeBm((HtzjCodeBm)list.get(1));
					session.setAttribute(SessionConst.LOGIN_SESSION, sessionData);
				}
				
				lgObjType = "�û���¼";
				lgNotes = "�û���Ч����";
				//��¼��־
				sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "�ɹ�");
				//��ҵ��������
				if("2".equals(go))
					goUrl = goUrl+"/companyAction.do?method=toframe";
				//��ҵ�������
				else if("3".equals(go))
					goUrl = goUrl+"/coremain/portal/spFrame.jsp";
				//Ȩ������ǰ̨�οͷ���
				else if("4".equals(go))
					goUrl = goUrl+"/qlsx.do?method=queryQlInfoList";
				//Ȩ�������̨
				else if("5".equals(go))
					goUrl = goUrl+"/coremain/qlsx/qlFrame.jsp";
				//˫���ǰ̨�οͷ���
				else if("6".equals(go)){
					goUrl = goUrl+"/coremain/drandom/drandomRsTable.jsp";
					//Base64.getBase64(userid);
				//˫�����̨
				}
				else if("7".equals(go)){
					goUrl = goUrl+"/km_ssj_idx.htm";
					//Base64.getBase64(userid);
					Cookie cook = new Cookie("userid",Base64.getBase64(userid));
					cook.setMaxAge(3600);
					response.addCookie(cook);
				//GIS��̨����
				}else if("8".equals(go)){
					goUrl = goUrl+"/km_gis_idx.htm";
					//Base64.getBase64(userid);
					Cookie cook = new Cookie("userid",Base64.getBase64(userid));
					cook.setMaxAge(3600);
					response.addCookie(cook);
				//��վǰ̨��ҵ���ʲ�ѯ	
				}else if("9".equals(go)){
					goUrl = goUrl+"/frontManageAction.do?method=queryExpireCert";
				//12319	
				}else if("10".equals(go)){
					goUrl = goUrl+"/km_12319.htm";
				//��ά����	
				}else if("11".equals(go)){
					goUrl = goUrl+"";
				//��ͼǰ̨�οͷ���
				}else if("12".equals(go)){
					goUrl = goUrl+"";
				}else if("13".equals(go)){
					goUrl = goUrl+"/coremain/portal/sqFrame.jsp";
				}
				
			}catch(Exception e){
				lgObjType = "�û���¼";
				lgNotes = "�û���¼�쳣";
				sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "ʧ��");
				throw new Exception(GlobalConst.LOGIN_ERROR);
			}
			
			
		}
		
		request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, loginForm);
		af = new ActionForward(goUrl);
		return af;
	}
		
	public ActionForward queryLogList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		LoginForm formBean = (LoginForm)form;
		SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
		
		if (formBean.getLgUserID() == null||"null".equals(formBean.getLgUserID()))
			formBean.setLgUserID("");

		
		List<AdmUserLog> logList = new ArrayList<AdmUserLog>();
		//DemoSC sc = new DemoSC(super.getBossCodeStr());

		//logList = sc.queryLogList(formBean);
		
		formBean.setTotalCount(Integer.parseInt(logList.get(0).getTotalCount()));
				
		request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, formBean);
		request.setAttribute("logList", logList);
		request.setAttribute("pager", formBean);
		
		
		return mapping.findForward("queryLogList");
	}
	
	public static void main (String[] args){
		String userid = "wytest1";
		Base64.getBase64(userid);
		System.out.println(Base64.getBase64(userid));
	}
	
}
