<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>测试</title>
	<link rel="stylesheet" type="text/css" href="css/zui.css">
	<link rel="stylesheet" type="text/css" href="css/theme/zui-theme-green.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">

	<script type="text/javascript" src="lib/jquery/jquery.js"></script>
	<script type="text/javascript" src="js/zui.js"></script>
</head>

<body>
<div class="main">
	<header class="header">
		<div class="header-left">
			<div class="sidebar-toggle"><i class="icon icon-bars"></i></div>
			<div class="logo">z-<span class="text-primary">admin</span></div>
		</div>
		<div class="header-search">
			<form method="post">
				<div class="search-group">
					<i class="icon icon-search"></i>
					<input type="text" class="form-control" placeholder="搜点什么吧...">
				</div>
			</form>
		</div>
		<div class="header-user">
			<div class="item dropdown">
				<span class="badge label label-dot label-danger"></span>
				<i class="icon icon-comments"></i>
			</div>
			<div class="item dropdown">
				<span class="badge label label-dot label-warning"></span>
				<i class="icon icon-bell-alt"></i>
			</div>
			<div class="item dropdown">
				<span data-toggle="dropdown">
					<i class="icon icon-user"></i>
					<span>当前用户</span>
					<span class="caret"></span>
				</span>
				<ul class="dropdown-menu pull-right">
      				<li>
			            <a href="#">修改头像</a>
			          </li>
		          	<li>
		            	<a href="#">修改资料</a>
		          	</li>
          <li class="divider"></li>
          <li>
            <a href="#">注销</a>
          </li>
        </ul>
			</div>
		</div>
	</header>
	<aside class="sidebar">
		<div class="sidebar-menu">
			<div class="item">
				<a href="#">
					<i class="icon icon-dashboard"></i>
					<span>主页</span>
				</a>
			</div>
			<div class="item">
				<a href="#">
					<i class="icon icon-bell-alt"></i>
					<span>消息中心</span>
				</a>
			</div>
			<div class="item vertical">
				<a href="#">
					<i class="icon icon-cogs"></i>
					<span>网站设置</span>
					<span class="arrow"></span>
				</a>
				<div class="vertical-menu">
											<a href="#">分类一</a>
						<a href="#">分类二</a>
						<a href="#">分类三</a>
									</div>
			</div>
			<div class="item vertical open active">
				<a href="#">
					<i class="icon icon-server"></i>
					<span>栏目管理</span>
					<span class="arrow"></span>
				</a>
				<div class="vertical-menu">
					<a href="#">分类一</a>
					<a href="#" class="active">分类二</a>
					<a href="#">分类三</a>
				</div>
			</div>
			<div class="item vertical">
				<a href="#">
					<i class="icon icon-user"></i>
					<span>会员管理</span>
					<span class="arrow"></span>
				</a>
				<div class="vertical-menu">
					<a href="#">分类一</a>
					<a href="#">分类二</a>
					<a href="#">分类三</a>
				</div>
			</div>
			<div class="item vertical">
				<a href="#">
					<i class="icon icon-shopping-cart"></i>
					<span>商城管理</span>
					<span class="arrow"></span>
				</a>
				<div class="vertical-menu">
					<a href="#">分类一</a>
					<a href="#">分类二</a>
					<a href="#">分类三</a>
				</div>
			</div>
			<div class="item vertical">
				<a href="#">
					<i class="icon icon-cubes"></i>
					<span>插件管理</span>
					<span class="arrow"></span>
				</a>
				<div class="vertical-menu">
					<a href="#">分类一</a>
					<a href="#">分类二</a>
					<a href="#">分类三</a>
				</div>
			</div>
		</div>
	</aside>
	<div class="main-content">
		<div class="wrapper">
			<ol class="breadcrumb">
  				<li><a href="#"><i class="icon icon-home"></i> 主页</a></li>
  				<li><a href="#">栏目管理</a></li>
  				<li class="active">栏目列表</li>
			</ol>
			<div class="panel">
				<div class="panel-heading"><h5>栏目管理</h5></div>
				<div class="panel-body">
					<div class="pull-right"><button class="btn btn-primary">添加栏目</button></div>
					<form action="#" method="post">
						<div class="search">
   							搜索名称：
   								<input type="text" class="text" name="topic" placeholder="搜索名称" style="width:150px;">
   							&nbsp;搜索分类：
   								<div class="btn-group btn-dropdown">
   									<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown">
   										<span class="drop-topic">请选择类别</span> <span class="caret"></span>
   									</button>
   									<ul class="dropdown-menu">
   										<li><a href="#">请选择类别</a></li>
   										<li class="divider"></li>
   										<li><a href="#">分类一</a></li>
   										<li><a href="#">分类二</a></li>
   									</ul>
   								</div>   
   							&nbsp;是否启用：
   								<div class="btn-group btn-dropdown">
   									<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown">
   										<span class="drop-topic">请选择</span> <span class="caret"></span>
   									</button>
   									<ul class="dropdown-menu">
   										<li><a href="#">请选择</a></li>
   										<li class="divider"></li>
   										<li><a href="#">启用</a></li>
   										<li><a href="#">禁用</a></li>
   									</ul>
   								</div>
   							&nbsp;<button type="submit" value="查询" class="btn btn-primary-outline btn-xs"><i class="icon-search"></i>&nbsp;查询</button>  
   						</div>
					</form>
					<table class="table table-bordered table-hover table-striped">
				        <thead>
				          	<tr>
				          		<th width="40" class="text-center"><input type="checkbox" name="checkbox"></th>
					            <th width="50" class="text-center">ID</th>
					            <th>栏目名称</th>
					            <th width="200" class="text-center">上级栏目</th>
					            <th width="100" class="text-center">是否启用</th>
					            <th width="150" class="text-center">更新日期</th>
					            <th width="140" class="text-center">操作</th>
				          	</tr>
				        </thead>
				        <tbody>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				          	<tr>
					          	<td class="text-center"><input type="checkbox" name="checkbox"></td>
					            <td class="text-center">1</td>
					            <td>这是栏目名称</td>
					            <td class="text-center">--</td>
					            <td class="text-center">
									<div class="btn-group">
          								<button type="button" class="btn btn-xs btn-primary">启用</button>
          								<button type="button" class="btn btn-xs">禁用</button>
        							</div>
					            </td>
					            <td class="text-center">2016-06-05 16:20</td>
					            <td class="text-center">
									<button type="button" class="btn btn-sm btn-success"><i class="icon-edit"></i> 编辑</button>
									<button type="button" class="btn btn-sm btn-danger"><i class="icon-trash"></i> 删除</button>
					            </td>
				          	</tr>
				        </tbody>
				        <tfoot>
							<tr>
				          		<th width="40" class="text-center"><input type="checkbox" name="checkbox"></th>
					            <th colspan="8">
									<button type="button" class="btn btn-danger"><i class="icon-trash"></i> 删除</button>
									<div class="pull-right">
										<ul class="pager">
  											<li class="previous"><a href="#">«</a></li>
										  	<li><a href="#">1</a></li>
										  	<li class="active"><a href="#">2</a></li>
										  	<li><a href="#">3</a></li>
										  	<li><a href="#">...</a></li>
										  	<li><a href="#">10</a></li>
										  	<li class="next"><a href="#">»</a></li>
										</ul>
									</div>
					            </th>
				          	</tr>
				        </tfoot>
				    </table>
				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript" src="js/admin.js"></script>
</body>
</html>
