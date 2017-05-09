/**
 * @Copyright (c) 2012,�´�½����������޹�˾ All rights reserved��
 * @package com.nl.util
 * @�ļ����ƣ�FileTool.java
 * @����˵�����ļ�����
 * @author sanjing   
 * @createdate Aug 16, 2012 1:47:26 PM
 * @version v1.0   
 */
package com.nl.util;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.tools.zip.*;

import com.nl.base.utils.GlobalFunc;


/**
 * @author sanjing
 *
 */
public class FileTool {
	
	/**
	 * ���ļ�
	 * @param filename
	 * @return
	 */
	
	public static String readFromFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                //System.out.println("line " + line + ": " + tempString);
                sbf.append(tempString);
                line++;
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sbf.toString();
    }
	
	/**
	 * �ļ�����
	 * 
	 * @param fileName
	 * @param newFileName
	 * @return
	 * @author sanjing
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public static boolean renameFile(String fileName,String newFileName){
		try{
			File file = new File(fileName);
			file.renameTo(new File(newFileName));
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * get all files in path and sub directory in path if the path is directory, 
	 * or get the file is the path is a file
	 * @param path The path witch files in
	 * @return files in path
	 */
	public static File[] getFiles(File file) {
		List lisFiles = new ArrayList();
		if (file.isDirectory()) {
			List lisRet = new ArrayList();
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					file = files[i];
					if (file.isFile()) {
						lisFiles.add(file);
					} else {
						File[] fTemp = getFiles(file);
						if (fTemp != null) {
							for (int j = 0; j < fTemp.length; j++) {
								lisRet.add(fTemp[j]);
							}
						}
					}
				}
			}
			lisFiles.addAll(lisRet);
		} else {
			lisFiles.add(file);
		}
		File[] temp = new File[lisFiles.size()];
		lisFiles.toArray(temp);
		lisFiles.clear();
		lisFiles = null;
		return temp;
	}
	
	/**
	 * ���ļ����ݶ���LIST��
	 * @param filePath
	 * @return
	 */
	public static List readFileNameToList(String filePath){
		List list = new ArrayList();
		File file = new File(filePath);
		if(!file.isDirectory()){
			//System.out.println("�ļ�"+file.getPath()+";"+file.getAbsolutePath()+";"+file.getName());
		}else if(file.isDirectory()){
			String[] fileList = file.list();
			for(int i=0;i<fileList.length;i++){
				list.add(fileList[i]);
			}
//			return list;
		}
		return list;
	}
	
	/**
	 * д�ļ�
	 * @param filename
	 * @param content
	 * @param append		����д�룬Ϊfalse��׷��д��Ϊtrue
	 * @return
	 */
	private static boolean writeToFile(String filename,String content,boolean append){
		File file = new File(filename);
		//buffд��������Ч����io������BufferedOutputStream
		OutputStream out= null;
			try {
				// ���ļ������
				out = new FileOutputStream(file,append);
				byte[] bytes = content.getBytes();
				//д���ļ�
				out.write(bytes);
				System.out.println("д�ļ�" + file.getAbsolutePath() + "�ɹ���д��ֵΪ��"+content);
				return true;
			} catch (IOException e){
			   System.out.println("д�ļ�" + file.getAbsolutePath() + "ʧ�ܣ�д��ֵΪ��"+content);
			   System.out.println(e.getMessage());
			   return false;
			} finally {
				if (out != null){
					try {
					//�ر�����ļ���
						out.close();
					}catch (IOException e1) {}
				}
			}
	}
	
	/**
	 * д�ļ�
	 * @param filename
	 * @param contentlist
	 * @param append
	 * @return
	 */
	private static boolean writeToFile(String filename,ArrayList contentlist,boolean append){
		File file = new File(filename);
		//buffд��������Ч����io������BufferedOutputStream
		OutputStream out= null;
		try {
			// ���ļ������
			out = new FileOutputStream(file,append);
			for(int i=0;i<contentlist.size();i++){
				byte[] bytes = ((String)contentlist.get(i)).getBytes();
				//д���ļ�
				out.write(bytes);
				//System.out.println("д�ļ�" + file.getAbsolutePath() + "�ɹ���д��ֵΪ��"+(String)contentlist.get(i));
			}			
			return true;
		} catch (IOException e){
		   System.out.println("д�ļ�" + file.getAbsolutePath() + "ʧ�ܣ�");
		   System.out.println(e.getMessage());
		   return false;
		} finally {
			if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
		}	
	}
	/**
	 * 
	 * 
	 * @param filename
	 * @param contentlist
	 * @param append
	 * @param file
	 * @return
	 * @author sanjing
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public static boolean writeFileByCsv(String filename,List contentlist,boolean append,File file){
		//File file = new File(filename);
		file = new File(filename);
		//buffд��������Ч����io������BufferedOutputStream
		OutputStream out= null;
		try {
			// ���ļ������
			out = new FileOutputStream(file,append);
			for(int i=0;i<contentlist.size();i++){
				List celllist = (List)contentlist.get(i);
				for(int j=0;j<celllist.size();j++){
					byte[] bytes = null;
					if(j!=celllist.size()-1)
						bytes = ((String)celllist.get(j)+",").getBytes();
					else
						bytes = ((String)celllist.get(j)+"\r").getBytes();
						
					
					//д���ļ�
					out.write(bytes);
					//System.out.println("д�ļ�" + file.getAbsolutePath() + "�ɹ���д��ֵΪ��"+(String)contentlist.get(i));	
				
				}
				
			}			
			return true;
		} catch (IOException e){
		   //System.out.println("д�ļ�" + file.getAbsolutePath() + "ʧ�ܣ�");
		   //System.out.println(e.getMessage());
			e.printStackTrace();
		   return false;
		} finally {
			if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
		}	
	}
	
	/**
	 * 
	 * 
	 * @param filename
	 * @param contentlist
	 * @param append
	 * @param file
	 * @return
	 * @author sanjing
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 
	public static boolean writeFileByCsv(String filename,List contentlist,boolean append){
		File file = new File(filename);
		//file = new File(filename);
		//buffд��������Ч����io������BufferedOutputStream
		OutputStream out= null;
		try {
			// ���ļ������
			out = new FileOutputStream(file,append);
			for(int i=0;i<contentlist.size();i++){
				List celllist = (List)contentlist.get(i);
				for(int j=0;j<celllist.size();j++){
					byte[] bytes = null;
					if(i!=celllist.size()-1)
						bytes = ((String)celllist.get(i)+",").getBytes();
					else
						bytes = ((String)celllist.get(i)+"\r").getBytes();
						
					
					//д���ļ�
					out.write(bytes);
					//System.out.println("д�ļ�" + file.getAbsolutePath() + "�ɹ���д��ֵΪ��"+(String)contentlist.get(i));	
				
				}
				
			}			
			return true;
		} catch (IOException e){
		   //System.out.println("д�ļ�" + file.getAbsolutePath() + "ʧ�ܣ�");
		   //System.out.println(e.getMessage());
			e.printStackTrace();
		   return false;
		} finally {
			if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
		}	
	}*/
	/**
	 * 
	 * 
	 * @param file
	 * @param csvFileName
	 * @param response
	 * @return
	 * @author sanjing
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public static boolean writeFileByCsv(File file, String csvFileName, HttpServletResponse response){
		//File file=new File(csvName);
		try{
			response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(csvFileName.getBytes("GBk"), "iso8859-1")
                    + "\"");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            FileInputStream in= new java.io.FileInputStream(file);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
                out.flush();
            }
            in.close();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
            if(file.exists()){
                file.delete();
            }
        } 
		return true;
	}
	
	public static void main(String[] args) {
		List contentlist = new ArrayList();
		contentlist.add("sanjing1");
		contentlist.add("sanjing2");
		contentlist.add("sanjing3");
		contentlist.add("sanjing4");
		List contentlist_1 = new ArrayList();
		contentlist_1.add("san1");
		contentlist_1.add("san2");
		contentlist_1.add("san3");
		contentlist_1.add("san4");
		List contentlist_2 = new ArrayList();
		contentlist_2.add("jing1");
		contentlist_2.add("");
		contentlist_2.add("jing3");
		contentlist_2.add("jing4");
		List list = new ArrayList();
		list.add(contentlist);
		list.add(contentlist_1);
		list.add(contentlist_2);
		FileTool ft = new FileTool();
		
		//ft.writeToFile("c:\\catalina.txt", contentlist, true);
		File file = null;
		boolean ba = FileTool.writeFileByCsv("d:\\dd.cvs",list,true,file);
 
		
	}

	 /**
     * 
     * @deprecated:��datalist���ݰ���һ��������","�ָ�ĸ�ʽд���ļ�������ļ�����չ����".csv"��ʽ����������excel��ʽһ��
     * @param filename:�ļ���
     * @param datalist��Ҫд���ļ������ݣ�Ҫ��������list����һ�������У��ڶ������Ƶ�Ԫ��
     * @param is_append���Ǹ���ԭ���ļ�������ԭ���ļ���׷�����ݣ�true����ԭ�����ļ���׷���µ����� false��ԭ�����ļ������ǣ��������������ɵ�
     * @return 
     * @author wangtt
     * @createdate Aug 21, 2012
     * @version v1.0
     */
    public static boolean writeFileByCsv(String filename,List datalist,boolean is_append){
    	File file =new File(filename);//�����ļ���
    	OutputStream out =null;//���������
    	try{
    		out = new FileOutputStream(file,is_append);//�������,���Ҷ�������ļ�ʱ�Ƿ񸲸�
    		for(int i=0;i<datalist.size();i++){//ѭ��������д�뵽�ļ���
    			//datalist:��ʾÿһ�е�����
    			List rowlist = (List)datalist.get(i);//��ȡĳһ��
//    			StringBuffer sb = new StringBuffer();
    			for(int j=0;j<rowlist.size();j++){
    				String cell = String.valueOf(rowlist.get(j));//��ȡĳһ��Ԫ��
    				if(cell == null || cell.equals("null")) cell = "";
    				byte[] bytes = null;//�����ֽ����飬��ŵ�Ԫ������
    				if(j==rowlist.size()-1){
    					bytes = (cell+"\r").getBytes();//һ�е����һ�е�ʱ��Ҫ���У����뻻�з�
    				}else{
    					bytes = (cell+",").getBytes();//�������һ�е�ʱ������","������Ҫ�û���������ļ���ʽ��csv���ļ�������","�ָԪ��ģ���
    				}
    				out.write(bytes);//д�ļ�
//    				sb.append(cell+",");
    			}
//    			System.out.println(i+":"+sb.toString());
    		}
    		return true;
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}finally{
    		if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
    	} 
    }

    public static boolean writeFileByCsvNew(String filename,List datalist,boolean is_append){
    	File file =new File(filename);//�����ļ���
    	OutputStream out =null;//���������
    	try{
    		out = new FileOutputStream(file,is_append);//�������,���Ҷ�������ļ�ʱ�Ƿ񸲸�
    		for(int i=0;i<datalist.size();i++){//ѭ��������д�뵽�ļ���
    			//datalist:��ʾÿһ�е�����
    			List rowlist = (List)datalist.get(i);//��ȡĳһ��
//    			StringBuffer sb = new StringBuffer();
    			for(int j=0;j<rowlist.size();j++){
    				String cell = String.valueOf(rowlist.get(j));//��ȡĳһ��Ԫ��
    				if(cell == null || cell.equals("null")) cell = "";
    				byte[] bytes = null;//�����ֽ����飬��ŵ�Ԫ������
    				if(j==rowlist.size()-1){
    					bytes = (cell+"\t"+"\r").getBytes();//һ�е����һ�е�ʱ��Ҫ���У����뻻�з�
    				}else{
    					bytes = (cell+"\t"+",").getBytes();//�������һ�е�ʱ������","������Ҫ�û���������ļ���ʽ��csv���ļ�������","�ָԪ��ģ���
    				}
    				out.write(bytes);//д�ļ�
//    				sb.append(cell+",");
    			}
//    			System.out.println(i+":"+sb.toString());
    		}
    		return true;
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}finally{
    		if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
    	} 
    }
    
    /**
     * 
     * 
     * @param filename:�ļ����������������ļ���
     * @param response����Ӧ�����ļ���������������ɵ��������
     * @return
     * @author wangtt
     * @createdate Aug 21, 2012
     * @version v1.0
     */
    public static boolean expFileByCvs(String filename,HttpServletResponse response,String key){
    	File file =new File(filename);//�����ļ���
    	//���ļ�ѹ��
    	String zipname=file.getName().substring(0, file.getName().indexOf("."))+".zip";//ѹ��������
    	String [] filenames = {file.getName()} ;//Ҫѹ�����ļ���
    	createZip(zipname,filenames);//����ѹ���ļ�
    	File zipfile = new File(zipname);
    	OutputStream out=null;
    	try{
    		response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(zipname.getBytes("GBk"), "iso8859-1") + "\"");
            
            setCookie(response,key);
            
            out = new BufferedOutputStream(response.getOutputStream()); //����Ӧ�����ȡ�����
            FileInputStream in = new java.io.FileInputStream(zipfile);//��ѹ���ļ�������������
            byte[] b = new byte[1024];//����ÿ�ζ�ȡ�ļ��Ĵ�С
            int i = 0;
            while ((i = in.read(b)) > 0) {//ѭ����ȡ�ļ���ֱ����ȡ��������Ϊֹ
                out.write(b, 0, i);//����ļ�
                out.flush();
            }
            in.close();
            out.close();
    	}catch(Exception e){
			e.printStackTrace();
			return false;
    	}finally{
    		if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
    		//��ϵͳ�Լ����ɵ��ļ�ɾ��������������ļ����䵽�û��ͻ���֮��
            if(file.exists()){
                file.delete();
            }
            if(zipfile.exists()){
            	zipfile.delete();
            }
        } 
    	return true;
    }
    
    /**
     * 
     * @description ��������
     * @param filename
     * @param response
     * @return
     * @author xiaqt   
     * @date Oct 12, 2012 5:00:12 PM
     */
    public static boolean expFileByCvs(String filename,HttpServletResponse response){
    	File file =new File(filename);//�����ļ���
    	//���ļ�ѹ��
    	String zipname=file.getName().substring(0, file.getName().indexOf("."))+".zip";//ѹ��������
    	String [] filenames = {file.getName()} ;//Ҫѹ�����ļ���
    	createZip(zipname,filenames);//����ѹ���ļ�
    	File zipfile = new File(zipname);
    	OutputStream out=null;
    	try{
    		response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(zipname.getBytes("GBk"), "iso8859-1") + "\"");
            
            out = new BufferedOutputStream(response.getOutputStream()); //����Ӧ�����ȡ�����
            FileInputStream in = new java.io.FileInputStream(zipfile);//��ѹ���ļ�������������
            byte[] b = new byte[1024];//����ÿ�ζ�ȡ�ļ��Ĵ�С
            int i = 0;
            while ((i = in.read(b)) > 0) {//ѭ����ȡ�ļ���ֱ����ȡ��������Ϊֹ
                out.write(b, 0, i);//����ļ�
                out.flush();
            }
            in.close();
            out.close();
    	}catch(Exception e){
			e.printStackTrace();
			return false;
    	}finally{
    		if (out != null){
				try {
				//�ر�����ļ���
					out.close();
				} catch (IOException e1) {}
			}
    		//��ϵͳ�Լ����ɵ��ļ�ɾ��������������ļ����䵽�û��ͻ���֮��
            if(file.exists()){
                file.delete();
            }
            if(zipfile.exists()){
            	zipfile.delete();
            }
        } 
    	return true;
    }
    /**
	 * ѹ���ļ���֧������·���������ļ��� ֧��rar��ʽ�Լ�zip��ʽ
	 * 
	 * @param compressFileName(ָ��ѹ������ļ���·��):path+"/tempfile/test.zip" 
	 * @param args(��Ҫѹ�����ļ�):String args[]={path+"/tempfile/download_123.txt",path+"/tempfile/download_456.txt",path+"/tempfile/download_789.txt"}  
	 * @return
	 * @author sanjing
	 * @createdate Aug 17, 2012
	 * @version v1.0
	 */
    public  static boolean createZip(String compressFileName,String args[]){    
        boolean flag = false;    
        InputStream in =null;//����������
        ZipOutputStream zout=null;
        try{            
            byte b[] = new byte[1024];       
            zout  = new ZipOutputStream(new FileOutputStream(compressFileName));//����ѹ�������
            for (int i = 0; i < args.length; i++) {
				in = new FileInputStream(args[i]); // ��������

				File file = new File(args[i]);
				String filename = file.getName();// ȡ���ļ���
				// ZipEntry e = new
				// ZipEntry(args[i].replace(File.separatorChar,'/')); //ѹ�����·��
				ZipEntry e = new ZipEntry(filename); // ѹ������ѹ���󲻴�·����
				zout.putNextEntry(e); // ��������з���ѹ������
				int len = 0;
				while ((len = in.read(b)) != -1) {
					zout.write(b, 0, len);
				}
				zout.closeEntry();
				in.close();
			}   
      	  	 zout.close();    
             flag = true;    
        }catch(Exception e){    
            e.printStackTrace();    
        }finally{
        	if (zout != null){
				try {
				// �ر�����ļ���
					zout.close();
				} catch (IOException e1) {}
			}
        	if (in != null){
				try {
    				//�ر�����ļ���
					in.close();
    				} catch (IOException e1) {}
    		}
        	 for(int i = 0; i < args.length; i++){  
        		 File file=new File(args[i]);  
        		 if(file.exists()){
                      file.delete();
                 }
        	 }
        }     
        return flag;    
    }    
    

	/**
	 * ���򵼳��������ݵ�excel
	 * 
	 * @param SheetName
	 *            excel�Ĺ���������
	 * @param titles
	 *            ����
	 * @param data
	 *            ����
	 * @param response
	 *            �����
	 * @return
	 */
	public static boolean AllDataToExcel(String xlsName,String SheetName, String[] titles, List data, HttpServletResponse response) {
	 	WritableWorkbook workbook = null;
         File file=new File(xlsName);

		try {

			workbook = Workbook.createWorkbook(file); // //���������

			// wfImportant �����ֶ�Font���� wfNormal �Ǳ����ֶ�
			jxl.write.WritableFont wfBold = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfBlack = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfNormal = new jxl.write.WritableFont(WritableFont.COURIER, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			// wfcfImportant �����ֶ�Cell���� wfcfNormal �Ǳ����ֶ�Cell����
			jxl.write.WritableCellFormat wcfBold = new jxl.write.WritableCellFormat(wfBold);
			jxl.write.WritableCellFormat wcfBlack = new jxl.write.WritableCellFormat(wfBlack);
			jxl.write.WritableCellFormat wcfNormal = new jxl.write.WritableCellFormat(wfNormal);
			// ����Cell��������Ϊ 25% Gray
			wcfBold.setBackground(jxl.format.Colour.GRAY_25);
			wcfBlack.setBackground(jxl.format.Colour.GRAY_25);
			// ���ñ߿�
			wcfBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfBlack.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfNormal.setBorder(Border.ALL, BorderLineStyle.THIN);
			// ���ö���
			wcfBold.setAlignment(Alignment.CENTRE);
			wcfBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfBlack.setAlignment(Alignment.CENTRE);
			wcfBlack.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNormal.setAlignment(Alignment.CENTRE);
			wcfNormal.setVerticalAlignment(VerticalAlignment.CENTRE);
			// �����Զ�����
//			wcfBold.setWrap(true);
//			wcfBlack.setWrap(true);
//			wcfNormal.setWrap(true);

			// ������sheet
			int sheetNum = (int) Math.ceil((double) data.size() / GlobalConst.SHEET_COUNT);
            if(sheetNum==0){sheetNum=1;};
            String sheetNameNum = "";
			for (int j = 0; j < sheetNum; j++) {
				if (j > 0 )
					sheetNameNum = String.valueOf(j);
				WritableSheet sheet = workbook.createSheet(SheetName + sheetNameNum, j); // ��sheet������
				
				// =com.newland.util.StringText.str2GB2312ToISO(sheet);
				// ����
				for (int i = 0; i < titles.length; i++) {
					jxl.write.Label label;

					label = new jxl.write.Label(i, 0, GlobalFunc.getWatermark(titles[i]), wcfBold);

					sheet.addCell(label);
					sheet.setColumnView(i, 20);
				}
				int intIndex = 0;
				if (intIndex < GlobalConst.SHEET_COUNT && data.size() > intIndex + j * GlobalConst.SHEET_COUNT) {
					int intCount = (data.size() - intIndex - j * GlobalConst.SHEET_COUNT) > GlobalConst.SHEET_COUNT ? GlobalConst.SHEET_COUNT : (data.size() - intIndex - j * GlobalConst.SHEET_COUNT);
					// ����
					for (int i = 0; i < intCount; i++) {
						List lst = (List) data.get(j * GlobalConst.SHEET_COUNT + i);
						String str = null;
						for (int t = 0; t < lst.size(); t++) {
							str = String.valueOf(lst.get(t));
							if (str == null || str.equals("null")) str = "";
							jxl.write.Label label = new jxl.write.Label(t, i + 1, str,wcfNormal);
							sheet.addCell(label);
						}
						intIndex++;
					}
				}
			}
            workbook.write();
			workbook.close();

            response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(xlsName.getBytes("gb2312"), "iso8859-1")
                    + "\"");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            FileInputStream in= new java.io.FileInputStream(file);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
                out.flush();
            }
            in.close();
            out.close();
		} catch (Exception ee) {
			ee.printStackTrace();
			// log.error("����excel����:" + ee.getMessage());
			return false;
		} finally{
            if(file.exists()){
                file.delete();
            }
        }
        return true;

	}
	
	public static boolean AllDataToExcel(String xlsName,String SheetName, String[] titles, List data, HttpServletResponse response,String key) {
	 	WritableWorkbook workbook = null;
         File file=new File(xlsName);

		try {			

			workbook = Workbook.createWorkbook(file); // //���������

			// wfImportant �����ֶ�Font���� wfNormal �Ǳ����ֶ�
			jxl.write.WritableFont wfBold = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfBlack = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfNormal = new jxl.write.WritableFont(WritableFont.COURIER, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			// wfcfImportant �����ֶ�Cell���� wfcfNormal �Ǳ����ֶ�Cell����
			jxl.write.WritableCellFormat wcfBold = new jxl.write.WritableCellFormat(wfBold);
			jxl.write.WritableCellFormat wcfBlack = new jxl.write.WritableCellFormat(wfBlack);
			jxl.write.WritableCellFormat wcfNormal = new jxl.write.WritableCellFormat(wfNormal);
			// ����Cell��������Ϊ 25% Gray
			wcfBold.setBackground(jxl.format.Colour.GRAY_25);
			wcfBlack.setBackground(jxl.format.Colour.GRAY_25);
			// ���ñ߿�
			wcfBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfBlack.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfNormal.setBorder(Border.ALL, BorderLineStyle.THIN);
			// ���ö���
			wcfBold.setAlignment(Alignment.CENTRE);
			wcfBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfBlack.setAlignment(Alignment.CENTRE);
			wcfBlack.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNormal.setAlignment(Alignment.CENTRE);
			wcfNormal.setVerticalAlignment(VerticalAlignment.CENTRE);
			// �����Զ�����
//			wcfBold.setWrap(true);
//			wcfBlack.setWrap(true);
//			wcfNormal.setWrap(true);

			// ������sheet
			int sheetNum = (int) Math.ceil((double) data.size() / GlobalConst.SHEET_COUNT);
            if(sheetNum==0){sheetNum=1;};
            String sheetNameNum = "";
			for (int j = 0; j < sheetNum; j++) {
				if (j > 0 )
					sheetNameNum = String.valueOf(j);
				WritableSheet sheet = workbook.createSheet(SheetName + sheetNameNum, j); // ��sheet������
				
				// =com.newland.util.StringText.str2GB2312ToISO(sheet);
				// ����
				for (int i = 0; i < titles.length; i++) {
					jxl.write.Label label;

					label = new jxl.write.Label(i, 0, GlobalFunc.getWatermark(titles[i]), wcfBold);

					sheet.addCell(label);
					sheet.setColumnView(i, 20);
				}
				int intIndex = 0;
				if (intIndex < GlobalConst.SHEET_COUNT && data.size() > intIndex + j * GlobalConst.SHEET_COUNT) {
					int intCount = (data.size() - intIndex - j * GlobalConst.SHEET_COUNT) > GlobalConst.SHEET_COUNT ? GlobalConst.SHEET_COUNT : (data.size() - intIndex - j * GlobalConst.SHEET_COUNT);
					// ����
					for (int i = 0; i < intCount; i++) {
						List lst = (List) data.get(j * GlobalConst.SHEET_COUNT + i);
						String str = null;
						for (int t = 0; t < lst.size(); t++) {
							str = String.valueOf(lst.get(t));
							if (str == null || str.equals("null")) str = "";
							jxl.write.Label label = new jxl.write.Label(t, i + 1, str,wcfNormal);
							sheet.addCell(label);
						}
						intIndex++;
					}
				}
			}
            workbook.write();
			workbook.close();

            response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(xlsName.getBytes("gb2312"), "iso8859-1")
                    + "\"");
            
            setCookie(response,key);
            
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            FileInputStream in= new java.io.FileInputStream(file);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
                out.flush();
            }
            in.close();
            out.close();
		} catch (Exception ee) {
			ee.printStackTrace();
			// log.error("����excel����:" + ee.getMessage());
			return false;
		} finally{
            if(file.exists()){
                file.delete();
            }
        }
        return true;

	}
	
	public static void setCookie(HttpServletResponse response,String key)
	{
		Cookie cook = new Cookie(key,"true"); 
        cook.setMaxAge(1800);
        cook.setPath("/");
        response.addCookie(cook);
	}
}
