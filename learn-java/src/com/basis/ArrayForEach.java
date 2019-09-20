package com.basis;

/**
 * fro-each循环，也叫增强for循环
 */
public class ArrayForEach {
    public static void main(String[] args) {
        double [] mylist = {1.9,2.9,3.4,3.5};
        //打印所有数组
        for(double array : mylist){
            System.out.println(array);
        }
    }
}
