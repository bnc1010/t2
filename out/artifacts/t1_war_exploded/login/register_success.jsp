<%--
  Created by IntelliJ IDEA.
  User: acm-19
  Date: 2019/3/27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>注册成功</title>
    <script type="text/javascript">
        var t = 3;
        function turn() {
            var h = document.getElementById("ee");
            h.innerText = "注册成功！"+ t +"秒后自动转跳到……"
            t--;
            setTimeout("turn()", 1000);
        }
    </script>
</head>
<body onload="turn()">


<h1 style="text-align: center" id="ee"></h1>
<% response.setHeader("refresh", "3;URL=../main/");%>
</body>
</html>
