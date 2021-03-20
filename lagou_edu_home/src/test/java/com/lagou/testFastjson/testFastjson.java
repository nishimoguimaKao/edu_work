package com.lagou.testFastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class testFastjson {

    // JavaBean对象转换为JSON对象
    @Test
    public void javaBeanToJSON() {

        // 1.创建Person对象
        Person person = new Person("小美", 20, DateUtils.getDateFormart());

        // 2.使用JSON对象将person对象转换为JSON数据
        String jsonString = JSON.toJSONString(person);
        System.out.println(jsonString); // {"age":20,"birthday":"2021-03-01 16:08:12","username":"小美"}

        String sql = "10";
        Integer integer = Integer.valueOf(sql);
        Integer integer1 = Integer.parseInt(sql);
        System.out.println(integer + " " + integer1);
    }

    // Java集合对象转换为JSON对象
    @Test
    public void listToJSON() {

        // 创建person对象
        Person person1 = new Person("小美1", 201, DateUtils.getDateFormart());
        Person person2 = new Person("小美2", 202, DateUtils.getDateFormart());
        Person person3 = new Person("小美3", 203, DateUtils.getDateFormart());
        Person person4 = new Person("小美4", 204, DateUtils.getDateFormart());

        // 创建集合并将person对象封装到集合中
        List<Person> list = new ArrayList<>();
        Collections.addAll(list, person1, person2, person3, person4);

        // 使用JSON对象的toJSONString方法
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString); // [{"USERNAME":"小美1","AGE":201,"BIRTHDAY":"2021-03-01 16:29:19"},{"USERNAME":"小美2","AGE":202,"BIRTHDAY":"2021-03-01 16:29:19"},{"USERNAME":"小美3","AGE":203,"BIRTHDAY":"2021-03-01 16:29:19"},{"USERNAME":"小美4","AGE":204,"BIRTHDAY":"2021-03-01 16:29:19"}]
    }

    // JSON转对象
    @Test
    public void jsonToJavaBean() {

        String json = "{\"age\":20,\"birthday\":\"2021-03-01 16:08:12\",\"username\":\"小美\"}";

        // 使用JSON对象的parseObject
        Person person = JSON.parseObject(json, Person.class);
        System.out.println(person);
    }

    // JSON转集合
    @Test
    public void jsonToCollection() {

        String json = "[{\"USERNAME\":\"小美1\",\"AGE\":201,\"BIRTHDAY\":\"2021-03-01 16:29:19\"},{\"USERNAME\":\"小美2\",\"AGE\":202,\"BIRTHDAY\":\"2021-03-01 16:29:19\"},{\"USERNAME\":\"小美3\",\"AGE\":203,\"BIRTHDAY\":\"2021-03-01 16:29:19\"},{\"USERNAME\":\"小美4\",\"AGE\":204,\"BIRTHDAY\":\"2021-03-01 16:29:19\"}]";

        // 使用JSON对象的parseArray，
        List<Person> list = JSON.parseArray(json, Person.class);

        System.out.println(list);

    }
}
