<%@ page import="javax.print.DocFlavor" %><%--
  Created by IntelliJ IDEA.
  User: acm-19
  Date: 2019/3/28
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta content=”text/html;charset=utf-8″ />
<head>
    <title>找回密码</title>
    <link href="style.css" type="text/css" rel="stylesheet">
    <link href="formStyle.css" type="text/css" rel="stylesheet">
    <script language="JavaScript" src="js2.js"></script>
</head>
<body>

<div class="login" style="width: 300px;height: 250px;">
    <h1>邮箱验证</h1>
    <form method="post" action="/FindPwdServlet" style="height: 225px">
        <input type="text" id="ee" name="email" placeholder="邮箱" required="required" />
        <input type="text" name="num" placeholder="邮箱验证码" required="required" />
        <input type="button" value="获取验证码" onclick="settime(this)" />
        <input type="submit" class="btn btn-primary btn-block btn-large" value="确定">
    </form>
    <br/>
        <%
            String status = (String)session.getAttribute("find_status");
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
