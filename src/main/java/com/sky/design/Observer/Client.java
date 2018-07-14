package com.sky.design.Observer;

/**
 *  观察者模式 --测试类
 * Observer模式也叫观察者模式，是由GoF提出的23种软件设计模式的一种。
 * Observer模式是行为模式之一，它的作用是当一个对象的状态发生变化时，能够自动通知其他关联对象，自动刷新对象状态。
 * Observer模式的概念
 * Observer模式是行为模式之一，它的作用是当一个对象的状态发生变化时，能够自动通知其他关联对象，自动刷新对象状态
 * Observer模式提供给关联对象一种同步通信的手段，使某个对象与依赖它的其他对象之间保持状态同步
 * Observer模式的典型应用
 - 侦听事件驱动程序设计中的外部事件
 - 侦听/监视某个对象的状态变化
 - 发布者/订阅者(publisher/subscriber)模型中，当一个外部事件（新的产品，消息的出现等等）被触发时，通知邮件列表中的订阅者
   Observer模式的优点
 - 对象之间可以进行同步通信
 - 可以同时通知一到多个关联对象
 - 对象之间的关系以松耦合的形式组合，互不依赖
 */
public class Client {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();
        //添加观察者对象
        publisher.addObserver(new SubscriberObserver());
        publisher.addObserver(new ManagerObserver());

        //发布新闻，触发通知事件
        publisher.publishNews("新消息", "news body");

        System.out.println("test!!!");

    }
}
