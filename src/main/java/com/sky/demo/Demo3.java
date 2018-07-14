package com.sky.demo;

import java.util.HashMap;

/**
 * StringBuffer and StringBuilder
 * Created by sunzhg on 2016/9/22.
 */

public class Demo3 {
    public static void main(String[] args) {
        StringBuffer sb=new StringBuffer("hello world !!!");
        sb.insert(0,"print: ");
        System.out.println(sb);
        sb.delete(6,12);
        //System.out.println(sb);

        HashMap map = new HashMap();
        map.put("1","good");
        map.put("2","hello");
        //System.out.println(map.containsKey("2"));

        Object p="4tw56";
        Object one=p.hashCode();
        //System.out.println(one.hashCode());
        System.out.println(one.hashCode());
        int h=0;
        h=h++;
        System.out.println(h);



    }
}
