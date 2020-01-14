package com.sipw.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.sipw.loader")
@ComponentScan("com.sipw.vo")
@ComponentScan("com.sipw.utils")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PoiExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiExcelApplication.class, args);
    }

}
