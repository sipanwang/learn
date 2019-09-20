package com.basis;

public class Father extends Parents{
    private String favor;
    public Father(String name,int age, String favor){
        //super代表调用父类的构造方法
        super(name,age);
        this.favor=favor;
    }
    public void setFavor(String favor) {
        this.favor = favor;
    }
    public String getFavor() {
        return favor;
    }
    public void Sys()
    {
        System.out.println("我是父亲，"+"我的名字是"+this.getName()+"，我的年龄是"+this.getAge()+"岁，我的爱好是"+this.getFavor());
    }
}
