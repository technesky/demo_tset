package com.sky.jsoup.bean;

/**
 * Created by sunzhg on 2016/9/20.
 */
public class BookParam {
    private int startIndex;//开始章节
    private String[] shiftWord;//过滤内容
    private HtmlParam dirParam;//获取目录参数
    private HtmlParam contentParam;//获取内容参数

    public BookParam() {
        this.startIndex = 0;
    }


    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String[] getShiftWord() {
        return shiftWord;
    }

    public void setShiftWord(String[] shiftWord) {
        this.shiftWord = shiftWord;
    }

    public HtmlParam getDirParam() {
        return dirParam;
    }

    public void setDirParam(HtmlParam dirParam) {
        this.dirParam = dirParam;
    }

    public HtmlParam getContentParam() {
        return contentParam;
    }

    public void setContentParam(HtmlParam contentParam) {
        this.contentParam = contentParam;
    }
}
