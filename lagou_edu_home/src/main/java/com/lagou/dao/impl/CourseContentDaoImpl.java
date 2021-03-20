package com.lagou.dao.impl;

import com.lagou.dao.CourseContentDao;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.utils.DateUtils;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

/**
 * 课程内容管理DAO层实现类
 */
public class CourseContentDaoImpl implements CourseContentDao {

    /**
     * 根据课程id查询课程相关信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {

        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.编写SQL
            String sql = "SELECT\n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_section WHERE course_id = ?";

            // 3.执行查询
            List<Course_Section> sectionList = queryRunner.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);

            // 4.根据章节id查询课时信息
            for (Course_Section section : sectionList) {

                // 调用方法，获取章节对应的课时
                List<Course_Lesson> lessonList = findLessonBySectionId(section.getId());

                // 将课时数据封装到章节对象中
                section.setLessonList(lessonList);
            }

            return sectionList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 根据章节id查询章节相关课时信息
     *
     * @param sectionId
     * @return
     */
    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {

        try {

            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT\n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_lesson\n" +
                    "WHERE section_id = ?";

            List<Course_Lesson> lessonList = queryRunner.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);
            return lessonList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseByCourseId(int courseId) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT id,course_name FROM course WHERE id = ?";

            Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), courseId);
            return course;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    /**
     * 保存章节信息
     *
     * @param section
     * @return
     */
    @Override
    public int saveSection(Course_Section section) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "INSERT INTO course_section(\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "status,\n" +
                    "create_time,\n" +
                    "update_time) \n" +
                    "VALUES (?,?,?,?,?,?,?)";
            Object[] params = {section.getCourse_id(),
                    section.getSection_name(),
                    section.getDescription(),
                    section.getOrder_num(),
                    section.getStatus(),
                    section.getCreate_time(),
                    section.getUpdate_time()};

            int update = queryRunner.update(sql, params);

            return update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改章节信息
     *
     * @param section
     * @return
     */
    @Override
    public int updateSection(Course_Section section) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time=?\n" +
                    "WHERE id = ?";

            Object[] params = {section.getSection_name(),
                    section.getDescription(),
                    section.getOrder_num(),
                    section.getUpdate_time(),
                    section.getId()};

            int result = queryRunner.update(sql, params);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改章节状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public int updateSectionStatus(int id, int status) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET status = ?, update_time = ? WHERE id = ?";

            Object[] param = {status, DateUtils.getDateFormart(), id};

            int result = queryRunner.update(sql, param);

            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * 保存课时信息
     *
     * @param lesson
     * @return
     */
    @Override
    public int saveLesson(Course_Lesson lesson) {

        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.创建保存课时信息sql语句
            String sql = "INSERT INTO course_lesson(\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num\n" +
                    ") VALUES(?,?,?,?,?,?)";

            // 3.创建动态sql语句所需参数
            Object[] params = {lesson.getCourse_id(),
                    lesson.getSection_id(),
                    lesson.getTheme(),
                    lesson.getDuration(),
                    lesson.getIs_free(),
                    lesson.getOrderNum()};

            // 4.执行保存操作
            int result = queryRunner.update(sql, params);

            // 5.返回保存信息
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改课时信息
     *
     * @param lesson
     * @return
     */
    @Override
    public int updateLesson(Course_Lesson lesson) {

        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.创建修改课时信息sql语句
            String sql = "UPDATE course_lesson SET\n" +
                    "course_id = ?,\n" +
                    "section_id = ?,\n" +
                    "theme = ?,\n" +
                    "duration = ?,\n" +
                    "is_free = ?,\n" +
                    "order_num = ?\n" +
                    "WHERE id = ?;";

            // 3.创建动态sql语句所需参数
            Object[] params = {lesson.getCourse_id(),
                    lesson.getSection_id(),
                    lesson.getTheme(),
                    lesson.getDuration(),
                    lesson.getIs_free(),
                    lesson.getOrderNum(),
                    lesson.getId()};

            // 4.执行保存操作
            int result = queryRunner.update(sql, params);

            // 5.返回保存信息
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
