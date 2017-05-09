package com.nl.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.nl.base.utils.GlobalFunc;
import com.nl.base.utils.Log;
import com.nl.base.utils.SystemTool; 
import com.nl.util.AppFunc;
import com.nl.util.AppParam;
import com.nl.util.AppPrivilege;
import com.nl.util.AppPrivileges; 
import com.nl.util.AppSystem;
import com.nl.util.GlobalConst;
import com.nl.util.SessionConst;
import com.nl.util.SessionData;
import com.nl.util.SessionTool;
/**
*
* @author sanjing
* @creatdate 2011-08-31
*/
public class BaseAppAction extends DispatchAction{
	//final private static Logger logger = Logger.getLogger(BaseAction.class);
	public final String ROLE = this.getClass().getName();
	private Logger log = null;
	
	private Log logger = null;
	
	//此值存放userid的值
	private String bossCodeStr = null;
	
	private boolean checkLoginFlag = true;
	
	private HttpSession curSession = null;
	
	private BaseAppActionForm baseAppActionForm = null;
	
	public BaseAppAction(){
		log = Logger.getLogger(this.getClass());
		log.info(ROLE + "::baseAppAction()->Enter@@@@@@@@");
		checkLogin();
		log.info(ROLE + "::baseAppAction()->Exit########");
	}
	
	public BaseAppAction(int flag){
		log = Logger.getLogger(this.getClass());
		log.info(ROLE + "::baseAppAction()->Enter@@@@@@@@");

		notCheckLogin();
		log.info(ROLE + "::baseAppAction()->Exit########");

	}
	
	public ActionForward exec(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse httpservletresponse)
    throws Exception
	{
	    return null;
	}
	
	
	@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ActionForward forward = null;
        try
        {
        	String parameter = request.getParameter(mapping.getParameter());
//            if (parameter == null)
//            	parameter = mapping.getParameter();
//            if(parameter == null || parameter.trim().length() < 1)
//                parameter = "exec";
//            log.debug(ROLE + "::baseAppAction()->Enter::即将调用 " + parameter);
//            if("execute".equals(parameter) || "perform".equals(parameter))
//            {
//                String message = DispatchAction.messages.getMessage("dispatch.recursive", mapping.getPath());
//                log.error(message);
//                throw new ServletException(message);
//            }
            forward = process(mapping, form, request, response);
//            if(forward == null){
//            	//当调用子类时  将登录人信息放到thread中去
//            	SessionTool.setSessionData(getSessionData());
//            	
//            	//函数进到这一步，应该可以取到用户信息了，且从前台参数中获取MENU_CLICK
//            	String menuClick = request.getParameter("menuClick");
//            	if (StringUtils.isNotEmpty(menuClick) && menuClick.equals("true")) {
//                    String funcname = GlobalFunc.getFunctionName(getBaseAppActionForm().getFunctionId());
//
//				}
            	
            	forward = dispatchMethod(mapping, form, request, response, parameter);
//            }
            //log.debug(parameter + " 调用结束");
//            log.debug(ROLE + "::baseAppAction()->Enter::" + parameter + " 调用结束");
        }
        catch(Exception e)
        {
            if(mapping.getScope().equals("request"))
                request.setAttribute(GlobalConst.Global_ERROR_ID, e);
            else
                request.getSession().setAttribute(GlobalConst.Global_ERROR_ID, e);
            String input = mapping.getInput();
            if(input == null || input.trim().length() < 1)
                forward = mapping.findForward(GlobalConst.Global_ERROR_PAGE);
            else
                forward = mapping.findForward(input.trim());
        }
        return forward;
    }
	
	public ActionForward process(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward af = null;
		
		this.setCurSession(request.getSession());
		this.setBaseAppActionForm((BaseAppActionForm)form);
		
		//对于密码修改的功能不进行权限的校验
		if (null != this.getBaseAppActionForm().getFunctionId() 
			&& this.getBaseAppActionForm().getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_PASSWORD_UPDATE))
			this.checkLoginFlag = false;
		
		//对于工号验证的功能不进行权限的校验
		if (null != this.getBaseAppActionForm().getFunctionId() 
			&& "1".equals(this.getBaseAppActionForm().getIs_check()))
			this.checkLoginFlag = false;
		
		try{
			//判断是否需要进行验证
			if(this.checkLoginFlag == true){
				//判断session是否存在
				boolean nRet = checkSession();
				
				if(false == nRet)
				{
					//跳转到重新登录界面
					af =  new ActionForward("/coremain/portal/global/sessionLose.jsp");
				    
				}else//已经登录，判断是否有当前系统的角色权限
				{
					boolean nRole = checkRole();
					//有系统权限
					if(true == nRole)
					{
						try {
							if (this.hasPrivilegeAnnotation(request)) {
								if (checkPrivilegeByAnnotation(request) == false) {
									 af = new ActionForward("/coremain/portal/global/noFunction.jsp");
								}
							} else {
								//校验是否有功能权限
								if (false == checkPrivilege())
								    //跳转到没有权限提示界面
								    af = new ActionForward("/coremain/portal/global/noFunction.jsp");
							}
						} catch (Exception e) {
							af =  new ActionForward("/coremain/portal/global/noFunction.jsp");
							throw e;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			getLogger("校验当前操作权限ACTION",GlobalConst.ERROR).error(e.getMessage());
		}
		return af;
    }
	
	public boolean checkRole(){
		//暂不做处理
		return true;
	}
	
	/**
	 * 检查用户在当前系统中的活动权限
	 * 
	 * @param request 当前会话信息
	 * @throws NLAppException 如果用户没有活动权限
	 */
	public boolean checkPrivilege() throws Exception {
		/*
		boolean checkResult = false;
		//此方法根据用户需要实现相关检查;
		SessionData sessionData = (SessionData)getCurSession().getAttribute(SessionConst.LOGIN_SESSION);
		Map sysPrivilegeMap = new HashMap();
		//sysPrivilegeMap = sessionData.getSysPrivilgeMap().get(getBaseAppActionForm().getSystemId());
		
		if (null != sysPrivilegeMap && sysPrivilegeMap.get(getBaseAppActionForm().getFunctionId()) != null)
			checkResult = true;
		
		return checkResult;
		*/
		return true;
		
	}
	
	/**
	 * @Title: hasPrivilegeAnnotation 
	 * @Description: 判断指定函数 和 类 是否有AppPrivileges的注释
	 * @author dq
	 * @date Dec 12, 2013 1:44:06 PM 
	 * @version V1.0
	 * @return boolean   
	 * @throws
	 */
	private boolean hasPrivilegeAnnotation(HttpServletRequest request){
		Class<? extends BaseAppAction> c = this.getClass();
		String methodParam = request.getParameter("method");
		if (methodParam == null || methodParam.equals("")) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("请求中的method参数为空");
			return false;
		}
		
		Method method = null;
		try {
			method = c.getMethod(methodParam, ActionMapping.class, ActionForm.class, 
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		
		if (method == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("在action类中找不到指定的函数");
			return false;
		}
		AppPrivileges appPrivileges = c.getAnnotation(AppPrivileges.class);
		AppPrivileges appPrivileges2 = method.getAnnotation(AppPrivileges.class);
		if (appPrivileges != null || appPrivileges2 != null) {
			//类和 函数中 有一个有权限控制的注释 就算有
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: checkPrivilegeByAnnotation 
	 * @Description: 通过 annotation注释取判断是否有模块权限
	 * @author dq   
	 * @date Oct 30, 2013 9:03:25 AM 
	 * @version V1.0  
	 * @param @return    
	 * @return boolean   
	 * @throws Exception 
	 * @throws
	 */
	public boolean checkPrivilegeByAnnotation(HttpServletRequest request) throws Exception {
		Class<? extends BaseAppAction> c = this.getClass();
		
		String methodParam = request.getParameter("method");
		if (methodParam == null || methodParam.equals("")) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("请求中的method参数为空");
			return false;
		}
		
		Method method = null;
		try {
			method = c.getMethod(methodParam, ActionMapping.class, ActionForm.class, 
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		
		if (method == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("在类中找不到指定的函数");
			return false;
		}
		Map paramMap = request.getParameterMap();
		if (paramMap == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("请求的参数为空");
			return false;
		}
		AppPrivileges appPrivileges = c.getAnnotation(AppPrivileges.class);
		AppPrivileges appPrivileges2 = method.getAnnotation(AppPrivileges.class);
		if ((appPrivileges == null || appPrivileges.limits() == null) &&
				(appPrivileges2 == null || appPrivileges2.limits() == null)) {
			return false;
		} else {
			Set<Map<String, String>> funcSet = new HashSet<Map<String, String>>();
			if (appPrivileges != null && appPrivileges.limits() != null) {
				Set<Map<String, String>> set = this.analysisPrivileges(appPrivileges, paramMap);
				if (set != null) {
					Iterator<Map<String, String>> iterator = set.iterator();
					while (iterator.hasNext()) {
						funcSet.add(iterator.next());
					}
				}
			}
			if (appPrivileges2 != null && appPrivileges2.limits() != null) {
				Set<Map<String, String>> set2 = this.analysisPrivileges(appPrivileges2, paramMap);
				if (set2 != null) {
					Iterator<Map<String, String>> iterator = set2.iterator();
					while (iterator.hasNext()) {
						funcSet.add(iterator.next());
					}
				}
			}
			
			//遍历所有权限 检测全部通过返回true 否则false
			Iterator<Map<String, String>> iterator = funcSet.iterator();
			while (iterator.hasNext()) {
				Map<String, String> map = iterator.next();
				String funcid = map.get(AppFunc.class.getName());
				String sysid = map.get(AppSystem.class.getName());
				if (StringUtils.isEmpty(getBaseAppActionForm().getFunctionId())) {
					if (!StringUtils.isEmpty(funcid)) {
						getBaseAppActionForm().setFunctionId(funcid);
					}
				}
				if (StringUtils.isEmpty(getBaseAppActionForm().getSystemId())) {
					if (!StringUtils.isEmpty(sysid)) {
						getBaseAppActionForm().setSystemId(sysid);
					}
				}

				getLogger(getBossCodeStr(), GlobalConst.EXECUTE).debug("权限编号::::" + funcid + "   系统编号:::::" + sysid);
				
				if (StringUtils.isEmpty(funcid) || StringUtils.isEmpty(sysid)) {
					throw new Exception("权限判断出错：：：：注释有误");
				}
				if (!this.hasPrivilege(funcid, sysid)) {
					return false;
				}
			}
			//如果没有任何 （参数匹配但是没有权限的情况） 就表示拥有访问的权限
			return true;
		}
	}
	
	/**
	 * 
	 * @Title: checkPrivilege 
	 * @Description: 判断是否拥有某权限
	 * @author dq   
	 * @date Dec 12, 2013 3:07:45 PM 
	 * @version V1.0  
	 * @param funcid 权限编号
	 * @param sysid 所属系统编号
	 * @return boolean   
	 * @throws 如果参数为空的话会记录错误但不抛出，结果返回false
	 */
	protected boolean hasPrivilege(String funcid, String sysid){
		if (funcid == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("校验权限参数 funcid 为空");
			return false;
		}
		if (sysid == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("校验权限参数 sysid 为空");
			return false;
		}
		/*Map sysPrivilegeMap = getSessionData().getSysPrivilgeMap().get(sysid);
		if(null != sysPrivilegeMap && sysPrivilegeMap.get(funcid) != null){
			return true;
		}else {
			return false;
		}*/
		return true;
	}
	
	/**
	 * 
	 * @Title: analysisPrivileges 
	 * @Description: 从AppPrivileges注释中 根据请求的参数Map 解析出需要验证的functionId
	 * @author dq   
	 * @date Dec 12, 2013 11:20:23 AM 
	 * @version V1.0  
	 * @param appPrivileges 函数或者类中的annotation类
	 * @param paramMap 请求参数 
	 * @return List<Map<String, String>>  map key=类AppFunc的名称 表示权限编号 key=类AppSystem的名称 表示系统编号
	 * @throws Exception  参数如果为空的话 会抛出参数为空的错误
	 */
	private Set<Map<String, String>> analysisPrivileges(AppPrivileges appPrivileges, Map paramMap) throws Exception{
		//如果参数为空的话
		if (appPrivileges == null) {
			throw new Exception("参数appPrivileges 为空");
		}
		//如果
		if (paramMap == null) {
			throw new Exception("参数paramMap 为空");
		}
		Set<Map<String, String>> funcSet = new HashSet<Map<String, String>>();
		if (appPrivileges.limits() != null) {
			//循环权限注释  所有权限 添加到一个集合中 剔重
			for (AppPrivilege appPrivilege : appPrivileges.limits()) {
				//如果需要校验sysid 需要拓展
				AppParam[] appParams = appPrivilege.limit();
				if (appParams == null || appParams.length == 0) {
					//如果参数limit 为空的话表示默认的权限  添加权限
					Map<String, String> funcMap = new HashMap<String, String>();
					funcMap.put(AppFunc.class.getName(), appPrivilege.funcId().toString());
					funcMap.put(AppSystem.class.getName(), appPrivilege.sysId().toString());
					funcSet.add(funcMap);
				}else {
					//如果limit 有值前所有参数与值 匹配表示该权限需要验证
					for (int i=0;i<appParams.length;i++) {
						AppParam appParam = appParams[i];
						String name = appParam.name();
						String value = appParam.value();
						//如果参数和值没有匹配上跳出循环
						if (paramMap.get(name) == null || !paramMap.get(name).equals(value)) {
							break;
						}
						//如果循环结束还没有跳出的话表示全部匹配上了
						if (i == appParams.length-1) {
							Map<String, String> funcMap = new HashMap<String, String>();
							funcMap.put(AppFunc.class.getName(), appPrivilege.funcId().toString());
							funcMap.put(AppSystem.class.getName(), appPrivilege.sysId().toString());
							funcSet.add(funcMap);
						}
					}
				}
			}
		}
		return funcSet;
	}
	
	/**
	 * 检查用户是否可以登录
	 * 两个SESSION值，在企业用户又是系统用户同时都登录的时候，会有问题。判断不了，可以解决的是，合并到一个SESSION，SESSION值用一样的。
	 * @return true为可以登录，false为不能登录
	 * @throws Exception 如果用户登陆权限
	 */
	public boolean checkSession()throws Exception {
		//判断当前用户是否登录
		SessionData sessionData = (SessionData)getCurSession().getAttribute(SessionConst.LOGIN_SESSION);
		SessionData c_sessionData = (SessionData)getCurSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
		if ((sessionData == null || (sessionData.getAdmUser() == null&&sessionData.getCompanyUser()==null))&&(c_sessionData == null || (c_sessionData.getAdmUser() == null&&c_sessionData.getCompanyUser()==null)))
		{
			return false;
		}   
		else
		{
			//bosscode赋值
			if(sessionData.getAdmUser() != null)
				setBossCodeStr(sessionData.getAdmUser().getAuID());
			if(sessionData.getCompanyUser() != null)
				setBossCodeStr(sessionData.getCompanyUser().getCompany_user_id());
			
			if(c_sessionData.getAdmUser() != null)
				setBossCodeStr(c_sessionData.getAdmUser().getAuID());
			if(c_sessionData.getCompanyUser() != null)
				setBossCodeStr(c_sessionData.getCompanyUser().getCompany_user_id());
			
			return true;
		}
		
			
	}
	
	/**
	 * 登录方法中使用
	 * @throws Exception
	 */
	public void notCheckLogin(){
		this.checkLoginFlag = false;
		
	}
	
	public void checkLogin(){
		this.checkLoginFlag = true;
		
	}
	
	public Log getLogger(){
		return logger;
	}
	
	public Log getLogger(String bossCode,String name) {
		return logger = SystemTool.getLoggerForWebApp(bossCode,name);
	}
	
	public Log getLogger(String name) {
		return logger = SystemTool.getLoggerForWebApp(this.bossCodeStr,name);
	}
	
	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public String getBossCodeStr() {
		return bossCodeStr;
	}

	public void setBossCodeStr(String bossCodeStr) {
		this.bossCodeStr = bossCodeStr;
	}

	public HttpSession getCurSession()
	{
		return curSession;
	}

	public void setCurSession(HttpSession curSession)
	{
		this.curSession = curSession;
	}

	public BaseAppActionForm getBaseAppActionForm()
	{
		return baseAppActionForm;
	}



	public SessionData getSessionData()
	{
		return (SessionData)curSession.getAttribute(SessionConst.LOGIN_SESSION);
	}
	
	public void setBaseAppActionForm(BaseAppActionForm baseAppActionForm)
	{
		this.baseAppActionForm = baseAppActionForm;
	}
	
}
