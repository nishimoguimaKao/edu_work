package com.lagou.dao.impl;

import com.lagou.dao.CourseDao;
import com.lagou.pojo.Course;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Course模块Dao层的实现类
 */
public class CourseDaoImpl implements CourseDao {

    /**
     * 查找所有课程信息
     *
     * @return
     */
    @Override
    public List<Course> finCourseList() {
        // 1.创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        // 2.编写sql语句,判断是否删除，即查找出is_del=0的数据
        String sql = "SELECT\n" +
                "    id,\n" +
                "    course_name,\n" +
                "    price,\n" +
                "    sort_num,\n" +
                "    STATUS\n" +
                "FROM course WHERE is_del = ?;";

        try {
            // 3.执行sql语句
            List<Course> courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);
            return courseList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 根据课程名或者课程状态来查询课程信息
     *
     * @param courseName
     * @param status
     * @return
     */
    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {

        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.编写SQL 当前的查询条件为多条件不定项查询
            // 2.1创建StringBuffer对象，将SQL字符串添加进缓冲区
            StringBuilder sb = new StringBuilder("SELECT id, course_name, price, sort_num, status FROM course WHERE 1=1 AND is_del = ?");

            // 2.2创建list集合保存参数
            List<Object> list = new ArrayList<>();
            // 是否删除默认为0
            list.add(0);

            // 2.3判断传入的参数是否为空
            if (null != courseName && "" != courseName) {
                sb.append(" AND course_name LIKE ?");
                // Like查询需要拼接% %
                courseName = "%" + courseName + "%";
                list.add(courseName);
            }
            if (null != status && "" != status) {
                sb.append(" AND status = ?");
                // 将Status转换为int
                int i = Integer.parseInt(status);
                list.add(i);
            }
            // 执行查询操作
            List<Course> courseList = queryRunner.query(sb.toString(), new BeanListHandler<Course>(Course.class), list.toArray());
            // 返回结果
            return courseList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 保存课程的营销信息
     *
     * @param course
     * @return
     */
    @Override
    public int saveCourseSalesInfo(Course course) {

        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.编写SQL
            String sql = "INSERT INTO course(\n" +
                    "course_name,\n" +
                    "brief,\n" +
                    "teacher_name,\n" +
                    "teacher_info,\n" +
                    "preview_first_field,\n" +
                    "preview_second_field,\n" +
                    "discounts,\n" +
                    "price,\n" +
                    "price_tag,\n" +
                    "share_image_title,\n" +
                    "share_title,\n" +
                    "share_description,\n" +
                    "course_description,\n" +
                    "course_img_url,\n" +
                    "status,\n" +
                    "create_time,\n" +
                    "update_time\n" +
                    ")VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // 3.准备参数
            Object[] objects = {course.getCourse_name(),
                    course.getBrief(),
                    course.getTeacher_name(),
                    course.getTeacher_info(),
                    course.getPreview_first_field(),
                    course.getPreview_second_field(),
                    course.getDiscounts(),
                    course.getPrice(),
                    course.getPrice_tag(),
                    course.getShare_image_title(),
                    course.getShare_title(),
                    course.getShare_description(),
                    course.getCourse_description(),
                    course.getCourse_img_url(),
                    course.getStatus(),
                    course.getCreate_time(),
                    course.getUpdate_time()};

            // 4.执行插入操作
            int update = queryRunner.update(sql, objects);
            return update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据课程id查询课程信息
     *
     * @param id
     * @return
     */
    @Override
    public Course findCourseById(int id) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT\n" +
                    "id,\n" +
                    "course_name,\n" +
                    "brief,\n" +
                    "teacher_name,\n" +
                    "teacher_info,\n" +
                    "preview_first_field,\n" +
                    "preview_second_field,\n" +
                    "discounts,\n" +
                    "price,\n" +
                    "price_tag,\n" +
                    "course_img_url,\n" +
                    "share_image_title,\n" +
                    "share_title,\n" +
                    "share_description,\n" +
                    "course_description,\n" +
                    "STATUS\n" +
                    "FROM course WHERE id = ?";

            Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), id);
            return course;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 修改课程营销信息
     *
     * @param course
     * @return
     */
    @Override
    public int updateCourseSalesInfo(Course course) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course SET \n" +
                    "course_name = ?,\n" +
                    "brief = ?,\n" +
                    "teacher_name = ?,\n" +
                    "teacher_info = ?,\n" +
                    "preview_first_field = ?,\n" +
                    "preview_second_field = ?,\n" +
                    "discounts = ?,\n" +
                    "price = ?,\n" +
                    "price_tag = ?,\n" +
                    "share_image_title = ?,\n" +
                    "share_title = ?,\n" +
                    "share_description = ?,\n" +
                    "course_description = ?,\n" +
                    "course_img_url = ?,\n" +
                    "update_time = ? \n" +
                    "WHERE id = ?";

            Object[] objects = {course.getCourse_name(),
                    course.getBrief(),
                    course.getTeacher_name(),
                    course.getTeacher_info(),
                    course.getPreview_first_field(),
                    course.getPreview_second_field(),
                    course.getDiscounts(),
                    course.getPrice(),
                    course.getPrice_tag(),
                    course.getShare_image_title(),
                    course.getShare_title(),
                    course.getShare_description(),
                    course.getCourse_description(),
                    course.getCourse_img_url(),
                    course.getUpdate_time(),
                    course.getId()};
            int i = queryRunner.update(sql, objects);
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改课程状态信息
     *
     * @param course
     * @return
     */
    @Override
    public int updateCourseStatus(Course course) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course SET status = ?, update_time = ? WHERE id = ?";

            Object[] objects = {course.getStatus(), course.getUpdate_time(), course.getId()};

            int i = queryRunner.update(sql, objects);
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
