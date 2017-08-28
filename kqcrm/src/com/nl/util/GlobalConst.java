package com.nl.util;

import java.util.HashMap;
import java.util.Map;

public class GlobalConst{
	
	public GlobalConst()
    {
    }

	public static Map<String, Map<String, Map<String, String>>> PRIV_INTERRUPT_FORMULA = new HashMap<String, Map<String, Map<String, String>>>();
	
	public static String LOGIN_GO = "LOGIN_GO";
	
	public static String Global_ERROR_PAGE = "Global_ERROR_PAGE";
    public static String Global_ERROR_ID = "Global_ERROR_ID";
    /**
	 * 导出excel时设置每个sheet可容纳60000条记录
	 */
	public static final int SHEET_COUNT =  60000;		
	/**
     * 导出明细中一次查询数据库最大的数量
     */
    public static int EXP_MAX_COUNT=0;
    /**
     * 导出数据中超过一定的数量就采用csv格式导出
     */
    public static int EXP_CSV_COUNT=0;
	/** WEB处理相关日志关键字 */
	public static final String LOG_WEB_APP = "WEB-APP";
	
	/** 系统日志关键字 */
	public static final String LOG_SERVER_APP = "SERVER_APP";
	
	/** 日志步骤 */
	public static final String ENTER = "Enter";
	
	public static final String EXECUTE = "Execute";
	
	public static final String EXIT = "Exit";
	
	public static final String ERROR = "Error";
	
	/**
	 * 设置锁定记录条数
	 */
	public static final int LOCK_NUM = 10;
	
	/*数据库配置*/
	public static String database_mode = "jdbc";//采用jdbc直连的方法
    public static String database_drive = "oracle.jdbc.driver.OracleDriver";
    public static final int dbpool_id = 1; // 1 
    /**
     * 数据源连接地址
     */
    public static String database_url = "jdbc:oracle:thin:zjtest/zjtest@10.34.8.92:1521:osctest";
    /**
     * 数据源连接用户名
     */
    public static String database_username = "";
    /**
     * 数据源连接密码
     */
    public static String database_password = "";
    /**
     * 数据源初始化最小连接数
     */
    public static String database_initialSize = "10";
    /**
     * 数据源最大可用连接数
     */
    public static String database_maxActive = "20";

    public static String JNDI_NAME = "dbpool_kqcrm";
    
    /**
     * 数据源连接
     */
    public static String database_jndi = "dbpool_kqcrm";

    /**
     * 数据库t3协议 url
     */
    public static String t3_url = "t3://localhost:8007";
    /**
     * 数据库t3协议 user;
     */
    public static String t3_user = "system";
    /**
     * 数据库t3协议 password;
     */
    public static String t3_password = "password";
    /**
     * 1: dbpool 2:t3pool 3:jdbc
     */
    public static int database_type = 1;// 1: dbpool 2:t3pool 3:jdbc
	/*数据库配置*/
	
	
	public static final String JOBS_JNDI = "dbpool_kqcrm";   //数据库JNDI名字的定义 
	
	public static final String APP_JNDI = "dbpool_kqcrm";
	public static final String CDR_JNDI = "dbpool_kqcrm";//核心库JNDI
	
	//报表系统URL地址
	public static String URL_BIZJ = "";
	//一线支撑URL地址
	public static String URL_BXZJ = "";
	//
	public static String FILE_PATH = "";
	public static String TARGET_PATH = "";
	public static final String UPLOAD_TMP_FILE_PATH = "/uploadFile/tmp";
	public static final String DOWNLOAD_FILE_PATH = "/uploadFile/template/";
	public static final String DOWNLOAD_BAKFILE_PATH = "/uploadFile/template/bak/";//备案模板
	//原业务支撑平台
	public static String URL_OLD_BIZJ = "";
	//FTP服务器信息
	public static String FTP_SERVER_IP = "";
	public static String FTP_SERVER_PORT = "";
	public static String FTP_SERVER_USERNAME = "";
	public static String FTP_SERVER_PASSWORD = "";
	
	//证书有效期止缓冲天数
	public static String BUFF_DAY="";
	
	/*系统登录错误提示*/
	public static final String LOGIN_USER_NOT_NULL = "用户名或密码不能为空，请重新输入！";
	public static final String LOGIN_USER_NOT_FOUND = "用户名不存在或失效，请核对后重新输入！";
	public static final String LOGIN_PASSWORD_FAIL = "用户密码不正确，请重新输入！";
	public static final String LOGIN_PASSWORD_SUCCESS = "用户登陆成功！";
	public static final String LOGIN_ERROR = "登录异常，请联系管理员！";
	public static final String LOGIN_ILLEGAL = "非法登录，请联系管理员！";
	
	/*全局的系统编号，和字典表中的相同*/
	public static final int[] Global_SYSTEM_ID = {99,1,2,3,4};
	
	/*form保存在request中的名称*/
	public static final String GLOBAL_CURRENT_FORM = "form";
	
	public static final String GLOBAL_SELECT_NAME = "--请选择--";
	public static final String GLOBAL_SELECT_ALL = "--全部--";
	public static final String GLOBLA_SELECT_VALUE = "-99";
	
	/*不同系统的system_id*/
	public static final String SYSTEM_ID_SYSTEM = "99";//系统管理
	public static final String SYSTEM_ID_CRM = "1";//客户管理
	public static final String SYSTEM_ID_HT = "2";//合同管理
	public static final String SYSTEM_ID_HF = "3";//客户回访
	
	/*菜单*/
	public static final String FUNCTION_SYS_MANAGE="99";//系统管理
	public static final String FUNCTION_SYS_USER="9910";//用户管理
	public static final String FUNCTION_SYS_USER_ADD="9911";
	public static final String FUNCTION_SYS_USER_EDIT="9912";
	public static final String FUNCTION_SYS_USER_DEL="9913";
	
	public static final String FUNCTION_SYS_ROLE="9920";//角色管理
	public static final String FUNCTION_SYS_ROLE_ADD="9921";
	public static final String FUNCTION_SYS_ROLE_EDIT="9922";
	public static final String FUNCTION_SYS_ROLE_DEL="9923";
	
	
	public static final String FUNCTION_CRM_MANAGE="110";//客户管理
	public static final String FUNCTION_CRM_USER_ADD = "111";//客户新增
	public static final String FUNCTION_CRM_USER_EDIT = "112";//客户新增
	public static final String FUNCTION_CRM_USER_DEL = "113";//客户新增
	public static final String FUNCTION_CRM_USER_EXP = "114";//客户导出
	
	public static final String FUNCTION_CRM_YXUSER_ADD = "121";//意向客户新增
	public static final String FUNCTION_CRM_YXUSER_EDIT = "122";//意向客户修改
	public static final String FUNCTION_CRM_YXUSER_DEL = "123";//意向客户删除
	public static final String FUNCTION_CRM_YXUSER_EXP = "124";//意向客户导出
	
	public static final String FUNCTION_HT_MANAGE="210";//合同管理
	public static final String FUNCTION_HT_USER_ADD = "211";//客户新增
	public static final String FUNCTION_HT_USER_EDIT = "212";//客户新增
	public static final String FUNCTION_HT_USER_DEL = "213";//客户新增
	public static final String FUNCTION_HT_USER_EXP = "214";//客户新增
	
	public static final String FUNCTION_HF_MANAGE="310";//客户回访
	public static final String FUNCTION_HF_USER_ADD = "311";//回访新增
	public static final String FUNCTION_HF_USER_EDIT = "312";//回访新增
	public static final String FUNCTION_HF_USER_DEL = "313";//回访新增
	public static final String FUNCTION_HF_USER_EXP = "314";//回访新增
	public static final String FUNCTION_REPAIR_ADD = "315";//维修记录录入
	public static final String FUNCTION_REPAIR_QUERY = "316";//维修记录查询
	
	public static final String FUNCTION_TJ_MANAGE="410";//统计报表
	public static final String FUNCTION_TJ_DEVICE="411";//设备统计
	
	
	/*操作类型*/
	public static final String OPERATION_INSERT = "1";//增加
	public static final String OPERATION_UPDATE = "2";//修改
	public static final String OPERATION_DELETE = "3";//删除
	public static final String OPERATION_QUERY = "4";//查询
	public static final String OPERATION_DETAIL = "5";//详细
	public static final String OPERATION_EXPORT = "6";//导出
	public static final String OPERATION_SEND = "7";//工单提交
	public static final String OPERATION_DEAL = "8";//工单处理
	public static final String OPERATION_BACK = "9";//工单退回
	public static final String OPERATION_FINISH = "10";//工单办结
	public static final String OPERATION_READ = "11";//工单阅知
	public static final String OPERATION_IMPORT = "12";//批量导入
	public static final String OPERATION_DOWNLOAD = "13";//附件下载
	public static final String OPERATION_LOGIN = "0";//登陆
	public static final String OPERATION_MENU_CLICK = "99";//菜单点击
	
	public static final Map<String, String> OPERATION_TEXT = new HashMap<String, String>();
	static{
		OPERATION_TEXT.put(OPERATION_INSERT, "增加");
		OPERATION_TEXT.put(OPERATION_UPDATE, "修改");
		OPERATION_TEXT.put(OPERATION_DELETE, "删除");
		OPERATION_TEXT.put(OPERATION_QUERY, "查询");
		OPERATION_TEXT.put(OPERATION_DETAIL, "详细");
		OPERATION_TEXT.put(OPERATION_SEND, "派发");
		OPERATION_TEXT.put(OPERATION_DEAL, "处理");
		OPERATION_TEXT.put(OPERATION_BACK, "退回");
		OPERATION_TEXT.put(OPERATION_MENU_CLICK, "点击");
	}
	
	/*系统成功失败返回结果定义*/
	public static final int GLOBAL_RESULT_SUCCESS = 0;//成功
	public static final int GLOBAL_RESULT_FAIL = -1;//失败
	
	/*流程任务环节状态*/
	public static final int FLOW_TASK_STATUS_NORMAL = 1;//正常
	public static final int FLOW_TASK_STATUS_OVER = 9 ;//结束
	
	/*子系统编号*/
	public static final int FLOW_SYS_DOCUMENT  =101;//公文系统
	public static final int FLOW_SYS_TMPMEET  =102;//临时动议
	public static final int FLOW_SYS_MONTHWORK  =103;//月度工作
	
	/*系统管理的权限编号*/
	public static final String FUNCTION_OPERATOR_MANAGE = "991010";//操作员管理
	public static final String FUNCTION_OPERATOR_DETAIL = "99101001";//操作员详细
	public static final String FUNCTION_OPERATOR_UPDATE = "99101002";//操作员信息修改
	public static final String FUNCTION_OPERATOR_ROLE_UPDATE = "99101003";//操作员角色修改
	public static final String FUNCTION_OPERATOR_PASSWORD_UPDATE = "99101004";//操作员密码修改
	public static final String FUNCTION_OPERATOR_PASSWORD_RESET = "99101005";//操作员密码重置
	public static final String FUNCTION_OPERATOR_ADD = "99101006";//操作员新增

	public static final String FUNCTION_ROLE_MANAGE = "991011";//角色管理
	public static final String FUNCTION_ROLE_ADD = "99101101";//角色增加
	public static final String FUNCTION_ROLE_UPDATE = "99101102";//角色修改
	public static final String FUNCTION_ROLE_DELETE = "99101103";//角色删除
	public static final String FUNCTION_ROLE_BATCH = "991012";//批量赋权限
	
	public static final String FUNCTION_NO_EXISTS = "该用户不存在！";
	public static final String FUNCTION_DEL_ROLE = "删除该用户角色失败！";
	public static final String FUNCTION_DEL_ROLEDATA = "删除该用户权限数据范围失败！";
	public static final String FUNCTION_INSERT_ROLE = "复制角色失败！";
	public static final String FUNCTION_INSERT_ROLEDATA = "复制权限数据范围失败！";
	public static final String FUNCTION_UPDATE_ROLEDATA = "更新权限数据范围失败！";
	public static final String FUNCTION_DEL_ROLE_NOCRM = "删除该用户与数据范围角色失败！";
	public static final String FUNCTION_DEL_ROLEDATA_NOCRM = "删除该用户权限数据范围失败！";
	
	/*等待进度条信息*/
	public static final String LOADING_DESC = "后台正在获取数据，需要些时间，请耐心等待。";
	public static final String LOADING_FILTER_DESC = "后台正在过滤数据，需要些时间，请耐心等待。";
	public static final String LOADING_IMPORT_DESC = "正在导入数据，需要些时间，请耐心等待。";
	/*数据下载中心cookie标识*/
	public static final String COOKIE_PORTAL_DOWNLOAD_KEY = "portal_java_downlad";
	public static final String COOKIE_PORTAL_UPLOAD_KEY = "portal_java_upload";
	/*营业厅人员计件信息查询cookie标识*/
	public static final String COOKIE_PORTAL_YYTCOUNT_KEY = "portal_java_yytcount";
	
	/*号码归属生产单元查询cookie标识*/
	public static final String COOKIE_NUM_OWNER_PRO_UNIT_KEY = "num_owner_pro_unit";
	
	
	/*外呼项目数据过滤cookie标识*/
	public static final String COOKIE_PORTAL_FILTER_KEY = "portal_java_filter";
	
	/*******org_tree_data_type*********/
	public static final int ORG_TREE_DATA_TYPE_20 = 20;//公告部门树
	public static final int ORG_TREE_DATA_TYPE_21 = 21;//公告范围树

	/**
	 * 公告附加上传路径
	 */
	public static String FILE_NOTICE_FILE_PATH = "uploadFile/portal/notice";
	
	/**
	 * 操作结果编码
	 */
	public static final String STATUS_CODE_SUCCESS = "200";//操作成功
	public static final String STATUS_CODE_FAIL = "300";//操作失败
	public static final String STATUS_CODE_TIMEOUT = "301";//会话超时
	
	/**
	 * 公司信息状态
	 */
	public static final String INFO_STATUS_INIT = "1";//为未申报
	public static final String INFO_STATUS_APPLY = "2";//为已申报
	public static final String INFO_STATUS_CANCEL = "3";//为已撤销
	public static final String INFO_STATUS_OK = "4";//为符合
	public static final String INFO_STATUS_MODIFY = "5";//为待修改
}
