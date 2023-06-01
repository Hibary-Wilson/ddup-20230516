package com.eureka.entity;

import lombok.Data;

/**
 * @program: itheima20210917_StudyTest
 * @description: 2023-05-12
 * @author: Mr.Huang
 * @create: 2023-05-12 14:42
 **/
@Data
public class PersonInfo {

    private String name; // 姓名
    private int salary;  // 薪资
    private int age;     // 年龄
    private String sex;  // 性别
    private String area; // 地区

    public PersonInfo(){}

    public PersonInfo(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }
}
