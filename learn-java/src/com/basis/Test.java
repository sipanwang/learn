package com.basis;

public class Test {
        public void pupAge(){
            int age =0 ;
            age = age + 7;
            int b =1;
            b+=age;
            System.out.println("小狗的年龄是 : " + age+"b是啥"+b);
        }

        public static void main(String[] args){
            Test test = new Test();
            test.pupAge();
            System.out.println(PrivateVariable.DEPARTMENT);
        }
}
