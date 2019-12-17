package com.sipw.springbootsecurity01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//不加扫描不到controller
@ComponentScan("controller")
//不加扫描不到config
@ComponentScan("config")
@SpringBootApplication
public class SpringbootSecurity01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurity01Application.class, args);
    }

}
