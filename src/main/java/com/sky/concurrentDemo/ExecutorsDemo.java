package com.sky.concurrentDemo;

import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Title: ExecutorsDemo
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 14:34
 */
public class ExecutorsDemo {
    @Test
    public void testScheduled() {
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(5);//定长周期性执行的线程池
        ScheduledFuture<Integer> future=null;

        try {
            for (int i = 1; i <=16 ; i++) {
                future=executorService.schedule(()->{
                    System.out.print(Thread.currentThread().getName());
                    return new Random().nextInt(10);
                },2,TimeUnit.SECONDS);
                System.out.println("  返回值："+future.get());
            }
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }finally {
            executorService.shutdown();
        }

    }

    @Test
    public void testExecutor() {
        //ExecutorService executorService= Executors.newFixedThreadPool(3);//固定线程数
        //xecutorService executorService= Executors.newSingleThreadExecutor();//固定1个线程数
        ExecutorService executorService= Executors.newCachedThreadPool();//不确定多少个线程，按需分配
        Future<Integer> future=null;

        try {
            for (int i = 1; i <=16 ; i++) {
                future=executorService.submit(()->{
                    System.out.print(Thread.currentThread().getName());
                    return new Random().nextInt(10);
                });
                System.out.println("  返回值："+future.get());
            }
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }finally {
            executorService.shutdown();
        }

    }

}