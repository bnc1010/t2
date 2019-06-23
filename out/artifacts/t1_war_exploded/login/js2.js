function sendMessage() {
    var temp = document.createElement("form");
    temp.action = "/EmailForFindServlet";
    temp.method = "post";
    temp.style.display = "none";
    temp.target="nm_iframe";
    var tp = document.getElementById("ee");
    var opt = document.createElement("input");
    opt.name = "email";
    opt.value = tp.value;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}
var countdown=60;
function settime(obj) {
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