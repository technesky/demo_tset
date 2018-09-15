package com.sky.jsoup.Thread;

import com.sky.jsoup.bean.Book;
import com.sky.utils.JsoupUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 线程处理类
 * Created by sky on 2016/7/5.
 */
public class TaskWithResult implements Callable<Book> {

    private BlockingQueue<Book> bqueue;

    public TaskWithResult(BlockingQueue<Book> bqueue){
        this.bqueue=bqueue;
    }

    @Override
    public Book call() throws Exception {
        try {
                Book book=bqueue.poll(2, TimeUnit.SECONDS);
                if (null!=book){
                    String contents= JsoupUtil.getBookContents(book);
                    book.setContent(contents);
                    return book;
                }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }



}
