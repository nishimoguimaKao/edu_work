package com.lagou.dao;

import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.List;

public class TestCourseDao {

    CourseDao courseDao = new CourseDaoImpl();

    // 测试查找全部课程信息
    @Test
    public void testFindCourseList() {

        System.out.println(courseDao.finCourseList());

    }

    // 测试条件查询
    @Test
    public void findByCourseNameAndStatus() {

        List<Course> list = courseDao.findByCourseNameAndStatus("爱妻", "");

        for (Course c :
                list) {
            System.out.println(c.getId() + " " + c.getCourse_name() + " " + c.getStatus());
        }

    }

    // 测试插入课程销售信息
    @Test
    public void testSaveCourseSalesInfo() {

        // 1.创建Course对象
        Course course = new Course();
        course.setCourse_name("爱妻26级");
        course.setBrief("学会去找对象");
        course.setTeacher_name("药水哥");
        course.setTeacher_info("人人都是药水哥");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.88);
        course.setShare_image_title("哈哈哈");
        course.setShare_title("嘻嘻嘻");
        course.setShare_description("天天向上");
        course.setCourse_description("爱情三十六计，就像一场游戏！");
        course.setCourse_img_url("https://www.xxx.com/xxx.jpg");
        course.setStatus(1);
        course.setCreate_time(DateUtils.getDateFormart());
        course.setUpdate_time(DateUtils.getDateFormart());

        int i = courseDao.saveCourseSalesInfo(course);

        System.out.println(i);

    }

    // 测试修改课程信息
    @Test
    public void testUpdateCourse() {

        //  根据id查询课程信息
        Course course = courseDao.findCourseById(1);

        course.setCourse_name("32个Java面试考点");
        course.setTeacher_name("王老师");
        course.setDiscounts(99.9);

        // 更新
        int i = courseDao.updateCourseSalesInfo(course);
        System.out.println(i);

        Course course1 = courseDao.findCourseById(1);
        System.out.println(course1);


    }


}
