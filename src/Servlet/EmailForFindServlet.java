package Servlet;

import Bean.EmailBean;
import Server.CommonServer;
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

public class EmailForFindServlet extends HttpServlet{
    EmailBean eb = new EmailBean();
    CommonServer cs = new CommonServer();
    UserServer us = new UserServer();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        String to = request.getParameter("email");


        Map map = new HashMap();
        map.put("status", "2");
        map.put("data","未知错误");

        boolean flag = true;


        if(!cs.JudgeEmail(to)){
            map.put("data", "邮箱格式错误");
            flag = false;
        }

        if(flag){
            try {
                if (!us.ExistEmail(to)){
                    map.put("data", "该邮箱未注册");
                    flag = false;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        HttpSession session = request.getSession();

        if (flag){
            String num = cs.getRandStr();
            try {
                eb.SendEmail(to, num);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("trueNum", num);
            map.put("status", "1");
            map.put("data","发送成功");
        }
        /*
        if (flag){
            String num ="123456";
            session.setAttribute("trueNum", num);
            map.put("status", "1");
            map.put("data","发送成功");
        }*/

        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        //System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
}
