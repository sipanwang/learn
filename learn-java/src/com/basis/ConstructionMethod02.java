package com.basis;

import javax.xml.bind.SchemaOutputResolver;

/**
 * 调用构造方法并给成员变量赋值示例
 */
public class ConstructionMethod02 {
    int age;
    public ConstructionMethod02(){

    }
    //name的构造方法
    public ConstructionMethod02(String name) {
        System.out.println("The student's name is "+name);
    }

    public int getAge() {
        System.out.println("The student's age is "+age);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        /*Create a Object*/
        ConstructionMethod02 con2 = new ConstructionMethod02("sunflower");
        /*Without set age is null now*/
        System.out.println("age1 is "+con2.age);
        /*Set age*/
        con2.setAge(20);
        /*Get age*/
        con2.getAge();
        /*Access member variables Like this（也可以这样访问成员变量）*/
        System.out.println("age2 is "+con2.age);
    }

}
