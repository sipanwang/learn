package com.basis;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理数组示例
 */
public class ArrayListTest {
    public static void main(String[] args) {
        //ArrayList<List> array[] = new ArrayList[9];
        double [] mylist ={1.9,2.9,3.4,3.5};

        //打印所有的数组元素
        for(int i=0; i < mylist.length; i++){
            System.out.println(mylist[i]+"");
        }

        //计算所有元素的总和
        double sum = 0;
        for(int i = 0;i< mylist.length;i++){
           sum += mylist[i];
        }
        System.out.println("总和是"+sum);

        //查找最大元素
        double max = mylist[0];
        for(int i =1;i< mylist.length;i++){
            if(mylist[i]>max)
                max =mylist[i];
            }
        System.out.println("max is "+max);
    }


}
