package Servlet;

import Bean.EmailBean;
import Server.CommonServer;
import Server.UserServer;
import Server.mapTojson;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmailForRegisterServlet extends HttpServlet {

    private CommonServer cs = new CommonServer();
    private EmailBean eb = new EmailBean();
    private UserServer us = new UserServer();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        String to = request.getParameter("email");

        Map map = new HashMap();

        map.put("status", "1");
        map.put("data", "邮箱格式错误");

        boolean flag = true;
        //System.out.println(to);

        if (!cs.JudgeEmail(to)){
            flag = false;
        }

        if (flag){
            try {
                if (us.ExistEmail(to)){
                    map.put("data", "该邮箱已注册");
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag) {
            String num = cs.getRandStr();
            map.put("status", "1");

            try {
                eb.SendEmail(to, num);
            }
            catch (MessagingException e) {
                e.printStackTrace();
            }

            HttpSession session = request.getSession();
            session.setAttribute("trueNum", num);
        }

        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        //System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

}
