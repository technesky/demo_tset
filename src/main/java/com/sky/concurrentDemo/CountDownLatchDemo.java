package com.sky.concurrentDemo;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @Title: CountDownLatchDemo
 * @Package: com.sky.concurrentDemo
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/11 13:46
 * 主线程最后执行，其它线程执行完成后主线程在执行
 */
/*enum CountrtEnums{
    //ONE(1,""),TWO(2,""),THREE(3,"韩"),FOUR(4,"韩"),FIVE(5,"韩"),SIX(6,"韩");
    private Integer retCode;
    private String retMsg;
}*/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"执行完成！");
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("主线程最后执行！");
    }

    @Test
    public void testEnum(){

    }
}