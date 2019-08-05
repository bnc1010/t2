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
        int pageid=1;
        if (request.getParameter("pageid") != null){
            pageid=Integer.parseInt(request.getParameter("pageid"));
        }
        FileServer fs = new FileServer();
        BlogBean [] ret = fs.GetPublicArticals(pageid);
        int n=0;
        BlogBean[] blogs = new BlogBean[7];
        for(int i=0; i<7; i++,n++){
            if(ret[i]==null){
                break;
            }
            blogs[n] = ret[i];
        }
        int pagecount = fs.GetPublicArticleCount(pageid);
    %>
    <title><%=user.getUserName()%>的博客</title>
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
<div id="father">
    <div id="top_bar">
        <a href="create.jsp">写文章</a>
        <a href="myarticals.jsp">我的文章</a>
        <a href="../">返回</a>
    </div>
    <div id="content_left"></div>
    <div id="content_center">
        <div id="left_box"></div>
        <div id="right_box">
            <%
                for(int i=0;i<n;i++){
            %>
            <div class="article_box">
                <a href="reader.jsp?aid=<%=blogs[i].getAid()%>&type=2"><%=blogs[i].getAname()%></a><br/>
                <h1 class="author"><%=blogs[i].getUn()%></h1>
                <h1 class="time">更新时间：<%=blogs[i].getLast_t()%></h1>
            </div>
            <%
                }
            %>
            <div class="page-wrap">
                <ul class="pagination" id="complete"></ul>
            </div>
        </div>

    </div>
    <div id="content_right"></div>
    <div id="bottom_bar"></div>
</div>
<script src="../../js/jquery.js"></script>
<script src="../../js/page.js"></script>
<script type="text/javascript">
    $(function () {
        PagingManage($('#complete'),<%=pagecount%>, <%=pageid%>);
    });
    function switchPage(divId, page) {
        window.location.href="./?" + "pageid=" + page;
    }
</script>
</body>
</html>
