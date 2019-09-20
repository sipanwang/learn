package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloRabbitMQController {
   /*  @Autowired //这个注释的含义
   private AmqpTemplate amqpTemplate;

    *//**实现消息的发送*//*
    @RequestMapping(value = "/helloQueue",method = RequestMethod.GET)
    public void send(){
        String message = "this is queue test!";
        this.amqpTemplate.convertAndSend("hello-queue",message);
    }
    @RequestMapping(value = "/helloQueue2",method = RequestMethod.GET)
    public void send2(){

    }*/
}