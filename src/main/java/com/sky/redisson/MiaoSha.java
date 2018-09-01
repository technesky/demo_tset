package com.sky.redisson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo_tset
 * @description: 秒杀功能(同时有N个线程抢购M个商品)
 * @author: suznhg
 * @create: 2018-09-01
 **/
public class MiaoSha {
    private static int shopNumber=10;//库存数量
    private static String shopKey="shopNumber";//库存数量
    private static String lockKey="LOCK_KEY";//库存数量
    private static int ThreadNumber=1100;//抢购人数


    private List<String> sucList=new ArrayList<>();
    private List<String> failList=new ArrayList<>();

    private CountDownLatch countDownLatch=new CountDownLatch(ThreadNumber);
    private CyclicBarrier cyclicBarrier=new CyclicBarrier(ThreadNumber,() -> {
        System.out.println("开始抢购！.......");
    });

    RedissonClient redissonClient;

    @Test
    public void Test() throws InterruptedException {
        //放入商品数量
        RBucket<Integer> bucket=redissonClient.getBucket(shopKey);
        //同步放置
        bucket.set(shopNumber);

        for (int i = 1; i <=ThreadNumber ; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();//等待一起执行
                    System.out.println(Thread.currentThread().getName()+"\t 开枪啦。。。。");
                    RLock fairLock =redissonClient.getLock(lockKey);
                    boolean res = fairLock.tryLock(3, 10, TimeUnit.SECONDS);
                    if (res){
                        RBucket<Integer> rBucket=redissonClient.getBucket("shopNumber");
                        Integer num= rBucket.get();
                        if (num>0){
                            num--;
                            rBucket.set(num);
                            System.out.println(Thread.currentThread().getName()+"\t 抢到了一个！！！还剩下："+num+"\t个。");
                            sucList.add(Thread.currentThread().getName());
                        }else {
                            failList.add(Thread.currentThread().getName());
                        }

                    }

                    fairLock.unlock();
                    System.out.println(Thread.currentThread().getName()+"\t 释放锁！！！！！");

                    countDownLatch.countDown();//控制主线程等其它线程执行完成后再执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }


            },"【线程-"+String.valueOf(i)+"】").start();
        }

        countDownLatch.await();//主线程等待 防止链接关闭
        System.out.println("主线程执行完毕！！！");

        System.out.println("成功抢到商品的人："+sucList.size()+"\t详情："+sucList);
        System.out.println("没抢到的的人："+failList.size()+"\t详情："+failList);

    }

    /**
     * 每次在测试方法运行之前 运行此方法
     * 创建客户端连接服务器的redisson对象
     */
    @Before
    public void before() {
        String ip = "127.0.0.1";
        String port = "6379";
        redissonClient = RedisUtils.getInstance().getRedisson(ip, port);
    }

    /**
     * 每次测试方法运行完之后 运行此方法
     * 用于关闭客户端连接服务器的redisson对象
     */
    @After
    public void after(){
        RedisUtils.getInstance().closeRedisson(redissonClient);
    }

}
