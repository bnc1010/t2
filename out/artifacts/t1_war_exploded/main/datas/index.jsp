<%@ page import="Bean.UserBean" %>
<%@ page import="Server.FileServer" %>
<%@ page import="Bean.FileBean" %><%--
  Created by IntelliJ IDEA.
  User: bnc
  Date: 19-5-10
  Time: 下午6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"/>
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
        FileBean[] files = fs.GetFiles(user, pageid);
        int n=0;
        for(int i=0; i<10; i++,n++){
            if(files[i]==null){
                break;
            }
        }
    %>
    <title><%=user.getUserName()%>的云空间</title>
    <link rel="stylesheet" href="self.css" type="text/css">
    <script src="js.js" type="text/javascript"></script>
</head>
<body>
<a href="../">返回</a>
<div id="alldiv">
    <div id="head">
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">点击上传</a>
    </div>
    <div id="filelist">
        <br/>
        <strong class="title">文件列表</strong><br/><br/><br/>
            <%
                for(int i=0;i<n;i++){
                    String tmp = files[i].getFname().substring(files[i].getFname().lastIndexOf('.'));
                    tmp = files[i].getFid() + tmp;
                    String path = files[i].getUid() + "/" + tmp;
            %>
                <a href="./yp/<%=path%>" download="<%=files[i].getFname()%>"><%=files[i].getFname()%></a>&emsp;
                <a href="/DeleteFile?file=<%=tmp%>&type=1">删除</a>
                <br/>
            <%
                }
            %>
    </div>
    <div id="btn">

        <%
            if (pageid>1){
        %>
        <a href="../datas?pageid=<%=pageid-1%>">上一页</a>
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
        <a href="../datas?pageid=<%=pageid+1%>">下一页</a>
        <%    }
        else{
               %>
        <a>下一页</a>
        <%
        }
        %>

    </div>
    <div id="light" class="white_content">
        <div id="close_btn">
        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">关闭</a>
        </div>
        <div style="margin:30px;">
            <label for="fileInput">
                <div id="selectFile">选择</div>
                <input type="file" value="" id="fileInput" name="files" onchange="fileSelected()" />
            </label><br/>
            <input type="button" id="upLoadBtn" value="上传" onclick="uploadFile()" />
        </div>
        <div style="margin:30px;height: 50px;">
            <div id="fileName"></div>
            <div id="fileSize"></div>
            <div id="fileType"></div>
        </div>
        <div style="margin:30px;width:80%;height:15px;border:1px solid #aeaeae;">
            <div id="progress" style="background:#4cff00;height:15px;width:0%;"></div>
            <div id="percentNumber"></div>
        </div>
        <div style="margin:30px;">
            <div id="msg"></div>
        </div>
    </div>
    <div id="fade" class="black_overlay"></div>
</div>
</body>
</html>
