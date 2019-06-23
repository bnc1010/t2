<html lang="zh">
<%@ page import="Bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    <link rel="stylesheet" type="text/css" href="./css/main.css"/>
    <script language="JavaScript" src="../js/timer.js"></script>
    <script language="JavaScript" src="js/jquery.js"></script>
    <script language="JavaScript" src="js/main.js"></script>
    <title>bnc-site</title>
    <%
        user = (UserBean)session.getAttribute("User");
        if (user == null){
            user = new UserBean();
            user.setUserName("未登录");
            response.sendRedirect("/");
        }
    %>
</head>

<body class="bk">
    <div id="container">
        <div id="header">

            <div class="nav">
                <!--导航条-->
                <ul class="nav-main">
                    <li id="li-1"><%=user.getUserName()%><span></span></li>
                </ul>
                <!--隐藏盒子-->
                <div id="box-1" class="hidden-box hidden-loc-index">
                    <ul>
                        <li><a href="#">个人信息</a></li>
                        <li><a href="/LogoutServlet">&emsp;退出</a></li>
                    </ul>
                </div>
            </div>
        </div>
        　　<div id="page">
                <div id="left">
                    <nav class="urls">
                        <ul class="ulUrl">
                            <!--li class="liUrl"><a class="urlword" href="./musicplayer/">音乐播放器</a></li>
                            <!--li class="liUrl"><a class="urlword" href="./pictureview/">图片游览器</a></li>
                            <!--li class="liUrl"><a class="urlword" href="./video">视频</a></li-->
                            <li class="liUrl"><a class="urlword" href="./datas">云盘</a></li>
                            <li class="liUrl"><a class="urlword" href="./blog">博客</a></li>
                        </ul>
                    </nav>
                </div>
        　　</div>
        　　<div id="footer">
                <div id="show_time"></div>
                <div class="div_beian">
                    <!--img src="http://www.beian.gov.cn/img/ghs.png" alt="备案标识" /><a  class="beiAnUrl" href="http://www.miitbeian.gov.cn/" target="_blank">浙ICP备19010350号</a-->
                    <b class="beiAnUrl">&copy;  2019 bnc </b>
                    <b class="beiAnUrl"> Email:1010744256@qq.com</b>
                </div>
             </div>
    </div>
</body>

</html>