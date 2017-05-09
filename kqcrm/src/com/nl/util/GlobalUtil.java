package com.nl.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nl.base.utils.GlobalFunc;
import com.nl.company.util.AppConst;

/**
 *
 * @author sanjing
 * @creatdate May 4, 2012
 */
public class GlobalUtil {
	private static Logger log = Logger.getLogger(GlobalUtil.class);
	
	// 转化字符串为十六进制编码 
    public static String toHexString(String s) 
    { 
	    String str=""; 
	    for (int i=0;i<s.length();i++) 
	    { 
	    int ch = (int)s.charAt(i); 
	    String s4 = Integer.toHexString(ch); 
	    str = str + s4; 
	    } 
	    return str; 
    }
	
	/**
	 * struts实现的取properties里面的值
	 * 
	 * @param filename
	 * @param key
	 * @return
	 * @author sanjing
	 * @createdate Oct 25, 2016
	 * @version v1.0
	 */
	public static String getPropertiesText(String filename,String key){
		String valueStr=null; 
		 
		 MessageResourcesFactory factory = MessageResourcesFactory.createFactory();
	     MessageResources resources = factory.createResources(filename);//application.properties
	     valueStr=resources.getMessage(key);
		 if (valueStr==null)
		 {
			 log.error("未找到"+filename+"文件中对应的数据");
			//throw new Exception("未找到对应的视图组件");
		 }
		 
		 return valueStr;
		
		
	}

	/**
	 * 返回当前人员登录的工号
	 * 
	 * @param request
	 * @return
	 * @author sanjing
	 * @createdate May 4, 2012
	 * @version v1.0
	 */
	public static String getBossCodeStr(HttpServletRequest request){
		log.info("GlobalUtil::getBossCodeStr()->Enter");
		try{
			SessionData sessionData = (SessionData)request.getSession().getAttribute(SessionConst.LOGIN_SESSION);
			if (sessionData != null && sessionData.getAdmUser() != null){
				log.info("GlobalUtil::getBossCodeStr()->Exit");
				return sessionData.getAdmUser().getAuID();
			}
			return null;
		}catch(Exception e){
			log.error("GlobalUtil::getBossCodeStr()->Exit"+e.getMessage());
			return null;
		}
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
			/*wcfBold.setWrap(true);
			wcfBlack.setWrap(true);
			wcfNormal.setWrap(true);*/

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
	
	public static boolean AllDataToExcel2007(String xlsName,String SheetName, String[] titles, List data, HttpServletResponse response) {
         File file=new File(xlsName);

		try {
			
			// 第一步，创建一个webbook，对应一个Excel文件  
	        XSSFWorkbook wb = new XSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        XSSFSheet sheet = wb.createSheet(SheetName);  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        XSSFRow row = sheet.createRow((int) 0);  
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        XSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
	        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
	        
	        XSSFFont font = wb.createFont();	          
	        font.setFontHeightInPoints((short)10);
       
	        XSSFCell cell = null; 
	        for (int i = 0; i < titles.length; i++) {
	        	cell = row.createCell((short) i);
				cell.setCellValue(titles[i]);
				
				//把字体应用到当前的样式
				style.setFont(font);  				
				cell.setCellStyle(style); 
			}

	        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
	        List list = null;
	        
	        for (int i = 0; i < data.size(); i++)  
	        {  
	        	List lst = (List) data.get(i);
	        	String str = null;
	            row = sheet.createRow((int) i + 1);  
	            // 第四步，创建单元格，并设置值  
	            
	            for (int t = 0; t < lst.size(); t++) {
	            	str = String.valueOf(lst.get(t));
	            	if (str == null || str.equals("null")) str = "";
	            	
		            cell = row.createCell((short) t);
		            cell.setCellValue(str); 		            
					cell.setCellStyle(style); 
	            }
	        } 

	        //FileOutputStream fout = new FileOutputStream("E:/"+xlsName);  
            //wb.write(fout);  
            //fout.close();  
                        
            response.reset();
            response.setContentType("application/vnd.ms-excel; charset=GBK");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(xlsName.getBytes("gb2312"), "iso8859-1")
                    + "\"");
            
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            wb.write(out);
            
            /*FileInputStream in= new java.io.FileInputStream(file);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
                out.flush();
            }
            
            in.close();*/
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
	
	/**
	 * 
	 * @param xlsName
	 * @param SheetName
	 * @param titles
	 * @param data
	 * @param response
	 * @return
	 */
	public static boolean AllDataToExcelByWtt(String xlsName,String SheetName, List titles, List data, HttpServletResponse response) {
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
			//wcfBold.setWrap(true);
			//wcfBlack.setWrap(true);
			//wcfNormal.setWrap(true);

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
				int title =0;//标题行数
				for (int i = 0; i < titles.size(); i++) {
					Map m   = (Map)titles.get(i);
					String col_name =  String.valueOf(m.get("col_name"));
					String col1 =  String.valueOf(m.get("col1"));
					String row1 =  String.valueOf(m.get("row1"));
					String col2 =  String.valueOf(m.get("col2"));
					String row2 =  String.valueOf(m.get("row2"));
					if(Integer.parseInt(row2)>title){
						title = Integer.parseInt(row2);
					}
					jxl.write.Label label;
					sheet.mergeCells(Integer.parseInt(col1), Integer.parseInt(row1), Integer.parseInt(col2), Integer.parseInt(row2));//合并单元格
					label = new jxl.write.Label(Integer.parseInt(col1), Integer.parseInt(row1), GlobalFunc.getWatermark(col_name), wcfBold);

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
							jxl.write.Label label = new jxl.write.Label(t, i +title+ 1, str,wcfNormal);
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
			//Logger.getLogger(ExcelUtil.class).info("导出文件名称"+xlsName);
            if(file.exists()){
                file.delete();
            }
        }
        return true;

	}
	
	/**
	 * 
	 * @param calendar
	 * @return 结果格式：yyyy-mm-dd
	 */
	public static String getDateForYYYYMMDD(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String month2 = "";
        String day2 = "";
        if (month < 10) {
            month2 = "0" + month;
        } else {
            month2 = String.valueOf(month);
        }

        if (day < 10) {
            day2 = "0" + day;
        } else {
            day2 = String.valueOf(day);
        }
        String date = year + "-" + month2 + "-" + day2;
        return date;
    }
	
	/**
	 **@param dfParam 需要格式化的参数，如：“yyyy-MM-dd”，“yyyy-MM”，“yyyyMMdd”等
	 * @param monthNum 是否需要得到间隔几个月的时间，如：“-1”表示上月，“0”表示当月，“1”表示下月
	 * @return java.lang.String
	 */
	public static String getSystemDateByParam(String dfParam,int monthNum)
	{
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, monthNum);
		java.text.SimpleDateFormat formatter1 = new java.text.SimpleDateFormat(dfParam);
		String transactionDate = formatter1.format(c.getTime());
		return transactionDate;
	}
	
	/**
	 **@param dfParam 需要格式化的参数，如：“yyyy-MM-dd”，“yyyy-MM”，“yyyyMMdd”等
	 * @param dayNum 是否需要得到间隔几个月的时间，如：“-1”表示前一天，“0”表示当天，“1”表示明天
	 * @return java.lang.String
	 */
	public static String getSystemPreDateByParam(String dfParam,int dayNum)
	{
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, dayNum);
		java.text.SimpleDateFormat formatter1 = new java.text.SimpleDateFormat(dfParam);
		String transactionDate = formatter1.format(c.getTime());
		return transactionDate;
	}
	
	
	/**
	 * 
	 * @param codeLen  随机码位数
	 * @return 随机码
	 */
	public static String getRandomCode(int codeLen)
	{
		//首先定义随机数据源
		//根据需要得到的数据码的长度返回随机字符串
		java.util.Random randomCode = new java.util.Random();
		String strCode = "";
		while (codeLen > 0)
		{
			int intCode = randomCode.nextInt(9);
			strCode += intCode;
			codeLen--;
		}
		return strCode;
	}
	
	/**
	 * 获取真实IP地址
	 * 
	 * @param request
	 * @return
	 * @author HP
	 * @createdate Aug 16, 2012
	 * @version v1.0
	 */
	public static String getIpAddr(HttpServletRequest request) {   
	    String ip = request.getHeader("x-forwarded-for");   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = request.getHeader("Proxy-Client-IP");   
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = request.getHeader("WL-Proxy-Client-IP");   
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = request.getRemoteAddr();   
	    }   
	    return ip;   
	}
	
	/**
	 * 返回主机名称，非通过代理的，代理方法未测试
	 * 
	 * @param request
	 * @return
	 * @author sanjing
	 * @createdate Oct 17, 2016
	 * @version v1.0
	 */
	public static String getRequestName(HttpServletRequest request){
		String requestName = request.getRemoteHost();
		return requestName;
		
	}
	/**
	 * 
	 * 判断一个字符串是否为纯数字构成
	 * @param str
	 * @return 是纯数字 true
	 * @author wangtt
	 * @createdate Dec 21, 2012
	 * @version v1.0
	 */
	public static boolean checkNumber(String str)
	{
		String number="0123456789";
		for(int i=0;i<str.length();i++){
			if(number.indexOf(str.charAt(i))==-1){
				return false;
			}
		} 
		return true;
	}
	
	 /**     
	  ** 获取单元格数据内容为字符串类型的数据     
	  **      
	  ** @param cell Excel单元格     
	  ** @return String 单元格数据内容     
	  **/    
	public static String getStringCellValue(HSSFCell cell) 
	{
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = new DecimalFormat("0").format(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
		break;
		}
		if (strCell.equals("") || strCell == null)
		{
			return "";        
		}        
		if (cell == null) 
		{            
			return "";        
		}        
		return strCell;    
	}
	
	public static String getStringCellValue(XSSFCell cell) 
	{        
		String strCell = "";        
		switch (cell.getCellType()) {         
		case XSSFCell.CELL_TYPE_STRING:            
			strCell = cell.getStringCellValue();            
			break;        
		case XSSFCell.CELL_TYPE_NUMERIC:    			
			strCell = new DecimalFormat("0").format(cell.getNumericCellValue());            
			break;       
		case XSSFCell.CELL_TYPE_BOOLEAN:            
			strCell = String.valueOf(cell.getBooleanCellValue());            
			break;        
		case XSSFCell.CELL_TYPE_BLANK:            
			strCell = "";            
			break;        
		default:            
			strCell = "";            
		break;       
		}        
		if (strCell.equals("") || strCell == null) 
		{            
			return "";        
		}        
		if (cell == null) 
		{            
			return "";        
		}        
		return strCell;    
	}
	

	public static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}else if(cell instanceof HSSFCell){
			return GlobalUtil.getStringCellValue((HSSFCell)cell);
		}else if(cell instanceof XSSFCell){
			return GlobalUtil.getStringCellValue((XSSFCell)cell);
		}
		return "";    
	}
	
	/**
	 * 获取文件名的真实名称（去掉路径）
	 */
	public static String getFinalFile(String filename){
		String real_name="";
		int begin_index = filename.lastIndexOf("\\"); 
		if(begin_index!=-1){
			real_name = filename.substring(begin_index+1);//不带“\”
		}else{
			real_name=filename;
		}
		return real_name;
	}
	public static boolean checkTaskId(String[] taskids,String task_id){
		for(int i=0;i<taskids.length;i++){
			String taskid = taskids[i];
			if(task_id.equals(taskid)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @Title: checkRoleByDept 
	 * @Description: 根据人员的hr_sno,判断是否子在当前登录人员的权限范围
	 * @author wtt   
	 * @date Apr 11, 2014 2:14:55 PM 
	 * @version V1.0  
	 * @param @return    
	 * @return boolean   
	 * @throws
	 */
	public static boolean checkRoleByDept(String hr_sno,String sno_dept){
		SessionData sessionData = new SessionData();
//		SystemSC systemSC = new SystemSC();
//		List<PortalUser> userlist = systemSC.checkRoleByDept(hr_sno,sno_dept);
//		if(userlist!=null&&userlist.size()>0){
//			return true;
//		}
		return false;
	}
	
	/**
	 * 判断字符串是否全是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	     Pattern pattern = Pattern.compile("[0-9]*"); 
	     return pattern.matcher(str).matches();    
	} 
	/**
	 * 设置COOKIE
	 * @param response
	 * @param key
	 */
	public static void setCookie(HttpServletResponse response,String key)
	{
		Cookie cook = new Cookie(key,"true"); 
        cook.setMaxAge(1800);
        cook.setPath("/");
        response.addCookie(cook);
	}
	
	/**
	 * 
	 *@Title: replaceSpecialSymbol 
	 *@Description: TODO去除字符串中的空格、回车、换行符、制表符  
	 *               注：\n 回车(\u000a) 
					    \t 水平制表符(\u0009) 
					    \s 空格(\u0008) 
					    \r 换行(\u000d)
	 *@author CJ
	 *@date Sep 18, 2014 3:47:31 PM 
	 *@param str
	 *@return
	 *@return String
	 */
	public static String replaceSpecialSymbol(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	/**
     *  判断某个字符串是否存在于数组中
     *  @param stringArray 原数组
     *  @param source 查找的字符串
     *  @return 是否找到
     */
   public static boolean arryContains(List<String> stringList, String source) {
       // 判断list是否为空
       if(stringList.size()==0){
    	   return false;
       }
       // 利用list的包含方法,进行判断
       if(stringList.contains(source)){
           return true;
       } else {
           return false;
       }
   }
   
   /**
    * 将8位日期转为10位以“YYYY-MM-DD”形式展现的日期
    *@Title: date8ToDate10 
    *@Description: TODO
    *@author CJ
    *@date Sep 25, 2015 12:51:07 PM 
    *@param str
    *@return
    *@return String
    */
   public static String date8ToDate10(String str)
   {
	   String result = "";
	   
	   if(str.length()==8&&isNumeric(str))
	   {
		   result = str.substring(0, 4)+"-"+str.substring(4, 6)+"-"+str.substring(6, 8);
		   return result;
	   }else{
		   return str;
	   }
   }
   
   /**
    * 上传附件置服务器
    *@Title: upFile 
    *@Description: TODO
    *@author CJ
    *@date Oct 25, 2016 8:26:53 PM 
    *@param ip
    *@param port
    *@param username
    *@param password
    *@param fis
    *@param destination
    *@param fileName
    *@return
    *@throws Exception
    *@return int
    */
   public static int upFile(String ip, String port, String username,
			String password, InputStream fis, String destination,
			String fileName) throws Exception {
//		getLogger("", GlobalConst.ENTER).info("开始上传附件至ftp：");
		int retCode = 0;
		try {
			int p = 0;
			if ("".equals(port))
				p = 21;
			else
				p = Integer.valueOf(port);

//			String filePath = GlobalConst.FILE_PATH_NEW ;// 上传到指定的upload包中
			String filePath = GlobalConst.FILE_PATH
			+ GlobalConst.UPLOAD_TMP_FILE_PATH;// 上传到指定的upload包中
			

			// 将文件从本地上传至FTP
			FTPTools ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// 登录FTP服务器
			ftpServer.buildList(destination);//在ftp上建立路径（文件夹）
			ftpServer.logout();

			ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// 登录FTP服务器
			String target = filePath + "/" + fileName;
			ftpServer.upFile(target, destination + "/" + fileName);
			ftpServer.logout();
			
			OutputStream bos = new FileOutputStream(filePath + "/" + fileName);// 建立一个上传文件的输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			bos.close();
			fis.close();

			// 将本地文件删除
			File file = new File(filePath + "/" + fileName);
			file.delete();
		} catch (Exception e) {
			retCode = -1;
			log.error("::上传文件至FTP服务器有错！::" + "error：" + e.getMessage());
			throw e;
		}
		return retCode;
	}
   
   /**
    * 远程FTP服务器上存放另存为名字的文件
    *@Title: upFile 
    *@Description: TODO
    *@author CJ
    *@date Nov 20, 2016 2:37:50 AM 
    *@param ip
    *@param port
    *@param username
    *@param password
    *@param fis
    *@param destination
    *@param fileName
    *@param realname
    *@return
    *@throws Exception
    *@return int
    */
   public static int upFile(String ip, String port, String username,
			String password, InputStream fis, String destination,
			String fileName,String realname) throws Exception {
//		getLogger("", GlobalConst.ENTER).info("开始上传附件至ftp：");
		int retCode = 0;
		try {
			int p = 0;
			if ("".equals(port))
				p = 21;
			else
				p = Integer.valueOf(port);

//			String filePath = GlobalConst.FILE_PATH_NEW ;// 上传到指定的upload包中
			String filePath = GlobalConst.FILE_PATH
			+ GlobalConst.UPLOAD_TMP_FILE_PATH;// 上传到指定的upload包中


			// 将文件从本地上传至FTP
			FTPTools ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// 登录FTP服务器
			ftpServer.buildList(destination);
			boolean is_ext = false;
			while(!is_ext)
			{
				Thread.sleep(3000);//休眠一秒，等文件夹建立好
				if(ftpServer.isDirExt(destination)){
					is_ext = true;
					continue;
				}
				ftpServer.buildList(destination);
			}
//			ftpServer.buildList(destination);//在ftp上建立路径（文件夹）
			ftpServer.logout();
			
			
			ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// 登录FTP服务器
			String target = filePath + "/" + realname;
			ftpServer.upFile(target, destination + "/" + fileName);
			ftpServer.logout();
		
//			OutputStream bos = new FileOutputStream(filePath + "/" + fileName);// 建立一个上传文件的输出流
//			int bytesRead = 0;
//			byte[] buffer = new byte[8192];
//			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
//				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
//			}
//			bos.close();
//			fis.close();


			// 将本地文件删除
			File file = new File(filePath + "/" + fileName);
			file.delete();
		} catch (Exception e) {
			retCode = -1;
			log.error("::上传文件至FTP服务器有错！::" + "error：" + e.getMessage());
			throw e;
		}
		return retCode;
	}
   /**
    * 模板文件下载
    *@Title: downLoad 
    *@Description: TODO
    *@author CJ
    *@date Oct 26, 2016 11:27:49 PM 
    *@param fileName
    *@param response
    *@return
    *@throws Exception
    *@return int
    */
   public static int downLoad(String fileName,HttpServletResponse response) throws Exception{
	    log.info("文件下载功能：");
		int retCode = 0;
		
		if(!"".equals(fileName))
		{
			Workbook workbook = null;
			File file = new File(GlobalConst.FILE_PATH+GlobalConst.DOWNLOAD_FILE_PATH+fileName);
			
			try {
				//workbook = Workbook.getWorkbook(file); // //缓冲区输出
				
	            response.reset();
	            response.setContentType("application/vnd.ms-excel; charset=GBK");
	            response.setHeader("Content-Disposition", "attachment; filename="
	                    + new String(fileName.getBytes("gb2312"), "iso8859-1")
	                    + "\"");
	            OutputStream out = new BufferedOutputStream(response.getOutputStream());
	            FileInputStream in= new FileInputStream(file);
	            byte[] b = new byte[1024];
	            int i = 0;
	            while ((i = in.read(b)) > 0) {
	                out.write(b, 0, i);
	                out.flush();
	            }
	            in.close();
	            out.close();
	            
	            log.error("结束文件下载功能。");
	            //return true;
			} catch (Exception ee) {
				log.error(
						"::文件下载功能有错！::" + "error::"+ee.getMessage());
				//return false;
				retCode = -1;
			} finally{
				
	        }
		}
		return retCode;
	}
   
   public static int downLoad(String fileName,String filePath,HttpServletResponse response) throws Exception{
	    log.info("文件下载功能：");
		int retCode = 0;
		
		if(!"".equals(fileName))
		{
			Workbook workbook = null;
			File file = new File(filePath+fileName);
			
			try {
				//workbook = Workbook.getWorkbook(file); // //缓冲区输出
				
	            response.reset();
	            response.setContentType("application/vnd.ms-excel; charset=GBK");
	            response.setHeader("Content-Disposition", "attachment; filename="
	                    + new String(fileName.getBytes("gb2312"), "iso8859-1")
	                    + "\"");
	            OutputStream out = new BufferedOutputStream(response.getOutputStream());
	            FileInputStream in= new FileInputStream(file);
	            byte[] b = new byte[1024];
	            int i = 0;
	            while ((i = in.read(b)) > 0) {
	                out.write(b, 0, i);
	                out.flush();
	            }
	            in.close();
	            out.close();
	            
	            log.error("结束文件下载功能。");
	            //return true;
			} catch (Exception ee) {
				log.error(
						"::文件下载功能有错！::" + "error::"+ee.getMessage());
				//return false;
				retCode = -1;
			} finally{
				
	        }
		}
		return retCode;
	}
   
   /**
    * 
    * 
    * @param strXml
    * @return
    * @throws Exception
    * @author sanjing
    * @createdate Nov 2, 2016
    * @version v1.0
    */
   public static String xmlTransTable(String strXml) throws Exception{
	   String strTable = "";
	   try{
		   	
			Document m_Document = DocumentHelper.parseText(strXml);
			Element m_rootElement = m_Document.getRootElement();
			strTable = strTable+"<table border=\"1\">";
			List tlist = new ArrayList(m_rootElement.elements("table"));
			for(int i=0;i<tlist.size();i++){
				strTable = strTable+"<tr>";
				strTable = strTable+"<td width=\"20%\">";
				Element m_cols1 = ((Element)tlist.get(i)).element("table_name");
				strTable = strTable+m_cols1.getStringValue();
				strTable = strTable+"</td>";
				strTable = strTable+"<td>";
				Element m_cols2 = ((Element)tlist.get(i)).element("file_name");
				strTable = strTable+m_cols2.getStringValue();
				strTable = strTable+"</td>";
				strTable = strTable+"<td width=\"20%\">";
				Element m_cols3 = ((Element)tlist.get(i)).element("table_body");
				strTable = strTable+m_cols3.getStringValue();
				strTable = strTable+"</td>";
				strTable = strTable+"<td width=\"20%\">";
				Element m_cols4  = ((Element)tlist.get(i)).element("Example_file_name");
				strTable = strTable+m_cols4.getStringValue();
				strTable = strTable+"</td>";
				strTable = strTable+"<td width=\"20%\">";
				Element m_cols5  = ((Element)tlist.get(i)).element("Example_table_body");
				strTable = strTable+m_cols5.getStringValue();
				strTable = strTable+"</td>";
				strTable = strTable+"</tr>";
			}
			strTable = strTable+"</table>";
			
			//System.out.println(m_rootElement.getStringValue());
			//System.out.println(strTable);
			
		}catch(Exception e){
			log.error(e.getMessage());
			
		}
		return strTable;
   }
   
}
