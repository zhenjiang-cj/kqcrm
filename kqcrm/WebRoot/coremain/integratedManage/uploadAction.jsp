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
    //上传图片类型
    // 1为企业资质图片，2为营业执照图片，3为企业章程图片，4为企业服务质量/服务收费制度图片，5为企业人员身份证图片，6为企业人员劳动合同图片，7为企业人员任职文件图片，8为企业人员社保证明图片，
    // 9为企业人员职称证书图片，10为物业服务项目合同材料图片，11为物业服务项目项目合同备案证明图片，12为企业上报申报表扫描件图片，13证书变更申请报告图片，14为工商变更核准通知书 
    
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
    
	// 把文件上传到服务器指定位置，并向前台返回文件名
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List fileList = null;
		// 文件类型解析request
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
				
				// 将文件存入本地服务器
				item.write(file);
				ft.add(file);
				//上传到ftp,将本地服务器上的文件上传到ftp上去，并且删除本地服务器上的文件
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