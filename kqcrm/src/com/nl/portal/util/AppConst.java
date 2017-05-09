package com.nl.portal.util;
/**
 *
 * @author HP
 * @creatdate Aug 20, 2013
 */
public class AppConst {
	/**
	 * 公告管理
	 */
	public static String FUNCTION_NOTICE_QUERY = "99301010";
	public static String FUNCTION_NOTICE_ADD = "99301020";
	public static String FUNCTION_NOTICE_MODIFY = "99301030";
	public static String FUNCTION_NOTICE_DEL = "99301040";
	
	/*
	 * 定制菜单
	 */
	public static String FUNCTION_CUSTOM_MENU = "994010";

	
	/**
	 * 人员机构信息管理系统
	 */
	public static String FUNCTION_PORTAO_USER="9950";
	public static String FUNCTION_PORTAL_USER_FRAME ="995010";
	public static String FUNCTION_PORTAL_USER_ADD="99501010";
	public static String FUNCTION_PORTAL_USER_DEL="99501020";
	public static String FUNCTION_PORTAL_USER_MODIFY="99501030";
	
	/**
	 * 组织机构信息管理
	 */
	public static String FUNCTION_PORTAL_DEPT="";
	public static String FUNCTION_PORTAL_DEPT_FRAME="995020";
	public static String FUNCTION_PORTAL_DEPT_ADD="99502010";
	public static String FUNCTION_PORTAL_DEPT_DEL="99502020";
	public static String FUNCTION_PORTAL_DEPT_MODIFY="99502030";

	
	public static int NOTICE_TYPE_INDEX = 1;//推送方式--界面
	public static int NOTICE_TYPE_MSG = 2;//推送方式--短信
	public static int NOTICE_TYPE_LIST = 3;//推送方式--列表
	
	public static int NOTICE_IS_SMS_YES = 1;//是否短信通知--是
	public static int NOTICE_IS_SMS_NO = 0;//是否短信通知--否
	
	public static int NOTICE_REGION_TYPE_DEPT = 1;//按组织机构划分
	public static int NOTICE_REGION_TYPE_GROUP = 2;//按区域划分
	
	public static String SYNC_FILE_PATH = "uploadFile/portal/";
	
	public static String FUNCTION_REPORT_INFO_MANAGE = "9960";
	public static String FUNCTION_REPORT_INFO_QUERY = "996010";
	public static String FUNCTION_REPORT_INFO_ADD = "996020";
	public static String FUNCTION_REPORT_INFO_MODIFY = "996030";
	public static String FUNCTION_REPORT_INFO_DEL = "996040";
	public static String FUNCTION_SERVICE_CONDITION = "996050";
	public static String FUNCTION_SERVICE_CONDITION_EXPORT = "996051";
	public static String FUNCTION_REPORT_DOWNLOAD = "996060";
	public static String FUNCTION_REPORT_DOWNLOAD_EXPORT = "996061";
}
