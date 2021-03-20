package com.lagou.web.servlet;

import com.lagou.utils.UUIDUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/upload")
public class fileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // 1.创建磁盘文件工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 2.创建文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 2.1设置上传文件的编码
            upload.setHeaderEncoding("utf-8");
            // 2.2判断表单是否为文件上传表单
            boolean multipartContent = upload.isMultipartContent(req);
            // 2.3是文件上传表单
            if (multipartContent) {
                // 3.解析request -- 获取表单项的集合
                List<FileItem> list = upload.parseRequest(req);
                // 4.遍历集合，获取表单项
                for (FileItem item : list) {
                    // 5.判断当前表单项是否为普通表单项
                    boolean formField = item.isFormField();
                    if (formField) {
                        // 普通表单项
                        String fieldName = item.getFieldName();// 属性名
                        String fieldValue = item.getString("utf-8"); // 属性值
                        // 打印普通上传项
                        System.out.println(fieldName + " = " + fieldValue);
                    } else {
                        // 文件上传项
                        // 获取文件名
                        String fileName = item.getName();

                        // 拼接心的文件名，使用UUID不重复
                        String newFileName = UUIDUtils.getUUID() + "_" + fileName;

                        // 获取输入流
                        InputStream in = item.getInputStream();

                        // 将文件上传到Tomcat服务器中，使用war模式而不是exploded模式
                        // 1.获取项目的运行目录 D:\apache-tomcat-8.5.55\webapps\lagou_edu_home\
                        String realPath = this.getServletContext().getRealPath("/");

                        // 2.截取到webapps的路径
                        String webappsPath = realPath.substring(0, realPath.indexOf("/lagou_edu_home"));


                        // 3.拼接输出路径，将文件保存到Tomcat服务器中的webapps\\upload文件夹中
                        FileOutputStream out = new FileOutputStream(webappsPath + "/upload/" + newFileName);

                        // 使用IOUtils完成文件的copy
                        IOUtils.copy(in, out);

                        // 关闭流
                        out.close();
                        in.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
