<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.nl.util.GlobalUtil"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.integratedManage.util.AppConst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.nl.integratedManage.sc.ApprovalFlowSC"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="com.nl.util.SessionData"%>
<%@page import="com.nl.util.SessionConst"%>  


<%
try
{
    //�ϴ�ͼƬ����
    // 1Ϊ��ҵ����ͼƬ��2ΪӪҵִ��ͼƬ��3Ϊ��ҵ�³�ͼƬ��4Ϊ��ҵ��������/�����շ��ƶ�ͼƬ��5Ϊ��ҵ��Ա���֤ͼƬ��6Ϊ��ҵ��Ա�Ͷ���ͬͼƬ��7Ϊ��ҵ��Ա��ְ�ļ�ͼƬ��8Ϊ��ҵ��Ա�籣֤��ͼƬ��
    // 9Ϊ��ҵ��Աְ��֤��ͼƬ��10Ϊ��ҵ������Ŀ��ͬ����ͼƬ��11Ϊ��ҵ������Ŀ��Ŀ��ͬ����֤��ͼƬ��12Ϊ��ҵ�ϱ��걨��ɨ���ͼƬ��13֤�������뱨��ͼƬ��14Ϊ���̱����׼֪ͨ�� 
    
    String companyInfoId = request.getParameter("companyInfoId");
    String applyOrderId = request.getParameter("applyOrderId");
    String fileType = request.getParameter("type");
    String auId = request.getParameter("auId");
    ApprovalFlowSC sc = new ApprovalFlowSC();
    Map<String, String> param = new HashMap<String, String>();
    param.put("type",fileType);
    param.put("auId",auId);    
    param.put("companyId",companyInfoId);        
    param.put("orderSeq",applyOrderId);
    
	String savePath = GlobalConst.FILE_PATH + GlobalConst.UPLOAD_TMP_FILE_PATH + "/" ;
    
	// ���ļ��ϴ���������ָ��λ�ã�����ǰ̨�����ļ���
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List fileList = null;
		// �ļ����ͽ���request
		fileList = upload.parseRequest(request);
		Iterator it = fileList.iterator();
		List<File>  ft = new ArrayList<File>();
		
		while (it.hasNext()) {
			String extName = "";
			FileItem item = (FileItem) it.next();
			if (!item.isFormField()) {
				int i=1;
				String name = item.getName();
				String type = item.getContentType();
				if (item.getName() == null
						|| item.getName().trim().equals("")) {
					continue;
				}
				File file = new File(savePath + name);
				
				// ���ļ����뱾�ط�����
				item.write(file);
				ft.add(file);
				//�ϴ���ftp,�����ط������ϵ��ļ��ϴ���ftp��ȥ������ɾ�����ط������ϵ��ļ�
				param.put("fileOrder",""+i);	
				param.put("fileOrderName",""+i);	
				sc.saveFile(new FileInputStream(file),item.getName(),param);
				i++;
			}
		}
		
	
}catch(Exception e){
	throw new Exception();
}
    
    
    
    
    
    
%>    