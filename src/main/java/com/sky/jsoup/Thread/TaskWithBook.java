package com.sky.jsoup.Thread;

import com.sky.jsoup.bean.Book;
import com.sky.util.JsoupByBeanUtil;
import com.sky.util.JsoupUtil;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * 线程
 * Created by sky on 2016/7/5.
 */
public class TaskWithBook implements Callable<Book> {

    private Book book;

    public TaskWithBook(Book book){
        this.book=book;
    }

    @Override
    public Book call() throws IOException {
        if (null!=book){
            //String contents= JsoupUtil.getBookContents(book);
            String contents= JsoupByBeanUtil.getContents(book);
            book.setContent(contents);
            return book;
        }
        return null;
    }



}
