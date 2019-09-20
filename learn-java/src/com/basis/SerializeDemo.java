package com.basis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 将EmployeeSerializable的属性序列化成文件
 */
public class SerializeDemo {
    public static void main(String [] args)
    {
        EmployeeSerializable e = new EmployeeSerializable();
        e.name = "Reyan Ali";
        e.address = "Phokka Kuan, Ambehta Peer";
        e.SSN = 11122333;
        e.number = 101;
        try
        {
            //将对象序列化后输出到指定目录
            FileOutputStream fileOut = new FileOutputStream("D:/IDEAWork/IDEAWorkSpace/learn/learn-java/tmp/employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /D:/IDEAWork/IDEAWorkSpace/learn/learn-java/tmp/employee.ser");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
