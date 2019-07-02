$(document).ready(function () {
    $('#btn-submit').click(function () {
        var user = $('#user');
        var pwd1 = $('#pwd1');
        var pwd2 = $('#pwd2');

        if (!pwd1.val()){
            layer.msg("请输入密码");
            return;
        }

        if (!pwd2.val()){
            layer.msg("请输入确认密码");
            return;
        }
        if (pwd1.val() != pwd2.val()){
            layer.msg("两次密码不一致");
            return;
        }
        if (pwd1.val().length < 6){
            layer.msg("密码过短，至少为6个字符");
            return;
        }
        $.ajax({
            type:"post",
            url:"/ChangePwdServlet",
            data:{"user":user.val(),"pwd1":pwd1.val(),"pwd2":pwd2.val()},
            dataType:"json",
            success:function (jsonmsg) {
                if (jsonmsg.status == 1){
                    if (jsonmsg.data == "admin"){
                        layer.msg("修改成功，正在跳转");
                        var fun = "window.location.href='/admin/index'";
                        window.setTimeout(fun, 2500);
                    }
                    else {
                        layer.msg("修改成功，正在跳转");
                        var fun = "window.location.href='/main'";
                        window.setTimeout(fun, 2500);
                    }
                }
                else {
                    layer.msg(jsonmsg.data);
                }
            },
            fail:function () {
                layer.msg("未知错误");
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