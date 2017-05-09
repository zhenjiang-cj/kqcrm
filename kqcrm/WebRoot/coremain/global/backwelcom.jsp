<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.nl.util.GlobalConst"%>
<div id="container" style="width: 828px;">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class=""><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" tabsPageHeaderContent-->
						<ul class="navTab-tab " style="left: 0px;">
							<li tabid="main" class="main selected"><a href="javascript:;"><span><span class="home_icon">首页</span></span></a></li>
						</ul>
					</div>			
					<div class="tabsLeft" style="display: none;">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight" style="display: none;">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li class="selected"><a href="javascript:;">首页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox mainContent" style="height: 538px;">
					<div class=" myBody page unitBox">
					<h2 class="contentTitle">&nbsp;</h2>
				<div>
				 <p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				 <p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<table width="500" border="0" align="center" cellpadding="0" cellspacing="0" class="myLogin">
				  <tbody><tr>
				    <td height="80" align="center">&nbsp;</td>
				  </tr>
				   <tr>
				    <td height="40" align="center"><h2 class="contentTitle">
				    <%
				    	try{
				    		String go = request.getSession().getAttribute(GlobalConst.LOGIN_GO).toString();
				    		if(go.equals("1")||go.equals("2"))
				    			out.println("欢迎登录企业资质申请系统");
				    		else if(go.equals("3"))
				    			out.println("欢迎登录企业资质审批系统");
				    		else if(go.equals("4"))
				    			out.println("欢迎登录权力事项前台系统");
				    		else if(go.equals("5"))
				    			out.println("欢迎登录权力事项后台管理系统");
				    		else if(go.equals("6"))
				    			out.println("欢迎登录双随机系统");
				    	}catch(Exception e){
				    		
				    		
				    	}
				    %>
				    
				    
				    </h2></td>
				  </tr> 
				    <tr>
				    <td height="89" align="center">
				      如您在使用过程中存在无法解决的问题, 请与本系统的技术支持单位联系。<br>
				        <br>
				    联系电话:0511-84425558</td>
				  </tr>
				</tbody></table>
				
				</div>
				</div>
								</div>
							</div>
						</div>