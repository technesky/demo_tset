package com.sky.demo;

import java.io.IOException;

/**
 * Runtime 调用gc可以手工释放内存
 * Created by sunzhg on 2016/9/22.
 */

public class Demo4 {
    public static void main(String[] args) {
        Runtime runtime=Runtime.getRuntime();
        System.out.println("1 maxMemory:"+runtime.maxMemory()/1024/1024);
        System.out.println("1 totalMemory:"+runtime.totalMemory()/1024/1024);
        System.out.println("1 freeMemory:"+runtime.freeMemory()/1024/1024);
        String str="";
        for (int i = 0; i <999999 ; i++) {
            str+="";
        }
        System.out.println("2 maxMemory:"+runtime.maxMemory()/1024/1024);
        System.out.println("2 totalMemory:"+runtime.totalMemory()/1024/1024);
        System.out.println("2 freeMemory:"+runtime.freeMemory()/1024/1024);
        runtime.gc();//释放垃圾
        System.out.println("3 maxMemory:"+runtime.maxMemory()/1024/1024);
        System.out.println("3 totalMemory:"+runtime.totalMemory()/1024/1024);
        System.out.println("3 freeMemory:"+runtime.freeMemory()/1024/1024);
        //调用本机程序
        try {
            Process pro=runtime.exec("calc.exe");
            Thread.sleep(1000);
            pro.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
