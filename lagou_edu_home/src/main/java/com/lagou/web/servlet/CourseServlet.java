package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    // 查询课程列表信息
    public void findCourseList(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.接收参数

            // 2.业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findCourseList();

            // 3.响应结果
            // SimplePropertyPreFilter指定需要转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                    Course.class,
                    "id",
                    "course_name",
                    "price",
                    "sort_num",
                    "status"
            );
            String result = JSON.toJSONString(courseList, filter);
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 根据课程名或状态查询课程信息
    public void findByCourseNameAndStatus(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.接收参数
            String course_name = request.getParameter("course_name");
            String status = request.getParameter("status");

            // 2.业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findByCourseNameAndStatus(course_name, status);

            // 3.返回结果
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                    Course.class, "id", "course_name", "price", "sort_num", "status"
            );
            String jsonString = JSON.toJSONString(courseList, filter);
            response.getWriter().println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据课程id查询课程信息
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.接收参数
            String id = request.getParameter("id");

            // 2.业务处理
            CourseService courseService = new CourseServiceImpl();
            Course course = courseService.findCourseById(Integer.parseInt(id));

            // 3.将course转变文JSNO格式
            // 过滤器
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                    Course.class,
                    "id",
                    "course_name",
                    "brief",
                    "teacher_name",
                    "teacher_info",
                    "price",
                    "price_tag",
                    "discounts",
                    "preview_first_field",
                    "preview_second_field",
                    "course_img_url",
                    "share_title",
                    "share_description",
                    "course_description",
                    "share_image_title"
            );
            String result = JSON.toJSONString(course, filter);

            // 4.响应到客户端
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 修改课程信息
    public void updateCourseStatus(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.获取参数
            String id = request.getParameter("id");

            // 2.业务处理
            CourseService courseService = new CourseServiceImpl();

            // 3.根据课程id查询课程信息
            Course course = courseService.findCourseById(Integer.parseInt(id));

            // 4.判断课程信息的状态，进行取反设置
            int status = course.getStatus();
            if (0 == status) {
                // 如果为0，设置为1
                course.setStatus(1);
            } else {
                // 如果为1 设置为0
                course.setStatus(0);
            }

            // 5.设置更新时间
            course.setUpdate_time(DateUtils.getDateFormart());

            // 6.修改状态
            Map<String, Integer> map = courseService.updateCourseStatus(course);

            // 7.map转换JSON
            String result = JSON.toJSONString(map);
            // 8.响应结果
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
