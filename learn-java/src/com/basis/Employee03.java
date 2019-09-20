package com.basis;

public class  Employee03 extends Employee02{

       public static void main(String[] args){
           Employee02 emp = new Employee03();
           emp.setName("李蕾");
           emp.setSalary(1000);
           emp.printEmp();

           Employee03 emp03 = new Employee03();
           emp03.name = "辉大张";
           emp03.printEmp();
       // Employee023 empOne = new Employee02("RUNOOB");
        //empOne.setSalary(1000);
       // empOne.printEmp();
    }
}
