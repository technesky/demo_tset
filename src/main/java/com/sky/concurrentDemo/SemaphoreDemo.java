package com.sky.concurrentDemo;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Title: SemaphoreDemo 信号灯
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 14:23
 * 抢占一个车位使用lock，抢占多个车位使用Semaphore（信号灯）
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //3个停车位
        Semaphore semaphore=new Semaphore(3);
        //模拟6个车
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    //抢占车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢占了车位！");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(8));
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位！走啦！！！！");
                } catch (InterruptedException e) {

                }finally {
                    //释放车位
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}