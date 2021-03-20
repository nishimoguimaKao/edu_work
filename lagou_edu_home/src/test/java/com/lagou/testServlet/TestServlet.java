package com.lagou.testServlet;

import com.lagou.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 当前的Servlet对应的是课程管理模块
 */
@WebServlet("/test")
public class TestServlet extends BaseServlet {

    /**
     * 添加课程
     *
     * @param req
     * @param resp
     */
    public void addCourse(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("新建课程！");
    }

    /**
     * 添加课程
     *
     * @param req
     * @param resp
     */
    public void findByName(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("根据课程名查询！");
    }

    /**
     * 添加课程
     *
     * @param req
     * @param resp
     */
    public void findByStatus(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("根据课程状态查询！");
    }
}
