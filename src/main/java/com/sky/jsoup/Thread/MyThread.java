package com.sky.jsoup.Thread;

import com.sky.jsoup.bean.Book;
import com.sky.util.JsoupUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 线程
 * Created by sunzhg on 2016/7/5.
 */
public class MyThread implements Runnable {
    private BlockingQueue<Book> bqueue;
    private BlockingQueue<Book> result;
    private CyclicBarrier cb;

    public MyThread(BlockingQueue<Book> bqueue,BlockingQueue<Book> result,CyclicBarrier cb){
        this.bqueue=bqueue;
        this.result=result;
        this.cb=cb;
    }
    @Override
    public void run() {
        boolean isRunning=true;
        try {
            while (isRunning){
                Book book=bqueue.poll(2, TimeUnit.SECONDS);
                if (null!=book){
                    //System.out.println(Thread.currentThread().getName()+"正在执行中。。。。"+book.getId());
                    String contents=JsoupUtil.getBookContents(book);
                    book.setContent(contents);
                    //获取返回值
                    result.add(book);
                }else {
                    isRunning=false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //通知 任务结束
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
