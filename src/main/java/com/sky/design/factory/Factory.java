package com.sky.design.factory;

/**
 * 工厂设计模式
 * Created by sky on 2016/9/24.
 */
public class Factory {
    public static final int APPLE=1;
    public static final int BANANA=2;

    public static Fruit getInstance(int flag){
        switch (flag){
            case APPLE:
                return new Apple();
            case BANANA:
                return new Banana();
            default:
                return null;
        }
    }
}
