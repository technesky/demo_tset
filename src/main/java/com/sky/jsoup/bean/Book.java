package com.sky.jsoup.bean;


/**
 * bookbean
 * Created by sunzhg on 2016/7/5.
 */
public class Book implements Comparable<Book>{
    private int id;
    private String name;
    private String url;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(Book o) {
        return this.id-o.id;
    }
}
