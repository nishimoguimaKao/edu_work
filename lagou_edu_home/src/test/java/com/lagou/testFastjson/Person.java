package com.lagou.testFastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    // 通过指定name属性来改变输出的名称
    // 可以指定ordinal属性来改变属性的位置
    // 可以指定serialize属性来确定是否转换为JSON对象

    @JSONField(name = "USERNAME", ordinal = 1)
    private String username;

    @JSONField(name = "AGE", ordinal = 2)
    private int age;

    @JSONField(name = "BIRTHDAY", ordinal = 3)
    private String birthday;
}
