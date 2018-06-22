package com.pcm.client;
import com.pcm.ws.jms.JwsServiceHello;
import com.pcm.ws.jms.JwsServiceHelloService;

/**
 * 
* Title: JwsClientHello
* Description: webService 客户端调用
* Version:1.0.0  
* @author panchengming
 */
public class JwsClientHello {

    public static void main(String[] args) {
    	//调用webservice
        JwsServiceHello hello=new JwsServiceHelloService().getJwsServiceHelloPort();
        String name=hello.getValue("贝多芬");
        System.out.println(name);
    }
}