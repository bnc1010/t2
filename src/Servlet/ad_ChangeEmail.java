package Servlet;

import Bean.UserBean;
import Server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ad_ChangeEmail extends HttpServlet {
    private UserServer us = new UserServer();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean user = (UserBean)session.getAttribute("User");
        String newEmail = req.getParameter("email");

        try {
            if (us.ChangeEmail(user, newEmail)){
                user.setEmail(newEmail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("admin/index/admin-info.jsp");
    }

}
