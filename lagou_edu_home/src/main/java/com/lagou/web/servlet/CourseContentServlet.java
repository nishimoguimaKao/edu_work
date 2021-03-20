package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.service.impl.CourseContentServiceImpl;
import jdk.jfr.Frequency;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    // 展示对应课程的章节与课程信息
    public void findSectionAndLessonByCourseId(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.获取参数
            String course_id = request.getParameter("course_id");

            // 2.业务处理
            CourseContentService courseContentService = new CourseContentServiceImpl();
            List<Course_Section> sectionList = courseContentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));

            // 3.转换为JSON格式
            String result = JSON.toJSONString(sectionList);
            response.getWriter().println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据课程信息回显课程信息
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.获取参数
            String course_id = request.getParameter("course_id");

            // 2.业务逻辑处理
            CourseContentService courseContentService = new CourseContentServiceImpl();
            Course course = courseContentService.findCourseByCourseId(Integer.parseInt(course_id));

            // 3.返回JSON数据
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name");
            String result = JSON.toJSONString(course, filter);
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 保存或者修改章节信息
    public void saveOrUpdateSection(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1.获取参数，从域名对象中获取
            Map<String, Object> map = (Map) request.getAttribute("map");

            // 2.创建Course_Section
            Course_Section section = new Course_Section();

            // 3.使用BeanUtils工具类，将map中的数据封装到section中
            // BeanUtils.populate(section, map);
            BeanUtils.copyProperties(section,map.get("section"));

            // 4.业务逻辑处理
            CourseContentService courseContentService = new CourseContentServiceImpl();
            String result = null;
            if (0 == section.getId()) {
                // id默认为0，新增操作
                result = courseContentService.saveSection(section);
            } else {
                // id不为0，修改操作
                result = courseContentService.updateSection(section);
            }

            // 5.响应结果
            response.getWriter().println(result);
        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }

    // 修改章节状态信息
    public void updateSectionStatus(HttpServletRequest request, HttpServletResponse response){

        try {
            // 1.接收参数
            int id = Integer.parseInt(request.getParameter("id"));
            int status = Integer.parseInt(request.getParameter("status"));

            // 2.业务处理
            CourseContentService courseContentService = new CourseContentServiceImpl();
            String result = courseContentService.updateSectionStatus(id, status);

            // 3.响应结果
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存或者修改课时信息
     *
     * @param request
     * @param response
     */
    public void saveOrUpdateLesson(HttpServletRequest request, HttpServletResponse response){

        try {
            // 1.获取参数，从域名对象中获取
            Map<String, Object> map = (Map) request.getAttribute("map");

            // 2.创建Course_Lesson
            Course_Lesson lesson = new Course_Lesson();

            // 3.使用BeanUtils工具类，将map中的数据封装到section中
            BeanUtils.populate(lesson,map);

            // 4.业务处理
            CourseContentService courseContentService = new CourseContentServiceImpl();
            String result = null;
            if (0 == lesson.getId()) {
                // 新增操作
                result = courseContentService.saveLesson(lesson);
            } else {
                // 修改操作
                result = courseContentService.updateLesson(lesson);
            }

            // 5.响应结果
            response.getWriter().println(result);
        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }

    }
}
