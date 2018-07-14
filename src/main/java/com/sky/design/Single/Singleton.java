package com.sky.design.Single;

/**
 * 单例模式 双检索
 * Created by sky on 2017/2/24.
 */
public class Singleton {
    private static Singleton singleton=null;
    private Singleton(){}

    public static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null)
                    singleton=new Singleton();
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance().hashCode());
    }
}
