package com.basis;

public class Mother  extends Parents{
    private String hairColor;//头发颜色
    public Mother(String name,int age,String hairColor){
        super(name,age);
        this.hairColor=hairColor;
    }
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
    public String getHairColor() {
        return hairColor;
    }
    public void Sys(){
        System.out.print("我是母亲，"+"我的名字是"+this.getName()+"，我的年龄是 "+this.getAge()+"岁，我头发的颜色是"+this.getHairColor());
    }
}
