/**
 * @Copyright (c) 2012,新大陆软件工程有限公司 All rights reserved。
 * @package com.nl.util
 * @文件名称：FileTool.java
 * @功能说明：文件描述
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
	 * 读文件
	 * @param filename
	 * @return
	 */
	
	public static String readFromFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
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
	 * 文件改名
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
	 * 把文件内容读到LIST中
	 * @param filePath
	 * @return
	 */
	public static List readFileNameToList(String filePath){
		List list = new ArrayList();
		File file = new File(filePath);
		if(!file.isDirectory()){
			//System.out.println("文件"+file.getPath()+";"+file.getAbsolutePath()+";"+file.getName());
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
	 * 写文件
	 * @param filename
	 * @param content
	 * @param append		覆盖写入，为false，追加写入为true
	 * @return
	 */
	private static boolean writeToFile(String filename,String content,boolean append){
		File file = new File(filename);
		//buff写，可以有效减少io次数，BufferedOutputStream
		OutputStream out= null;
			try {
				// 打开文件输出流
				out = new FileOutputStream(file,append);
				byte[] bytes = content.getBytes();
				//写入文件
				out.write(bytes);
				System.out.println("写文件" + file.getAbsolutePath() + "成功！写入值为："+content);
				return true;
			} catch (IOException e){
			   System.out.println("写文件" + file.getAbsolutePath() + "失败！写入值为："+content);
			   System.out.println(e.getMessage());
			   return false;
			} finally {
				if (out != null){
					try {
					//关闭输出文件流
						out.close();
					}catch (IOException e1) {}
				}
			}
	}
	
	/**
	 * 写文件
	 * @param filename
	 * @param contentlist
	 * @param append
	 * @return
	 */
	private static boolean writeToFile(String filename,ArrayList contentlist,boolean append){
		File file = new File(filename);
		//buff写，可以有效减少io次数，BufferedOutputStream
		OutputStream out= null;
		try {
			// 打开文件输出流
			out = new FileOutputStream(file,append);
			for(int i=0;i<contentlist.size();i++){
				byte[] bytes = ((String)contentlist.get(i)).getBytes();
				//写入文件
				out.write(bytes);
				//System.out.println("写文件" + file.getAbsolutePath() + "成功！写入值为："+(String)contentlist.get(i));
			}			
			return true;
		} catch (IOException e){
		   System.out.println("写文件" + file.getAbsolutePath() + "失败！");
		   System.out.println(e.getMessage());
		   return false;
		} finally {
			if (out != null){
				try {
				//关闭输出文件流
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
		//buff写，可以有效减少io次数，BufferedOutputStream
		OutputStream out= null;
		try {
			// 打开文件输出流
			out = new FileOutputStream(file,append);
			for(int i=0;i<contentlist.size();i++){
				List celllist = (List)contentlist.get(i);
				for(int j=0;j<celllist.size();j++){
					byte[] bytes = null;
					if(j!=celllist.size()-1)
						bytes = ((String)celllist.get(j)+",").getBytes();
					else
						bytes = ((String)celllist.get(j)+"\r").getBytes();
						
					
					//写入文件
					out.write(bytes);
					//System.out.println("写文件" + file.getAbsolutePath() + "成功！写入值为："+(String)contentlist.get(i));	
				
				}
				
			}			
			return true;
		} catch (IOException e){
		   //System.out.println("写文件" + file.getAbsolutePath() + "失败！");
		   //System.out.println(e.getMessage());
			e.printStackTrace();
		   return false;
		} finally {
			if (out != null){
				try {
				//关闭输出文件流
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
		//buff写，可以有效减少io次数，BufferedOutputStream
		OutputStream out= null;
		try {
			// 打开文件输出流
			out = new FileOutputStream(file,append);
			for(int i=0;i<contentlist.size();i++){
				List celllist = (List)contentlist.get(i);
				for(int j=0;j<celllist.size();j++){
					byte[] bytes = null;
					if(i!=celllist.size()-1)
						bytes = ((String)celllist.get(i)+",").getBytes();
					else
						bytes = ((String)celllist.get(i)+"\r").getBytes();
						
					
					//写入文件
					out.write(bytes);
					//System.out.println("写文件" + file.getAbsolutePath() + "成功！写入值为："+(String)contentlist.get(i));	
				
				}
				
			}			
			return true;
		} catch (IOException e){
		   //System.out.println("写文件" + file.getAbsolutePath() + "失败！");
		   //System.out.println(e.getMessage());
			e.printStackTrace();
		   return false;
		} finally {
			if (out != null){
				try {
				//关闭输出文件流
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
     * @deprecated:将datalist数据按照一行数据用","分割的格式写入文件，如果文件的扩展名是".csv"格式，其内容与excel格式一样
     * @param filename:文件名
     * @param datalist：要写入文件的内容，要求有两层list，第一层类似行，第二层类似单元格
     * @param is_append：是覆盖原有文件还是在原有文件上追加内容，true：在原来的文件上追加新的内容 false：原来的文件被覆盖，内容是重新生成的
     * @return 
     * @author wangtt
     * @createdate Aug 21, 2012
     * @version v1.0
     */
    public static boolean writeFileByCsv(String filename,List datalist,boolean is_append){
    	File file =new File(filename);//生成文件流
    	OutputStream out =null;//定义输出流
    	try{
    		out = new FileOutputStream(file,is_append);//打开输出流,并且定义输出文件时是否覆盖
    		for(int i=0;i<datalist.size();i++){//循环将内容写入到文件中
    			//datalist:表示每一行的数据
    			List rowlist = (List)datalist.get(i);//获取某一行
//    			StringBuffer sb = new StringBuffer();
    			for(int j=0;j<rowlist.size();j++){
    				String cell = String.valueOf(rowlist.get(j));//获取某一单元格
    				if(cell == null || cell.equals("null")) cell = "";
    				byte[] bytes = null;//定义字节数组，存放单元格内容
    				if(j==rowlist.size()-1){
    					bytes = (cell+"\r").getBytes();//一行的最后一列的时候要换行，输入换行符
    				}else{
    					bytes = (cell+",").getBytes();//不是最后一列的时候，输入","符，主要用户方便输出文件格式是csv的文件（是用","分割单元格的）。
    				}
    				out.write(bytes);//写文件
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
				//关闭输出文件流
					out.close();
				} catch (IOException e1) {}
			}
    	} 
    }

    public static boolean writeFileByCsvNew(String filename,List datalist,boolean is_append){
    	File file =new File(filename);//生成文件流
    	OutputStream out =null;//定义输出流
    	try{
    		out = new FileOutputStream(file,is_append);//打开输出流,并且定义输出文件时是否覆盖
    		for(int i=0;i<datalist.size();i++){//循环将内容写入到文件中
    			//datalist:表示每一行的数据
    			List rowlist = (List)datalist.get(i);//获取某一行
//    			StringBuffer sb = new StringBuffer();
    			for(int j=0;j<rowlist.size();j++){
    				String cell = String.valueOf(rowlist.get(j));//获取某一单元格
    				if(cell == null || cell.equals("null")) cell = "";
    				byte[] bytes = null;//定义字节数组，存放单元格内容
    				if(j==rowlist.size()-1){
    					bytes = (cell+"\t"+"\r").getBytes();//一行的最后一列的时候要换行，输入换行符
    				}else{
    					bytes = (cell+"\t"+",").getBytes();//不是最后一列的时候，输入","符，主要用户方便输出文件格式是csv的文件（是用","分割单元格的）。
    				}
    				out.write(bytes);//写文件
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
				//关闭输出文件流
					out.close();
				} catch (IOException e1) {}
			}
    	} 
    }
    
    /**
     * 
     * 
     * @param filename:文件名，根据其生成文件流
     * @param response：响应对象，文件流输出到由其生成的输出流中
     * @return
     * @author wangtt
     * @createdate Aug 21, 2012
     * @version v1.0
     */
    public static boolean expFileByCvs(String filename,HttpServletResponse response,String key){
    	File file =new File(filename);//生成文件流
    	//将文件压缩
    	String zipname=file.getName().substring(0, file.getName().indexOf("."))+".zip";//压缩后名称
    	String [] filenames = {file.getName()} ;//要压缩的文件名
    	createZip(zipname,filenames);//创建压缩文件
    	File zipfile = new File(zipname);
    	OutputStream out=null;
    	try{
    		response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(zipname.getBytes("GBk"), "iso8859-1") + "\"");
            
            setCookie(response,key);
            
            out = new BufferedOutputStream(response.getOutputStream()); //由响应对象获取输出流
            FileInputStream in = new java.io.FileInputStream(zipfile);//由压缩文件流生成输入流
            byte[] b = new byte[1024];//定义每次读取文件的大小
            int i = 0;
            while ((i = in.read(b)) > 0) {//循环读取文件，直到读取不到内容为止
                out.write(b, 0, i);//输出文件
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
				//关闭输出文件流
					out.close();
				} catch (IOException e1) {}
			}
    		//将系统自己生成的文件删除，在输出流将文件传输到用户客户端之后
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
     * @description 方法描述
     * @param filename
     * @param response
     * @return
     * @author xiaqt   
     * @date Oct 12, 2012 5:00:12 PM
     */
    public static boolean expFileByCvs(String filename,HttpServletResponse response){
    	File file =new File(filename);//生成文件流
    	//将文件压缩
    	String zipname=file.getName().substring(0, file.getName().indexOf("."))+".zip";//压缩后名称
    	String [] filenames = {file.getName()} ;//要压缩的文件名
    	createZip(zipname,filenames);//创建压缩文件
    	File zipfile = new File(zipname);
    	OutputStream out=null;
    	try{
    		response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(zipname.getBytes("GBk"), "iso8859-1") + "\"");
            
            out = new BufferedOutputStream(response.getOutputStream()); //由响应对象获取输出流
            FileInputStream in = new java.io.FileInputStream(zipfile);//由压缩文件流生成输入流
            byte[] b = new byte[1024];//定义每次读取文件的大小
            int i = 0;
            while ((i = in.read(b)) > 0) {//循环读取文件，直到读取不到内容为止
                out.write(b, 0, i);//输出文件
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
				//关闭输出文件流
					out.close();
				} catch (IOException e1) {}
			}
    		//将系统自己生成的文件删除，在输出流将文件传输到用户客户端之后
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
	 * 压缩文件，支持中文路径及中文文件名 支持rar格式以及zip格式
	 * 
	 * @param compressFileName(指定压缩后的文件及路径):path+"/tempfile/test.zip" 
	 * @param args(所要压缩的文件):String args[]={path+"/tempfile/download_123.txt",path+"/tempfile/download_456.txt",path+"/tempfile/download_789.txt"}  
	 * @return
	 * @author sanjing
	 * @createdate Aug 17, 2012
	 * @version v1.0
	 */
    public  static boolean createZip(String compressFileName,String args[]){    
        boolean flag = false;    
        InputStream in =null;//定义输入流
        ZipOutputStream zout=null;
        try{            
            byte b[] = new byte[1024];       
            zout  = new ZipOutputStream(new FileOutputStream(compressFileName));//创建压缩输出流
            for (int i = 0; i < args.length; i++) {
				in = new FileInputStream(args[i]); // 打开输入流

				File file = new File(args[i]);
				String filename = file.getName();// 取得文件名
				// ZipEntry e = new
				// ZipEntry(args[i].replace(File.separatorChar,'/')); //压缩后带路径
				ZipEntry e = new ZipEntry(filename); // 压缩对象（压缩后不带路径）
				zout.putNextEntry(e); // 在输出流中放入压缩对象
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
				// 关闭输出文件流
					zout.close();
				} catch (IOException e1) {}
			}
        	if (in != null){
				try {
    				//关闭输出文件流
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
	 * 竖向导出所有数据到excel
	 * 
	 * @param SheetName
	 *            excel的工作表名称
	 * @param titles
	 *            标题
	 * @param data
	 *            数据
	 * @param response
	 *            输出流
	 * @return
	 */
	public static boolean AllDataToExcel(String xlsName,String SheetName, String[] titles, List data, HttpServletResponse response) {
	 	WritableWorkbook workbook = null;
         File file=new File(xlsName);

		try {

			workbook = Workbook.createWorkbook(file); // //缓冲区输出

			// wfImportant 必填字段Font设置 wfNormal 非必填字段
			jxl.write.WritableFont wfBold = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfBlack = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfNormal = new jxl.write.WritableFont(WritableFont.COURIER, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			// wfcfImportant 必填字段Cell设置 wfcfNormal 非必填字段Cell设置
			jxl.write.WritableCellFormat wcfBold = new jxl.write.WritableCellFormat(wfBold);
			jxl.write.WritableCellFormat wcfBlack = new jxl.write.WritableCellFormat(wfBlack);
			jxl.write.WritableCellFormat wcfNormal = new jxl.write.WritableCellFormat(wfNormal);
			// 所有Cell背景设置为 25% Gray
			wcfBold.setBackground(jxl.format.Colour.GRAY_25);
			wcfBlack.setBackground(jxl.format.Colour.GRAY_25);
			// 设置边框
			wcfBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfBlack.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfNormal.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 设置对齐
			wcfBold.setAlignment(Alignment.CENTRE);
			wcfBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfBlack.setAlignment(Alignment.CENTRE);
			wcfBlack.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNormal.setAlignment(Alignment.CENTRE);
			wcfNormal.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置自动换行
//			wcfBold.setWrap(true);
//			wcfBlack.setWrap(true);
//			wcfNormal.setWrap(true);

			// 计算总sheet
			int sheetNum = (int) Math.ceil((double) data.size() / GlobalConst.SHEET_COUNT);
            if(sheetNum==0){sheetNum=1;};
            String sheetNameNum = "";
			for (int j = 0; j < sheetNum; j++) {
				if (j > 0 )
					sheetNameNum = String.valueOf(j);
				WritableSheet sheet = workbook.createSheet(SheetName + sheetNameNum, j); // 新sheet的名字
				
				// =com.newland.util.StringText.str2GB2312ToISO(sheet);
				// 标题
				for (int i = 0; i < titles.length; i++) {
					jxl.write.Label label;

					label = new jxl.write.Label(i, 0, GlobalFunc.getWatermark(titles[i]), wcfBold);

					sheet.addCell(label);
					sheet.setColumnView(i, 20);
				}
				int intIndex = 0;
				if (intIndex < GlobalConst.SHEET_COUNT && data.size() > intIndex + j * GlobalConst.SHEET_COUNT) {
					int intCount = (data.size() - intIndex - j * GlobalConst.SHEET_COUNT) > GlobalConst.SHEET_COUNT ? GlobalConst.SHEET_COUNT : (data.size() - intIndex - j * GlobalConst.SHEET_COUNT);
					// 数据
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
			// log.error("导出excel出错:" + ee.getMessage());
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

			workbook = Workbook.createWorkbook(file); // //缓冲区输出

			// wfImportant 必填字段Font设置 wfNormal 非必填字段
			jxl.write.WritableFont wfBold = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfBlack = new jxl.write.WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			jxl.write.WritableFont wfNormal = new jxl.write.WritableFont(WritableFont.COURIER, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			// wfcfImportant 必填字段Cell设置 wfcfNormal 非必填字段Cell设置
			jxl.write.WritableCellFormat wcfBold = new jxl.write.WritableCellFormat(wfBold);
			jxl.write.WritableCellFormat wcfBlack = new jxl.write.WritableCellFormat(wfBlack);
			jxl.write.WritableCellFormat wcfNormal = new jxl.write.WritableCellFormat(wfNormal);
			// 所有Cell背景设置为 25% Gray
			wcfBold.setBackground(jxl.format.Colour.GRAY_25);
			wcfBlack.setBackground(jxl.format.Colour.GRAY_25);
			// 设置边框
			wcfBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfBlack.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfNormal.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 设置对齐
			wcfBold.setAlignment(Alignment.CENTRE);
			wcfBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfBlack.setAlignment(Alignment.CENTRE);
			wcfBlack.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNormal.setAlignment(Alignment.CENTRE);
			wcfNormal.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置自动换行
//			wcfBold.setWrap(true);
//			wcfBlack.setWrap(true);
//			wcfNormal.setWrap(true);

			// 计算总sheet
			int sheetNum = (int) Math.ceil((double) data.size() / GlobalConst.SHEET_COUNT);
            if(sheetNum==0){sheetNum=1;};
            String sheetNameNum = "";
			for (int j = 0; j < sheetNum; j++) {
				if (j > 0 )
					sheetNameNum = String.valueOf(j);
				WritableSheet sheet = workbook.createSheet(SheetName + sheetNameNum, j); // 新sheet的名字
				
				// =com.newland.util.StringText.str2GB2312ToISO(sheet);
				// 标题
				for (int i = 0; i < titles.length; i++) {
					jxl.write.Label label;

					label = new jxl.write.Label(i, 0, GlobalFunc.getWatermark(titles[i]), wcfBold);

					sheet.addCell(label);
					sheet.setColumnView(i, 20);
				}
				int intIndex = 0;
				if (intIndex < GlobalConst.SHEET_COUNT && data.size() > intIndex + j * GlobalConst.SHEET_COUNT) {
					int intCount = (data.size() - intIndex - j * GlobalConst.SHEET_COUNT) > GlobalConst.SHEET_COUNT ? GlobalConst.SHEET_COUNT : (data.size() - intIndex - j * GlobalConst.SHEET_COUNT);
					// 数据
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
			// log.error("导出excel出错:" + ee.getMessage());
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
