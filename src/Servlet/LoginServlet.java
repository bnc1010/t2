package Servlet;

import Bean.UserBean;
import Server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserServer us = new UserServer();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{

        HttpSession session=request.getSession();
        String un = request.getParameter("user");
        String pwd =  request.getParameter("pwd");

        UserBean user = new UserBean();
        user.setUserName(un);
        user.setPwd(pwd);
        try {
            if(!us.ExistUser(user)){
                session.setAttribute("log_status", "用户名或密码错误");
                response.sendRedirect("/login");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int sta = -1;
        try {
            sta = us.CheckValid(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (sta == -1){
            session.setAttribute("log_status", "用户名或密码错误");
            response.sendRedirect("/login");
            return;
        }
        else if (sta == 0){
            session.setAttribute("log_status", "该用户已被封号");
            response.sendRedirect("/login");
            return;
        }
        try {
            user = us.FullUserMessage(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            us.SaveLog(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            us.UpdateLast_t(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("User", user);
        if (user.getUserName().equals("admin")){
            response.sendRedirect("/admin/index");
        }
        else {
            response.sendRedirect("/main/");
        }
    }
}
