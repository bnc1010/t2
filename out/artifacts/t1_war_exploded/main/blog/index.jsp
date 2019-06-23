<%@ page import="Bean.UserBean" %>
<%@ page import="Server.FileServer" %>
<%@ page import="Bean.BlogBean" %><%--
  Created by IntelliJ IDEA.
  User: bnc
  Date: 19-5-28
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"></jsp:useBean>
<html>
<head>
    <%
        user = (UserBean) session.getAttribute("User");
        if (user == null){
            user = new UserBean();
            user.setUserName("未登录");
            response.sendRedirect("/");
        }
        int pageid=0;
        if (request.getParameter("pageid") == null){
            pageid=1;
        }
        else{
            pageid=Integer.parseInt(request.getParameter("pageid"));
        }
        FileServer fs = new FileServer();
        BlogBean [] ret = fs.GetPublicArticals(pageid);
        int n=0;
        BlogBean[] blogs = new BlogBean[10];
        for(int i=0; i<10; i++,n++){
            if(ret[i]==null){
                break;
            }
            blogs[n] = ret[i];
        }
    %>
    <title><%=user.getUserName()%>的博客</title>
</head>
<body>
<a href="../">返回</a><br/><br/>

<a href="myarticals.jsp">我的文章</a><br/>
<a href="create.jsp">创建新文章</a><br/><br/>


<div>
    <p>热门文章：</p>
    <%
        for(int i=0;i<n;i++){
    %>
    <a href="reader.jsp?aid=<%=blogs[i].getAid()%>&type=2"><%=blogs[i].getAname()%></a>
    <small>作者：<%=blogs[i].getUn()%>&emsp;更新时间：<%=blogs[i].getLast_t()%></small>
    <br/>
    <%
        }
    %>

    <%
        if (pageid>1){
    %>
    <a href="./?pageid=<%=pageid-1%>">上一页</a>
    <%
    }
    else{
    %>
    <a>上一页</a>
    <% }
    %>
    <%
        if(n==10){
    %>
    <a href="./?pageid=<%=pageid+1%>">下一页</a>
    <%    }
    else{
    %>
    <a>下一页</a>
    <%
        }
    %>
</div>
</body>
</html>
