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

public class FindPwdServlet extends HttpServlet{
    UserServer us = new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {

        String email = request.getParameter("email");
        String num = request.getParameter("num");

        //System.out.println(email);
        //System.out.println(num);

        HttpSession session = request.getSession();
        String trueNum = (String)session.getAttribute("trueNum");

        //System.out.println(trueNum);

        Map map = new HashMap();
        map.put("status", "2");
        map.put("data","未知错误");

        boolean flag =true;

        if(!num.equals(trueNum)){
            map.put("data", "验证码错误");
            flag = false;
        }

        if (flag){
            UserBean user = new UserBean();
            user.setUid(-1);
            try {
                user = us.GetUserByEmail(email);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if (user.getUid() == -1){
                map.put("data", "该邮箱未注册");
                flag = false;
            }

            if (flag){
                try {
                    session.setAttribute("FUser", user);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                map.put("status", "1");
                map.put("data","验证成功");
            }
        }
        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        //System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

}
