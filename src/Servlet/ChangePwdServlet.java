package Servlet;

import Bean.UserBean;
import Server.UserServer;
import Server.mapTojson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChangePwdServlet extends HttpServlet{
    private UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        String un = request.getParameter("user");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        HttpSession session = request.getSession();

        Map map = new HashMap();
        map.put("status", "2");
        map.put("data","未知错误");

        boolean ad = false;

        UserBean user = (UserBean)session.getAttribute("User");
        if (user == null){
            user = new UserBean();
            user.setUserName(un);
        }
        else{
            ad = true;
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

        if (ad){
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
            map.put("status", "1");
            map.put("data", un);
            mapTojson mtj = new mapTojson();
            String json = mtj.write(map);
            //System.out.println(json);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(json);
        }
    }

}
