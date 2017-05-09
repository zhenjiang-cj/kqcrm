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
		//对方传递userid过来
		String userid = request.getParameter("userid");
		
		//对方传递随机串过来
		String strtoken = request.getParameter("token");
		//访问的模块标识
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
		
		//企业用户登录访问，跳转到登录界面
		if("1".equals(go)){
			String sRand = null;
			String checkflag = null;
			try{
				sRand = session.getAttribute(SessionConst.COMPANY_AUTH_IMAGE).toString();
				checkflag = request.getParameter("checkflag");
			}catch(Exception e){
				checkflag = null;
			}
			//没有图片验证，跳转到登录页面
			if(sRand == null||checkflag==null){
				goUrl = goUrl+"/coremain/portal/companylogin.jsp";
				af = new ActionForward(goUrl);
				return af;
			}
			
			String message = "";
			String forwardStr = "index";
			String loginStatus = "成功";
			
			if(loginForm.getCompanyUserId()!=null){
				super.setBossCodeStr(loginForm.getCompanyUserId().trim());
				lgOptrType = loginForm.getCompanyUserId().trim();
			}
			
			//判断是否需要进行密码修改
			//需要密码修改
			if(1==loginForm.getChangePassFlag()){
				//后台校验两次密码输入一致
				if(loginForm.getNewPwd().equals(loginForm.getRenewPwd())){
					//两次密码输入一致
					KmCompanyUser companyUser = ((SessionData)session.getAttribute(SessionConst.COMPANY_LOGIN_SESSION)).getCompanyUser();
					OperatorManageSC opersc = new OperatorManageSC(companyUser.getCompany_user_id());
					loginForm.setCompanyUserId(companyUser.getCompany_user_id());
					int rs = opersc.changeCompanyPass(loginForm);
					//修改成功
					if(1==rs){
						lgObjType = "密码修改";
						lgNotes = "企业用户初始密码修改成功";
						loginStatus = "成功";
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
						
						goUrl = goUrl+"/login.do?method=login&go=2";
						af = new ActionForward(goUrl);
						return af;
					}else{
						lgObjType = "密码修改";
						lgNotes = "企业用户初始密码修改错误";
						loginStatus = "失败";
						//密码修改失败
						nRet =8;
						forwardStr = "changePass";
						
					}
					
				}else{
					lgObjType = "密码修改";
					lgNotes = "企业用户初始密码修改两次输入密码不一致";
					loginStatus = "失败";
					//两次密码输入不一致
					nRet =7;
					forwardStr = "changePass";
				}
				sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
			}else{
				//图片验证码正确
				if(sRand.equals(loginForm.getValidPic())){
					
					KmCompanyUser companyUser = new KmCompanyUser();
					companyUser.setCompany_user_id(loginForm.getCompanyUserId());
					companyUser.setUser_pwd(loginForm.getUserPwd());
					sessionData.setCompanyUser(companyUser);
					session.setAttribute(SessionConst.COMPANY_LOGIN_SESSION, sessionData);
					try{
						
						//用户名密码为空
						if("".equals(loginForm.getCompanyUserId())&&"".equals(loginForm.getUserPwd())){
							message = GlobalConst.LOGIN_USER_NOT_NULL;
							lgObjType = "企业登录";
							lgNotes = "企业用户登录用户名密码为空";
							nRet = 4;
						}else{
							OperatorManageSC opersc = new OperatorManageSC(loginForm.getCompanyUserId());
							nRet = opersc.queryCompanyUser(loginForm,companyUser);
							//用户名密码正确
							if(1==nRet){
								lgObjType = "企业登录";
								lgNotes = "企业用户登录成功";
								loginStatus = "成功";
								sessionData.setCompanyUser(companyUser);
								session.setAttribute(SessionConst.COMPANY_LOGIN_SESSION, sessionData);
								
								//判断是否是默认密码，是默认密码，则跳转密码修改页面
								if(("123").equals(loginForm.getUserPwd())){
									forwardStr = "changePass";

								}else{
									sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
									goUrl = goUrl+"/login.do?method=login&go=2";
									af = new ActionForward(goUrl);
									return af;
									
								}
							//用户不存在	
							}else if(2==nRet){
								lgObjType = "企业登录";
								lgNotes = "企业用户不存在";
								loginStatus = "失败";
							//用户密码不正确	
							}else if(3==nRet){
								lgObjType = "企业登录";
								lgNotes = "企业用户密码错误";
								loginStatus = "失败";
							//其他异常错误，返回为0	
							}else{
								lgObjType = "企业登录";
								lgNotes = "企业用户登录异常";
								loginStatus = "失败";
							}
						}
						//forwardStr = "index";
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
						
					}catch(Exception e){
						lgObjType = "企业登录";
						lgNotes = "企业用户登录异常";
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "失败");
						//throw new Exception(GlobalConst.LOGIN_ERROR);
						//其他异常错误
						nRet = 6;
					}
				}else{
					lgObjType = "企业登录";
					lgNotes = "企业用户登录验证码错误";
					nRet =5;
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, loginStatus);
				}
			}

			request.setAttribute("logincode", nRet);
			return mapping.findForward(forwardStr);
			
		}else{
			//测试数据
			userid = "wytest1";
			
			if(userid!=null){
				super.setBossCodeStr(userid);
				lgOptrType = userid;
			}else{
				//权力事项前台游客访问
				if("4".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "用户登录";
						lgNotes = "用户登录访问权力事项";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "企业登录";
						lgNotes = "企业登录访问权力事项";
					}
					else{
						lgObjType = "游客登录";
						lgNotes = "游客登录访问权力事项";
						
					}
					
					//记录日志
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "成功");
					goUrl = goUrl+"/qlsx.do?method=queryQlInfoList";
					af = new ActionForward(goUrl);
					return af;
				//企业资质申请
				}else if("2".equals(go)){
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "企业登录";
						lgNotes = "企业登录进行企业资质申请";
					}
					//记录日志
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "成功");
					goUrl = goUrl+"/companyAction.do?method=toframe";
					af = new ActionForward(goUrl);
					return af;
				//双随机抽查结果前台游客访问	
				}else if ("6".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "用户登录";
						lgNotes = "用户登录访问双随机结果";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "企业登录";
						lgNotes = "企业登录访问双随机结果";
					}
					else{
						lgObjType = "游客登录";
						lgNotes = "游客登录访问双随机结果";
						
					}
					
					//记录日志
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "成功");
					goUrl = goUrl+"/coremain/drandom/drandomRsTable.jsp";
					af = new ActionForward(goUrl);
					return af;
				//网站前台企业资质查询		
				}else if ("9".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "用户登录";
						lgNotes = "用户登录访问企业资质查询";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "企业登录";
						lgNotes = "企业登录访问企业资质查询";
					}
					else{
						lgObjType = "游客登录";
						lgNotes = "游客登录访问企业资质查询";
						
					}
					
					//记录日志
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "成功");
					goUrl = goUrl+"/frontManageAction.do?method=queryExpireCert";
					af = new ActionForward(goUrl);
					return af;
				//地图前台游客访问	
				}else if ("12".equals(go)){
					sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
					if(sessionData!=null){
						lgObjType = "用户登录";
						lgNotes = "用户登录访问地图前台";
					}
					if(request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION)!=null){
						sessionData = (SessionData)request.getSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
						lgObjType = "企业登录";
						lgNotes = "企业登录访问地图前台";
					}
					else{
						lgObjType = "游客登录";
						lgNotes = "游客登录访问地图前台";
						
					}
					
					//记录日志
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "成功");
					goUrl = goUrl+"";
					af = new ActionForward(goUrl);
					return af;
					
				}
					
			}
			try{

				boolean isValid =  sc.checkUser(userid,strtoken);
				//用户请求不合法，跳转到错误页面
				if(!isValid){
					lgObjType = "用户登录";
					lgNotes = "用户请求访问非法";
					//记录日志
					sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "失败");
					throw new Exception(GlobalConst.LOGIN_ILLEGAL);
				
				//请求合法，创建用户信息和组织机构信息
				}else{
					List list  = sc.getUserInfos(userid);
					if(null == list){
						lgObjType = "用户登录";
						lgNotes = "用户和机构不存在";
						//记录日志
						sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "失败");
						throw new Exception(GlobalConst.LOGIN_USER_NOT_FOUND);
					}
					//未查询到有效用户信息，跳转到错误页面
					if(null==list.get(0)){
						lgObjType = "用户登录";
						lgNotes = "未查询到有效用户";
						throw new Exception(GlobalConst.LOGIN_ERROR);
					}
					if(null==list.get(1)){
						lgObjType = "用户登录";
						lgNotes = "未查询到有效用户组织机构信息";
						throw new Exception(GlobalConst.LOGIN_ERROR);
					}
					//将用户基本信息封装到session中
					sessionData.setAdmUser((AdmUserFc)list.get(0));
					//将用户组织机构信息封装到session中 
					sessionData.setHtzjCodeBm((HtzjCodeBm)list.get(1));
					session.setAttribute(SessionConst.LOGIN_SESSION, sessionData);
				}
				
				lgObjType = "用户登录";
				lgNotes = "用户有效访问";
				//记录日志
				sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "成功");
				//企业资审申请
				if("2".equals(go))
					goUrl = goUrl+"/companyAction.do?method=toframe";
				//企业资质审核
				else if("3".equals(go))
					goUrl = goUrl+"/coremain/portal/spFrame.jsp";
				//权力事项前台游客访问
				else if("4".equals(go))
					goUrl = goUrl+"/qlsx.do?method=queryQlInfoList";
				//权力事项后台
				else if("5".equals(go))
					goUrl = goUrl+"/coremain/qlsx/qlFrame.jsp";
				//双随机前台游客访问
				else if("6".equals(go)){
					goUrl = goUrl+"/coremain/drandom/drandomRsTable.jsp";
					//Base64.getBase64(userid);
				//双随机后台
				}
				else if("7".equals(go)){
					goUrl = goUrl+"/km_ssj_idx.htm";
					//Base64.getBase64(userid);
					Cookie cook = new Cookie("userid",Base64.getBase64(userid));
					cook.setMaxAge(3600);
					response.addCookie(cook);
				//GIS后台访问
				}else if("8".equals(go)){
					goUrl = goUrl+"/km_gis_idx.htm";
					//Base64.getBase64(userid);
					Cookie cook = new Cookie("userid",Base64.getBase64(userid));
					cook.setMaxAge(3600);
					response.addCookie(cook);
				//网站前台企业资质查询	
				}else if("9".equals(go)){
					goUrl = goUrl+"/frontManageAction.do?method=queryExpireCert";
				//12319	
				}else if("10".equals(go)){
					goUrl = goUrl+"/km_12319.htm";
				//运维评估	
				}else if("11".equals(go)){
					goUrl = goUrl+"";
				//地图前台游客访问
				}else if("12".equals(go)){
					goUrl = goUrl+"";
				}else if("13".equals(go)){
					goUrl = goUrl+"/coremain/portal/sqFrame.jsp";
				}
				
			}catch(Exception e){
				lgObjType = "用户登录";
				lgNotes = "用户登录异常";
				sc.createSysOperatingLogSimple(request, lgOptrType, lgObjType, lgNotes, "失败");
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
