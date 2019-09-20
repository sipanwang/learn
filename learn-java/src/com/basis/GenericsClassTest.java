package com.basis;

/**
 * java定义一个泛型类
 */
public class GenericsClassTest <T>{
    private T t;

    public void add(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }

    public static void main(String[] args) {
        GenericsClassTest<Integer> it = new GenericsClassTest<>();
        GenericsClassTest<String> st = new GenericsClassTest<>();

        it.add(new Integer(10));
        st.add(new String("加油"));

        System.out.printf("整型值为：%d\n\n",it.get());
        System.out.printf("字符串为: %s\n",st.get());
    }
}
