var files;
function fileChange(fileInput) {
    files = fileInput.files;
    var tempHtml = "";
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        tempHtml += "<ul>"
            + "<li style='width: 30%'>"
            + file.name
            + "</li>"
            + "<li style='width: 68%'><progress id='p"+i+"' max='100' value=''></progress><span id='sp"+i+"'>待上传</span></li></ul>";
    }
    document.getElementById("fileList").innerHTML += tempHtml;
}

function upload() {
    if (files == null || files.length === 0){
        layer.msg("未选择文件");
        return;
    }
    for (var i =0 ; i < files.length; i++){
        fileUpload(files[i], i);
    }
}

function fileUpload(file, index) {
    var formData = new FormData();
    formData.append(file.name, file);
    var xhr = new XMLHttpRequest();
    var oldUploaded=0;
    var curUploaded=0;
    var total=0;
    var percent=0;
    xhr.upload.addEventListener("progress",function(event){
        var loaded=event.loaded;
        if(oldUploaded==0){
            total=event.total;
            oldUploaded=event.loaded;
        }
        curUploaded=event.loaded;
        percent=Math.round(event.loaded/event.total*100);
        document.getElementById("p"+index).value=percent;
    });
    var upSpeed=setInterval(function(){
        if(oldUploaded!=0){
            //得到的是一个byte值
            var result=curUploaded-oldUploaded;
            var leave=total-curUploaded;
            var label=document.getElementById("sp"+index);
            //console.log(percent);
            if (percent == "100"){
                label.innerHTML="上传成功";
            }
            else {
                label.innerHTML=percent+"%&nbsp;&nbsp;"+Math.round(result/(1024*1024)*100)/100+"M/S&nbsp;&nbsp;剩余"+Math.round(leave/result)+"秒";
            }
            if(total==oldUploaded&&result==0){
                clearInterval(upSpeed);
            }
            oldUploaded=curUploaded;
        }
    },500);
    xhr.open("post", "/UploadServlet", true);
    xhr.send(formData);
}
function del_file(fid, fname) {
    layer.confirm("确定删除文件\"" + fname + "\"？",{
            btn:['是', '否']},
        function () {
            $.ajax({
                type:"post",
                data:{"file":fid, "type":"1"},
                dataType: "json",
                url:"/DeleteFile",
                success:function (msg) {
                    layer.msg(msg.data);
                    setTimeout("window.location.reload()", "1000");
                }
            });
        },
        function () {

        }
    );
}

function del_folder(fid, fname) {
    layer.confirm("文件夹删除后，所有子文件夹及文件都将被删除，是否确认删除文件夹\"" + fname + "\"?",{
            btn:['是', '否']},
        function () {
            $.ajax({
                type: "post",
                url: "/DeleteFolder",
                data: {"file":fid},
                dataType: "json",
                success: function (msg) {
                    layer.msg(msg.data);
                    setTimeout("window.location.reload()", "1000");
                }
            })
        },
        function () {
        }
    );
}