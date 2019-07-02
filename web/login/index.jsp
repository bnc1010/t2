<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="usr" class="Bean.UserBean" scope="page"></jsp:useBean>
<html lang="zh">
<meta content=”text/html;charset=utf-8″ />
<head>
    <link href="style.css" type="text/css" rel="stylesheet">
</head>
<body >
<% session.setAttribute("re_status", null);%>
<% session.setAttribute("find_status", null);%>
<div style="width: 70px;">
    <a href="../" class="back"><h1>返回</h1></a>
</div>
<div class="login_wrap">
    <h3>登录</h3>
    <div class="login_border">
        <div class="input">
            <form id="form_for_login">
                <ul class="items">
                    <li>
                        <%--                        <label for="username">用户名：</label>--%>
                        <input class="input_style" id="username" name="username" size="35" type="text"
                               placeholder="  用户名"/>
                    </li>
                    <li>
                        <%--                        <label for="password">密码：</label>--%>
                        <input class="input_style" id="password" name="password" size="35" type="password"
                               placeholder="  密码"/>
                    </li>
                    <li>
                        <button tabindex="3" type="button" class="btn btn-primary" id="btn-submit" style="width:208px;">
                            登&emsp;录
                        </button>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

<script src="../js/jquery-3.2.1.js"></script>
<script src="../js/layer/layer.js"></script>
<script src="../js/login.js"></script>
</body>
</html>