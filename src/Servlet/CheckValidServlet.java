package Servlet;

import Bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckValidServlet extends HttpServlet {
    private UserBean user = new UserBean();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        user = (UserBean) session.getAttribute("User");
        String from = (String)session.getAttribute("from");

        if (user == null){
            response.sendRedirect("/");
        }
        else {
            session.setAttribute("log_status", "ok");
            response.sendRedirect(from);
        }
    }

}
