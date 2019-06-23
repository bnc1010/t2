<%@ page import="Bean.UserBean" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Bean.UserBean" scope="session"></jsp:useBean>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>网站后台管理模版</title>
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
		<div class="layui-tab page-content-wrap">
		  <ul class="layui-tab-title">
		    <li class="layui-this">修改资料</li>
		    <li>修改密码</li>
		  </ul>
		  <div class="layui-tab-content">
		    <div class="layui-tab-item layui-show">
		    	<form class="layui-form" style="width: 90%;padding-top: 20px;" action="/ad_ChangeEmail" method="post">
				  <div class="layui-form-item">
				    <label class="layui-form-label">ID：</label>
				    <div class="layui-input-block">
				      <input type="text" name="id" disabled autocomplete="off" class="layui-input layui-disabled" value="<%=user.getUid()%>">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">用户名：</label>
				    <div class="layui-input-block">
				      <input type="text" name="username" disabled autocomplete="off" class="layui-input layui-disabled" value="<%=user.getUserName()%>">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">邮箱：</label>
				    <div class="layui-input-block">
				      <input type="text" name="email" required  lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input" value="<%=user.getEmail()%>">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminInfo">提交</button>
				    </div>
				  </div>
				</form>
		    </div>
		    <div class="layui-tab-item">
		    	<form class="layui-form" v style="width: 90%;padding-top: 20px;" action="/ChangePwdServlet" method="post">
				  <div class="layui-form-item">
				    <label class="layui-form-label">用户名：</label>
				    <div class="layui-input-block">
				      <input type="text" name="username" disabled autocomplete="off" class="layui-input layui-disabled" value="admin">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">新密码：</label>
				    <div class="layui-input-block">
				      <input type="password" name="pwd1" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">重复密码：</label>
				    <div class="layui-input-block">
				      <input type="password" name="pwd2" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminPassword">提交</button>
				    </div>
				  </div>
				</form>
		    </div>
		  </div>
		</div>
	<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
	<script>
		//Demo
		layui.use(['form','element'], function(){
		  var form = layui.form();
		  form.render();
		});
	</script>
	</body>
</html>