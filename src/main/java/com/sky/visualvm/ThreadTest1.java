package com.sky.visualvm;

public class ThreadTest1 {
    private int j=0;
    public static void main(String[] args) {
        for (int p=0;p<2;p++){
            final ThreadTest1 threadTest1=new ThreadTest1();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<100;i++){
                        threadTest1.inc();
                    }
                }
            }).start();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<100;i++){
                        threadTest1.dec();
                    }            }
            }).start();
        }


    }


    private  synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }
    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }
}