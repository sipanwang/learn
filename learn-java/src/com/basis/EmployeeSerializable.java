package com.basis;

import java.io.Serializable;

/**
 *序列化实体类，要想将实体类进行序列化，必须要实现Serializable接口
 */
public class EmployeeSerializable implements Serializable {
    public String name;
    public String address;
    //SSN属性不可序列化，使用transient标记为短暂的
    public transient int SSN;
    public int number;
    public void mailCheck()
    {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }
}
