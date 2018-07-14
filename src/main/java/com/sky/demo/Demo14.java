package com.sky.demo;

/**
 * @Title: 类加载顺序
 * @Package: com.sky.demo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 11:53
 * 先加载父类，在加载子类
 * 先加载class类模板
 * 加载静态代码块（只加载一次）
 * 加载普通方法块
 * 加载构造方法
 */
class Father{
    public Father(){
        System.out.println("111");
    }
    {
        System.out.println("222");

    }
    static {
        System.out.println("333");
    }
}
class Son extends Father{
    public Son(){
        System.out.println("444");
    }
    {
        System.out.println("555");
    }
    static {
        System.out.println("666");
    }
}
public class Demo14 {
    public static void main(String[] args) {
        new Son();
        System.out.println("========================");
        new Son();
        System.out.println("========================");
        new Father();
    }

}