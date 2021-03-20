package com.lagou.service;

import com.lagou.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * 课程管理模块的service层接口
 */
public interface CourseService {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public String saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public String updateCourseSalesInfo(Course course);

    public Map<String,Integer> updateCourseStatus(Course course);

}
