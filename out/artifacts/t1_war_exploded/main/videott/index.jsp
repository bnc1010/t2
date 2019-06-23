<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh" xmlns="">

<head>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/main.js" ></script>
    <%
        String logStatus = (String)session.getAttribute("log-status");
        if (logStatus != "ok"){
            session.setAttribute("from", "./video/");
            response.sendRedirect("../judgeLoginfo.jsp");
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
                    <li value="../video/videos/名侦探柯南930.mp4" title="名侦探柯南930">名侦探柯南930</li>
                    <li value="../video/videos/名侦探柯南931.mp4" title="名侦探柯南931">名侦探柯南931</li>
                    <li value="../video/videos/名侦探柯南932.mp4" title="名侦探柯南932">名侦探柯南932</li>
                    <li value="../video/videos/名侦探柯南933.mp4" title="名侦探柯南933">名侦探柯南933</li>
                    <li value="../video/videos/名侦探柯南934.mp4" title="名侦探柯南934">名侦探柯南934</li>
                    <li value="../video/videos/名侦探柯南935.mp4" title="名侦探柯南935">名侦探柯南935</li>
                    <li value="../video/videos/名侦探柯南936.mp4" title="名侦探柯南936">名侦探柯南936</li>
                    <li value="../video/videos/廉政风云.mkv" title="廉政风云">廉政风云</li>
                    <li value="../video/videos/江湖儿女.mp4" title="江湖儿女">江湖儿女</li>
                </ul>
        </div >
    </div>
</body>
</html>