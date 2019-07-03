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
        int pageid=1;
        if (request.getParameter("pageid") != null){
            pageid=Integer.parseInt(request.getParameter("pageid"));
        }
        String fa = "00000000-0000-0000-0000-000000000000";
        if (request.getParameter("folderid") != null){
            fa = request.getParameter("folderid");
        }
        session.setAttribute("fa", fa);
        FileServer fs = new FileServer();
        FileBean[] files = fs.GetFiles(user,fa, pageid);
        int n=0;
         for(int i=0; i<10; i++,n++){
            if(files[i]==null){
                break;
            }
         }
         int pagecount = fs.GetUserPageFileCount(user, fa);
    %>
    <title><%=user.getUserName()%>的云空间</title>
    <link rel="stylesheet" href="../css/datas.css" />
</head>
<body>
<a href="../">返回</a>
    <button id="btn_addfolder">添加文件夹</button>
    <button id="btn_uploadfile">上传文件</button>
    <div id="div_files">
        <table id="table_files">
            <tr>
                <td class="td_fname"><strong>文件名</strong></td>
                <td class="td_sz"><strong>大小</strong></td>
                <td class="td_time"><strong>上传时间</strong></td>
                <td class="td_op"><strong>操作</strong></td>
            </tr>
        <%
            if (fa.equals("00000000-0000-0000-0000-000000000000")){%>
            <tr>
                <td class="td_fname"><a href="index.jsp?folderid=00000000-0000-0000-0000-000000000000&pageid=1">/</a></td>
                <td class="td_sz"></td>
                <td class="td_time"></td>
                <td class="td_op"></td>
            </tr>
            <%}
            else{%>
            <tr>
                <td class="td_fname"><a href="<%="index.jsp?folderid=" + fs.FindFaFolder(fa)+ "&pageid=1"%>">../</a>
                <td class="td_sz"></td>
                <td class="td_time"></td>
                <td class="td_op"></td>
            </tr>
            <%}
            for (int i = 0; i < n; i++){
                if (files[i].getIsdir()){%>
            <tr id="<%="trid" + i %>">
                <td class="td_fname"><div class="td_content"><a href="<%="index.jsp?folderid=" + files[i].getFid()+ "&pageid=1"%>"><%=files[i].getFname()%></a></div></td>
                <td class="td_sz"><div class="td_content"></div></td>
                <td class="td_time"><div class="td_content"><%=files[i].getSubmit_t()%></div></td>
                <td class="td_op"><div class="td_content"><a href="javascript:void(0)" onclick="del_folder('<%=files[i].getFid()%>', '<%=files[i].getFname()%>')">删除</a></div></td>
            </tr>
                <%}
                else{
                    String path="yp/" + files[i].getUid() + "/" + files[i].getFid() + files[i].getType();
                %>
            <tr id="<%="trid" + i %>">
                <td class="td_fname"><div class="td_content"><a href="javascript:void(0)" onclick="view('<%=path%>', '<%=files[i].getType()%>', '<%=files[i].getFname()%>')"><%=files[i].getFname()%></a></div></td>
                <td class="td_sz"><div class="td_content"><%=files[i].getSize()%></div></td>
                <td class="td_time"><div class="td_content"><%=files[i].getSubmit_t()%></div></td>
                <td class="td_op">
                    <div class="td_content">
                        <a href="javascript:void(0)" onclick="del_file('<%=files[i].getFid()%>','<%=files[i].getFname()%>')">删除</a>
                        <a href="<%= path%>" download="<%=files[i].getFname()%>">下载</a>
                    </div>
                </td>
            </tr>
            <% }
            }
        %>
        </table>
    </div>
    <div class="page-wrap">
        <ul class="pagination" id="complete"></ul>
    </div>
    <div class="hover_div" id="hover_addfolder"></div>
    <div id="auto_div_addfolder" class="auto_div">
        文件夹名:
        <input type='text' id='txt_foldername' value='新建文件夹'/><br/>
        <input type='button' id='btn_folder_submit' value='确定'>
    </div>
    <div class="hover_div" id="hover_uploadfile"></div>
    <div id="auto_div_uploadfile" class="auto_div" >
        <div id="btns_upload">
            <input type="file" value="选择文件" id="selectFile" multiple onchange="fileChange(this)" />
            <input type="button" id="fileUpload" value="上传" onclick="upload()" />
        </div>
        <div class="fileList" id="fileList">
            <ul>
                <li style="width: 30%">文件名</li>
                <li style="width: 68%">上传情况</li>
            </ul>
        </div>
    </div>
    <div class="hover_div" id="view_div_hover"></div>
    <div class="auto_div" id="view_div"></div>
</body>
<script src="../../js/jquery-3.2.1.js"></script>
<script src="../../js/layer/layer.js"></script>
<script src="../../js/page.js"></script>
<script src="../../js/yunpan.js"></script>
<script src="../../js/viewfile.js"></script>
<script type="text/javascript">
    $(function () {
        PagingManage($('#complete'),<%=pagecount%>, <%=pageid%>);
    });
    function switchPage(divId, page) {
        window.location.href="index.jsp?folderid=" + '<%=fa%>' + "&pageid=" + page;
    } 
    $(document).ready(function () {

        $('#btn_addfolder').click(function () {
            $('#hover_addfolder').css("visibility", "visible");
            $('#auto_div_addfolder').css("visibility", "visible");
        });
        $('#btn_uploadfile').click(function () {
            $('#hover_uploadfile').css("visibility", "visible");
            $('#auto_div_uploadfile').css("visibility", "visible");
        });
        $('#hover_addfolder').click(function () {
            $('#hover_addfolder').css("visibility", "hidden");
            $('#auto_div_addfolder').css("visibility", "hidden");
        });
        $('#hover_uploadfile').click(function () {
            $('#hover_uploadfile').css("visibility", "hidden");
            $('#auto_div_uploadfile').css("visibility", "hidden");
        });


        $('#btn_folder_submit').click(function () {
            $.ajax({
                type:'post',
                url:'/CreateFolderServlet',
                data:{'name': $('#txt_foldername').val(),'fa': '<%=fa%>'},
                dataType:'json',
                success:function (msg) {
                    if (msg.status == 1){
                        var nurl ="index.jsp?folderid=" + '<%=fa%>'+ "&pageid=1";
                        alert(nurl);
                        window.location.href=nurl;
                    }
                    else {
                        layer.msg(msg.data);
                    }
                }
            });
        });
    });
</script>
</html>
