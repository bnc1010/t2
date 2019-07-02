package Servlet;

import Bean.UserBean;
import Server.FileServer;
import Server.mapTojson;
import org.apache.catalina.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateFolderServlet")
public class CreateFolderServlet extends HttpServlet {
    private FileServer fs = new FileServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute("User");
        String fa = request.getParameter("fa");
        String fname = request.getParameter("name");
        //System.out.println(fa);
        //System.out.println(fname);
        Map map = new HashMap();
        map.put("status" , "2");
        map.put("data","未知错误");

        boolean flag = true;

        try {
            if (!fs.CheckFolderValid(fname,fa,user)){
                flag = false;
                map.put("data","该目录下已有名为" + fname +"的文件夹");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag){
            try {
                if (!fs.SaveFolder(fname, fa , user)){
                    map.put("data","创建失败");
                }
                else{
                    map.put("status", "1");
                    map.put("data" ,"创建成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mapTojson mtj = new mapTojson();
        String json = mtj.write(map);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
