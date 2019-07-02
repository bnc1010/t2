package Servlet;

import Bean.FileBean;
import Server.FileServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DeleteFolder")
public class DeleteFolder extends HttpServlet {
    private FileServer fs = new FileServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fid = request.getParameter("file");

        Map map = new HashMap();
        map.put("status", "2");
        map.put("data","未知错误");

        try {
            FileBean file = fs.GetFile(fid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
