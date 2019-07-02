function sendMessage() {
    $.ajax({
        type:"post" ,
        url:"/EmailForFindServlet",
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
        var ee = $('#ee');
        var num = $('#num');
        if (!ee.val()){
            layer.msg("请输入邮箱");
            return;
        }
        if (!num.val()){
            layer.msg("请输入验证码");
            return;
        }

        $.ajax({
            type:"post",
            url:"/FindPwdServlet",
            data:{"email":ee.val(), "num":num.val()},
            dataType:"json",
            success:function (jsonmsg) {
                if (jsonmsg.status == 1){
                    window.location.href="change_pwd.jsp";
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