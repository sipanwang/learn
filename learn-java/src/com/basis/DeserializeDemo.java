package com.basis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 反序列化测试类，已知employee.ser 文件里存储了 Employee 对象。
 */

public class DeserializeDemo {
    public static void main(String[] args) {
        EmployeeSerializable e = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:/IDEAWork/IDEAWorkSpace/learn/learn-java/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (EmployeeSerializable) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("EmployeeSerializable class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized EmployeeSerializable...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Number: " + e.number);
    }
}
