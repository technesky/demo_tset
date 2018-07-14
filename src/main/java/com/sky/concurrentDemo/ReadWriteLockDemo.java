package com.sky.concurrentDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Title: 读写锁
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 12:29
 * 一个线程写  五十个线程读
 *
 */

class MyQueue{
    private Object object;
    private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();

    public void readObject() {
        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t"+object);
        }finally {
            rwlock.readLock().unlock();
        }
    }
    public void writeObject(Object object) {
        rwlock.writeLock().lock();
        try{
            this.object = object;
            System.out.println(Thread.currentThread().getName()+"\t"+object);
        }finally {
            rwlock.writeLock().unlock();
        }
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyQueue myQueue=new MyQueue();

        new Thread(() -> {
            myQueue.writeObject("test2018");
        },"writeThread").start();

        TimeUnit.SECONDS.sleep(1);

        for (int i = 1; i <=50 ; i++) {
            new Thread(() -> {
                myQueue.readObject();
            },String.valueOf(i)).start();
        }

    }
}