package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDao;
import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程管理模块的service层实现类
 */
public class CourseServiceImpl implements CourseService {

    // 创建CourseDao，合成复用原则
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        return courseDao.finCourseList();
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findByCourseNameAndStatus(courseName, status);
    }

    @Override
    public String saveCourseSalesInfo(Course course) {

        // 1.补齐课程营销信息
        String dateFormart = DateUtils.getDateFormart();
        course.setCreate_time(dateFormart);
        course.setUpdate_time(dateFormart);
        course.setStatus(1);

        // 2.执行插入操作
        int row = courseDao.saveCourseSalesInfo(course);

        // 3.判断
        if (row > 0) {
            // 插入成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 插入失败
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {

        int row = courseDao.updateCourseSalesInfo(course);

        // 判断是否修改成功
        if (row > 0) {
            // 修改成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 修改失败
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {

        // 调用DAO
        int row = courseDao.updateCourseStatus(course);
        System.out.println("修改的行数：" + row);

        Map<String, Integer> map = new HashMap<>();

        // 判断是否修改成功
        if (row > 0) {
            // 修改成功
            if (0 == course.getStatus()) {
                map.put("status", 0);
            } else {
                map.put("status", 1);
            }
        } else {
            // 修改失败

        }
        return map;
    }
}
