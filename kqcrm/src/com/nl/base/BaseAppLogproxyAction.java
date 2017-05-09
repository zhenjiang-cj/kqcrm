package com.nl.base;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nl.base.utils.GlobalFunc;
import com.nl.util.AjaxException;
import com.nl.util.AppFunctionLogger;
import com.nl.util.GlobalConst;
import com.nl.util.PortalzjException;

/*
 * ��Ϊû��ʹ��aop��Ƭ�ķ�ʽ�ֲ�Ӱ��֮ǰ��ϵͳ�����Ժ�����
 * ��־������
 */
public class BaseAppLogproxyAction<T extends BaseAppActionForm> extends BaseAppAction {
	
	/**
	 * 
	 * @Title: dispatchMethod 
	 * @Description: ����ͬһ����  action�е���־����Ϣ
	 * @author dq   
	 * @date 2014-2-24 ����03:11:32 
	 * @version V1.0 
	 * @throws Exception
	 */
	@Override
	public ActionForward dispatchMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String name) throws Exception{
		String funcName = "";
		String operatorType = "";
		String objectParam = "";
		boolean needLog = false;
		try {
			//ʹ��ע������  ��Ǻ���������  �Ƿ���Ҫ��¼log  ������������
			Method method = this.getClass().getMethod(name, ActionMapping.class, ActionForm.class, 
					HttpServletRequest.class, HttpServletResponse.class);
			AppFunctionLogger funcLogger = method.getAnnotation(AppFunctionLogger.class);
			funcName = funcLogger == null || StringUtils.isEmpty(funcLogger.name()) ? "" : funcLogger.name();
			operatorType = funcLogger == null || StringUtils.isEmpty(funcLogger.type()) ? "" : funcLogger.type();
			needLog = funcLogger == null ? false : funcLogger.needLog();
			objectParam = funcLogger == null || funcLogger.objectParam() == null ? "" : funcLogger.objectParam();
		} catch (Exception e) {
			getLogger(getBossCodeStr(), GlobalConst.ERROR).error("�������ƻ�ȡ����");
		}
		try {
			getLogger(getBossCodeStr(), GlobalConst.ENTER).info(funcName);
			ActionForward af = super.dispatchMethod(mapping, form, request, response, name);
			getLogger(getBossCodeStr(), GlobalConst.ENTER).info(funcName);
			
			if (needLog) {
				//����������Ҫ��¼ʱ
				//****************************START  ��¼��־***********************************
				int operating_type = Integer.valueOf(operatorType);//��������

				String functionIdStr = getBaseAppActionForm().getFunctionId();
				long function_id = StringUtils.isEmpty(functionIdStr) ? 0 : Long.parseLong(functionIdStr);
				int sys_id = StringUtils.isEmpty(getBaseAppActionForm().getSystemId()) ? 
						0 : Integer.parseInt(getBaseAppActionForm().getSystemId());//ϵͳ���
				String operating_oper_id = getSessionData().getAdmUser().getAuID();//������
				String operating_oper_name = getSessionData().getAdmUser().getAuName();
				int operating_result = GlobalConst.GLOBAL_RESULT_SUCCESS;//���
				String operating_object = "";
				
				if (!StringUtils.isEmpty(objectParam)) {
					operating_object = request.getParameter(objectParam);
				}
				
				String remark = operating_oper_name + "����"+GlobalConst.OPERATION_TEXT.get(operatorType)+"��" + funcName;
				//GlobalFunc.createSysOperatingLog(operating_oper_id,operating_oper_name,operating_type, function_id, operating_object, sys_id,operating_result, remark);
				//****************************END   ��¼��־***********************************
			}
			
			return af;
		} catch (Exception e) {
			e.printStackTrace();
			Exception exception = new PortalzjException(getBossCodeStr(), funcName + "����  ", e);
			if (e.getClass().equals(AjaxException.class)) {
				return null;
			}else {
				throw exception;
			}
		}
	}

	@Override
	public T getBaseAppActionForm(){
		return (T)super.getBaseAppActionForm();
	}
}
