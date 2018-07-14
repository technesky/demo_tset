package com.sky.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Demo13
 * @Package: com.sky.demo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/5/25 10:29
 */
public class Demo13 {
    public static void main(String[] args) {
        System.out.println(0%3);
        System.out.println(1%3);
        System.out.println(2%3);
        System.out.println(3%3);
        System.out.println(4%3);
        System.out.println(5%3);
        System.out.println(6%3);

        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        /*for (String s:list){
            list.remove(s);
            System.out.println(s);
        }*/

        for (int i = 0; i <list.size() ; i++) {
            list.remove(i);
            System.out.println(list.get(i));

        }

        int[] arr=new int[6];
        int[] y= new int[]{1,2,3,4,5};

        System.out.println("arr====>"+arr[5]);
        System.out.println("y====>"+y[2]);


    }
}