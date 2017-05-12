package com.nl.portal.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.huawei.csp.bsf.pwm.service.impl.PasswordForMD5;
import com.nl.base.BaseAppAction;
import com.nl.base.utils.GlobalFunc;
import com.nl.portal.actionForm.LoginForm;
import com.nl.portal.actionForm.OperatorForm;
import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.AdmUserLog;
import com.nl.portal.dt.HtzjCodeBm;
import com.nl.portal.dt.IscPriDataRel;
import com.nl.portal.dt.IscRolePrivilege;
import com.nl.portal.dt.IscUserRoleData;
import com.nl.portal.dt.KmCompanyUser;
import com.nl.portal.dt.RemindUserOrgCfg;
import com.nl.portal.dt.SysMenu;
import com.nl.portal.dt.SysOperator;
import com.nl.portal.sc.OperatorManageSC;
import com.nl.portal.sc.SystemSC;
import com.nl.portal.vo.PortalDeptInfoVo;
import com.nl.util.Base64;
import com.nl.util.GlobalConst;
import com.nl.util.GlobalUtil;
import com.nl.util.SessionConst;
import com.nl.util.SessionData;
import com.nl.util.config.NoLimitUser;

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
		HttpSession session = request.getSession();
		PasswordForMD5 md5 = new PasswordForMD5();
		SessionData sessionData = new SessionData();
		SysOperator sysOperator;
		String message = "";
		String forwardStr = "";
		String user_id = loginForm.getUser_id();
		String password = loginForm.getUser_pswd();
		SystemSC systemSC = new SystemSC(user_id);
		
		if (!"".equals(user_id)&&!"".equals(password))
		{
			//查询用户是否存在
			sysOperator = systemSC.getOperatorById(user_id);
			if (sysOperator != null)
			{
				//校验密码是否正确
				if (md5.encode(password).equalsIgnoreCase(sysOperator.getUserPassword()))
				{
						
					//封装session需要的信息
					sessionData.setSno(sysOperator.getSno());
					sessionData.setUser_id(sysOperator.getUser_id());
					sessionData.setUser_name(sysOperator.getUser_name());
					sessionData.setProvinces(sysOperator.getProvinces()==null?"":sysOperator.getProvinces());
					sessionData.setCity(sysOperator.getCity()==null?"":sysOperator.getCity());
					sessionData.setRegion(sysOperator.getRegion()==null?"":sysOperator.getRegion());
					sessionData.setOrg_level(sysOperator.getOrg_level()==null?"1":sysOperator.getOrg_level());
					

						//获取工号系统菜单
						systemSC.getOperMenu(sessionData);


						
						forwardStr = "index";
						message = GlobalConst.LOGIN_PASSWORD_SUCCESS;

				}
				else //操作员密码不正确
				{
					message = GlobalConst.LOGIN_PASSWORD_FAIL;
					forwardStr = "login";
				}
			}
			else//没有找到对应的操作员
			{
				message = GlobalConst.LOGIN_USER_NOT_FOUND;
				forwardStr = "login";
			}			
		}
		else 
		{
			message = GlobalConst.LOGIN_USER_NOT_NULL;
			forwardStr = "login";
		}
		
		loginForm.setMessage(message);								
		request.setAttribute(GlobalConst.GLOBAL_CURRENT_FORM, loginForm);
		session.setAttribute(SessionConst.LOGIN_SESSION, sessionData);
	
//		if("login".equals(forwardStr)){
//		//记录日志
//		SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
//		String operating_oper_id = sessdata.getSysOperator().getSNo();
//		int operating_type = Integer.parseInt(GlobalConst.OPERATION_LOGIN);
//		//long function_id = Long.parseLong(loginForm.getFunctionId());
//		String operating_object = "";
//		int sys_id = Integer.parseInt(GlobalConst.SYSTEM_ID_PORTAL);
//		int operating_result = 0;
//		
//		GlobalFunc.createSysOperatingLog(bosscodestr, operating_oper_id, operating_type, 0,
//				operating_object, sys_id, operating_result, message+"IP:"+GlobalUtil.getIpAddr(request));
//		}
//		
		return mapping.findForward(forwardStr);
	}
	
	
	public ActionForward queryLogList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		LoginForm formBean = (LoginForm)form;
		SessionData sessdata = (SessionData) request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
		
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
