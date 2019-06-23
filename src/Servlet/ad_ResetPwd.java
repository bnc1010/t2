package Servlet;

import Bean.UserBean;
import Server.AdminServer;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ad_ResetPwd")
public class ad_ResetPwd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("User");
        if (user == null || !user.getUserName().equals("admin")){
            user = new UserBean();
            user.setUserName("未登录");
            response.sendRedirect("/");
        }
        String id = request.getParameter("uid");
        AdminServer as = new AdminServer();

        try {
            if(as.ResetPwd(id)){
                response.sendRedirect("/admin/index/user-list.jsp?reset_sta=ok");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/index/user-list.jsp?reset_sta=fail");
    }
}
