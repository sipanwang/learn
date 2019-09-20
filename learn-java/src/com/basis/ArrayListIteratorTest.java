package com.basis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListIteratorTest {
    public static void main(String[] args) {
        List <String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");

        //第一种遍历方法使用for-Each 遍历list
        //也可以写成for(int i =0;i<list.size();i++)这种形式
        for(String str : list){
            System.out.println(str);
        }

        //第二种遍历，把链表 变为数组相关的内容进行遍历
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        for(int i =0; i<strArray.length; i++){//这里也可以写成for(String str : strArray)这种形式
            System.out.println(strArray[i]);
        }

        //第三种遍历 使用迭代器
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()){//判断下一个元素之后有值
            System.out.println(ite.next());
        }
    }
}
