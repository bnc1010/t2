<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="usr" class="Bean.UserBean" scope="page"></jsp:useBean>
<html lang="zh">
<meta content=”text/html;charset=utf-8″ />
<head>
<link href="style.css" type="text/css" rel="stylesheet">
<link href="formStyle.css" type="text/css" rel="stylesheet">
</head>

<body>
<% session.setAttribute("re_status", null);%>
<% session.setAttribute("find_status", null);%>

<div class="login" style="width: 300px;height: 250px;">
    <h1>登录</h1>
    <form method="post" action="/LoginServlet" style="height: 130px">
        <input type="text" name="user" placeholder="用户名" required="required" />
        <input type="password" name="pwd" placeholder="密码" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">登录</button>
    </form>
    <br/>
    <% String status = (String)session.getAttribute("log_status");
            if (status != null){%>
                <h4><%=status%></h4>
        <%
            }
        %>
</div>
<div style="width: 70px;">
    <a href="../" style="text-decoration: none;"><h1>返回</h1></a>
</div>
</body>
</html>

