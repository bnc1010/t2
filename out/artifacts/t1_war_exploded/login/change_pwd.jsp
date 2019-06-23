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
    <link href="formStyle.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
    user = (UserBean) session.getAttribute("User");
%>
<div class="login" style="width: 300px;height: 250px;">
    <h1>找回密码</h1>
    <form method="post" action="/ChangePwdServlet" style="height: 225px">
        <input type="text" name="user" value=<%=user.getUserName()%> readonly="readonly"/>
        <input type="password" name="pwd1" placeholder="新密码" required="required" />
        <input type="password" name="pwd2" placeholder="重复密码" required="required" />
        <input type="submit" class="btn btn-primary btn-block btn-large" value="确定">
    </form>
        <%
            String status = (String) session.getAttribute("change_status");
            if (status != null){%>
                <h4><%=status%></h4>
        <%
            }
        %>
</div>
<div style="width: 70px;">
    <a href="../" style="text-decoration: none;"><h1>返回</h1></a>
</div>
<iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
</body>
</html>
