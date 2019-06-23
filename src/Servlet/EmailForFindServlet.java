package Servlet;

import Bean.EmailBean;
import Server.CommonServer;
import Server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmailForFindServlet extends HttpServlet{
    EmailBean eb = new EmailBean();
    CommonServer cs = new CommonServer();
    UserServer us = new UserServer();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        String to = request.getParameter("email");

        if(to == null || !cs.JudgeEmail(to)){
            request.setAttribute("find-status", "邮箱格式错误");
            response.sendRedirect("/login/register.jsp");
            return;
        }

        try {
            if (!us.ExistEmail(to)){
                request.setAttribute("find-status", "该邮箱未注册");
                response.sendRedirect("/login/register.jsp");
                return;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String num = cs.getRandStr();
        //String num ="123456";
        try {
            eb.SendEmail(to, num);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("trueNum", num);
        response.sendRedirect("/login/find_pwd.jsp");
    }
}
