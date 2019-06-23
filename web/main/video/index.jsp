<%@ page import="Bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean" />
<html lang="zh" xmlns="">

<head>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/main.js" ></script>
    <%
        user = (UserBean) session.getAttribute("User");
        if (user == null){
            user = new UserBean();
            user.setUserName("未登录");
            response.sendRedirect("/");
        }
    %>
</head>


<body bgcolor="#556b2f">
    <div id="cans">
            <div class="video_box">
                <video style="margin-left:10px;" id="video" controls="controls" autoplay="autoplay" src=""></video>
            </div>
            <div class="item_list" id="playList">
                <header>
                    <h4 class="item_title">播放列表</h4>
                </header>
                <ul class="item_ul">
                    <li value="videos/名侦探柯南930.mp4" title="名侦探柯南930">名侦探柯南930</li>
                    <li value="videos/名侦探柯南931.mp4" title="名侦探柯南931">名侦探柯南931</li>
                    <li value="videos/名侦探柯南932.mp4" title="名侦探柯南932">名侦探柯南932</li>
                    <li value="videos/名侦探柯南933.mp4" title="名侦探柯南933">名侦探柯南933</li>
                    <li value="videos/名侦探柯南934.mp4" title="名侦探柯南934">名侦探柯南934</li>
                    <li value="videos/名侦探柯南935.mp4" title="名侦探柯南935">名侦探柯南935</li>
                    <li value="videos/名侦探柯南936.mp4" title="名侦探柯南936">名侦探柯南936</li>
                </ul>
        </div >
    </div>
</body>
</html>