package com.lagou.dao;

import com.lagou.pojo.Course;

import java.util.List;

/**
 * Course模块的DAO层接口
 */
public interface CourseDao {

    // 查询课程列表信息
    public List<Course> finCourseList();

    // 根据条件查询信息
    public List<Course> findByCourseNameAndStatus(String courseName,String status);

    // 保存课程的营销信息
    public int saveCourseSalesInfo(Course course);

    // 根据课程ID查询课程信息
    public  Course findCourseById(int id);

    // 修改课程营销信息
    public int updateCourseSalesInfo(Course course);

    // 修改课程状态
    public int updateCourseStatus(Course course);
}
