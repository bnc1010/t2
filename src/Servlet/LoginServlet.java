package Servlet;
import Bean.UserBean;
import Server.UserServer;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import Server.mapTojson;

public class LoginServlet extends HttpServlet {
    private UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        HttpSession session=request.getSession();
        String un = request.getParameter("username");
        String pwd =  request.getParameter("password");

        UserBean user = new UserBean();
        user.setUserName(un);
        user.setPwd(pwd);
        boolean flog = true;
        Map map = new HashMap();

        map.put("status", "2");
        map.put("data", "用户名或密码错误");
        try {
            if(!us.ExistUser(user)){
                map.put("data", "用户名不存在");
                flog = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flog){
            int sta = -1;
            try {
                sta = us.CheckValid(user);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (sta == -1){
                map.put("data", "用户名或密码错误");
                flog = false;
            }
            else if (sta == 0){
                map.put("data", "该用户已被封号");
                flog = false;
            }
        }
        if (flog){

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

            if (user.getUserName().equals("admin")){
                map.put("status", "1");
                map.put("data", "admin");
            }
            else {
                map.put("status", "1");
                map.put("data", un);
            }

            try {
                us.FullUserMessage(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("User", user);
        }

        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("logout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("username");
            response.sendRedirect("/login.jsp");
        }
    }
}