package Servlet;

import Bean.UserBean;
import Server.CommonServer;
import Server.UserServer;
import Server.mapTojson;
import org.apache.catalina.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(trueNum);

        Map map = new HashMap();
        map.put("status", "2");
        map.put("data","注册失败");

        boolean flag = true;

        if (!num.equals(trueNum)){
            map.put("data", "验证码错误");
            flag = false;
        }

        if(flag){
            if (!pwd1.equals(pwd2)){
                map.put("data", "两次密码不一致");
                flag = false;
            }
        }

        CommonServer cs = new CommonServer();
        UserBean user = new UserBean();
        user.setUserName(un);
        user.setPwd(pwd1);
        user.setEmail(email);
        user.setLast_t(cs.GetTime());

        if (flag){
            try {
                if(us.ExistUser(user)){
                    map.put("data", "用户名已存在");
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag){
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
            map.put("status", "1");
            map.put("data", "注册成功");
            session.setAttribute("User", user);
        }
        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        //System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
}
