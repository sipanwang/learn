package com.basis;
/**
 * enum枚举值测试类
 */
public class FreshJuiceTest {

        public static void main(String[] args) {
            FreshJuice juice = new FreshJuice();
            juice.size = FreshJuice.FreshJuiceSize.SMALL;
            System.out.println(juice.size);

    }
}
