package com.basis;

public class SubClass1 extends SuperClass {
    private int n;

    SubClass1(){ // 自动调用父类的无参数构造器
        System.out.println("SubClass");
    }
    public SubClass1(int n){
        super(300);  // 调用父类中带有参数的构造器
        System.out.println("SubClass(int n):"+n);
        this.n = n;
    }
}
