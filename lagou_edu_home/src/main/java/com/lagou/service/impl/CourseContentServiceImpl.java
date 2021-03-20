package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseContentDao;
import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.utils.DateUtils;
import com.lagou.utils.DruidUtils;

import java.util.List;

/**
 * 课程内容管理Service层实现类
 */
public class CourseContentServiceImpl implements CourseContentService {

    // 合成复用原则
    CourseContentDao courseContentDao = new CourseContentDaoImpl();

    /**
     * 根据课程id查询课程内容
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        return courseContentDao.findSectionAndLessonByCourseId(courseId);
    }

    /**
     * 根据id查询课程信息
     *
     * @param id
     * @return
     */
    @Override
    public Course findCourseByCourseId(int id) {
        return courseContentDao.findCourseByCourseId(id);
    }

    /**
     * 保存章节信息
     *
     * @param section
     * @return
     */
    @Override
    public String saveSection(Course_Section section) {

        // 1.补全章节信息
        section.setStatus(2); // 0隐藏 1待更新 2已发布
        String dateFormart = DateUtils.getDateFormart();
        section.setCreate_time(dateFormart);
        section.setUpdate_time(dateFormart);

        // 2.调用Dao
        int row = courseContentDao.saveSection(section);

        // 3.根据是否插入成功，封装对应信息
        if (row > 0) {
            //保存成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 保存失败
            return StatusCode.FAIL.toString();
        }

    }

    /**
     * 修改章节信息
     *
     * @param section
     * @return
     */
    @Override
    public String updateSection(Course_Section section) {

        // 1.补全信息
        String dateFormart = DateUtils.getDateFormart();
        section.setUpdate_time(dateFormart);

        // 2.调用dao
        int row = courseContentDao.updateSection(section);

        // 3.判断是否更新成功
        if (row > 0) {
            // 更新成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 更新失败
            return StatusCode.FAIL.toString();
        }
    }

    /**
     * 修改章节状态信息
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public String updateSectionStatus(int id, int status) {

        int row = courseContentDao.updateSectionStatus(id, status);

        // 判断是否修改成功
        if (row > 0) {
            // 修改成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 修改失败
            return StatusCode.FAIL.toString();
        }
    }

    /**
     * 保存课时信息
     *
     * @param lesson
     * @return
     */
    @Override
    public String saveLesson(Course_Lesson lesson) {

        // 1.补全章节信息
        String dateFormart = DateUtils.getDateFormart();
        lesson.setCreate_time(dateFormart);
        lesson.setUpdate_time(dateFormart);

        // 2.调用dao
        int row = courseContentDao.saveLesson(lesson);

        // 3.判断是否保存成功
        if (row > 0) {
            // 保存成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 保存失败
            return StatusCode.FAIL.toString();
        }
    }

    /**
     * 修改课时信息
     *
     * @param lesson
     * @return
     */
    @Override
    public String updateLesson(Course_Lesson lesson) {

        // 1.补全章节信息
        String dateFormart = DateUtils.getDateFormart();
        lesson.setUpdate_time(dateFormart);

        // 2.调用dao
        int row = courseContentDao.updateLesson(lesson);

        // 3.判断是否保存成功
        if (row > 0) {
            // 保存成功
            return StatusCode.SUCCESS.toString();
        } else {
            // 保存失败
            return StatusCode.FAIL.toString();
        }
    }
}
