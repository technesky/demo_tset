package com.sky.concurrentDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: lock基本使用和线程间通信
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 10:54
 */

class ShareData{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void insr()  {
        lock.lock();
        try {
            //多线程中判断用while 不用if
            while (number!=0){
                condition.await();

            }
            //做事
            ++number;
            System.out.println(Thread.currentThread().getName()+" number："+number);
            //通知
            condition.signalAll();

        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }

    }


    public void desr()  {
        lock.lock();
        try {
            //多线程中判断用while 不用if
            while (number==0){
                condition.await();
            }
            //做事
            --number;
            System.out.println(Thread.currentThread().getName()+" number："+number);
            //通知
            condition.signalAll();

        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }

    }

    public synchronized void test(){

    }

}
public class NotifyDemo {

    public static void main(String[] args) {
        ShareData shareData=new ShareData();

        for (int i = 1; i <=2 ; i++) {
            new Thread(() -> {
                for (int j = 1; j <=10 ; j++) shareData.insr();
            },"AAA"+i).start();

        }
        for (int i = 1; i <=2 ; i++) {
            new Thread(() -> {
                for (int j = 1; j <=10 ; j++) shareData.desr();
            },"BBB"+i).start();

        }

    }



}