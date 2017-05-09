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
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.nl.base.utils.GlobalFunc;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class JxlTool {
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
			wcfBold.setWrap(true);
			wcfBlack.setWrap(true);
			wcfNormal.setWrap(true);

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
	
	
	
	
	
}
