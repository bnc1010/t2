<%@ page import="Bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" class="Bean.UserBean" />
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图片浏览</title>

<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css">
	<%
		user = (UserBean) session.getAttribute("User");
		if (user == null){
			user = new UserBean();
			user.setUserName("未登录");
			response.sendRedirect("/");
		}
	%>
</head>
	
<body background="img/bk.png" marginwidth="100%" marginheight="100%">

<div id="slider">
	<ul class="slides clearfix">
		<li><img class="responsive" src="img/13.jpg"></li>
		<li><img class="responsive" src="img/14.jpg"></li>
		<li><img class="responsive" src="img/3.jpg"></li>
		<li><img class="responsive" src="img/8.jpg"></li>
		<li><img class="responsive" src="img/5.jpg"></li>
		<li><img class="responsive" src="img/6.jpg"></li>

	</ul>
	<ul class="controls">
		<li><img src="img/prev.png" alt="previous"></li>
		<li><img src="img/next.png" alt="next"></li>
	</ul>
	<ul class="pagination">
		<li class="active"></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>

<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="dist/easySlider.js"></script>
<script type="text/javascript">
	$(function() {
		$("#slider").easySlider( {
			slideSpeed: 500,
			paginationSpacing: "15px",
			paginationDiameter: "12px",
			paginationPositionFromBottom: "20px",
			slidesClass: ".slides",
			controlsClass: ".controls",
			paginationClass: ".pagination"					
		});
	});
</script>
<div style="text-align:center;">
</div>
</body>
</html>