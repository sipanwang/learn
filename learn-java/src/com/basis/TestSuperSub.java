package com.basis;

public class TestSuperSub {
    public static void main (String args[]){
        System.out.println("------SubClass1 类继承------");
        SubClass1 sc1 = new SubClass1();
        SubClass1 sc2 = new SubClass1(100);
        System.out.println("------SubClass2 类继承------");
        SubClass2 sc3 = new SubClass2();
        SubClass2 sc4 = new SubClass2(200);
    }
}
