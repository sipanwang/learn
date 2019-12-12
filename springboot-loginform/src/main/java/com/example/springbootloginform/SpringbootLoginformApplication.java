package com.example.springbootloginform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("controller")
@SpringBootApplication
public class SpringbootLoginformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLoginformApplication.class, args);
    }

}
