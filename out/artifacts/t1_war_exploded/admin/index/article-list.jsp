<%@ page import="Server.FileServer" %>
<%@ page import="Bean.UserBean" %>
<%@ page import="Bean.BlogBean" %>
<%@ page import="Server.AdminServer" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Bean.UserBean" scope="session"></jsp:useBean>
<html class="iframe-h">

	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>博客管理</title>
		<link rel="stylesheet" type="text/css" href="../../static/admin/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../../static/admin/css/admin.css" />
		<%
			user = (UserBean) session.getAttribute("User");
			if (user == null || !user.getUserName().equals("admin")){
				user = new UserBean();
				user.setUserName("未登录");
				response.sendRedirect("/");
			}
			AdminServer fs = new AdminServer();
			int pageid=0;
			int pagecount = 0;
			if (request.getParameter("pageid") == null){
				pageid=1;
				pagecount = fs.GetAllArticalPageCount();
				session.setAttribute("Pagecount1",pagecount);
			}
			else{
				pageid=Integer.parseInt(request.getParameter("pageid"));
				pagecount = (int)session.getAttribute("Pagecount1");
			}

			BlogBean[] ret = fs.GetAllArtical(pageid);
			int n=0;
			BlogBean[] blogs = new BlogBean[10];
			for(int i=0; i<10; i++,n++){
				if(ret[i]==null){
					break;
				}
				blogs[n] = ret[i];
			}
			String del = request.getParameter("del_sta");
		%>
	</head>
	<body>
		<div class="wrap-container clearfix">
				<div class="column-content-detail">
					<form class="layui-form" action="">
						<div class="layui-form-item">
							<div class="layui-inline tool-btn">
								<button class="layui-btn layui-btn-small layui-btn-normal addBtn" data-url="article-add.html"><i class="layui-icon">&#xe654;</i></button>
								<button class="layui-btn layui-btn-small layui-btn-danger delBtn"  data-url="article-add.html"><i class="layui-icon">&#xe640;</i></button>
							</div>
							<div class="layui-inline">
								<input type="text" name="title" required lay-verify="required" placeholder="请输入查询值" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<select name="status" lay-filter="status">
									<option value="">请选择一查询参数</option>
									<option value="010">ID</option>
									<option value="021">作者</option>
									<option value="0571">标题</option>
								</select>
							</div>
							<button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
						</div>
					</form>
					<div class="layui-form" id="table-list">
						<table class="layui-table" lay-even lay-skin="nob">
							<colgroup>
								<col width="50">
								<col class="hidden-xs" width="50">
								<col class="hidden-xs" width="100">
								<col>
								<col class="hidden-xs" width="150">
								<col class="hidden-xs" width="150">
								<col width="80">
								<col width="150">
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
									<th class="hidden-xs">ID</th>
									<th class="hidden-xs">作者</th>
									<th>标题</th>
									<th class="hidden-xs"></th>
									<th class="hidden-xs">更新时间</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<%
								for (int i=0; i<n; i++){%>
								<tr>
									<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
									<td class="hidden-xs" style="width: 300px"><%=blogs[i].getAid()%></td>
									<td class="hidden-xs"><%=blogs[i].getUn()%></td>
									<td><%=blogs[i].getAname()%></td>
									<td class="hidden-xs"></td>
									<td class="hidden-xs" style="width: 200px"><%=blogs[i].getLast_t()%></td>
									<td><button class="layui-btn layui-btn-mini layui-btn-normal"><%=blogs[i].getSf()==1 ? "公开" : "私密"%></button></td>
									<td>
										<div class="layui-inline">
											<button class="layui-btn layui-btn-small layui-btn-normal go-btn" data-id="-1" data-url="../../main/blog/reader.jsp?aid=<%=blogs[i].getAid()%>&type=3"><i class="layui-icon">&#xe642;</i></button>
											<button class="layui-btn layui-btn-small layui-btn-danger del-btn" data-id="-1" data-url="/DeleteBlog?aid=<%=blogs[i].getAid()%>"><i class="layui-icon">&#xe640;</i></button>
										</div>
									</td>
								</tr>
							<%}%>
							</tbody>
						</table>
						<div class="page-wrap" style="
            margin-bottom: 100px">
							<ul class="pagination" id="complete">
							</ul>
						</div>
					</div>
				</div>
		</div>
		<script src="../../js/jquery.js" type="text/javascript"></script>
		<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../js/page.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../js/jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
            $(function () {
                PagingManage($('#complete'),<%=pagecount%>, <%=pageid%>);
            });
            function switchPage(divId, page) {
                window.location.href="article-list.jsp?pageid=" + page;
            }

            layui.use(['layer', 'form', 'element', 'jquery', 'dialog'], function() {
                var layer = layui.layer;

				<%if (del != null){%>
					layer.msg('<%=del.equals("ok")?"删除成功！":"删除失败！"%>');
                <%}%>
            });
		</script>
	</body>
</html>