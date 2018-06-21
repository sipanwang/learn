package com.pcm.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
* Title: ServiceHello
* Description: ����jdk1.7���ϵ�javax.jws ����webservice�ӿ�
                @WebService �� ����һ��ע�⣬��������ָ�������෢����һ��ws��
                Endpoint �C ����Ϊ�˵�����࣬���ķ���publish���ڽ�һ���Ѿ������@WebServiceע��
                ����󶨵�һ����ַ�Ķ˿��ϡ� 
* Version:1.0.0  
* @author panchengming
 */
@WebService  
public class JwsServiceHello {

    /** ���ͻ��˵��÷���  �÷����ǷǾ�̬�ģ��ᱻ����
     * @param name  �������
     * @return String ���ؽ��
     * */
    public String getValue(String name){
        return "��ӭ�㣡 "+name;
    }

    /**
     * �����ϼ�@WebMentod(exclude=true)�󣬴˷�������������
     * @param name
     * @return
     */
    @WebMethod(exclude=true)  
    public String getHello(String name){
        return "��ã� "+name;
    }

    /** ��̬�������ᱻ����
     * @param name
     * @return
     */
    public static String getString(String name){
        return "�ټ���"+name;
    }


     //ͨ��EndPoint(�˵����)����һ��WebService
    public static void main(String[] args) {
     /*����:1,���صķ����ַ;
           2,�ṩ�������;
      */
     Endpoint.publish("http://192.168.137.100:8080/Service/ServiceHello", new JwsServiceHello());
     System.out.println("�����ɹ�!");
     //�����ɹ��� ����������� http://192.168.137.100:8080/Service/ServiceHello?wsdl
    }
}