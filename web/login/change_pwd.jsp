<%@ page import="Bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: acm-19
  Date: 2019/4/3
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"/>
<html>
<head>
    <title>找回密码</title>
    <link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
    user = (UserBean) session.getAttribute("FUser");
%>
<div style="width: 70px;">
    <a href="../" class="back"><h1>返回</h1></a>
</div>
<div class="login_wrap">
    <h3>找回密码</h3>
    <div class="login_border">
        <div class="input">
            <form id="form_for_login">
                <ul class="items">
                    <li>
                        <input class="input_style" type="text" id="user" name="user" value=<%=user.getUserName()%> readonly="readonly"/>
                    </li>
                    <li>
                        <input class="input_style" type="password" id="pwd1" name="pwd1" placeholder="新密码"/>
                    </li>
                    <li>
                        <input class="input_style" type="password" id="pwd2" name="pwd2" placeholder="重复密码"/>
                    </li>
                    <li>
                        <input class="btn btn-primary" type="button" id="btn-submit" value="确定">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
<script src="../js/jquery-3.2.1.js"></script>
<script src="../js/layer/layer.js"></script>
<script src="../js/changepwd.js"></script>
</html>
