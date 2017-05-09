package com.nl.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * ��ҳ��ʾ������ʵ���࣬����ʵ��ҳ�������һҳ����һҳ�Ȳ����Ķ��壬�Լ��Ϸ���У�����ʾ
 * 
 * @author xuejing
 * @modify shiyj
 */
public class PageListTag extends BodyTagSupport {

	private static final long serialVersionUID = 5770740594458274871L;

	/** ��ǰҳ */
	private int currentPage = 1;

	/** ���ӵ�URL */
	private String linkUrl = "";

	/** ��¼���� */
	private int resultSum = 0;

	/** ÿҳ��ʾ������,Ĭ��ÿҳ��ʾ10�� */
	private int pageDisplayNum = 10;
	
	/*
	 * session
	 */
	private SessionData sessionData;
	
	/**��ҳ��ʾ����ʽ���ͣ�Ĭ��Ϊmeneame
	 �ɹ�ѡ����ʽ�����У�
	 digg,yahoo,yahoo2,meneame,flickr,sabrosus,scott,quotes,black,black2,
     black-red,grayr,yellow,jogger,starcraft2,tres,megas512,technorati,
     youtube,msdn,badoo,manu,green-black,viciao
	 */
	private String styleClass = "meneame";
	
	/**ǰ��ҳ�Ƿ���ʾ����,Ĭ�ϲ���ʾ*/
	private boolean aroundType = false;

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}
	

	@Override
	public int doEndTag() throws JspException {
		String out = "";
		if (pageDisplayNum == 0)
			pageDisplayNum = 10;
		out = showPageOne(currentPage, linkUrl, resultSum, pageDisplayNum,styleClass);
		try {
			pageContext.getOut().print(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * ��֯ҳ����ʾ��Ҫ��HTML����
	 * 
	 * @param currentPage
	 * @param linkUrl
	 * @param resultSum
	 * @param pageDisplayNum
	 * @return
	 */
	protected String showPageOne(int currentPage, String linkUrl, int resultSum,
			int pageDisplayNum,String styleClass) 
	{
		StringBuffer buf = new StringBuffer();
		String strUrl = null;
		//������ʽ
    	String zz ="/[^"+"\\"+"d]/g";
		strUrl = JoinChar(linkUrl);// ��֯URL
		// tms pageListTagҳ�沿��
		
		//����������Ϊ0
		if (resultSum != 0)
		{
			int totalPage = resultSum % pageDisplayNum == 0 ? resultSum
					/ pageDisplayNum : resultSum / pageDisplayNum + 1;
				
				//buf.append("<div class='pages_btns' align='center' style='padding-left: 25px'> ");
				//buf.append("�ܹ�"+ resultSum + "��<b>&nbsp;</b>");
			    buf.append(currentPage + "/" + totalPage + "ҳ�� ��"+resultSum+"����¼&nbsp;&nbsp;&nbsp;&nbsp;");
				
			//totalpage����1��չʾ	
				// ��һҳ
				if (currentPage == 1)
				{
					buf.append("                     <strong>[��ҳ]</strong>&nbsp;&nbsp;");	
					buf.append("                      <strong>��һҳ</strong>&nbsp;&nbsp;");
				} 
				else 
				{
					buf.append("                     <a href='" + strUrl + "currentPage=" +(1)+ "&startNum=0" +"&endNum=" +(1*pageDisplayNum) + "'><strong>[��ҳ]</strong></a>&nbsp;&nbsp;");
					buf.append("                   <a href='" + strUrl + "currentPage=" + (currentPage - 1) + "&startNum=" + ((currentPage-2)*pageDisplayNum) +"&endNum=" +((currentPage-1)*pageDisplayNum) + "'><strong>��һҳ</strong></a>&nbsp;&nbsp;");
					
					
				}
//			    /**************************************/
//			    if(totalPage <= 7)
//			    {
//		            for (int i = 0; i < totalPage; i++)
//		            {
//		            		if((i+1)==currentPage)
//		  	                {
//		  	                	buf.append("<span class='current'>" + currentPage + "</span>	");
//		  	                    i = i+1;
//		  	                    if(currentPage==totalPage)break;
//		  	                }
//		  	                buf.append("                     <a href='" + strUrl + "currentPage=" +(i+1) + "&startNum=" + ((i)*pageDisplayNum) +"&endNum=" +((i+1)*pageDisplayNum) + "'>" + (i+1) + "</a>	");	
//		            }
//		        }
//			    
//			    else if(totalPage <= 20)
//			    {
//		            //û�а�...����
//		            int l = 0;
//		            int r = 0;
//		            if(currentPage<5)
//		            {
//		                l=currentPage-1;
//		                r=10-l-1;
//		            }
//		            else if(totalPage-currentPage<5)
//		            {
//		                r=totalPage-currentPage;
//		                l=10-1-r;
//		            }
//		            else
//		            {
//		                l=4;
//		                r=5;
//		            }
//		            int tmp =  currentPage-l;
//		            //add 
//	            	for (int i = tmp; i < tmp+10; i++)
//	  	            {
//	  	            		 if(i==currentPage )
//	  	 	                {
//	  	 	                	buf.append("<span class='current'>" + currentPage + "</span>	");
//	  	 	                    i = i+1;
//	  	 	                    //add
//	  	 	                    //if(tmp+10==totalPage) break;
//	  	 	                    if(currentPage==totalPage) break;
//	  	 	                    
//	  	 	                }
//	  	            		 if(i<=totalPage && i>0)
//	  	            		 {
//	  	            			 buf.append("                     <a href='" + strUrl + "currentPage=" +(i) + "&startNum=" + ((i-1)*pageDisplayNum) +"&endNum=" +((i)*pageDisplayNum) + "'>" + (i) + "</a>	");	
//	  	            		 }
//	  	            		 //buf.append("                     <a href='" + strUrl + "currentPage=" +(i) + "'>" + (i) + "</a>	");	
//	  	            }
//		          
//		           // buf.append("...");
//		            //buf.append("                     <a href='" + strUrl + "currentPage=" +(totalPage-1) + "'>" + (totalPage-1) + "</a>	");	
//		           // buf.append("                     <a href='" + strUrl + "currentPage=" +(totalPage) + "'>" + (totalPage) + "</a>	");	
//		        }
//
//			    
//			    else if(currentPage<7)
//			    {
//		            for (int i = 0; i < 8; i++) 
//		            {
//		            		if(i+1==currentPage)
//		   	                {
//		   	                	buf.append("<span class='current'>" + currentPage + "</span>	");
//		   	                    i = i+1;
//		   	                }
//		   	                buf.append("                     <a href='" + strUrl + "currentPage=" +(i+1)+ "&startNum=" + ((i)*pageDisplayNum) +"&endNum=" +((i+1)*pageDisplayNum) + "'>" +(i+1) + "</a>	");	
//		            }
//		            buf.append("...");
//		            buf.append("                     <a href='" + strUrl + "currentPage=" +(totalPage-1) + "&startNum=" + ((totalPage-2)*pageDisplayNum) +"&endNum=" +((totalPage-1)*pageDisplayNum)+ "'>" +(totalPage-1) + "</a>	");	
//		            buf.append("                     <a href='" + strUrl + "currentPage=" +(totalPage) + "&startNum=" + ((totalPage-1)*pageDisplayNum) +"&endNum=" +((totalPage)*pageDisplayNum) + "'>" +(totalPage) + "</a>	");	
//		        }
//
//			    else if(currentPage>totalPage-6)
//			    {
//			    	 buf.append("                     <a href='" + strUrl + "currentPage=" +(1) + "&startNum=" + (0*pageDisplayNum) +"&endNum=" +(1*pageDisplayNum) + "'>" +(1) + "</a>	");	
//			    	 buf.append("                     <a href='" + strUrl + "currentPage=" +(2) + "&startNum=" + (1*pageDisplayNum) +"&endNum=" +(2*pageDisplayNum)+ "'>" +(2) + "</a>	");	
//			    	 buf.append("...");
//		            for (int i = totalPage-8; i <totalPage ; i++)
//		            {
//		            		if(i+1==currentPage)
//		  	                {
//		  	                	buf.append("<span class='current'>" + currentPage + "</span>	");
//		  	                    i = i+1;
//		  	                    if(currentPage==totalPage) break;
//		  	                }
//		  	                buf.append("                     <a href='" + strUrl + "currentPage=" +(i+1) + "&startNum=" + ((i)*pageDisplayNum) +"&endNum=" +((i+1)*pageDisplayNum) + "'>" +(i+1) + "</a>	");	
//		            }
//		        }
//			    
//			    else
//			    {
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(1) + "&startNum=" + ((1-1)*pageDisplayNum) +"&endNum=" +((1)*pageDisplayNum)+ "'>" +(1) + "</a>	");
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(2) + "&startNum=" + ((2-1)*pageDisplayNum) +"&endNum=" +((2)*pageDisplayNum)+ "'>" +(2) + "</a>	");
//			    	buf.append("...");
//		            
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(currentPage-2) + "&startNum=" + ((currentPage-3)*pageDisplayNum) +"&endNum=" +((currentPage-2)*pageDisplayNum)+ "'>" +(currentPage-2) + "</a>	");
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(currentPage-1) + "&startNum=" + ((currentPage-2)*pageDisplayNum) +"&endNum=" +((currentPage-1)*pageDisplayNum)+ "'>" +(currentPage-1) + "</a>	");
//			    	buf.append("<span class='current'>" + currentPage + "</span>	");
//			    	
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(currentPage+1) + "&startNum=" + ((currentPage)*pageDisplayNum) +"&endNum=" +((currentPage+1)*pageDisplayNum)+ "'>" +(currentPage+1) + "</a>	");
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(currentPage+2) + "&startNum=" + ((currentPage+1)*pageDisplayNum) +"&endNum=" +((currentPage+2)*pageDisplayNum)+ "'>" +(currentPage+2) + "</a>	");
//		            
//			    	buf.append("...");
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(totalPage-1) + "&startNum=" + ((totalPage-2)*pageDisplayNum) +"&endNum=" +((totalPage-1)*pageDisplayNum)+ "'>" +(totalPage-1) + "</a>	");
//			    	buf.append("                     <a href='" + strUrl + "currentPage=" +(totalPage) + "&startNum=" + ((totalPage-1)*pageDisplayNum) +"&endNum=" +((totalPage)*pageDisplayNum)+ "'>" +(totalPage) + "</a>	");
//		        }  

			    /*******************************************/
			    
				// ���һҳ
				if (currentPage == totalPage) {
					buf.append("                      <strong>��һҳ</strong>&nbsp;&nbsp;");
					buf.append("                      <strong>[��ҳ]</strong>&nbsp;&nbsp;");
				} else {
					buf.append("                      <a href='" + strUrl + "currentPage=" + (currentPage + 1) + "&startNum="  + ((currentPage)*pageDisplayNum) +"&endNum=" +((currentPage+1)*pageDisplayNum)+ "'><strong>��һҳ</strong></a>&nbsp;&nbsp;");
					buf.append("                     <a href='" + strUrl + "currentPage=" +totalPage + "&startNum="  + ((totalPage-1)*pageDisplayNum) +"&endNum=" +((totalPage)*pageDisplayNum) + "'><strong>[ĩҳ]</strong></a>&nbsp;&nbsp;");	
				}

				//������ҳ
				//buf.append("��"+totalPage+"ҳ ÿҳ"+pageDisplayNum+"��");
		    /**********************************֧��ֱ������������תҳ�� add  by gej******************************************************/
			    
			    
			    
				buf.append("<strong>����</strong>");
				buf.append("<input type='text' id='fy' name='selectPage' size='4'  value='' onkeyup=\"javescript:this.value=this.value.replace("+zz+", '')\"><strong> ҳ</strong>&nbsp;&nbsp;");
				buf.append(" <input type='button' id='' name='' value='GO' onclick='goPage()' />");
				//buf.append(" </div>");
				
				//javascript 
				buf.append("<script type=\"text/javascript\">");
				buf.append("function goPage(){");
				buf.append("var t=document.getElementById('fy').value;");
				buf.append("if(t=='' || t=='0'){");
				buf.append("t='1';");
				buf.append("document.getElementById('fy').value=1;");
				buf.append("}");
				buf.append("if(t>"+totalPage+"){");
				buf.append("t='"+totalPage+"'");
				buf.append("}");
				buf.append("window.location.href='"+strUrl+"currentPage=\'+t");
				buf.append("+'&startNum=\'+(t-1)*" +pageDisplayNum + "+'&endNum=\'+t*" + (pageDisplayNum)+ ";");
				buf.append("}");
				buf.append("</script>");
				
			/**************************************************end********************************************************/
		}
		return (buf.toString());
	}

	/**
	 * ���ַ�м��� ? �� &
	 * 
	 * @param strUrl
	 *            ----��ַ.
	 * @return ���� ? �� & ����ַ.
	 */

	protected String JoinChar(String strUrl) {
		String result = "";
		if (strUrl.equals("") || strUrl.length() <= 0) {
			return result;
		}
		if (strUrl.indexOf("?") < strUrl.length()) {
			if (strUrl.indexOf("?") > -1) {
				if (strUrl.indexOf("&") < strUrl.length()) {
					result = strUrl + "&";
				} else {
					result = strUrl;
				}
			} else {
				result = strUrl + "?";
			}
		} else {
			result = strUrl;
		}
		return result;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageDisplayNum() {
		return pageDisplayNum;
	}

	public void setPageDisplayNum(int pageDisplayNum) {
		this.pageDisplayNum = pageDisplayNum;
	}

	public int getResultSum() {
		return resultSum;
	}

	public void setResultSum(int resultSum) {
		this.resultSum = resultSum;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public boolean isAroundType() {
		return aroundType;
	}

	public void setAroundType(boolean aroundType) {
		this.aroundType = aroundType;
	}


	public SessionData getSessionData() {
		return sessionData;
	}


	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}
}
