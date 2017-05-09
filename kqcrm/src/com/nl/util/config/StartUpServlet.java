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
      
      //加载properties文件
      initConfig();
      
      //加载字典表数据
      loadDict();
      
      //加载流程数据文件
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
     * 加载系统字典
     */
    private void loadDict() {
        logger.info("*********portalzj 开始初始化字典表**********");
        DictMgmt.getInstance().loadDictInfo();
        logger.info("**********portalzj 初始化字典表成功**********");
    }
    
    /**
     * 读取配置文件,初始化配置信息
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
                e.printStackTrace();  //异常日志打印
            }
        }
    }


}
