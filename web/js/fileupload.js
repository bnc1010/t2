function fileSelected() {
    //重置状态显示
    document.getElementById('percentNumber').innerHTML = '';
    document.getElementById("progress").style.width = "0%";
    var file = document.getElementById('fileInput').files[0];
    if (file) {
        var fileSize = 0;
        if (file.size > 1024 * 1024)
            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        else
            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
        document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
        document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
        document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
    }
}


function uploadFile() {
    var fd = new FormData();
    var file = document.getElementById('fileInput').files[0];
    if (file.length === 0){
        noFile();
        return;
    }
    fd.append("fileInput", file);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("post", "/UploadServlet");//修改为自己服务器接口地址
    //xhr.setRequestHeader("Access-Control-Allow-Origin", "*");//需要在IIS里面配置，就可以跨域请求了
    //xhr.setRequestHeader("Content-Type", "multipart/form-data");
    xhr.send(fd);
}
function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        document.getElementById('percentNumber').innerHTML = percentComplete + '%';
        var jindutiao = document.getElementById("progress");
        jindutiao.style.width = percentComplete + "%";
    }
    else {
        document.getElementById('percentNumber').innerHTML = '不支持进度计算';
    }
}
function uploadComplete(evt) {
    //evt.target.responseText
    layer.msg("上传成功");
}
function uploadFailed(evt) {
    layer.msg("上传过程中有一个错误");
}
function uploadCanceled(evt) {
    layer.msg("用户取消了上传或者浏览器删除了连接");
}
function noFile() {
    layer.msg("未选择文件");
}