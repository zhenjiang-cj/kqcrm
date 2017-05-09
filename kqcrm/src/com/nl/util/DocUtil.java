package com.nl.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.ws.Endpoint;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;


public class DocUtil {
	
	/**
	 * ��ȡword�ĵ�
	 * @param filename�������ļ���ַ
	 * @return
	 * @throws Exception
	 */
	public static void getDoc(String filename,String outfile) throws Exception{
		//filename:�ļ�������ַ "C:\\Users/Think/Desktop/test.doc"
		InputStream is = new FileInputStream(filename);  
		HWPFDocument doc = new HWPFDocument(is); 
		Range range = doc.getRange();
		
		range.replaceText("${num}", "123");  
		
		doc.write(new FileOutputStream(outfile));  
	}
	
	
	public static void main(String[] args) throws Exception {
		try {  
//			InputStream is = new FileInputStream("C:\\Users/Think/Desktop/test.doc");  
//		      HWPFDocument doc = new HWPFDocument(is);  
//		      //����ı�  
////		      System.out.println(doc.getDocumentText());
//		      //�ı�
//		      Range range = doc.getRange();
//		      //����(һ�о���һ������)
//		      int paraNum = range.numParagraphs();  
//		      System.out.println(paraNum);  
//		      for (int i=0; i<paraNum; i++) {  
//		    	  range.getSection(0);
////		       this.insertInfo(range.getParagraph(i));  
//		         System.out.println("����" + (i+1) + "��" + range.getParagraph(i).text());  
////		         if (i == (paraNum-1)) {  
////		            this.insertInfo(range.getParagraph(i));  
////		         }  
//		      }
//		      range.insertBefore("������ţ�");
//		      //range.delete();
//		      range.getParagraph(0).setFontAlignment(0);
//		      range.getParagraph(0).setFirstLineIndent(0);
//		      //��
//		      int secNum = range.numSections();  
//		      System.out.println(secNum);  
//		      Section section = range.getSection(0); 
//		      section.insertAfter("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		         System.out.println(section.getMarginLeft());  
//	         System.out.println(section.getMarginRight());  
//	         System.out.println(section.getMarginTop());  
//	         System.out.println(section.getMarginBottom());  
//	         System.out.println(section.getPageHeight());  
//	         System.out.println(section.text());  
////		      DocUtil du = new DocUtil();
////		      du.testReadByDoc();
//
//		      doc.write(new FileOutputStream("C:\\Users/Think/Desktop/text.doc"));  
			DocUtil.getDoc("C:\\Users/Think/Desktop/���.doc", "C:\\Users/Think/Desktop/text.doc");
			
		      
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
	}
	
	public void testReadByDoc() throws Exception {  
	      InputStream is = new FileInputStream("C:\\Users/Think/Desktop/test.doc");  
	      HWPFDocument doc = new HWPFDocument(is);  
	      //�����ǩ��Ϣ  
	      this.printInfo(doc.getBookmarks());  
	      //����ı�  
	      System.out.println(doc.getDocumentText());  
	      Range range = doc.getRange();  
//	    this.insertInfo(range);  
	      this.printInfo(range);  
	      //�����  
	      this.readTable(range);  
	      //���б�  
	      this.readList(range);  
	      //ɾ��range  
	      Range r = new Range(2, 5, doc);  
	      r.delete();//���ڴ��н���ɾ���������Ҫ���浽�ļ�����Ҫ�ٰ���д���ļ�  
	      //�ѵ�ǰHWPFDocumentд���������  
	      doc.write(new FileOutputStream("C:\\Users/Think/Desktop/text.doc"));  
	      this.closeStream(is);  
	   }  
	    
	   /** 
	    * �ر������� 
	    * @param is 
	    */  
	   private void closeStream(InputStream is) {  
	      if (is != null) {  
	         try {  
	            is.close();  
	         } catch (IOException e) {  
	            e.printStackTrace();  
	         }  
	      }  
	   }  
	    
	   /** 
	    * �����ǩ��Ϣ 
	    * @param bookmarks 
	    */  
	   private void printInfo(Bookmarks bookmarks) {  
	      int count = bookmarks.getBookmarksCount();  
	      System.out.println("��ǩ������" + count);  
	      Bookmark bookmark;  
	      for (int i=0; i<count; i++) {  
	         bookmark = bookmarks.getBookmark(i);  
	         System.out.println("��ǩ" + (i+1) + "�������ǣ�" + bookmark.getName());  
	         System.out.println("��ʼλ�ã�" + bookmark.getStart());  
	         System.out.println("����λ�ã�" + bookmark.getEnd());  
	      }  
	   }  
	    
	   /** 
	    * ����� 
	    * ÿһ���س�������һ�����䣬���Զ��ڱ����ԣ�ÿһ����Ԫ�����ٰ���һ�����䣬ÿ�н�������һ�����䡣 
	    * @param range 
	    */  
	   private void readTable(Range range) {  
	      //����range��Χ�ڵ�table��  
	      TableIterator tableIter = new TableIterator(range);  
	      Table table;  
	      TableRow row;  
	      TableCell cell;  
	      while (tableIter.hasNext()) {  
	         table = tableIter.next();  
	         int rowNum = table.numRows();  
	         for (int j=0; j<rowNum; j++) {  
	            row = table.getRow(j);  
	            int cellNum = row.numCells();  
	            for (int k=0; k<cellNum; k++) {  
	                cell = row.getCell(k);  
	                //�����Ԫ����ı�  
	                System.out.println(cell.text().trim());  
	            }  
	         }  
	      }  
	   }  
	    
	   /** 
	    * ���б� 
	    * @param range 
	    */  
	   private void readList(Range range) {  
	      int num = range.numParagraphs();  
	      Paragraph para;  
	      for (int i=0; i<num; i++) {  
	         para = range.getParagraph(i);  
	         if (para.isInList()) {  
	            System.out.println("list: " + para.text());  
	         }  
	      }  
	   }  
	    
	   /** 
	    * ���Range 
	    * @param range 
	    */  
	   private void printInfo(Range range) {  
	      //��ȡ������  
	      int paraNum = range.numParagraphs();  
	      System.out.println(paraNum);  
	      for (int i=0; i<paraNum; i++) {  
//	       this.insertInfo(range.getParagraph(i));  
	         System.out.println("����" + (i+1) + "��" + range.getParagraph(i).text());  
	         if (i == (paraNum-1)) {  
	            this.insertInfo(range.getParagraph(i));  
	         }  
	      }  
	      int secNum = range.numSections();  
	      System.out.println(secNum);  
	      Section section;  
	      for (int i=0; i<secNum; i++) {  
	         section = range.getSection(i);  
	         System.out.println(section.getMarginLeft());  
	         System.out.println(section.getMarginRight());  
	         System.out.println(section.getMarginTop());  
	         System.out.println(section.getMarginBottom());  
	         System.out.println(section.getPageHeight());  
	         System.out.println(section.text());  
	      }  
	   }  
	    
	   /** 
	    * �������ݵ�Range������ֻ��д���ڴ��� 
	    * @param range 
	    */  
	   private void insertInfo(Range range) {  
	      range.insertAfter("Hello");  
	   } 

}
