package com.sky.jsoup.core;

import com.sky.jsoup.Thread.BookTask;
import com.sky.jsoup.bean.Book;
import com.sky.jsoup.bean.BookParam;
import com.sky.utils.JsoupByBeanUtil;
import com.sky.utils.JsoupUtil;
import com.sky.utils.PropertiesUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.sky.jsoup.bean.Parameters;

/**
 * 读取在线网站的小说，生成txt文件
 * Created by sunzhg on 2016/7/5.
 */
public class CreateTxtMain {
    private static PropertiesUtil propertiesUtil = new PropertiesUtil();
    private static Properties p = propertiesUtil.load("/properties/bmwen.properties");
    public static long startTime=System.currentTimeMillis();

    public static void main(String[] args) {
        //初始化参数
        Parameters parameters=new Parameters();
        parameters.init(p);
        parameters.createBookParam();
        String dirUrl=Parameters.dirurl;
        //多页
       if (dirUrl.indexOf("@")>-1){
           String[] arr=dirUrl.split("@");
           for (int i = 0; i <arr.length; i++) {
               test1(Parameters.bookParam,arr[i]);
           }
        //单页
       }else{
           test1(Parameters.bookParam,dirUrl);
       }
    }
    public static void test1(BookParam bookParam,String dirUrl){

        try {
            String str="";
            BlockingQueue<Book> bookBlockingQueue= JsoupByBeanUtil.getDir(bookParam,dirUrl);
            //返回数据集合
            BlockingQueue<Book> result = new LinkedBlockingQueue<Book>(bookBlockingQueue.size());
            System.out.println("-----------任务总数："+bookBlockingQueue.size());
            BookTask bookTask =new BookTask();

            str=bookTask.BookSubmit(bookBlockingQueue);
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //
        long endTime=System.currentTimeMillis();

        System.out.println("run time: "+(endTime-startTime)/1000+"s");
    }


    public static void test2(){
        //初始化参数
        Parameters parameters=new Parameters();
        parameters.init(p);
        //获取目录所在页面元素
        Document docum = JsoupUtil.getHtmlDoc(Parameters.dirurl);
        try {
            String str="";
            BlockingQueue<Book> bookBlockingQueue=JsoupUtil.getBookElement(docum,parameters.dirParam);
            //返回数据集合
            BlockingQueue<Book> result = new LinkedBlockingQueue<Book>(bookBlockingQueue.size());

            //多线程获取和处理数据
            System.out.println("-----------任务总数："+bookBlockingQueue.size());
            BookTask bookTask =new BookTask();
            //第一种方式execute  27s
            //bookTask.execute(bookBlockingQueue,result);
            //第二种方式（效率最高，资源占用最大 15s）
            //str=bookTask.submit(bookBlockingQueue);
            //第三种方式 poolSubmit  26s
            str=bookTask.poolSubmit(bookBlockingQueue);
            //第四种方式 BookSubmit  32
            //str=bookTask.BookSubmit(bookBlockingQueue);


            System.out.println(str);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //
        long endTime=System.currentTimeMillis();

        System.out.println("run time: "+(endTime-startTime)/1000+"s");
    }
}
