<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.nl.portal.sc.*"%>
<%@page import="com.nl.portal.dt.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.nl.util.SessionConst"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.base.utils.GlobalFunc"%>

<%@page import="com.nl.portal.actionForm.*"%>
<%@page import="net.sf.json.JSONArray"%>

<%
HashMap<String,String> resultMap = new HashMap<String,String>();
SessionData sessionData = (SessionData)request.getAttribute(SessionConst.LOGIN_SESSION);

try
{
	String provinces = request.getParameter("provinces");
	String city = request.getParameter("city");
	UserForm formBean = new UserForm();
	
	UserSc sc = new UserSc();
	formBean.setProvinces(provinces);
	formBean.setCity(city);
	//formBean.setRegion(sessionData.getRegion());

	List<UserInfo> userlist = sc.getRegionByCity(formBean);
	
	String options = "<option value=''>---全部---</option>";
	if(null!=userlist&&userlist.size()>0){ 
         for(int i=0;i<userlist.size();i++){
        	 UserInfo user = userlist.get(i);
        	 String org_id = user.getOrg_id();
        	 String org_name = user.getOrg_name();
             options = options+"<option value="+org_id+">"+org_name+"</option>"; 
         }
	}
	resultMap.put("cityMap",options);
	//String result = GlobalFunc.getJosnStrForObject(resultMap);
	JSONArray jsonArray = JSONArray.fromObject(resultMap);
	//System.out.println("jsonArray=="+jsonArray);
	out.print(jsonArray);
}catch(Exception e){
//	getLogger(bosscodestr,GlobalConst.ERROR).error("进入欢迎页面出错:"+e.getMessage());
	out.print("[]");
	throw new Exception();
	
}
	
%>    