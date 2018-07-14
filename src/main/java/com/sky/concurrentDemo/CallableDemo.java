package com.sky.concurrentDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title: Callable获取多线程
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 12:50
 *
 * FutureTask主要用于比较耗时的任务，通过get异步获取内容，主线程不会阻塞。
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask=new FutureTask<Integer>(() -> {
            return 2018;
        });

        new Thread(futureTask,"AAA").start();

        //获取结果
        Integer result=futureTask.get();

        System.out.println(result);
    }

}