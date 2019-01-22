package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//注解此类是控制层，必须要加
@RestController
public class HelloWordController {
    //指定映射访问路径
    @RequestMapping("hellospring")
    public  String hello(){
      return "Hello Spring Boot";
    };
}
