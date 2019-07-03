package Servlet;

import Bean.FileBean;
import Bean.UserBean;
import Server.FileServer;
import Server.mapTojson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DeleteFolder")
public class DeleteFolder extends HttpServlet {
    private FileServer fs = new FileServer();
    private int cnt = 0;

    private void delFile(FileBean file) throws Exception {
        fs.DeleteFile(file.getFid());
        String path = getServletContext().getRealPath("/") + "main/datas/yp" + "/" + file.getUid() + "/" + file.getFid() + file.getType();
        File mfile = new File(path);
        if (mfile.exists()){
            mfile.delete();
        }
        cnt++;
    }

    private void dfs(String fid, UserBean user) throws Exception {
        ArrayList<FileBean> sonfiles = fs.GetFiles(user, fid);

        for (FileBean file : sonfiles){
            if (file.getIsdir()){
                dfs(file.getFid(), user);
            }
            else{
                delFile(file);
            }
        }

        fs.DeleteFile(fid);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fid = request.getParameter("file");

        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute("User");

        Map map = new HashMap();
        map.put("status", "2");
        map.put("data","未知错误");

        try {
            FileBean file = fs.GetFile(fid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dfs(fid, user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        map.put("status","1");
        map.put("data","共删除" + cnt +"个文件");

        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
