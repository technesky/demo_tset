package com.sky.concurrentDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Title: NotSafe
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 15:12
 */
public class NotSafe {
    public static void main(String[] args) {
        //List<String> list=new ArrayList<>();
        //使用写实复制技术
        List<String> list=new CopyOnWriteArrayList<>();

        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}