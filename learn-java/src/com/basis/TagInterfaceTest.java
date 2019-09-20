package com.basis;

public class TagInterfaceTest {
    public static void main(String[] args) {
        Object obj = new Dog();
        /*if(obj instanceof Dog){
            System.out.println("是DOG的实例");
        }else if(obj instanceof Animals){
            System.out.println("是Animals的实例");
        }*/
        System.out.println(obj instanceof Dog);//true
        System.out.println(obj instanceof Animals);//true
        System.out.println(obj instanceof Father);//true
    }
}
