<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.nl.util.GlobalUtil"%>
<%@page import="com.nl.util.GlobalConst"%>
<%@page import="com.nl.company.util.AppConst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.nl.company.sc.CompanySc"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileUpload"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.RequestContext"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletRequestContext"%>
<%
try
{
    //String param = request.getParameter("q");
    //�ϴ�ͼƬ����
    // 1Ϊ��ҵ����ͼƬ��2ΪӪҵִ��ͼƬ��3Ϊ��ҵ�³�ͼƬ��4Ϊ��ҵ��������/�����շ��ƶ�ͼƬ��5Ϊ��ҵ��Ա���֤ͼƬ��6Ϊ��ҵ��Ա�Ͷ���ͬͼƬ��7Ϊ��ҵ��Ա��ְ�ļ�ͼƬ��8Ϊ��ҵ��Ա�籣֤��ͼƬ��
    // 9Ϊ��ҵ��Աְ��֤��ͼƬ��10Ϊ��ҵ������Ŀ��ͬ����ͼƬ��11Ϊ��ҵ������Ŀ��Ŀ��ͬ����֤��ͼƬ��12Ϊ��ҵ�ϱ��걨��ɨ���ͼƬ��13֤�������뱨��ͼƬ��14Ϊ���̱����׼֪ͨ�� 
    String param_type = request.getParameter("up");
    String param_companyid = request.getParameter("companyid");
    String param_orderid = request.getParameter("orderid");
    String param_operid = request.getParameter("operid");
    //String param_fileid = request.getParameter("fileid");
    //System.out.println("%%%%"+param_type);
    CompanySc sc = new CompanySc();
    Map<String, String> param = new HashMap<String, String>();
    param.put("type",param_type);
    param.put("companyid",param_companyid);
    param.put("orderid",param_orderid);
    param.put("operid",param_operid);
    //param.put("fileid",param_fileid);
	//String savePath = request.getSession().getServletContext().getRealPath("");

	String savePath = GlobalConst.FILE_PATH + AppConst.UPLOAD_TMP_FILE_PATH + "/" ;
	//savePath = savePath + "\\data\\"; 
    
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
				param.put("fileNumber",""+i);
				sc.saveFile(new FileInputStream(file),item.getName(),param);
				i++;
			}
		}
		//for(int i = 0;i<ft.size();i++){
		//	File item = ft.get(i);
			//�ϴ���ftp,�����ط������ϵ��ļ��ϴ���ftp��ȥ������ɾ�����ط������ϵ��ļ�
		//	sc.saveFile(new FileInputStream(item),item.getName(),param);
		//}
		
	
}catch(Exception e){
//	throw new Exception(AppConst.CENTERTASK_ERROR_INFO);
	throw new Exception();
}
    
    

%>    