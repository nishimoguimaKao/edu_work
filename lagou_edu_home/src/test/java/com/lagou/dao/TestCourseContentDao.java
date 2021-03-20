package com.lagou.dao;

import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.List;

public class TestCourseContentDao {

    CourseContentDao courseContentDao = new CourseContentDaoImpl();

    // 测试 根据课程id查询课程相关信息
    @Test
    public void testFindSectionAndLessonByCourseId() {

        List<Course_Section> list = courseContentDao.findSectionAndLessonByCourseId(59);

        for (Course_Section cs:list) {

            System.out.println(cs.getId()+ " " + cs.getSection_name());

            List<Course_Lesson> lessonList = cs.getLessonList();

            for (Course_Lesson cl :lessonList) {

                System.out.println(cl.getId() + " " + cl.getTheme() + " " + cl.getSection_id());

            }

        }

    }

    // 测试根据课程id查询课程信息
    @Test
    public void testFindCourseByCourseId() {

        Course course = courseContentDao.findCourseByCourseId(59);

        System.out.println(course.getId() + " " + course.getCourse_name());

    }

    // 保存章节
    @Test
    public void testSaveSection() {

        Course_Section section = new Course_Section();

        section.setCourse_id(59);
        section.setSection_name("Vue高级2");
        section.setDescription("Vue相关的高级技术！");
        section.setOrder_num(8);
        String dateFormart = DateUtils.getDateFormart();
        section.setCreate_time(dateFormart);
        section.setUpdate_time(dateFormart);
        section.setStatus(2); //状态，0:隐藏；1：待更新；2：已发布

        int i = courseContentDao.saveSection(section);
        System.out.println(i);

    }

    // 修改章节
    @Test
    public void testUpdateSection() {

        Course_Section section = new Course_Section();

        section.setId(44);
        section.setSection_name("微服务加购");
        section.setDescription("微服务加购技术详解");
        section.setOrder_num(3);
        section.setUpdate_time(DateUtils.getDateFormart());

        int i = courseContentDao.updateSection(section);

        System.out.println(i);

    }

    // 修改章节状态
    @Test
    public void testUpdateSectionStatus() {

        int i = courseContentDao.updateSectionStatus(46, 1);

        System.out.println(i);

    }

    // 保存课时信息
    @Test
    public void testSaveLesson() {

        Course_Lesson lesson = new Course_Lesson();

        lesson.setCourse_id(1);
        lesson.setSection_id(2);
        lesson.setTheme("ssssssssssssss");
        lesson.setDuration(50);
        lesson.setIs_free(0);
        lesson.setOrderNum(4);

        int i = courseContentDao.saveLesson(lesson);

        System.out.println(i);

    }

    // 修改课时信息
    @Test
    public void testUpdateLesson() {

        Course_Lesson lesson = new Course_Lesson();

        lesson.setId(42);
        lesson.setCourse_id(22);
        lesson.setSection_id(22);
        lesson.setTheme("2222222222");
        lesson.setDuration(222);
        lesson.setIs_free(1);
        lesson.setOrderNum(2);

        int i = courseContentDao.updateLesson(lesson);

        System.out.println(i);

    }
}
