package com.sky.design.Observer;

import java.util.Observable;

/**
 * 观察者模式 --NewsPublisher
 * Created by sky on 2017/3/27.
 */
public class NewsPublisher extends Observable {

    public void publishNews(String newsTitle,String newsBody){
        News news=new News(newsTitle,newsBody);
        //通过setChanged()方法标明对象的状态已发生变化
        setChanged();
        System.out.println("News published:" + newsTitle);
        //通知各Observer，并发送一个名为news对象的消息
        this.notifyObservers(news);

    }
}
