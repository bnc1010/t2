<!DOCTYPE html>
<%@ page import="Bean.UserBean" %>
<%@ page import="Server.FileServer" %>
<%@ page import="Bean.BlogBean" %><%--
  Created by IntelliJ IDEA.
  User: bnc
  Date: 19-5-28
  Time: 下午1:21
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
        FileServer fs = new FileServer();
        String aid = request.getParameter("aid");
        session.setAttribute("aid", aid);
        String path ="/main/datas/blog/" + user.getUid() + "/" + aid + ".md";
        BlogBean artical = fs.getArtical(aid);
        //System.out.println(path);
    %>
    <title><%=user.getUserName()%>的博客</title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="/md_editor/css/editormd.css" />
</head>
<body>
<a href="reader.jsp?aid=<%=aid%>&type=1">返回</a><br/>
<div id="layout">
    <form action="/UploadBlog" method="post">
        <input type="submit" name="submit" value="发表" class="btn" style="margin-left: 5%;" />
        <input type="text" name="aname" value="<%=artical.getAname()%>" required="required" placeholder="文章名">
        <small>是否公开：</small><input type="checkbox" name="self">
        <div class="editormd" id="test-editormd">
            <textarea name="md_source">
</textarea>
        </div>
    </form>
</div>


<script src="js/jquery.min.js" type="text/javascript" language="JavaScript"></script>
<script src="../../md_editor/editormd.js" type="text/javascript" language="JavaScript"></script>
<script>
    var testEditor;
    $(function() {
        $.get('<%=path%>', function (md) {
            testEditor = editormd("test-editormd", {
                width: "90%",
                height: 740,
                path: 'lib/',
                theme: "dark",
                previewTheme: "dark",
                editorTheme: "pastel-on-dark",
                markdown: md,
                codeFold: true,
                //syncScrolling : false,
                saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
                searchReplace: true,
                //watch : false,                // 关闭实时预览
                htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
                //toolbar  : false,             //关闭工具栏
                //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                emoji: true,
                taskList: true,
                tocm: true,         // Using [TOCM]
                tex: true,                   // 开启科学公式TeX语言支持，默认关闭
                flowChart: true,             // 开启流程图支持，默认关闭
                sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
                //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "./php/upload.php",
                onload: function () {
                    console.log('onload', this);
                    //this.fullscreen();
                    //this.unwatch();
                    //this.watch().fullscreen();

                    //this.setMarkdown("#PHP");
                    //this.width("100%");
                    //this.height(480);
                    //this.resize("100%", 640);
                }
            });
        });
    });
</script>
</body>
</html>
