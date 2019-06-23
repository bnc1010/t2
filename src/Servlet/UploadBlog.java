package Servlet;

import Bean.UserBean;
import Server.FileServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class UploadBlog extends HttpServlet {

    private final String UPLOAD_DIRECTORY="main/datas/blog";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        String s = request.getParameter("md_source");
        String aname = request.getParameter("aname");
        String self  = request.getParameter("self");

        int se = 0;
        if(self != null && self.equals("on")){
            se = 1;
        }
        else{
            se = 0;
        }
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute("User");
        String aid = (String) session.getAttribute("aid");

        String path = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + '/' + user.getUid();

        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        FileServer fs = new FileServer();
        if(aid == null){
            aid = UUID.randomUUID().toString();
            try {
                fs.SaveBlog(aid, user, aname, se);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                fs.UpdateBlog(aid, aname, se);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String file = path + File.separator + aid + ".md";
        File ff = new File(file);
        if (!ff.exists()){
            ff.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(s);
        fw.flush();
        fw.close();
        response.sendRedirect("/main/blog/myarticals.jsp");
    }
}
