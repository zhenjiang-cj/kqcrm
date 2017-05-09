package com.nl.portal.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.nl.base.BaseAppAction;
import com.nl.base.utils.GlobalFunc;
import com.nl.portal.actionForm.CommonForm;
import com.nl.portal.dt.KmDictCfg;
import com.nl.portal.sc.SystemSC;
import com.nl.ql.util.AppErrorInfo;
import com.nl.util.GlobalConst;


public class CommonAction extends BaseAppAction
{

	private final Logger logger = Logger.getLogger(this.getClass());
	
	public CommonAction()
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
	 * @createdate Oct 18, 2016
	 * @version v1.0
	 */
	public void queryDictById(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String bosscodestr = super.getBossCodeStr();
			getLogger(bosscodestr,GlobalConst.ENTER).info("开始执行查询字典信息功能action：");
			int retCode = 0;
			try
			{
				CommonForm formBean = (CommonForm)form;
				//查询字典信息列表
				SystemSC sc = new SystemSC(bosscodestr);
				List<KmDictCfg> list = sc.queryDictCfgList(formBean.getDict_id());
				response.setContentType("text/html; charset=GBK");
				PrintWriter pWriter = null;
				pWriter = response.getWriter();
				pWriter.write(GlobalFunc.getJosnStrForList(list));
			}catch(Exception e){
				getLogger(bosscodestr,GlobalConst.ERROR).error("error:"+e.getMessage());
				throw new Exception(AppErrorInfo.QLINFO_QUERY);
			}
	}
	
	

}
