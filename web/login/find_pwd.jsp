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
    <script language="JavaScript" src="js2.js"></script>
</head>

<body>
<div style="width: 70px;">
    <a href="../" class="back"><h1>返回</h1></a>
</div>

<div class="login_wrap">
    <h3>邮箱验证</h3>
    <div class="login_border">
        <div class="input">
            <form class="form_for_login" style="height: 150px">
                <ul class="items">
                    <li>
                        <input class="input_style" type="text" id="ee" name="email" placeholder="邮箱"/>
                    </li>
                    <li>
                        <input class="input_style" type="text" id="num" name="num" placeholder="邮箱验证码"/>
                    </li>
                    <li>
                        <input class="btn btn-primary" type="button" value="获取验证码" onclick="settime(this)" />
                    </li>
                    <li>
                        <input type="button" id="btn-submit" class="btn btn-primary" value="确定">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
<script src="../js/jquery-3.2.1.js"></script>
<script src="../js/layer/layer.js"></script>
<script src="../js/findpwd.js"></script>
</html>
