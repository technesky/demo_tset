package com.sky.demo;

/**
 * 匿名内部类
 * Created by sunzhg on 2016/9/23.
 */

interface ITest{
    public void print(String msg);
}
public class Demo8 {

    public static void main(String[] args) {
        String str="测试匿名内部类";
        new ITest(){
            @Override
            public void print(String msg) {
                System.out.println(msg);
            }
        }.print(str);
    }
}
