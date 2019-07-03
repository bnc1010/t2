$('#view_div_hover').click(function () {
    var view_div = $('#view_div');
    view_div.html("");
    view_div.css("visibility", "hidden");
    if (view_div.hasClass("music_player")) {
        view_div.removeClass("music_player");
    }
    if (view_div.hasClass("video_player")) {
        view_div.removeClass("video_player");
    }
    $('#view_div_hover').css("visibility", "hidden");
});

function view(path, type, name) {
    var view_div = $('#view_div');
    view_div.css("visibility", "visible");
    $('#view_div_hover').css("visibility", "visible");
    var content;
    if (type === ".mp3" || type === ".m4a"){
        view_div.addClass("music_player");
        content = "<p>正在播放" + name + "</p><audio src=\"" + path + "\" controls autoplay>游览器兼容问题，播放失败</audio>";
        view_div.html(content);
    }
    else if (type === ".mp4" || type ===".ogg" || type === ".webm"){
        view_div.addClass("video_player");
        content = "<p>正在播放" + name + "</p><video src=\"" + path + "\" controls autoplay>游览器兼容问题，播放失败</video>";
        view_div.html(content);
    }
}