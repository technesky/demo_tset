package com.sky.util;

import com.sky.jsoup.bean.Book;
import com.sky.jsoup.bean.BookParam;
import com.sky.jsoup.bean.Parameters;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Jsoup工具类
 * Created by sunzhg on 2016/7/5.
 */
public class JsoupByBeanUtil {

    /**
     * 获取Html Document
     * @param url
     */
    public static Document getHtmlDoc(String url) throws IOException {
        return Jsoup.connect(url).timeout(60000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0").get();
    }

    /**
     * 获取目录信息
     * @param bookParam
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static BlockingQueue<Book> getDir(BookParam bookParam,String dirurl) throws IOException, InterruptedException {
        Document doc = getHtmlDoc(dirurl);
        Element dir =null;
        switch (bookParam.getDirParam().getType()){
            case 1:
                dir = doc.getElementById(bookParam.getDirParam().getId());
                break;
            case 2:
                if (0!=bookParam.getDirParam().getClassIndex()){
                    dir = doc.getElementsByAttributeValue("class",bookParam.getDirParam().getClassName()).get(bookParam.getDirParam().getClassIndex());
                }else {
                    dir = doc.getElementsByAttributeValue("class",bookParam.getDirParam().getClassName()).first();
                }
                break;
            default:
                return new LinkedBlockingQueue<Book>(0);
        }
        Elements links = dir.getElementsByTag(bookParam.getDirParam().getTagName());
        BlockingQueue<Book> bookBlockingQueue = new LinkedBlockingQueue<Book>(links.size());
        int i = 0;
        Book book;
        for (Element link : links) {
            i++;
            if (i > bookParam.getStartIndex()) {
                book =new Book();
                book.setId(i);
                book.setName(link.text());
                book.setUrl(link.attr("href"));
                bookBlockingQueue.put(book);
                if (i==bookParam.getStartIndex()+1){
                    System.out.println("---开始章节名字---"+book.getName());
                }else if (i==links.size()){
                    System.out.println("---结束章节名字---"+book.getName());
                }
            }

        }
        return bookBlockingQueue;
    }


    /***
     * 获取文章内容
     * @param book
     * @return
     * @throws IOException
     */
    public static String getContents(Book book) throws IOException {
        BookParam bookParam=Parameters.bookParam;
        System.out.println("抓取的url------>"+bookParam.getContentParam().getUrl()+book.getUrl());
        Document document=getHtmlDoc(bookParam.getContentParam().getUrl()+book.getUrl());
        String contents="";
        if(null==document) return "";
        switch (bookParam.getContentParam().getType()){
            case 1:
                contents = document.getElementById(bookParam.getContentParam().getId()).text();
                break;
            case 2:
                contents = document.getElementsByAttributeValue("class", bookParam.getContentParam().getClassName()).text();
                break;
            case 3:
                //contents = document.getElementById("BookText").text();
                break;
            default:
                return "";
        }

        if(contents.length()>100){
            //数据过滤
            contents=filterContents(document,contents,bookParam.getShiftWord());
            System.out.println("Succeed - " + book.getId() +"-"+ book.getName());
        }else{
            System.out.println("* Failure - " + book.getId() + "-"+ book.getName());
        }
        return contents;
    }

    /**
     * 过滤文章内容
     * @param document
     * @param contents
     * @param shiftWord
     * @return
     */
    public static String filterContents(Document document,String contents,String[] shiftWord){
        //过滤a标签内容
        Elements elementsa=document.getElementsByTag("a");
        //过滤p标签内容
        Elements elementsp=document.getElementsByTag("p");

        //系统过滤
        for (Element element : elementsp) {
            contents = contents.replaceAll(element.text(), "");
        }

        for (Element element : elementsa) {
            contents = contents.replaceAll(element.text(), "");
        }
        //自定义过滤
        for(String str : shiftWord){
            contents = contents.replaceAll(str, "");
        }

        return contents;
    }

    public static void main(String[] args) {

    }

}
