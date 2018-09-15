package com.sky.jsoup.Thread;

import com.sky.jsoup.bean.Book;
import com.sky.jsoup.bean.Parameters;
import com.sky.jsoup.core.CreateTxtMain;
import com.sky.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 最后一个任务
 * Created by sunzhg on 2016/7/6.
 */
public class LastTask implements Runnable {
    private BlockingQueue<Book> result;

    public LastTask(BlockingQueue<Book> result){
        this.result=result;
    }

    @Override
    public void run() {
        System.out.println("最后的一个任务执行...总记录数："+result.size());
        Iterator<Book> it=result.iterator();
        List<Book> list=new ArrayList<>();
        while (it.hasNext()){
            list.add(it.next());
        }
        Collections.sort(list);
        //写文件
        FileUtils.writeFile(Parameters.filePath, list, "UTF-8");
        //
        long endTime=System.currentTimeMillis();

        System.out.println("run time: "+(endTime-CreateTxtMain.startTime)/1000+"s");
    }
}
