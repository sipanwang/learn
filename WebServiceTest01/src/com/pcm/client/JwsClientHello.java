package com.pcm.client;
import com.pcm.ws.jms.JwsServiceHello;
import com.pcm.ws.jms.JwsServiceHelloService;

/**
 * 
* Title: JwsClientHello
* Description: webService �ͻ��˵���
* Version:1.0.0  
* @author panchengming
 */
public class JwsClientHello {

    public static void main(String[] args) {
         //����webservice
        JwsServiceHello hello=new JwsServiceHelloService().getJwsServiceHelloPort();
        String name=hello.getValue("�����");
        System.out.println(name);
    }
}