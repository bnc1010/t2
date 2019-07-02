package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.UserBean;
import Server.CommonServer;
import Server.FileServer;
import Server.mapTojson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "main/datas/yp";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 300;  // 3MB
    private static final long MAX_FILE_SIZE     = 1024 * 1024 * 400; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 500; // 500MB
    private FileServer fs = new FileServer();
    private CommonServer cs = new CommonServer();
    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检测是否为多媒体上传
        PrintWriter writer = response.getWriter();
        if (!ServletFileUpload.isMultipartContent(request)) {
            writer.println("表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        HttpSession session=request.getSession();
        UserBean user = (UserBean)session.getAttribute("User");
        String fa = (String)session.getAttribute("fa");
        //System.out.println(fa);
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + '/' + user.getUid();
        //System.out.println(uploadPath);

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            //System.out.println('#');
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            //System.out.println(formItems.size());
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    //System.out.println(item.getName());
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        //System.out.println(fileName);
                        String fid = UUID.randomUUID().toString();
                        //System.out.println(fid);
                        String sp = fileName.substring(fileName.lastIndexOf('.'));
                        //System.out.println(sp);
                        String filePath = uploadPath + File.separator + fid + sp;
                        //System.out.println(filePath);
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        String sz = cs.FileSize(item.getSize());
                        // 保存文件到硬盘
                        item.write(storeFile);

                        try {
                            fs.SaveFile(user, fileName, fid, sz, fa);
                            writer.println("上传成功");
                            writer.flush();
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            writer.println(ex.getMessage());
        }
        writer.println("未知错误");
        writer.flush();
    }
}