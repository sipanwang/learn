package com.basis;

public class Cake extends Object{
    private int id;
    public Cake (int id){
        this.id = id;
        System.out.println("Cake Object"+id+"is created");
    }

    protected  void finalize() throws java.lang.Throwable{
        super.finalize();
        System.out.println("Cake Object"+id+"is disposed");
    }
}
