<%@ page import="Bean.UserBean" %>
<%@ page import="Server.FileServer" %>
<%@ page import="Bean.BlogBean" %><%--
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
        BlogBean[] files = fs.GetArticals(user, pageid);
        int n=0;
        for(int i=0; i<10; i++,n++){
            if(files[i]==null){
                break;
            }
        }

    %>
    <title>我的文章</title>
</head>
<body>
<a href="./">返回</a><br/>
<%
    for(int i=0;i<n;i++){
%>
<a href="reader.jsp?aid=<%=files[i].getAid()%>&type=1"><%=files[i].getAname()%></a>&emsp;&emsp;&emsp;&emsp;
<a href="/DeleteBlog?aid=<%=files[i].getAid()%>">删除</a>
<br/>
<%
    }
%>

<%
    if (pageid>1){
    %>
    <a href="./myarticals.jsp?pageid=<%=pageid-1%>">上一页</a>
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
    <a href="./myarticals.jsp?pageid=<%=pageid+1%>">下一页</a>
    <%    }
    else{
    %>
    <a>下一页</a>
    <%
        }
%>

</body>
</html>
