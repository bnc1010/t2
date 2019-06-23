package Servlet;

import Bean.FileBean;
import Bean.UserBean;
import Server.FileServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class DeleteFile extends HttpServlet {
    private FileServer fs = new FileServer();
    private final String UPLOAD_DIRECTORY="main/datas/yp";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String file = request.getParameter("file");
        String type = request.getParameter("type");
        String fid = "";
        char[] tfile = file.toCharArray();
        for(int i=0; i< tfile.length;i++){
            if (tfile[i] == '.')break;
            fid += tfile[i];
        }
        FileBean ff = null;
        try {
            ff = fs.GetFile(fid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fs.DeleteFile(fid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + "/" + ff.getUid() + "/" + ff.getFid() + ff.getType();
        System.out.println(path);
        File fff = new File(path);
        if (fff.exists()){
            fff.delete();
        }

        if (type.equals("2")){
            response.sendRedirect("/admin/index/file-list.jsp?del_sta=ok");
            return;
        }

        response.sendRedirect("/main/datas");
    }
}
