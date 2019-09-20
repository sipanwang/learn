package com.basis;

/**
 * finalize() 方法测试类
 */
public class FinalizationTest {
    public static void main(String[] args) {
        Cake c1 = new Cake(1);
        Cake c2 = new Cake(2);
        Cake c3 = new Cake(3);

        c2=c3=null;
        //调用Java垃圾收集器
        System.gc();
    }
}
