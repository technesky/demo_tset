package com.sky.jedis;

public interface Function<E, T> {

    public T callBack(E e);

}
