package com.lagou.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseServlet extends HttpServlet {

    /**
     * doGet方法作为一个调度器，根据请求功能的不同，调用不同的方法
     * 规定必须传入一个参数
     * methodName=功能名
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取访问的方法名
        //String methodName = req.getParameter("methodName");
        String methodName = null;

        // 1.1获取POST请求的content-type类型
        String contentType = req.getHeader("Content-Type");

        // 1.2判断传递的数据是不是json类型
        if ("application/json;charset=utf-8".equalsIgnoreCase(contentType)) {

            // 是json格式，调getPostJSON
            String postJSON = getPostJSON(req);

            // 将JSON字符串转换为map
            Map<String, Object> map = JSON.parseObject(postJSON, Map.class);

            // 从map集合中获取methodName
            methodName = (String) map.get("methodName");

            // 将获取到的数据保存到request域中
            req.setAttribute("map", map);
        } else {
            methodName = req.getParameter("methodName");
        }

        // 2.判断执行对应的方法
        /*if (methodName.equals("addCourse")) {
            addCourse(req, resp);
        } else if (methodName.equals("findByName")) {
            findByName(req, resp);
        } else if (methodName.equals("findByStatus")){
            findByStatus(req, resp);
        } else {
            System.out.println("请求的功能不存在！");
        }*/
        if (methodName != null) {
            // 通过反射优化代码，提升代码的可维护性

            try {
                // 1.获取字节码文件对象 this = TestServlet
                // 在这里根据注解路径访问的是哪个Servlet，this就是哪个Servlet
                Class<? extends BaseServlet> c = this.getClass();

                // 2.是根据传入的方法名获取对应的对象
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

                // 3.调用method对象的invoke方法，执行对应的功能
                method.invoke(this, req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求的方法不存在！");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * POST请求格式为application/json;charset=utf-8
     * 使用该方法读取
     *
     * @param request
     * @return
     */
    public String getPostJSON(HttpServletRequest request) {

        try {
            // 1.从requset中获取缓冲输入流对象
            BufferedReader reader = request.getReader();

            // 2.创建StringBuffer保存读取出的数据
            StringBuffer stringBuffer = new StringBuffer();

            // 3.循环读取
            String line = null;
            while (null != (line = reader.readLine())) {
                // 将每次读取的数据追加到StringBuffer中
                stringBuffer.append(line);
            }

            // 4.返回结果
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
