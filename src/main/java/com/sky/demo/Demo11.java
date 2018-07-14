package com.sky.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Created by sky on 2017/2/24.
 */
public class Demo11 {

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();
        //扩容
        list1.ensureCapacity(100000);
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        list.add("eeee");


        for (int i = 0; i <list.size() ; i++) {
            if (i==2) list.remove(i);
        }
        Iterator tt=list.listIterator();
        while (tt.hasNext()){
            if (tt.next().equals("eeee")) tt.remove();
        }

        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));

        }
        System.out.println(list.size());
    }
}
