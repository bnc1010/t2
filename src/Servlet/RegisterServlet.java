package Servlet;

import Bean.UserBean;
import Server.UserServer;
import org.apache.catalina.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        String un = request.getParameter("user");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        String email = request.getParameter("email");
        String num = request.getParameter("num");

        HttpSession session = request.getSession();
        String trueNum = (String)session.getAttribute("trueNum");

        if (!num.equals(trueNum)){
            request.setAttribute("re_status", "验证码错误");
            response.sendRedirect("/login/register.jsp");
            return;
        }

        if (!pwd1.equals(pwd2)){
            request.setAttribute("re_status", "两次密码不一致");
            response.sendRedirect("/login/register.jsp");
            return;
        }

        if (pwd1.length()<6){
            request.setAttribute("re_status", "密码过短，至少为6个字符");
            response.sendRedirect("/login/register.jsp");
            return;
        }

        UserBean user = new UserBean();
        user.setUserName(un);
        user.setPwd(pwd1);
        user.setEmail(email);

        try {
            if(us.ExistUser(user)){
                request.setAttribute("re_status", "用户名已存在");
                response.sendRedirect("/login/register.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (us.ExistEmail(user)){
                request.setAttribute("re_status", "该邮箱已被注册");
                response.sendRedirect("/login/register.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            us.InsertUser(user);
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

        try {
            us.SaveLog(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("User", user);
        response.sendRedirect("/login/register_success.jsp");
    }

}
