package Servlet;

import Bean.EmailBean;
import Server.CommonServer;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmailForRegisterServlet extends HttpServlet {

    private CommonServer cs = new CommonServer();
    private EmailBean eb = new EmailBean();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        String to = request.getParameter("email");

        if (to == null || !cs.JudgeEmail(to)){
            request.setAttribute("re-status", "邮箱格式错误");
            response.sendRedirect("/login/register.jsp");
            return;
        }

        String num = cs.getRandStr();
        try {
            eb.SendEmail(to, num);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("trueNum", num);
        response.sendRedirect("/login/register.jsp");
    }
}
