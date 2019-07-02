<%@ page import="Bean.UserBean" %><%--
Created by IntelliJ IDEA.
User: acm-19
Date: 2019/3/26
Time: 10:14
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"/>
<html lang="zh">
<meta content=”text/html;charset=utf-8″/>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8"/>
<link rel="stylesheet" type="text/css" href="./main/css/style.css"/>
<link rel="stylesheet" type="text/css" href="./main/css/main.css"/>
<script language="JavaScript" src="js/timer.js"></script>
<title>bnc-site</title>
</head>

<body class="bk">
    <% session.setAttribute("log_status", null);%>
    <% session.setAttribute("find_status", null);%>
    <% session.setAttribute("change_status", null);%>
    <% session.setAttribute("re_status", null);%>
    <%
        user = (UserBean) session.getAttribute("User");
        if(user != null){
            if (user.getUserName().equals("admin")){
                response.sendRedirect("admin/");
            }
            else{
                response.sendRedirect("main/");
            }
        }
    %>
    <div id="container">
        <div id="header">
            <a style="text-decoration: none;" class="login" href="./login/find_pwd.jsp">忘记密码</a>
            <a style="text-decoration: none;" class="login" href="./login/register.jsp">注册&emsp;</a>
            <a style="text-decoration: none;" class="login" href="./login/">登录&emsp;</a>

        </div>
        　　<div id="page"></div>
        　　<div id="footer">
                <div id="show_time"></div>
                <div class="div_beian">
                    <!--img src="http://www.beian.gov.cn/img/ghs.png" alt="备案标识" /><a  class="beiAnUrl" href="http://www.miitbeian.gov.cn/" target="_blank">浙ICP备19010350号</a-->
                    <b class="beiAnUrl">&copy;  2019 bnc </b>
                    <b class="beiAnUrl"> Email:1010744256@qq.com</b>
                </div>
             </div>
    </div>
</body>

</html>
