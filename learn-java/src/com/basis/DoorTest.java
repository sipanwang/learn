package com.basis;

import java.lang.annotation.ElementType;
import java.util.Scanner;

/**
 * 抽象类和接口的测试类
 */
public class DoorTest {

    public static void main(String[] args) {
        AbstractDoorAction da = new AbstractDoorAction();
        da.name= "铁门";
        da.action="开门";
        da.hello();

        Scanner sc =new Scanner(System.in);
        int i = sc.nextInt();
        da.input(da.name,da.action,i);

    }
}
