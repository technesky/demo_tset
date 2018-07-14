package com.sky.demo;

/**
 *System
 * Created by sunzhg on 2016/9/22.
 */
class Book{
    public Book(){
        System.out.println("一本新书");
    }

    /**
     * 构造方法 是对象初始化时使用
     * finalize 是对象销毁时调用
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("新书被销毁！！！");
    }
}
public class Demo5 {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        Book book=new Book();
        System.gc();//手动调用垃圾回收
        book=null;
        System.gc();

    }
}
