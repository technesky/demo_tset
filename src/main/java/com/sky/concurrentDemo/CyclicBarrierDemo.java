package com.sky.concurrentDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title: CyclicBarrierDemo
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 14:15
 */
public class CyclicBarrierDemo {
    private static int number=7;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier=new CyclicBarrier(number,() -> {
            System.out.println("人齐了开会！");
        });


        for (int i = 1; i <=number ; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 来了一个。。。");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"\t 开始开会。。。");

                } catch (InterruptedException |BrokenBarrierException e) {

                }
            },String.valueOf(i)).start();
        }
    }

}