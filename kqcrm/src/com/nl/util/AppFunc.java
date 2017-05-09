package com.nl.util;
public enum AppFunc {
	FUNC_GH_DETAIL_EXPORT("13401021"),//固话号码信息管理
	
	/**********需求上报系统**********/
	FUNC_REQUIREMENT_ADD("138001"),//工单上报
	FUNC_REQUIREMENT_MY_DEAL("138002"),//我的代办
	FUNC_REQUIREMENT_BY_MY_HAND("138003"),//我的待阅
	FUNC_REQUIREMENT_LIST("138004"),//流程跟踪
	FUNC_REQUIREMENT_MSGALARM_CONFIGURATION("138005"),//流程跟踪
	
	/**********公告管理********/
	FUNC_PORTAL_NOTICE_HISTORY("993011"),//历史公告
	FUNC_PORTAL_NOTICE_LIST("99301010"),//公告管理页面
	FUNC_PORTAL_NOTICE_INSERT("99301020"),//公告新增
	FUNC_PORTAL_NOTICE_MODIFY("99301030"),//公告修改
	FUNC_PORTAL_NOTICE_DELETE("99301040"),//公告删除
	
	FUNC_PORTAL_NOTICE_MANAGER("993020"),//公告管理员
	

	/**********营销案中断********/
	FUNC_PRIVBREAK_FLOW_ADD("139001"),//营销案中断工单流申请
	FUNC_PRIVBREAK_FLOW_MYDEAL("139002"),//营销案中断工单流申请
	FUNC_PRIVBREAK_FLOW_BYMYHAND("139003"),//营销案中断工单流申请
	FUNC_PRIVBREAK_FLOW_LIST("139004"),//营销案中断工单流申请
	FUNC_PRIVBREAK_FLOW_LIST_EXPORT("1390041"),//营销案中断工单流申请
	FUNC_PRIVBREAK_FLOW_CATALOG_CFG("139005")//营销案中断工单流申请
	;
	
	
	
	private String FunctionId="";
	private AppFunc(String FunctionId){
		this.FunctionId=FunctionId;
	}
	@Override
	public String toString() {
		return this.FunctionId;
	}
}
