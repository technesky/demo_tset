package com.sky.demo;

/**
 * 二叉树 binary tree
 * Created by sunzhg on 2016/9/23.
 */
class Computer{
    private String title;
    private int price;

    public Computer() {
    }

    public Computer(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
public class Demo7 {
    public static void main(String[] args) {
        System.out.println("test one!");
    }
}
