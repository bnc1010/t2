<!DOCTYPE html>
<%@ page import="Bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"/>

<html>
	<head>
		<meta content=”text/html;charset=utf-8″/>
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>后台管理</title>
		<link rel="stylesheet" type="text/css" href="../../static/admin/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="../../static/admin/css/admin.css"/>
		<%
			user = (UserBean) session.getAttribute("User");
			if (user == null || !user.getUserName().equals("admin")){
				user = new UserBean();
				user.setUserName("未登录");
				response.sendRedirect("/");
			}
		%>
	</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--侧边栏-->
			<div class="main-layout-side">
				<div class="m-logo">
				</div>
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				  <li class="layui-nav-item layui-nav-itemed">
				    <a href="javascript:;"><i class="iconfont">&#xe7f4;</i>菜单管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="menu1.jsp" data-id='1' data-text="后台菜单"><span class="l-line"></span>后台菜单</a></dd>
				      <dd><a href="javascript:;" data-url="menu2.jsp" data-id='2' data-text="前台菜单"><span class="l-line"></span>前台菜单</a></dd>
				    </dl>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe7c3;</i>管理</a>
				    <dl class="layui-nav-child">
						<dd><a href="javascript:;" data-url="user-list.jsp" data-id='9' data-text="用户管理"><span class="l-line"></span>用户管理</a></dd>
						<dd><a href="javascript:;" data-url="article-list.jsp" data-id='3' data-text="博客管理"><span class="l-line"></span>博客管理</a></dd>
						<dd><a href="javascript:;" data-url="file-list.jsp" data-id='6' data-text="文件管理"><span class="l-line"></span>文件管理</a></dd>
					</dl>
				  </li>
				   <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xeb97;</i>友情链接</a>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="email.jsp" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe64b;</i>邮件系统</a>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="admin-info.jsp" data-id='5' data-text="个人信息"><i class="iconfont">&#xe7ae;</i>个人信息</a>
				  </li>
				  <li class="layui-nav-item">
				  	<a href="javascript:;" data-url="system.jsp" data-id='6' data-text="系统设置"><i class="iconfont">&#xe78e;</i>系统设置</a>
				  </li>
				</ul>
			</div>
			<!--右侧内容-->
			<div class="main-layout-container">
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe792;</span>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item"><a href="javascript:;" data-url="email.jsp" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe64b;</i></a></li>
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-url="admin-info.jsp" data-id='5' data-text="个人信息"><%=user.getUserName()%></a>
					  </li>
					  <li class="layui-nav-item"><a href="../../LogoutServlet">退出</a></li>
					</ul>
				</div>
				<!--主体内容-->
				<div class="main-layout-body">
					<!--tab 切换-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">后台主页</li>
					  </ul>
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<iframe src="welcome.jsp" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
					    	<!--1end-->
					    </div>
					  </div>
					</div>
				</div>
			</div>
			<!--遮罩-->
			<div class="main-mask">
				
			</div>
		</div>
		<script type="text/javascript">
			var scope={
				link:'./welcome.jsp'
			}
		</script>
		<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
		
	</body>
</html>
