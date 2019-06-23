<!DOCTYPE html>
<%@ page import="Bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: bnc
  Date: 19-5-28
  Time: 下午5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"></jsp:useBean>
<html>
<head>
    <%
        session.setAttribute("aid", null);
        user = (UserBean) session.getAttribute("User");
        if (user == null){
            user = new UserBean();
            user.setUserName("未登录");
            response.sendRedirect("/");
        }
    %>
    <title>创作文章</title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="/md_editor/css/editormd.css" />
</head>
<body>
<a href="./">返回</a><br/>
<div id="layout">
    <form action="/UploadBlog" method="post">
        <input type="submit" name="submit" value="发表" class="btn" style="margin-left: 5%;" />
        <input type="text" name="aname" required="required" placeholder="文章名">
        <small>是否公开：</small><input type="checkbox" name="self">
        <div class="editormd" id="test-editormd">
            <textarea name="md_source">
</textarea>
        </div>
    </form>
</div>

<script src="js/jquery.min.js" type="text/javascript" language="JavaScript"></script>
<script src="/md_editor/editormd.js" type="text/javascript" language="JavaScript"></script>
<script src="js/create.js" type="text/javascript" language="JavaScript"></script>

</body>
</html>
