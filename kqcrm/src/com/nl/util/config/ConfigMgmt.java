package com.nl.util.config;


import org.apache.log4j.Logger;

import com.nl.util.GlobalConst;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ��ȡ�����ļ���Ϣ
 *
 * @author zhouk
 * @createDate 2009-03-25
 * @version 1.0
 */
public class ConfigMgmt {
    private final static Logger logger = Logger.getLogger(ConfigMgmt.class);
    private static Properties cfgProp = new Properties();

    /**
     * ����������Ϣ
     *
     * @param pros ������Ϣ
     */
    private void loadConf(Properties pros) {
        try {
            // ���ݿ����ӷ�ʽ
        	logger.info("*********portalzj ��ʼ��ʼ��porperties�����ļ�**********");
            GlobalConst.database_mode = pros.getProperty("database_mode");
            GlobalConst.database_drive = pros.getProperty("database_drive");//
            GlobalConst.database_initialSize = pros.getProperty("database_initialSize");//
            GlobalConst.database_maxActive = pros.getProperty("database_maxActive");//
            GlobalConst.database_password = pros.getProperty("database_password"); //
            GlobalConst.database_username = pros.getProperty("database_username");//
            GlobalConst.database_url = pros.getProperty("database_url"); // ���ݷ���URL
            GlobalConst.URL_BXZJ = pros.getProperty("url_bxzj");
            GlobalConst.URL_BIZJ = pros.getProperty("url_bizj");
            GlobalConst.FILE_PATH =  pros.getProperty("file_path");
            GlobalConst.TARGET_PATH =  pros.getProperty("target_path");
            GlobalConst.URL_OLD_BIZJ = pros.getProperty("url_old_bizj");
            GlobalConst.FTP_SERVER_IP = pros.getProperty("ftp_server_ip");
            GlobalConst.FTP_SERVER_PORT = pros.getProperty("ftp_server_port");
            GlobalConst.FTP_SERVER_USERNAME = pros.getProperty("ftp_server_username");
            GlobalConst.FTP_SERVER_PASSWORD = pros.getProperty("ftp_server_password");
            GlobalConst.EXP_MAX_COUNT = Integer.valueOf(pros.getProperty("exp_max_count"));
            GlobalConst.EXP_CSV_COUNT = Integer.valueOf(pros.getProperty("exp_csv_count"));
            GlobalConst.BUFF_DAY = pros.getProperty("buff_day");

            
            pros.clear();
            // �ر�������
            logger.info("*********portalzj ��ʼ��porperties�����ļ����**********");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * ����������Ϣ
     *
     * @param strFileName �����ļ�·��
     * @return ���
     */
    public boolean loadConf(String strFileName) {
        cfgProp.clear();
        try {
            FileInputStream propsFile;
            propsFile = new FileInputStream(strFileName);
            loadConf(propsFile);
            propsFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ����������Ϣ
     *
     * @param input �ļ���
     * @return ���
     */
    public boolean loadConf(InputStream input) {
        cfgProp.clear();
        try {
            cfgProp.load(input);
            loadConf(cfgProp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        ConfigMgmt mgmt = new ConfigMgmt();
        mgmt.loadConf("E:\\workspace\\eqmMgr\\WebRoot\\WEB-INF\\config\\config.properties");
    }
}