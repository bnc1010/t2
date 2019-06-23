package Servlet;

import Server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeUserSta")
public class ChangeUserSta extends HttpServlet {
    private UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String uid = request.getParameter("uid");

        if (type.equals("1")) {
            try {
                if (us.ChangeSta(uid, 0)) {
                    response.sendRedirect("/admin/index/user-list.jsp?ch_0_sta=ok");
                    return;
                } else {
                    response.sendRedirect("/admin/index/user-list.jsp?ch_0_sta=fail");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            try {
                if (us.ChangeSta(uid, 1)) {
                    response.sendRedirect("/admin/index/user-list.jsp?ch_1_sta=ok");
                    return;
                } else {
                    response.sendRedirect("/admin/index/user-list.jsp?ch_1_sta=fail");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
