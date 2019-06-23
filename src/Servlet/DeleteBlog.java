package Servlet;

import Bean.BlogBean;
import Bean.UserBean;
import Server.FileServer;
import org.apache.catalina.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class DeleteBlog extends HttpServlet {

    private FileServer fs = new FileServer();
    private final String UPLOAD_DIRECTORY="main/datas/blog";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String aid = request.getParameter("aid");
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute("User");

        BlogBean blog = null;

        try {
            blog = fs.getArtical(aid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fs.DeleteBlog(aid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + '/' + blog.getUid();
        String file = path + "/" + aid + ".md";
        File ff = new File(file);

        if (ff.exists() && ff.isFile()){
            if (ff.delete()){
                if (user.getUserName().equals("admin")){
                    response.sendRedirect("/admin/index/article-list.jsp?del_sta=ok");
                    return;
                }
                else{
                    response.sendRedirect("/main/blog/myarticals.jsp?del_sta=ok");
                    return;
                }
            }
        }
        if (user.getUserName().equals("admin")){
            response.sendRedirect("/admin/index/article-list.jsp?del_sta=fail");
            return;
        }
        else{
            response.sendRedirect("/main/blog/myarticals.jsp?del_sta=fail");
            return;
        }

    }

}
