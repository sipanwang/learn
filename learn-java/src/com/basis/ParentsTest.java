package com.basis;

public class ParentsTest {
    public static void main(String[] args)
    {
        Parents p=new Parents("王瑞",36);
        Father f=new Father("张峰",45,"打保龄球");
        //多态
        Parents k=new Father("张峰",45,"打保龄球");
        Mother m=new Mother("孙娟",36,"棕色");

        p.Sys();
        f.Sys();
        k.Sys();
        m.Sys();
    }
}
