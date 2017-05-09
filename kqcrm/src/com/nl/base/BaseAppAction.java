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
	
	//��ֵ���userid��ֵ
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
//            log.debug(ROLE + "::baseAppAction()->Enter::�������� " + parameter);
//            if("execute".equals(parameter) || "perform".equals(parameter))
//            {
//                String message = DispatchAction.messages.getMessage("dispatch.recursive", mapping.getPath());
//                log.error(message);
//                throw new ServletException(message);
//            }
            forward = process(mapping, form, request, response);
//            if(forward == null){
//            	//����������ʱ  ����¼����Ϣ�ŵ�thread��ȥ
//            	SessionTool.setSessionData(getSessionData());
//            	
//            	//����������һ����Ӧ�ÿ���ȡ���û���Ϣ�ˣ��Ҵ�ǰ̨�����л�ȡMENU_CLICK
//            	String menuClick = request.getParameter("menuClick");
//            	if (StringUtils.isNotEmpty(menuClick) && menuClick.equals("true")) {
//                    String funcname = GlobalFunc.getFunctionName(getBaseAppActionForm().getFunctionId());
//
//				}
            	
            	forward = dispatchMethod(mapping, form, request, response, parameter);
//            }
            //log.debug(parameter + " ���ý���");
//            log.debug(ROLE + "::baseAppAction()->Enter::" + parameter + " ���ý���");
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
		
		//���������޸ĵĹ��ܲ�����Ȩ�޵�У��
		if (null != this.getBaseAppActionForm().getFunctionId() 
			&& this.getBaseAppActionForm().getFunctionId().equals(GlobalConst.FUNCTION_OPERATOR_PASSWORD_UPDATE))
			this.checkLoginFlag = false;
		
		//���ڹ�����֤�Ĺ��ܲ�����Ȩ�޵�У��
		if (null != this.getBaseAppActionForm().getFunctionId() 
			&& "1".equals(this.getBaseAppActionForm().getIs_check()))
			this.checkLoginFlag = false;
		
		try{
			//�ж��Ƿ���Ҫ������֤
			if(this.checkLoginFlag == true){
				//�ж�session�Ƿ����
				boolean nRet = checkSession();
				
				if(false == nRet)
				{
					//��ת�����µ�¼����
					af =  new ActionForward("/coremain/portal/global/sessionLose.jsp");
				    
				}else//�Ѿ���¼���ж��Ƿ��е�ǰϵͳ�Ľ�ɫȨ��
				{
					boolean nRole = checkRole();
					//��ϵͳȨ��
					if(true == nRole)
					{
						try {
							if (this.hasPrivilegeAnnotation(request)) {
								if (checkPrivilegeByAnnotation(request) == false) {
									 af = new ActionForward("/coremain/portal/global/noFunction.jsp");
								}
							} else {
								//У���Ƿ��й���Ȩ��
								if (false == checkPrivilege())
								    //��ת��û��Ȩ����ʾ����
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
			getLogger("У�鵱ǰ����Ȩ��ACTION",GlobalConst.ERROR).error(e.getMessage());
		}
		return af;
    }
	
	public boolean checkRole(){
		//�ݲ�������
		return true;
	}
	
	/**
	 * ����û��ڵ�ǰϵͳ�еĻȨ��
	 * 
	 * @param request ��ǰ�Ự��Ϣ
	 * @throws NLAppException ����û�û�лȨ��
	 */
	public boolean checkPrivilege() throws Exception {
		/*
		boolean checkResult = false;
		//�˷��������û���Ҫʵ����ؼ��;
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
	 * @Description: �ж�ָ������ �� �� �Ƿ���AppPrivileges��ע��
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
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("�����е�method����Ϊ��");
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
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("��action�����Ҳ���ָ���ĺ���");
			return false;
		}
		AppPrivileges appPrivileges = c.getAnnotation(AppPrivileges.class);
		AppPrivileges appPrivileges2 = method.getAnnotation(AppPrivileges.class);
		if (appPrivileges != null || appPrivileges2 != null) {
			//��� ������ ��һ����Ȩ�޿��Ƶ�ע�� ������
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: checkPrivilegeByAnnotation 
	 * @Description: ͨ�� annotationע��ȡ�ж��Ƿ���ģ��Ȩ��
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
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("�����е�method����Ϊ��");
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
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("�������Ҳ���ָ���ĺ���");
			return false;
		}
		Map paramMap = request.getParameterMap();
		if (paramMap == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("����Ĳ���Ϊ��");
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
			
			//��������Ȩ�� ���ȫ��ͨ������true ����false
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

				getLogger(getBossCodeStr(), GlobalConst.EXECUTE).debug("Ȩ�ޱ��::::" + funcid + "   ϵͳ���:::::" + sysid);
				
				if (StringUtils.isEmpty(funcid) || StringUtils.isEmpty(sysid)) {
					throw new Exception("Ȩ���жϳ���������ע������");
				}
				if (!this.hasPrivilege(funcid, sysid)) {
					return false;
				}
			}
			//���û���κ� ������ƥ�䵫��û��Ȩ�޵������ �ͱ�ʾӵ�з��ʵ�Ȩ��
			return true;
		}
	}
	
	/**
	 * 
	 * @Title: checkPrivilege 
	 * @Description: �ж��Ƿ�ӵ��ĳȨ��
	 * @author dq   
	 * @date Dec 12, 2013 3:07:45 PM 
	 * @version V1.0  
	 * @param funcid Ȩ�ޱ��
	 * @param sysid ����ϵͳ���
	 * @return boolean   
	 * @throws �������Ϊ�յĻ����¼���󵫲��׳����������false
	 */
	protected boolean hasPrivilege(String funcid, String sysid){
		if (funcid == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("У��Ȩ�޲��� funcid Ϊ��");
			return false;
		}
		if (sysid == null) {
			getLogger(getBossCodeStr(), GlobalConst.EXECUTE).error("У��Ȩ�޲��� sysid Ϊ��");
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
	 * @Description: ��AppPrivilegesע���� ��������Ĳ���Map ��������Ҫ��֤��functionId
	 * @author dq   
	 * @date Dec 12, 2013 11:20:23 AM 
	 * @version V1.0  
	 * @param appPrivileges �����������е�annotation��
	 * @param paramMap ������� 
	 * @return List<Map<String, String>>  map key=��AppFunc������ ��ʾȨ�ޱ�� key=��AppSystem������ ��ʾϵͳ���
	 * @throws Exception  �������Ϊ�յĻ� ���׳�����Ϊ�յĴ���
	 */
	private Set<Map<String, String>> analysisPrivileges(AppPrivileges appPrivileges, Map paramMap) throws Exception{
		//�������Ϊ�յĻ�
		if (appPrivileges == null) {
			throw new Exception("����appPrivileges Ϊ��");
		}
		//���
		if (paramMap == null) {
			throw new Exception("����paramMap Ϊ��");
		}
		Set<Map<String, String>> funcSet = new HashSet<Map<String, String>>();
		if (appPrivileges.limits() != null) {
			//ѭ��Ȩ��ע��  ����Ȩ�� ��ӵ�һ�������� ����
			for (AppPrivilege appPrivilege : appPrivileges.limits()) {
				//�����ҪУ��sysid ��Ҫ��չ
				AppParam[] appParams = appPrivilege.limit();
				if (appParams == null || appParams.length == 0) {
					//�������limit Ϊ�յĻ���ʾĬ�ϵ�Ȩ��  ���Ȩ��
					Map<String, String> funcMap = new HashMap<String, String>();
					funcMap.put(AppFunc.class.getName(), appPrivilege.funcId().toString());
					funcMap.put(AppSystem.class.getName(), appPrivilege.sysId().toString());
					funcSet.add(funcMap);
				}else {
					//���limit ��ֵǰ���в�����ֵ ƥ���ʾ��Ȩ����Ҫ��֤
					for (int i=0;i<appParams.length;i++) {
						AppParam appParam = appParams[i];
						String name = appParam.name();
						String value = appParam.value();
						//���������ֵû��ƥ��������ѭ��
						if (paramMap.get(name) == null || !paramMap.get(name).equals(value)) {
							break;
						}
						//���ѭ��������û�������Ļ���ʾȫ��ƥ������
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
	 * ����û��Ƿ���Ե�¼
	 * ����SESSIONֵ������ҵ�û�����ϵͳ�û�ͬʱ����¼��ʱ�򣬻������⡣�жϲ��ˣ����Խ�����ǣ��ϲ���һ��SESSION��SESSIONֵ��һ���ġ�
	 * @return trueΪ���Ե�¼��falseΪ���ܵ�¼
	 * @throws Exception ����û���½Ȩ��
	 */
	public boolean checkSession()throws Exception {
		//�жϵ�ǰ�û��Ƿ��¼
		SessionData sessionData = (SessionData)getCurSession().getAttribute(SessionConst.LOGIN_SESSION);
		SessionData c_sessionData = (SessionData)getCurSession().getAttribute(SessionConst.COMPANY_LOGIN_SESSION);
		if ((sessionData == null || (sessionData.getAdmUser() == null&&sessionData.getCompanyUser()==null))&&(c_sessionData == null || (c_sessionData.getAdmUser() == null&&c_sessionData.getCompanyUser()==null)))
		{
			return false;
		}   
		else
		{
			//bosscode��ֵ
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
	 * ��¼������ʹ��
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
