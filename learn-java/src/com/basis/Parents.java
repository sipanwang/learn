package com.basis;

/**
 * 定义一个父类 Parents 和两个子类 Father 类和 Mother 类：Parents 类具有两
 个属性：姓名和年龄，该类所包含的方法除了构造方法和相应的 get 和 set 方法，
 还有一个 print（）用来打印对象的具体的信息，例如，输出“我是家长，我的
 名字是...，我的年龄是...岁"；Father 类增加了一个属性：爱好，并重写了父类
 Parents 中的 print()方法，输出“我是父亲，我的名字是...，我的年龄是...岁，我
 的爱好是...”；Mother 类增加了一个属性：头发的颜色，并重写了父类 Parents 中
 的 print()方法，输出“我是母亲，我的名字是...，我的年龄是...岁，我头发的颜
 色是...”。最后定义一个测试类 Test，要求显示如下信息：
 我是家长，我的名字是王瑞，我的年龄是 36 岁
 我是父亲，我的名字是张峰，我的年龄是 45 岁，我的爱好是打保龄球
 我是母亲，我的名字是孙娟，我的年龄是 36 岁，我的头发颜色是棕色
 *
 */
public class Parents {
    //姓名
    private String name;
    //年龄
    private int age;
    public Parents(){
}
    public Parents(String name,int age){
        this.setName(name);
        this.setAge(age);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    public void Sys(){
        System.out.println("我是家长，"+"我的名字是"+this.getName()+"，我的年龄是 "+this.getAge()+"岁");
    }
}
