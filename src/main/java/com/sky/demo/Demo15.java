package com.sky.demo;

/**
 * @Title: Demo15笔试题
 * @Package: com.sky.demo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/12 12:03
 */
public class Demo15 {
    public static void main(String[] args) {
        String a="sss";
        String b=new String("sss");
        String c="sss";
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a.equals(b));
        a=a+"ggg";
        System.out.println(a);
        System.out.println(c);

        System.out.println("===================================");
        Integer i=3;
        int j=3;
        System.out.println(i==j);
        System.out.println("===================================");
        float t= (float) 3.14;
        double m=  3.14;
        double pdou=  3L;
    }
}