package com.sky.jsoup.bean;


import java.util.Properties;

/**
 * 常量参数
 * Created by sunzhg on 2016/7/14.
 */
public class Parameters {
    public static String dirurl;
    //章节url
    public static String finurl;
    //保存路径
    public static String filePath;
    //开始章节
    public static String startIndex;
    //屏蔽关键字
    public static String[] shiftWord;
    //目录获取参数
    public static String[] dirParam;
    //内容获取参数
    public static String[] contentParam;

    public static BookParam bookParam;

    public void init(Properties p) {
        //文章目录
        this.dirurl = p.getProperty("dirurl");
        //章节url
        this.finurl = p.getProperty("finurl");
        //保存路径
        this.filePath = p.getProperty("filePath");
        //开始章节
        this.startIndex = p.getProperty("startIndex");
        //屏蔽关键字
        this.shiftWord = p.getProperty("shiftWord").split("@");
        //目录获取参数
        this.dirParam = p.getProperty("dirParam").split("@");
        //内容获取参数
        this.contentParam = p.getProperty("contentParam").split("@");
    }

    public void createBookParam() {
        BookParam bookParam = new BookParam();
        bookParam.setShiftWord(this.shiftWord);
        bookParam.setStartIndex(Integer.parseInt(this.startIndex));

        HtmlParam dirParamBean = null;
        if (this.dirParam.length == 2) {
            dirParamBean = new HtmlParam(1);
            dirParamBean.setId(dirParam[0]);
            dirParamBean.setTagName(dirParam[1]);
        } else if (this.dirParam.length == 3) {
            dirParamBean = new HtmlParam(2);
            dirParamBean.setClassName(dirParam[1]);
            dirParamBean.setTagName(dirParam[2]);//
        } else if (this.dirParam.length == 4) {
            dirParamBean = new HtmlParam(2);
            dirParamBean.setClassName(dirParam[1]);
            dirParamBean.setClassIndex(Integer.parseInt(dirParam[2]));
            dirParamBean.setTagName(dirParam[3]);
        }
        dirParamBean.setUrl(dirurl);
        bookParam.setDirParam(dirParamBean);
        HtmlParam conParamBean = null;
        if (this.contentParam.length == 1) {
            conParamBean = new HtmlParam(1);
            conParamBean.setId(contentParam[0]);
        } else if (this.contentParam.length == 2) {
            conParamBean = new HtmlParam(2);
            conParamBean.setClassName(contentParam[1]);
        } else if (this.contentParam.length == 3) {
            conParamBean = new HtmlParam(2);
            conParamBean.setClassName(contentParam[1]);
            conParamBean.setClassIndex(Integer.parseInt(contentParam[2]));
        }
        conParamBean.setUrl(finurl);
        bookParam.setContentParam(conParamBean);

        this.bookParam = bookParam;
    }

}
