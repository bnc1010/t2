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
</head>
<body>
<div style="width: 70px;">
    <a href="../" class="back"><h1>返回</h1></a>
</div>
<div class="login_wrap">
    <h3>注册</h3>
    <div class="login_border">
        <div class="input">
            <form id="form_for_login" style="height: 272px">
                <ul class="items">
                    <li>
                        <input class="input_style" type="text" id="user" name="user" placeholder="用户名" />
                    </li>
                    <li>
                        <input class="input_style" type="password" id="pwd1" name="pwd1" placeholder="密码" />
                    </li>
                    <li>
                        <input class="input_style" type="password" id="pwd2" name="pwd2" placeholder="重复密码" />
                    </li>
                    <li>
                        <input class="input_style" type="text" id="ee" name="email" placeholder="邮箱"/>
                    </li>
                    <li>
                        <input class="input_style" type="text" id="num" name="num" placeholder="邮箱验证码" />
                    </li>
                    <li>
                        <input type="button" class="btn btn-primary" value="获取验证码" onclick="settime(this)" />
                    </li>
                    <li>
                        <button id="btn-submit" type="button" class="btn btn-primary">注册</button>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

</body>
<script src="../js/jquery-3.2.1.js"></script>
<script src="../js/layer/layer.js"></script>
<script src="../js/register.js"></script>
</html>
