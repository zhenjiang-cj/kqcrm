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
	
	// ת���ַ���Ϊʮ�����Ʊ��� 
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
	 * strutsʵ�ֵ�ȡproperties�����ֵ
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
			 log.error("δ�ҵ�"+filename+"�ļ��ж�Ӧ������");
			//throw new Exception("δ�ҵ���Ӧ����ͼ���");
		 }
		 
		 return valueStr;
		
		
	}

	/**
	 * ���ص�ǰ��Ա��¼�Ĺ���
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
			/*wcfBold.setWrap(true);
			wcfBlack.setWrap(true);
			wcfNormal.setWrap(true);*/

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
	
	public static boolean AllDataToExcel2007(String xlsName,String SheetName, String[] titles, List data, HttpServletResponse response) {
         File file=new File(xlsName);

		try {
			
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
	        XSSFWorkbook wb = new XSSFWorkbook();  
	        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
	        XSSFSheet sheet = wb.createSheet(SheetName);  
	        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
	        XSSFRow row = sheet.createRow((int) 0);  
	        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
	        XSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ  
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
				
				//������Ӧ�õ���ǰ����ʽ
				style.setFont(font);  				
				cell.setCellStyle(style); 
			}

	        // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���  
	        List list = null;
	        
	        for (int i = 0; i < data.size(); i++)  
	        {  
	        	List lst = (List) data.get(i);
	        	String str = null;
	            row = sheet.createRow((int) i + 1);  
	            // ���Ĳ���������Ԫ�񣬲�����ֵ  
	            
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
			// log.error("����excel����:" + ee.getMessage());
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
			//wcfBold.setWrap(true);
			//wcfBlack.setWrap(true);
			//wcfNormal.setWrap(true);

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
				int title =0;//��������
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
					sheet.mergeCells(Integer.parseInt(col1), Integer.parseInt(row1), Integer.parseInt(col2), Integer.parseInt(row2));//�ϲ���Ԫ��
					label = new jxl.write.Label(Integer.parseInt(col1), Integer.parseInt(row1), GlobalFunc.getWatermark(col_name), wcfBold);

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
			// log.error("����excel����:" + ee.getMessage());
			return false;
		} finally{
			//Logger.getLogger(ExcelUtil.class).info("�����ļ�����"+xlsName);
            if(file.exists()){
                file.delete();
            }
        }
        return true;

	}
	
	/**
	 * 
	 * @param calendar
	 * @return �����ʽ��yyyy-mm-dd
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
	 **@param dfParam ��Ҫ��ʽ���Ĳ������磺��yyyy-MM-dd������yyyy-MM������yyyyMMdd����
	 * @param monthNum �Ƿ���Ҫ�õ���������µ�ʱ�䣬�磺��-1����ʾ���£���0����ʾ���£���1����ʾ����
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
	 **@param dfParam ��Ҫ��ʽ���Ĳ������磺��yyyy-MM-dd������yyyy-MM������yyyyMMdd����
	 * @param dayNum �Ƿ���Ҫ�õ���������µ�ʱ�䣬�磺��-1����ʾǰһ�죬��0����ʾ���죬��1����ʾ����
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
	 * @param codeLen  �����λ��
	 * @return �����
	 */
	public static String getRandomCode(int codeLen)
	{
		//���ȶ����������Դ
		//������Ҫ�õ���������ĳ��ȷ�������ַ���
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
	 * ��ȡ��ʵIP��ַ
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
	 * �����������ƣ���ͨ������ģ�������δ����
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
	 * �ж�һ���ַ����Ƿ�Ϊ�����ֹ���
	 * @param str
	 * @return �Ǵ����� true
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
	  ** ��ȡ��Ԫ����������Ϊ�ַ������͵�����     
	  **      
	  ** @param cell Excel��Ԫ��     
	  ** @return String ��Ԫ����������     
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
	 * ��ȡ�ļ�������ʵ���ƣ�ȥ��·����
	 */
	public static String getFinalFile(String filename){
		String real_name="";
		int begin_index = filename.lastIndexOf("\\"); 
		if(begin_index!=-1){
			real_name = filename.substring(begin_index+1);//������\��
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
	 * @Description: ������Ա��hr_sno,�ж��Ƿ����ڵ�ǰ��¼��Ա��Ȩ�޷�Χ
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
	 * �ж��ַ����Ƿ�ȫ������
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	     Pattern pattern = Pattern.compile("[0-9]*"); 
	     return pattern.matcher(str).matches();    
	} 
	/**
	 * ����COOKIE
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
	 *@Description: TODOȥ���ַ����еĿո񡢻س������з����Ʊ��  
	 *               ע��\n �س�(\u000a) 
					    \t ˮƽ�Ʊ��(\u0009) 
					    \s �ո�(\u0008) 
					    \r ����(\u000d)
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
     *  �ж�ĳ���ַ����Ƿ������������
     *  @param stringArray ԭ����
     *  @param source ���ҵ��ַ���
     *  @return �Ƿ��ҵ�
     */
   public static boolean arryContains(List<String> stringList, String source) {
       // �ж�list�Ƿ�Ϊ��
       if(stringList.size()==0){
    	   return false;
       }
       // ����list�İ�������,�����ж�
       if(stringList.contains(source)){
           return true;
       } else {
           return false;
       }
   }
   
   /**
    * ��8λ����תΪ10λ�ԡ�YYYY-MM-DD����ʽչ�ֵ�����
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
    * �ϴ������÷�����
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
//		getLogger("", GlobalConst.ENTER).info("��ʼ�ϴ�������ftp��");
		int retCode = 0;
		try {
			int p = 0;
			if ("".equals(port))
				p = 21;
			else
				p = Integer.valueOf(port);

//			String filePath = GlobalConst.FILE_PATH_NEW ;// �ϴ���ָ����upload����
			String filePath = GlobalConst.FILE_PATH
			+ GlobalConst.UPLOAD_TMP_FILE_PATH;// �ϴ���ָ����upload����
			

			// ���ļ��ӱ����ϴ���FTP
			FTPTools ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// ��¼FTP������
			ftpServer.buildList(destination);//��ftp�Ͻ���·�����ļ��У�
			ftpServer.logout();

			ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// ��¼FTP������
			String target = filePath + "/" + fileName;
			ftpServer.upFile(target, destination + "/" + fileName);
			ftpServer.logout();
			
			OutputStream bos = new FileOutputStream(filePath + "/" + fileName);// ����һ���ϴ��ļ��������
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// ���ļ�д�������
			}
			bos.close();
			fis.close();

			// �������ļ�ɾ��
			File file = new File(filePath + "/" + fileName);
			file.delete();
		} catch (Exception e) {
			retCode = -1;
			log.error("::�ϴ��ļ���FTP�������д�::" + "error��" + e.getMessage());
			throw e;
		}
		return retCode;
	}
   
   /**
    * Զ��FTP�������ϴ�����Ϊ���ֵ��ļ�
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
//		getLogger("", GlobalConst.ENTER).info("��ʼ�ϴ�������ftp��");
		int retCode = 0;
		try {
			int p = 0;
			if ("".equals(port))
				p = 21;
			else
				p = Integer.valueOf(port);

//			String filePath = GlobalConst.FILE_PATH_NEW ;// �ϴ���ָ����upload����
			String filePath = GlobalConst.FILE_PATH
			+ GlobalConst.UPLOAD_TMP_FILE_PATH;// �ϴ���ָ����upload����


			// ���ļ��ӱ����ϴ���FTP
			FTPTools ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// ��¼FTP������
			ftpServer.buildList(destination);
			boolean is_ext = false;
			while(!is_ext)
			{
				Thread.sleep(3000);//����һ�룬���ļ��н�����
				if(ftpServer.isDirExt(destination)){
					is_ext = true;
					continue;
				}
				ftpServer.buildList(destination);
			}
//			ftpServer.buildList(destination);//��ftp�Ͻ���·�����ļ��У�
			ftpServer.logout();
			
			
			ftpServer = new FTPTools(ip, p, username, password);
			ftpServer.login();// ��¼FTP������
			String target = filePath + "/" + realname;
			ftpServer.upFile(target, destination + "/" + fileName);
			ftpServer.logout();
		
//			OutputStream bos = new FileOutputStream(filePath + "/" + fileName);// ����һ���ϴ��ļ��������
//			int bytesRead = 0;
//			byte[] buffer = new byte[8192];
//			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
//				bos.write(buffer, 0, bytesRead);// ���ļ�д�������
//			}
//			bos.close();
//			fis.close();


			// �������ļ�ɾ��
			File file = new File(filePath + "/" + fileName);
			file.delete();
		} catch (Exception e) {
			retCode = -1;
			log.error("::�ϴ��ļ���FTP�������д�::" + "error��" + e.getMessage());
			throw e;
		}
		return retCode;
	}
   /**
    * ģ���ļ�����
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
	    log.info("�ļ����ع��ܣ�");
		int retCode = 0;
		
		if(!"".equals(fileName))
		{
			Workbook workbook = null;
			File file = new File(GlobalConst.FILE_PATH+GlobalConst.DOWNLOAD_FILE_PATH+fileName);
			
			try {
				//workbook = Workbook.getWorkbook(file); // //���������
				
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
	            
	            log.error("�����ļ����ع��ܡ�");
	            //return true;
			} catch (Exception ee) {
				log.error(
						"::�ļ����ع����д�::" + "error::"+ee.getMessage());
				//return false;
				retCode = -1;
			} finally{
				
	        }
		}
		return retCode;
	}
   
   public static int downLoad(String fileName,String filePath,HttpServletResponse response) throws Exception{
	    log.info("�ļ����ع��ܣ�");
		int retCode = 0;
		
		if(!"".equals(fileName))
		{
			Workbook workbook = null;
			File file = new File(filePath+fileName);
			
			try {
				//workbook = Workbook.getWorkbook(file); // //���������
				
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
	            
	            log.error("�����ļ����ع��ܡ�");
	            //return true;
			} catch (Exception ee) {
				log.error(
						"::�ļ����ع����д�::" + "error::"+ee.getMessage());
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
