package com.sky.demo;

import java.util.concurrent.*;

/**
 *线程池的使用
 */


public class Demo12 {

    private static Executor executor= Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        //创建任务等待队列
        BlockingQueue<String> workQueue  = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < 20; i++) {
            workQueue.offer(i+"");

        }
        //创建线程池，池中保存的线程数为5，允许的最大线程数为10，50秒
        ThreadPoolExecutor pool=new ThreadPoolExecutor(10,20,60,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));
        while (workQueue.size()>0){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("---pool-----"+Thread.currentThread().getName()+"--------正在执行！");

                }
            });
        }
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        pool.shutdown();



        /*for (int i = 0; i < 20; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("---executor-----"+Thread.currentThread().getName()+"--------正在执行！");
                }
            });
        }*/

    }
}