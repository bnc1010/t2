package Servlet;

import Bean.UserBean;
import Server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangePwdServlet extends HttpServlet{
    private UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        String un = request.getParameter("user");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        HttpSession session = request.getSession();
        if (!pwd1.equals(pwd2)){
            session.setAttribute("change_status", "密码不一致！");
            response.sendRedirect("/login/change_pwd.jsp");
            return;
        }

        if (pwd1.length() < 6){
            session.setAttribute("change_status", "密码过短，至少为6个字符");
            response.sendRedirect("/login/change_pwd.jsp");
            return;
        }

        UserBean user = (UserBean)session.getAttribute("User");
        if (user == null){
            user = new UserBean();
            user.setUserName(un);
        }

        try {
            us.ChangePassword(user, pwd1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            us.FullUserMessage(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        if (user.getUserName().equals("admin")){
            response.sendRedirect("admin/index/admin-info.jsp");
            return;
        }
        else{
            session.setAttribute("User", user);
            try {
                us.UpdateLast_t(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                us.SaveLog(user);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/login/find_pwd_success.jsp");
        }
    }

}
