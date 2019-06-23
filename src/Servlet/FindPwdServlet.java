package Servlet;

import Bean.UserBean;
import Server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FindPwdServlet extends HttpServlet{
    UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {

        String email = request.getParameter("email");
        String num = request.getParameter("num");

        HttpSession session = request.getSession();
        String trueNum = (String)session.getAttribute("trueNum");

        if(!num.equals(trueNum)){
            session.setAttribute("find_status", "验证码错误");
            response.sendRedirect("/login/find_pwd.jsp");
            return;
        }
        UserBean user = new UserBean();
        user.setUid(-1);
        try {
            user = us.GetUserByEmail(email);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (user.getUid() == -1){
            session.setAttribute("find_status", "该邮箱未注册");
            response.sendRedirect("/login/find_pwd.jsp");
            return;
        }

        try {
            session.setAttribute("User", user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/login/change_pwd.jsp");
    }

}
