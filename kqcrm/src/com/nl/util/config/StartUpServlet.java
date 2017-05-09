package com.nl.util.config;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class StartUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
	public void init() throws ServletException 
	{

      super.init();
      
      //����properties�ļ�
      initConfig();
      
      //�����ֵ������
      loadDict();
      
      //�������������ļ�
//      loadFlow();

    }

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
    	String uri = request.getRequestURI();
    	if(uri.endsWith("/dict.init")){
			loadDict();
		}else if(uri.endsWith("/config.init")){
			initConfig();
		}
    }
    /**
     * ����ϵͳ�ֵ�
     */
    private void loadDict() {
        logger.info("*********portalzj ��ʼ��ʼ���ֵ��**********");
        DictMgmt.getInstance().loadDictInfo();
        logger.info("**********portalzj ��ʼ���ֵ��ɹ�**********");
    }
    
    /**
     * ��ȡ�����ļ�,��ʼ��������Ϣ
     */
    private void initConfig() 
    {
        InputStream is = null;
        try 
        {
            ServletContext context = getServletContext();
            String cfgFile = context.getInitParameter("config");
            is = context.getResourceAsStream(cfgFile);
            ConfigMgmt load = new ConfigMgmt();
            load.loadConf(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();  //�쳣��־��ӡ
            }
        }
    }


}
