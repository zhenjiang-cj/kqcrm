package com.nl.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.nl.base.utils.SystemTool;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 *
 * @author xiaqt
 * @creatdate Aug 3, 2012
 */
public class FTPTools {

	private FtpClient ftpclient;
    private String ipAddress;
    private int ipPort;
    private String userName;
    private String PassWord;
    
    /**
     * ���캯��
     * @param ip String ����IP
     * @param port String ����FTP�˿ں�
     * @param username String FTP�û���
     * @param password String FTP����
     * @throws Exception
     */
    public FTPTools(String ip, int port, String username, String password) 
    {
        ipAddress = ip;
        ipPort = port;
        userName = username;
        PassWord = password;
        try
        {
        	ftpclient = new FtpClient(ipAddress, ipPort);
        }catch(Exception e){
        }
        //ftpclient = new FtpClient(ipAddress);
    }

    /**
     * ���캯��
     * @param ip String ����IP��Ĭ�϶˿�Ϊ21
     * @param username String FTP�û���
     * @param password String FTP����
     * @throws Exception
     */
    public FTPTools(String ip, String username, String password)
    {
        ipAddress = ip;
        ipPort = 21;
        userName = username;
        PassWord = password;
        try {
        	ftpclient = new FtpClient(ipAddress, ipPort);
        }catch(Exception e){
        	e.printStackTrace();
        }
        //ftpclient = new FtpClient(ipAddress);
    }

    /**
     * ��¼FTP������
     * @throws Exception
     */
    public void login() 
    {
    	try	{
    		ftpclient.login(userName, PassWord);
    	}catch(Exception e){
    		SystemTool.getLoggerForWebApp().error("ftp��������¼���� �û�����"+userName + " ���룺"+PassWord +" Cause��"+e.getMessage());
        }
    }
    
    public void changePath(String path)
    {
    	try{
    		ftpclient.cd(path);
    	}	catch(Exception e){
    		e.printStackTrace();
    	}	
  	}  
	
    /**
     * �˳�FTP������
     * @throws Exception
     */
    public void logout()
    {
    	try	{
     		ftpclient.closeServer();
    	}catch(Exception e)	{
    		ftpclient.sendServer("QUIT\r\n");
//    		��ftpclient.closeServer();�Ͽ�FTP����ʱ����������˳�
    	}
//        int reply = ftpclient.readServerResponse(); //ȡ�÷������ķ�����Ϣ
    }
    
    /**
     * ����ļ����Ƿ����
     * @param dir
     * @param ftpClient
     * @return
     */
    private Boolean isDirExist(String dir) { 
	  try { 
		  ftpclient.cd(dir); 
	  } catch (Exception e) { 
	
	   return false; 
	  } 
	  return true; 
	}
     
    public Boolean isDirExt(String dir) { 
  	  try { 
  		  ftpclient.cd(dir); 
  	  } catch (Exception e) { 
  	
  	   return false; 
  	  } 
  	  return true; 
  	}


    /**
     * ��FTP�������Ͻ���ָ����Ŀ¼,��Ŀ¼�Ѿ����ڵ�����²���Ӱ��Ŀ¼�µ��ļ�,���������ж�FTP
     * �ϴ��ļ�ʱ��֤Ŀ¼�Ĵ���Ŀ¼��ʽ������"/"��Ŀ¼��ͷ
     * @param pathList String
     * @throws Exception
     */
    public void buildList(String pathList)
    {//System.out.println("pathList============"+pathList);
    	try{
    	if(!this.isDirExist(pathList.substring(1,pathList.length())))	//�жϸ�Ŀ¼�Ƿ����
    	{
	        ftpclient.ascii();
	        StringTokenizer s = new StringTokenizer(pathList, "/"); //sign
	        String pathName = "";
	        while (s.hasMoreTokens()) 
	        {
	            pathName = pathName + "/" + s.nextToken();
	            //System.out.println("pathName>>>>>>>>>>"+pathName.substring(1,pathName.length()));
	            try {
	            	
//	                ftpclient.sendServer("XMKD " + pathName.substring(1,pathName.length()) + "\r\n");
	            	ftpclient.sendServer("MKD " + pathName.substring(1,pathName.length()) + "\r\n");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
//	            int reply = ftpclient.readServerResponse();
	        }
	        ftpclient.binary();
	    	}
    	}catch(Exception e){
    	}
    }

    /**
     * ȡ��ָ��Ŀ¼�µ������ļ�����������Ŀ¼����
     * ����nameList�õ����������е������õ�ָ��Ŀ¼�µ������ļ���
     * @param fullPath String
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList fileNames(String fullPath) 
    {
        byte[] names = new byte[2048];
        int bufsize = 0;
    	try{
	        ftpclient.ascii(); //ע�⣬ʹ���ַ�ģʽ
	        TelnetInputStream list = ftpclient.nameList(fullPath);
	        bufsize = list.read(names, 0, names.length); //�����ж�ȡ
	        list.close();
    	}catch(Exception e)	{	
    		e.printStackTrace();
    	}
        ArrayList namesList = new ArrayList();
        int i = 0;
        int j = 0;
        
        while (i < bufsize )/*names.length*/ 
        {
            //char bc = (char) names;
            //System.out.println(i + "  " + bc + " : " + (int) names);
            //i = i + 1;
            if (names[i] == 10) 
            { //�ַ�ģʽΪ10��������ģʽΪ13
                //�ļ����������п�ʼ�±�Ϊj,i-jΪ�ļ����ĳ���,�ļ����������еĽ����±�Ϊi-1
                //System.out.write(names, j, i - j);
                //System.out.println(j + "   " + i + "    " + (i - j));
                String tempName = new String(names, j, i - j);
                namesList.add(tempName);
                //System.out.println(temp);
                // ������봦
                //j = i + 2; //��һ��λ�ö�����ģʽ
                j = i + 1; //��һ��λ���ַ�ģʽ
            }
            i = i + 1;
        }
        return namesList;
    }
    /**
     * �ϴ��ļ���FTP������,destination·����FTP��������"/"��ʼ�����ļ�����
     * �ϴ��ļ�ֻ��ʹ�ö�����ģʽ�����ļ�����ʱ�ٴ��ϴ���Ḳ��
     * @param source String
     * @param destination String
     * @throws Exception
     */
    public void upFile(String source, String destination)throws Exception  
    {
 //       buildList(destination.substring(0, destination.lastIndexOf("/")));
        try{
	        ftpclient.binary(); //���д���������buildList֮��
//	        TelnetOutputStream ftpOut = ftpclient.put(destination);
	        TelnetOutputStream ftpOut = ftpclient.put(destination.substring(1,destination.length()));
	        TelnetInputStream ftpIn = new TelnetInputStream(new FileInputStream(source), true);
	        byte[] buf = new byte[204800];
	        int bufsize = 0;
	        while ((bufsize = ftpIn.read(buf, 0, buf.length)) != -1) {
	            ftpOut.write(buf, 0, bufsize);
	        }
	        ftpIn.close();
	        ftpOut.close();
        }catch(Exception e) {
        	e.printStackTrace();
        	throw e;
        }
    }
    

    /**
     * �ϴ��ļ���FTP������,destination·����FTP��������"/"��ʼ�����ļ�����
     * �ϴ��ļ�ֻ��ʹ�ö�����ģʽ�����ļ�����ʱ�ٴ��ϴ���Ḳ��
     * @param source String
     * @param destination String
     * @throws Exception
     */
    public void upFile(InputStream fis, String destination)
    {
        try{
	        ftpclient.binary(); //���д���������buildList֮��
	        TelnetOutputStream ftpOut = ftpclient.put(destination);
	        TelnetInputStream ftpIn = new TelnetInputStream(fis, true);
	        byte[] buf = new byte[204800];
	        int bufsize = 0;
	        while ((bufsize = ftpIn.read(buf, 0, buf.length)) != -1) {
	            ftpOut.write(buf, 0, bufsize);
	        }
	        ftpIn.close();
	        ftpOut.close();
        }catch(Exception e) {
    		SystemTool.getLoggerForWebApp().error("�ļ��ϴ����� �ļ�����"+destination + " Cause��"+e.getMessage());
        }
    }

    /**
     * ��FTP�ļ��������������ļ�SourceFileName��������destinationFileName
     * ���е��ļ����ж�Ҫ�����������·��������
     * @param SourceFileName String
     * @param destinationFileName String
     * @throws Exception
     */
    public void downFile(String SourceFileName, String destinationFileName) 
    {
    	try{
	        ftpclient.binary(); //һ��Ҫʹ�ö�����ģʽ
	        TelnetInputStream ftpIn = ftpclient.get(SourceFileName.substring(1,SourceFileName.length()));
	        byte[] buf = new byte[204800];
	        int bufsize = 0;
	        FileOutputStream ftpOut = new FileOutputStream(destinationFileName);
	        while ((bufsize = ftpIn.read(buf, 0, buf.length)) != -1) 
	            ftpOut.write(buf, 0, bufsize);
	        ftpOut.close();
	        ftpIn.close();
    	}catch(Exception e)	{
    		SystemTool.getLoggerForWebApp().error("�ļ����س��� �������ļ�����"+SourceFileName + " ���ļ���: "+destinationFileName+" Cause��"+e.getMessage());
    	}
    }
    
    public TelnetInputStream get(String SourceFileName)
    {
    	TelnetInputStream ftpIn = null;
    	try {
			ftpIn = ftpclient.get(SourceFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftpIn;
    }
    
    /**
     * 
     * @description ��ȡFTP���ļ���
     * @param SourceFileName FTP���ļ���ȫ·�������ļ���
     * @return
     * @author xiaqt   
     * @date Aug 6, 2012 4:38:51 PM
     */
    public InputStream readFile(String SourceFileName) 
    {
    	InputStream ftpIn = null;
    	try{
	        ftpclient.binary(); //һ��Ҫʹ�ö�����ģʽ
	        ftpIn = ftpclient.get(SourceFileName);
    	}catch(Exception e)	{
    		e.printStackTrace();
    	}
    	return ftpIn;
    }
	
}
