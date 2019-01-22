package com.example.springboothelloword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Create By sipw on 2019-01-22
 */
//指定扫描的控制层，默认程序启动时加载该路径下的所有controller
@ComponentScan("com.example.controller")
@SpringBootApplication
public class SpringbootHellowordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHellowordApplication.class, args);
    }

}

