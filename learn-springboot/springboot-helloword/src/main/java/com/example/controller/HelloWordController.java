package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

//注解此类是控制层，必须要加
@RestController
public class HelloWordController {
    @Autowired
    private  Student stu;
    @Value("${name}")
    private String name;

    @RequestMapping("getStu")
    public String getName() {
        System.out.println(name);
        return name;
    }

    @RequestMapping("getStu")
    public Student getStu() {
        System.out.println(stu.getName());
        return stu;
    }

    //指定映射访问路径
    @RequestMapping("hellospring")
    public  String hello(){
        String a = name;
        System.out.println(a);
        return "Hello Spring Boot"+a;
    };
}
