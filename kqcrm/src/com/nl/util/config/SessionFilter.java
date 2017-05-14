package com.nl.util.config;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nl.base.utils.GlobalFunc;
import com.nl.base.utils.GlobalRsDt;
import com.nl.util.GlobalConst;
import com.nl.util.SessionConst;
import com.nl.util.SessionData;
 
public class SessionFilter implements Filter{
	
	public void destroy() {
	}
	
	public void doJump(int retCode,String url,String objString,ServletRequest request,ServletResponse response){
		GlobalRsDt rsDt = new GlobalRsDt();
		String remark = "";
		String jsonString = "";
		PrintWriter pWriter = null;
		
		if(retCode==0){
			remark = objString+"成功";	
			
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
			remark =  objString+"失败";
			
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

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		//session.setMaxInactiveInterval(60);
		String contextPath = httpRequest.getContextPath();
		String url = httpRequest.getRequestURI();
		//  登录方法标志:默认为0,当为1时,为登录方法,不拦截
		String loginflag = "0";
		// System.out.println("请求的URL为：" + url);
        if(url.equals(contextPath + "/login.jsp") || url.equals(contextPath + "/login") ){
        	chain.doFilter(request, response);
        }else if(url.contains(".css") || url.contains(".js") || url.contains(".png") || url.contains(".gif") || url.contains(".jpg") || url.contains(".bmp")){
			chain.doFilter(request, response);
        }else {
        	if(request.getParameter("loginflag") !=null && !"".equals(request.getParameter("loginflag"))){
				loginflag = request.getParameter("loginflag");
        	}
        	if("1".equals(loginflag)){
        		chain.doFilter(request, response);
        	}else{
 
	     		SessionData sessionData = (SessionData)session.getAttribute(SessionConst.LOGIN_SESSION);
	     		if(sessionData==null){
//	     			httpResponse.sendRedirect(contextPath + "/login.jsp");
	     			//url =contextPath+"/index.jsp";
					//doJump(0,url,"登录",request,response);
	     			//request.setAttribute("loginflag", "0");
					//chain.doFilter(request, response);
	    		}else{
	    			if(sessionData.getUser_id()==null||sessionData.getUser_id().equals("")){
		     			httpResponse.sendRedirect(contextPath + "/login.jsp");
		     			//request.setAttribute("loginflag", "0");
						chain.doFilter(request, response);
	    			}else{
	    				chain.doFilter(request, response);
	    			}
	    		}
     		
     		
				
			}
         }
	 }

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
