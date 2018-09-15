package com.sky.utils;

import com.sky.jsoup.bean.Book;
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
public class JsoupUtil {

    /**
     * 获取Html Document
     * @param url
     */
    public static Document getHtmlDoc(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(60000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取模版
     * @param doc
     * @param strs
     * @return
     * @throws InterruptedException
     */
    public static BlockingQueue<Book> getBookElement(Document doc,String... strs) throws InterruptedException {
        int startIndex= Integer.parseInt(Parameters.startIndex);
        switch (strs.length){
            case 2:
                return getElement(startIndex,doc,strs[0],strs[1]);
            case 3:
                return getElement(startIndex,doc,strs[0],strs[1],strs[2]);
        }
        return null;
    }

    /**
     * 模版1
     * @param startIndex
     * @param doc
     * @param id
     * @param a
     * @return
     * @throws InterruptedException
     */
    public static BlockingQueue<Book> getElement(int startIndex,Document doc,String id,String a) throws InterruptedException {
        Element content = doc.getElementById(id);
        Elements links = content.getElementsByTag(a);
        BlockingQueue<Book> bookBlockingQueue = new LinkedBlockingQueue<Book>(links.size());
        int i = 0;
        Book book;
        for (Element link : links) {
            i++;
            if (i > startIndex) {
                book =new Book();
                book.setId(i);
                book.setName(link.text());
                book.setUrl(link.attr("href"));
                bookBlockingQueue.put(book);
            }

        }
        return bookBlockingQueue;
    }

    /**
     * 模版2
     * @param startIndex
     * @param doc
     * @param classStr
     * @param key
     * @param a
     * @return
     * @throws InterruptedException
     */
    public static BlockingQueue<Book> getElement(int startIndex,Document doc,String classStr,String key,String a) throws InterruptedException {
        Element content = doc.getElementsByAttributeValue(classStr,key).first();
        Elements links = content.getElementsByTag(a);
        BlockingQueue<Book> bookBlockingQueue = new LinkedBlockingQueue<Book>(links.size());
        int i = 0;
        Book book;
        for (Element link : links) {
            i++;
            if (i > startIndex) {
                book =new Book();
                book.setId(i);
                book.setName(link.text());
                book.setUrl(link.attr("href"));
                bookBlockingQueue.put(book);
                if (i==startIndex+1){
                    System.out.println("---开始章节名字---"+book.getName());
                }else if (i==links.size()){
                    System.out.println("---结束章节名字---"+book.getName());
                }
            }

        }
        return bookBlockingQueue;
    }

    /**
     *
     * @param book
     * @return
     */
    public static String getBookContents(Book book){
        String[] shiftWord= Parameters.shiftWord;
        String finurl=Parameters.finurl+book.getUrl();
        String[] sp=Parameters.contentParam;
        return getContents(book.getId(), book.getName(), finurl,shiftWord,sp);
    }

    /**
     * 获取文章内容
     * @param index
     * @param eName
     * @param conUrl
     * @param shiftWord
     * @return
     */
    public static String getContents(int index,String eName, String conUrl, String[] shiftWord,String... strings){
        System.out.println("抓取的url------>"+conUrl);
        Document document=getHtmlDoc(conUrl);
        String contents="";
        if(null==document) return "";
        switch (strings.length){
            case 1:
                contents = document.getElementById(strings[0]).text();
                break;
            case 2:
                contents = document.getElementsByAttributeValue(strings[0], strings[1]).text();
                break;
            case 3:
                //contents = document.getElementById("BookText").text();
                break;
        }

        if(contents.length()>100){
            //数据过滤
            contents=filterContents(document,contents,shiftWord);
            System.out.println("Succeed - " + index +"-"+ eName);
        }else{
            System.out.println("* Failure - " + index + "-"+ eName);
        }
        return contents;
    }

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
