package com.sky.design.Observer;

/**
 *
 * Created by sky on 2017/3/27.
 */
public class News {
    private String title;
    private String body;

    public News() {
    }

    public News(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
