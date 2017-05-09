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
     * 构造函数
     * @param ip String 机器IP
     * @param port String 机器FTP端口号
     * @param username String FTP用户名
     * @param password String FTP密码
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
     * 构造函数
     * @param ip String 机器IP，默认端口为21
     * @param username String FTP用户名
     * @param password String FTP密码
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
     * 登录FTP服务器
     * @throws Exception
     */
    public void login() 
    {
    	try	{
    		ftpclient.login(userName, PassWord);
    	}catch(Exception e){
    		SystemTool.getLoggerForWebApp().error("ftp服务器登录出错 用户名："+userName + " 密码："+PassWord +" Cause："+e.getMessage());
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
     * 退出FTP服务器
     * @throws Exception
     */
    public void logout()
    {
    	try	{
     		ftpclient.closeServer();
    	}catch(Exception e)	{
    		ftpclient.sendServer("QUIT\r\n");
//    		用ftpclient.closeServer();断开FTP出错时用下列语句退出
    	}
//        int reply = ftpclient.readServerResponse(); //取得服务器的返回信息
    }
    
    /**
     * 检查文件夹是否存在
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
     * 在FTP服务器上建立指定的目录,当目录已经存在的情况下不会影响目录下的文件,这样用以判断FTP
     * 上传文件时保证目录的存在目录格式必须以"/"根目录开头
     * @param pathList String
     * @throws Exception
     */
    public void buildList(String pathList)
    {//System.out.println("pathList============"+pathList);
    	try{
    	if(!this.isDirExist(pathList.substring(1,pathList.length())))	//判断该目录是否存在
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
     * 取得指定目录下的所有文件名，不包括目录名称
     * 分析nameList得到的输入流中的数，得到指定目录下的所有文件名
     * @param fullPath String
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList fileNames(String fullPath) 
    {
        byte[] names = new byte[2048];
        int bufsize = 0;
    	try{
	        ftpclient.ascii(); //注意，使用字符模式
	        TelnetInputStream list = ftpclient.nameList(fullPath);
	        bufsize = list.read(names, 0, names.length); //从流中读取
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
            { //字符模式为10，二进制模式为13
                //文件名在数据中开始下标为j,i-j为文件名的长度,文件名在数据中的结束下标为i-1
                //System.out.write(names, j, i - j);
                //System.out.println(j + "   " + i + "    " + (i - j));
                String tempName = new String(names, j, i - j);
                namesList.add(tempName);
                //System.out.println(temp);
                // 处理代码处
                //j = i + 2; //上一次位置二进制模式
                j = i + 1; //上一次位置字符模式
            }
            i = i + 1;
        }
        return namesList;
    }
    /**
     * 上传文件到FTP服务器,destination路径以FTP服务器的"/"开始，带文件名、
     * 上传文件只能使用二进制模式，当文件存在时再次上传则会覆盖
     * @param source String
     * @param destination String
     * @throws Exception
     */
    public void upFile(String source, String destination)throws Exception  
    {
 //       buildList(destination.substring(0, destination.lastIndexOf("/")));
        try{
	        ftpclient.binary(); //此行代码必须放在buildList之后
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
     * 上传文件到FTP服务器,destination路径以FTP服务器的"/"开始，带文件名、
     * 上传文件只能使用二进制模式，当文件存在时再次上传则会覆盖
     * @param source String
     * @param destination String
     * @throws Exception
     */
    public void upFile(InputStream fis, String destination)
    {
        try{
	        ftpclient.binary(); //此行代码必须放在buildList之后
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
    		SystemTool.getLoggerForWebApp().error("文件上传出错 文件名："+destination + " Cause："+e.getMessage());
        }
    }

    /**
     * 从FTP文件服务器上下载文件SourceFileName，到本地destinationFileName
     * 所有的文件名中都要求包括完整的路径名在内
     * @param SourceFileName String
     * @param destinationFileName String
     * @throws Exception
     */
    public void downFile(String SourceFileName, String destinationFileName) 
    {
    	try{
	        ftpclient.binary(); //一定要使用二进制模式
	        TelnetInputStream ftpIn = ftpclient.get(SourceFileName.substring(1,SourceFileName.length()));
	        byte[] buf = new byte[204800];
	        int bufsize = 0;
	        FileOutputStream ftpOut = new FileOutputStream(destinationFileName);
	        while ((bufsize = ftpIn.read(buf, 0, buf.length)) != -1) 
	            ftpOut.write(buf, 0, bufsize);
	        ftpOut.close();
	        ftpIn.close();
    	}catch(Exception e)	{
    		SystemTool.getLoggerForWebApp().error("文件下载出错 服务器文件名："+SourceFileName + " 新文件名: "+destinationFileName+" Cause："+e.getMessage());
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
     * @description 获取FTP上文件流
     * @param SourceFileName FTP上文件的全路径包括文件名
     * @return
     * @author xiaqt   
     * @date Aug 6, 2012 4:38:51 PM
     */
    public InputStream readFile(String SourceFileName) 
    {
    	InputStream ftpIn = null;
    	try{
	        ftpclient.binary(); //一定要使用二进制模式
	        ftpIn = ftpclient.get(SourceFileName);
    	}catch(Exception e)	{
    		e.printStackTrace();
    	}
    	return ftpIn;
    }
	
}
