package com.basis;

/**
 * 构造方法示例
 * 类注释快捷键是输入/** 然后按enter键
 * ayther:sipanwang
 * date:2019-08-27
 */
public class ConstructionMethod01 {
    //无参构造方法
    public ConstructionMethod01(){

    }
    //有参构造方法
    public ConstructionMethod01(String name){
        System.out.print("Student's name is "+name);
    }
    //添加主函数快捷键是psvm
    public static void main(String[] args) {
        ConstructionMethod01 con = new ConstructionMethod01("sunflower");
    }
}
