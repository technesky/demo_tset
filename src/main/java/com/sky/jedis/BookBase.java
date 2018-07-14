package com.sky.jedis;

/**
 * Created by sky on 2018/4/17.
 */
public class BookBase {
    private String name;
    private Integer size;

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return  "name="+name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
