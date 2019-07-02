function sendMessage() {
    $.ajax({
        type:"post" ,
        url:"/EmailForRegisterServlet",
        data:{email:$('#ee').val()},
        dataType: "json",
        success:function (msg) {
            if (msg.status == 1){
                layer.msg("发送成功");
            }
            else {
                layer.msg(msg.data);
            }
        },
        fail:function(){
            layer.msg("未知错误");
        }
    });
}
var countdown = 60;
function settime(obj) {

    if(!$('#ee').val()){
        layer.msg("请输入邮箱");
        return;
    }

    if (countdown === 0) {
        obj.removeAttribute("disabled");
        obj.value="获取验证码";
        countdown = 60;
        return;
    }
    else if(countdown === 60){
        sendMessage();
        countdown--;
    }
    else
    {
        obj.setAttribute("disabled", true);
        obj.value="重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function() {settime(obj)},1000)
}

$(document).ready(function () {
    $('#btn-submit').click(function () {
        if(!$('#user').val()){
            layer.msg("请输入用户名");
            return;
        }
        var pwd1 = $('#pwd1');
        if(!pwd1.val()){
            layer.msg("请输入密码");
            return;
        }
        if(!$('#pwd2').val()){
            layer.msg("请输入重复密码");
            return;
        }
        if(pwd1.val().length < 6){
            layer.msg("密码过短，至少为6个字符长度");
            return;
        }
        if(!$('#ee').val()){
            layer.msg("请输入邮箱");
            return;
        }
        if(!$('#num').val()){
            layer.msg("请输入邮箱验证码");
            return;
        }
        $.ajax({
            type:"post",
            url:"/RegisterServlet",
            data:$('#form_for_login').serialize(),
            dataType: "json",
            success:function (jsonmsg) {
                if (jsonmsg.status == 1){

                    layer.msg("注册成功，自动转跳中");
                    var fun = "window.location.href='/main'";
                    window.setTimeout(fun, 2500);
                }
                else {
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