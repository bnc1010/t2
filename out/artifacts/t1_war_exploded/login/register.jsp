<%@ page import="java.util.StringTokenizer" %><%--
  Created by IntelliJ IDEA.
  User: acm-19
  Date: 2019/3/27
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta content=”text/html;charset=utf-8″ />
<head>
    <title>注册</title>
    <link href="style.css" type="text/css" rel="stylesheet">
    <link href="formStyle.css" type="text/css" rel="stylesheet">
    <script language="JavaScript" src="js1.js"></script>
</head>
<body>

<div class="login" style="width: 300px;height: 300px;">
    <h1>注册</h1>
    <form method="post" action="/RegisterServlet" style="height: 272px">
        <input type="text" name="user" placeholder="用户名" required="required" />
        <input type="password" name="pwd1" placeholder="密码" required="required" />
        <input type="password" name="pwd2" placeholder="重复密码" required="required" />
        <input type="text" id="ee" name="email" placeholder="邮箱" required="required" />
        <input type="text" name="num" placeholder="邮箱验证码" required="required" />
        <input type="button" value="获取验证码" onclick="settime(this)" />
        <button type="submit" class="btn btn-primary btn-block btn-large">注册</button>
    </form>
    <br/>
    <%
        String status = (String)session.getAttribute("re_status");
        if (status != null){%>
        <h4><%=status%></h4>
        <%
            }
        %>
</div>
<iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
<div style="width: 70px;">
    <a href="../" style="text-decoration: none;"><h1>返回</h1></a>
</div>
</body>
</html>
