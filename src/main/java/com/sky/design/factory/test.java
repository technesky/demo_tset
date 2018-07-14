package com.sky.design.factory;

/**
 *
 * Created by sky on 2016/9/24.
 */
public class test {

    public static void main(String[] args) {
        Fruit fruit=Factory.getInstance(2);
        fruit.eat();
    }

}
