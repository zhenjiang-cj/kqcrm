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
	 * ����excelʱ����ÿ��sheet������60000����¼
	 */
	public static final int SHEET_COUNT =  60000;		
	/**
     * ������ϸ��һ�β�ѯ���ݿ���������
     */
    public static int EXP_MAX_COUNT=0;
    /**
     * ���������г���һ���������Ͳ���csv��ʽ����
     */
    public static int EXP_CSV_COUNT=0;
	/** WEB���������־�ؼ��� */
	public static final String LOG_WEB_APP = "WEB-APP";
	
	/** ϵͳ��־�ؼ��� */
	public static final String LOG_SERVER_APP = "SERVER_APP";
	
	/** ��־���� */
	public static final String ENTER = "Enter";
	
	public static final String EXECUTE = "Execute";
	
	public static final String EXIT = "Exit";
	
	public static final String ERROR = "Error";
	
	/**
	 * ����������¼����
	 */
	public static final int LOCK_NUM = 10;
	
	/*���ݿ�����*/
	public static String database_mode = "jdbc";//����jdbcֱ���ķ���
    public static String database_drive = "oracle.jdbc.driver.OracleDriver";
    public static final int dbpool_id = 1; // 1 
    /**
     * ����Դ���ӵ�ַ
     */
    public static String database_url = "jdbc:oracle:thin:zjtest/zjtest@10.34.8.92:1521:osctest";
    /**
     * ����Դ�����û���
     */
    public static String database_username = "";
    /**
     * ����Դ��������
     */
    public static String database_password = "";
    /**
     * ����Դ��ʼ����С������
     */
    public static String database_initialSize = "10";
    /**
     * ����Դ������������
     */
    public static String database_maxActive = "20";

    public static String JNDI_NAME = "dbpool_kqcrm";
    
    /**
     * ����Դ����
     */
    public static String database_jndi = "dbpool_kqcrm";

    /**
     * ���ݿ�t3Э�� url
     */
    public static String t3_url = "t3://localhost:8007";
    /**
     * ���ݿ�t3Э�� user;
     */
    public static String t3_user = "system";
    /**
     * ���ݿ�t3Э�� password;
     */
    public static String t3_password = "password";
    /**
     * 1: dbpool 2:t3pool 3:jdbc
     */
    public static int database_type = 1;// 1: dbpool 2:t3pool 3:jdbc
	/*���ݿ�����*/
	
	
	public static final String JOBS_JNDI = "dbpool_kqcrm";   //���ݿ�JNDI���ֵĶ��� 
	
	public static final String APP_JNDI = "dbpool_kqcrm";
	public static final String CDR_JNDI = "dbpool_kqcrm";//���Ŀ�JNDI
	
	//����ϵͳURL��ַ
	public static String URL_BIZJ = "";
	//һ��֧��URL��ַ
	public static String URL_BXZJ = "";
	//
	public static String FILE_PATH = "";
	public static String TARGET_PATH = "";
	public static final String UPLOAD_TMP_FILE_PATH = "/uploadFile/tmp";
	public static final String DOWNLOAD_FILE_PATH = "/uploadFile/template/";
	public static final String DOWNLOAD_BAKFILE_PATH = "/uploadFile/template/bak/";//����ģ��
	//ԭҵ��֧��ƽ̨
	public static String URL_OLD_BIZJ = "";
	//FTP��������Ϣ
	public static String FTP_SERVER_IP = "";
	public static String FTP_SERVER_PORT = "";
	public static String FTP_SERVER_USERNAME = "";
	public static String FTP_SERVER_PASSWORD = "";
	
	//֤����Ч��ֹ��������
	public static String BUFF_DAY="";
	
	/*ϵͳ��¼������ʾ*/
	public static final String LOGIN_USER_NOT_NULL = "�û��������벻��Ϊ�գ����������룡";
	public static final String LOGIN_USER_NOT_FOUND = "�û��������ڻ�ʧЧ����˶Ժ��������룡";
	public static final String LOGIN_PASSWORD_FAIL = "�û����벻��ȷ�����������룡";
	public static final String LOGIN_PASSWORD_SUCCESS = "�û���½�ɹ���";
	public static final String LOGIN_ERROR = "��¼�쳣������ϵ����Ա��";
	public static final String LOGIN_ILLEGAL = "�Ƿ���¼������ϵ����Ա��";
	
	/*ȫ�ֵ�ϵͳ��ţ����ֵ���е���ͬ*/
	public static final int[] Global_SYSTEM_ID = {99,1,2,3,4};
	
	/*form������request�е�����*/
	public static final String GLOBAL_CURRENT_FORM = "form";
	
	public static final String GLOBAL_SELECT_NAME = "--��ѡ��--";
	public static final String GLOBAL_SELECT_ALL = "--ȫ��--";
	public static final String GLOBLA_SELECT_VALUE = "-99";
	
	/*��ͬϵͳ��system_id*/
	public static final String SYSTEM_ID_SYSTEM = "99";//ϵͳ����
	public static final String SYSTEM_ID_CRM = "1";//�ͻ�����
	public static final String SYSTEM_ID_HT = "2";//��ͬ����
	public static final String SYSTEM_ID_HF = "3";//�ͻ��ط�
	
	/*�˵�*/
	public static final String FUNCTION_SYS_MANAGE="99";//ϵͳ����
	public static final String FUNCTION_SYS_USER="9910";//�û�����
	public static final String FUNCTION_SYS_USER_ADD="9911";
	public static final String FUNCTION_SYS_USER_EDIT="9912";
	public static final String FUNCTION_SYS_USER_DEL="9913";
	
	public static final String FUNCTION_SYS_ROLE="9920";//��ɫ����
	public static final String FUNCTION_SYS_ROLE_ADD="9921";
	public static final String FUNCTION_SYS_ROLE_EDIT="9922";
	public static final String FUNCTION_SYS_ROLE_DEL="9923";
	
	
	public static final String FUNCTION_CRM_MANAGE="110";//�ͻ�����
	public static final String FUNCTION_CRM_USER_ADD = "111";//�ͻ�����
	public static final String FUNCTION_CRM_USER_EDIT = "112";//�ͻ�����
	public static final String FUNCTION_CRM_USER_DEL = "113";//�ͻ�����
	public static final String FUNCTION_CRM_USER_EXP = "114";//�ͻ�����
	
	public static final String FUNCTION_CRM_YXUSER_ADD = "121";//����ͻ�����
	public static final String FUNCTION_CRM_YXUSER_EDIT = "122";//����ͻ��޸�
	public static final String FUNCTION_CRM_YXUSER_DEL = "123";//����ͻ�ɾ��
	public static final String FUNCTION_CRM_YXUSER_EXP = "124";//����ͻ�����
	
	public static final String FUNCTION_HT_MANAGE="210";//��ͬ����
	public static final String FUNCTION_HT_USER_ADD = "211";//�ͻ�����
	public static final String FUNCTION_HT_USER_EDIT = "212";//�ͻ�����
	public static final String FUNCTION_HT_USER_DEL = "213";//�ͻ�����
	public static final String FUNCTION_HT_USER_EXP = "214";//�ͻ�����
	
	public static final String FUNCTION_HF_MANAGE="310";//�ͻ��ط�
	public static final String FUNCTION_HF_USER_ADD = "311";//�ط�����
	public static final String FUNCTION_HF_USER_EDIT = "312";//�ط�����
	public static final String FUNCTION_HF_USER_DEL = "313";//�ط�����
	public static final String FUNCTION_HF_USER_EXP = "314";//�ط�����
	public static final String FUNCTION_REPAIR_ADD = "315";//ά�޼�¼¼��
	public static final String FUNCTION_REPAIR_QUERY = "316";//ά�޼�¼��ѯ
	
	public static final String FUNCTION_TJ_MANAGE="410";//ͳ�Ʊ���
	public static final String FUNCTION_TJ_DEVICE="411";//�豸ͳ��
	
	
	/*��������*/
	public static final String OPERATION_INSERT = "1";//����
	public static final String OPERATION_UPDATE = "2";//�޸�
	public static final String OPERATION_DELETE = "3";//ɾ��
	public static final String OPERATION_QUERY = "4";//��ѯ
	public static final String OPERATION_DETAIL = "5";//��ϸ
	public static final String OPERATION_EXPORT = "6";//����
	public static final String OPERATION_SEND = "7";//�����ύ
	public static final String OPERATION_DEAL = "8";//��������
	public static final String OPERATION_BACK = "9";//�����˻�
	public static final String OPERATION_FINISH = "10";//�������
	public static final String OPERATION_READ = "11";//������֪
	public static final String OPERATION_IMPORT = "12";//��������
	public static final String OPERATION_DOWNLOAD = "13";//��������
	public static final String OPERATION_LOGIN = "0";//��½
	public static final String OPERATION_MENU_CLICK = "99";//�˵����
	
	public static final Map<String, String> OPERATION_TEXT = new HashMap<String, String>();
	static{
		OPERATION_TEXT.put(OPERATION_INSERT, "����");
		OPERATION_TEXT.put(OPERATION_UPDATE, "�޸�");
		OPERATION_TEXT.put(OPERATION_DELETE, "ɾ��");
		OPERATION_TEXT.put(OPERATION_QUERY, "��ѯ");
		OPERATION_TEXT.put(OPERATION_DETAIL, "��ϸ");
		OPERATION_TEXT.put(OPERATION_SEND, "�ɷ�");
		OPERATION_TEXT.put(OPERATION_DEAL, "����");
		OPERATION_TEXT.put(OPERATION_BACK, "�˻�");
		OPERATION_TEXT.put(OPERATION_MENU_CLICK, "���");
	}
	
	/*ϵͳ�ɹ�ʧ�ܷ��ؽ������*/
	public static final int GLOBAL_RESULT_SUCCESS = 0;//�ɹ�
	public static final int GLOBAL_RESULT_FAIL = -1;//ʧ��
	
	/*�������񻷽�״̬*/
	public static final int FLOW_TASK_STATUS_NORMAL = 1;//����
	public static final int FLOW_TASK_STATUS_OVER = 9 ;//����
	
	/*��ϵͳ���*/
	public static final int FLOW_SYS_DOCUMENT  =101;//����ϵͳ
	public static final int FLOW_SYS_TMPMEET  =102;//��ʱ����
	public static final int FLOW_SYS_MONTHWORK  =103;//�¶ȹ���
	
	/*ϵͳ�����Ȩ�ޱ��*/
	public static final String FUNCTION_OPERATOR_MANAGE = "991010";//����Ա����
	public static final String FUNCTION_OPERATOR_DETAIL = "99101001";//����Ա��ϸ
	public static final String FUNCTION_OPERATOR_UPDATE = "99101002";//����Ա��Ϣ�޸�
	public static final String FUNCTION_OPERATOR_ROLE_UPDATE = "99101003";//����Ա��ɫ�޸�
	public static final String FUNCTION_OPERATOR_PASSWORD_UPDATE = "99101004";//����Ա�����޸�
	public static final String FUNCTION_OPERATOR_PASSWORD_RESET = "99101005";//����Ա��������
	public static final String FUNCTION_OPERATOR_ADD = "99101006";//����Ա����

	public static final String FUNCTION_ROLE_MANAGE = "991011";//��ɫ����
	public static final String FUNCTION_ROLE_ADD = "99101101";//��ɫ����
	public static final String FUNCTION_ROLE_UPDATE = "99101102";//��ɫ�޸�
	public static final String FUNCTION_ROLE_DELETE = "99101103";//��ɫɾ��
	public static final String FUNCTION_ROLE_BATCH = "991012";//������Ȩ��
	
	public static final String FUNCTION_NO_EXISTS = "���û������ڣ�";
	public static final String FUNCTION_DEL_ROLE = "ɾ�����û���ɫʧ�ܣ�";
	public static final String FUNCTION_DEL_ROLEDATA = "ɾ�����û�Ȩ�����ݷ�Χʧ�ܣ�";
	public static final String FUNCTION_INSERT_ROLE = "���ƽ�ɫʧ�ܣ�";
	public static final String FUNCTION_INSERT_ROLEDATA = "����Ȩ�����ݷ�Χʧ�ܣ�";
	public static final String FUNCTION_UPDATE_ROLEDATA = "����Ȩ�����ݷ�Χʧ�ܣ�";
	public static final String FUNCTION_DEL_ROLE_NOCRM = "ɾ�����û������ݷ�Χ��ɫʧ�ܣ�";
	public static final String FUNCTION_DEL_ROLEDATA_NOCRM = "ɾ�����û�Ȩ�����ݷ�Χʧ�ܣ�";
	
	/*�ȴ���������Ϣ*/
	public static final String LOADING_DESC = "��̨���ڻ�ȡ���ݣ���ҪЩʱ�䣬�����ĵȴ���";
	public static final String LOADING_FILTER_DESC = "��̨���ڹ������ݣ���ҪЩʱ�䣬�����ĵȴ���";
	public static final String LOADING_IMPORT_DESC = "���ڵ������ݣ���ҪЩʱ�䣬�����ĵȴ���";
	/*������������cookie��ʶ*/
	public static final String COOKIE_PORTAL_DOWNLOAD_KEY = "portal_java_downlad";
	public static final String COOKIE_PORTAL_UPLOAD_KEY = "portal_java_upload";
	/*Ӫҵ����Ա�Ƽ���Ϣ��ѯcookie��ʶ*/
	public static final String COOKIE_PORTAL_YYTCOUNT_KEY = "portal_java_yytcount";
	
	/*�������������Ԫ��ѯcookie��ʶ*/
	public static final String COOKIE_NUM_OWNER_PRO_UNIT_KEY = "num_owner_pro_unit";
	
	
	/*�����Ŀ���ݹ���cookie��ʶ*/
	public static final String COOKIE_PORTAL_FILTER_KEY = "portal_java_filter";
	
	/*******org_tree_data_type*********/
	public static final int ORG_TREE_DATA_TYPE_20 = 20;//���沿����
	public static final int ORG_TREE_DATA_TYPE_21 = 21;//���淶Χ��

	/**
	 * ���渽���ϴ�·��
	 */
	public static String FILE_NOTICE_FILE_PATH = "uploadFile/portal/notice";
	
	/**
	 * �����������
	 */
	public static final String STATUS_CODE_SUCCESS = "200";//�����ɹ�
	public static final String STATUS_CODE_FAIL = "300";//����ʧ��
	public static final String STATUS_CODE_TIMEOUT = "301";//�Ự��ʱ
	
	/**
	 * ��˾��Ϣ״̬
	 */
	public static final String INFO_STATUS_INIT = "1";//Ϊδ�걨
	public static final String INFO_STATUS_APPLY = "2";//Ϊ���걨
	public static final String INFO_STATUS_CANCEL = "3";//Ϊ�ѳ���
	public static final String INFO_STATUS_OK = "4";//Ϊ����
	public static final String INFO_STATUS_MODIFY = "5";//Ϊ���޸�
}
