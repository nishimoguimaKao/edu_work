package com.lagou.testServlet;

import com.lagou.base.BaseServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/test2")
public class TestServlet2 extends BaseServlet {

    public void show(){
        System.out.println("TestServlet2执行了！");
    }
}
