<!DOCTYPE html>
<%@ page import="Bean.UserBean" %>
<%@ page import="Server.UserServer" %>
<%@ page import="Server.FileServer" %>
<%@ page import="Bean.BlogBean" %><%--
  Created by IntelliJ IDEA.
  User: bnc
  Date: 19-5-28
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"></jsp:useBean>
<html>
<head>
    <%
        FileServer fs = new FileServer();
        String aid = request.getParameter("aid");
        BlogBean artical = fs.getArtical(aid);
        String path = "../datas/blog/" + artical.getUid() + "/" + artical.getAid() + ".md";
        UserServer us = new UserServer();
    %>
    <title><%=artical.getAname()%></title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="../../md_editor/css/editormd.css" />
    <link rel="stylesheet" href="css/reader.css">
</head>
<body>
<a href="../../login/">返回</a>
<br/>

<div id="layout">
    <header>
    </header>

    <div id="sidebar">
        <h1>Table of Contents</h1>
        <div class="markdown-body editormd-preview-container" id="custom-toc-container">#custom-toc-container</div>
    </div>
    <div id="test-editormd-view">
        <textarea style="display:none;" name="test-editormd-markdown-doc">

        </textarea>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="lib/marked.min.js"></script>
<script src="lib/prettify.min.js"></script>
<script src="lib/raphael.min.js"></script>
<script src="lib/underscore.min.js"></script>
<script src="lib/sequence-diagram.min.js"></script>
<script src="lib/flowchart.min.js"></script>
<script src="lib/jquery.flowchart.min.js"></script>
<script src="../../md_editor/editormd.js"></script>
<script type="text/javascript">
    $(function() {
        var testEditormdView, testEditormdView2;
        $.get('<%=path%>', function(markdown) {

            testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                markdown        : markdown ,//+ "\r\n" + $("#append-test").text(),
                //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                htmlDecode      : "style,script,iframe",  // you can filter tags decode
                //toc             : false,
                tocm            : true,    // Using [TOCM]
                tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
                //gfm             : false,
                //tocDropdown     : true,
                // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                emoji           : true,
                taskList        : true,
                tex             : true,  // 默认不解析
                flowChart       : true,  // 默认不解析
                sequenceDiagram : true  // 默认不解析
            });

            //console.log("返回一个 jQuery 实例 =>", testEditormdView);

            // 获取Markdown源码
            //console.log(testEditormdView.getMarkdown());

            //alert(testEditormdView.getMarkdown());
        });

        testEditormdView2 = editormd.markdownToHTML("test-editormd-view2", {
            htmlDecode      : "style,script,iframe",  // you can filter tags decode
            emoji           : true,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true,  // 默认不解析
        });
    });
</script>
</body>
</html>
