package com.basis;

import javax.sound.midi.Soundbank;
import java.net.SocketTimeoutException;

/**
 * 防盗门继承门的抽象类，开锁的功能由开锁的接口实现
 */

public class AbstractDoorAction extends AbstractDoor implements InterfaceLock {
    public void hello(){
        System.out.println("门的型号为"+name);
        System.out.println("要进行的操作为"+action);
        System.out.println("请输入密码");
    }

    public void opendoor() {
        System.out.println("密码正确，开门");
    }

    public void closedoor() {
        System.out.println("密码错误，关门");
    }

    @Override
    public void input(String name,String action,int openpassowrd) {
        if(name.equals("铁门") && action.equals("开门") && openpassowrd == 123 )opendoor();
        else closedoor();

    }

    @Override
    public void output(String name,String action,int openpassowrd) {
        System.out.println("请输入关门密码");
    }
}

