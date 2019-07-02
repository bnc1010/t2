
$(document).ready(function () {
    $("#btn-submit").click(function () {
        if (!$('#username').val()) {
            layer.msg("请输入用户名！");
            return;
        }
        if (!$('#password').val()) {
            layer.msg("请输入密码！");
            return;
        }
        $.ajax({
            type: "post",
            url: "/LoginServlet",
            data: $('#form_for_login').serialize(),
            dataType: "json",
            success: function (jsonmsg) {

                if (jsonmsg.status == 1) {
                    if (jsonmsg.data == "admin"){
                        window.location.href='/admin/index';
                    }
                    else {
                        window.location.href='/main';
                    }
                    //window.setTimeout("window.location.href='/book.do?type=pageList'", 1000);
                } else {
                    layer.msg(jsonmsg.data);
                }
            }
        });
    });
    
    $('#form_for_login').keydown(function (e) {
        var e = e || event,
            keycode = e.which || e.keyCode;
        if (keycode === 13) {
            $("#btn-submit").trigger("click");
        }
    });
});