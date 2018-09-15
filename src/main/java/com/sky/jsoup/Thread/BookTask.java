package com.sky.jsoup.Thread;

import com.sky.jsoup.bean.Book;
import com.sky.jsoup.bean.Parameters;
import com.sky.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by sunzhg on 2016/7/5.
 */
public class BookTask {


    public void execute(BlockingQueue<Book> bqueue, BlockingQueue<Book> result){
        //
        ExecutorService service = Executors.newCachedThreadPool();
        //线程数
        int threadNum=20;
        //创建CyclicBarrier对象，
        //并设置执行完一组n个线程的并发任务后，执行LastTask
        CyclicBarrier cb = new CyclicBarrier(threadNum, new LastTask(result));

        for (int i = 0; i < threadNum; i++) {
            service.execute(new MyThread(bqueue,result,cb));
        }
        service.shutdown();

    }

    /**
     *默认的线程池
     * @param bqueue
     */
    public String submit(BlockingQueue<Book> bqueue){
        ExecutorService service = Executors.newCachedThreadPool();

        List<Future<Book>> resultList = new ArrayList<Future<Book>>();
        List<Book> list = new ArrayList<Book>();

        while (bqueue.size()>0){
            //创建10个任务并执行
            //for (int i = 0; i < 10; i++){
                //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
                Future<Book> future = service.submit(new TaskWithResult(bqueue));
                //将任务执行结果存储到List中
                resultList.add(future);
            //}
        }

        //遍历任务的结果   
        for (Future<Book> fs : resultList){
            try{
                while(!fs.isDone());//Future返回如果没有完成，则一直循环等待，直到Future返回完成
                if (null!=fs&&null!=fs.get()){
                    list.add(fs.get());//打印各个线程（任务）执行的结果
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }finally{
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务  
                service.shutdown();
            }
        }
        Collections.sort(list);

        //写文件
        FileUtils.writeFile(Parameters.filePath,list,"UTF-8");
        return "success";
    }

    /**
     * 自定义线程池
     * @param bqueue
     */
    public String poolSubmit(BlockingQueue<Book> bqueue){
        //创建任务等待队列
        BlockingQueue<Runnable> workQueue  = new ArrayBlockingQueue<Runnable>(bqueue.size());
        //创建线程池，池中保存的线程数为5，允许的最大线程数为10，50秒
        ThreadPoolExecutor pool=new ThreadPoolExecutor(10,20,60,TimeUnit.SECONDS,workQueue);
        //返回对象集合
        List<Future<Book>> resultList = new ArrayList<Future<Book>>();
        List<Book> list = new ArrayList<Book>();
        //第一种方法
        /**其中，重写的rejectedExecution()方法调用了getQueue()方法，得到了workQueue，再调用其put()方法，将task放到workQueue中，而这个put()方法是阻塞的
         * 这就达到了想要的效果：当workQueue满时，submit()一个task会导致调用我们自定义的RejectedExecutionHandler，
         * 而我们自定义的RejectedExecutionHandler会保证该task继续被尝试用阻塞式的put()到workQueue中。
         * 尽管这种方法非常简单，但是使用它是非常不好的，原因包括但不限于：
         * [1] ThreadPoolExecutor的API不建议这样做
         */
                /*pool.setRejectedExecutionHandler(new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()){
                            try {
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });*/
        //第二种方法
        /**
         * 当使用这种拒绝策略时，如果workQueue满了，ThreadPoolExecutor就会在调用者线程（即生产者线程）
         * 中执行要提交的task——生产者线程帮消费者线程干了自己“不应该干的活”。
         */
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        boolean status=true;
        while (bqueue.size()>0&&status){
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<Book> future = pool.submit(new TaskWithResult(bqueue));
            //将任务执行结果存储到List中
            resultList.add(future);
            if (pool.getTaskCount()>bqueue.size()){
                status=false;
            }
            System.out.println("------任务数量--->" + pool.getTaskCount() + "---线程数---：" + pool.getLargestPoolSize());
        }
        System.out.println("结束后任务队列数量:"+resultList.size());
        //遍历任务的结果
        for (Future<Book> fs : resultList){
            try{
                //Future返回如果没有完成，则一直循环等待，直到Future返回完成
                while(!fs.isDone());
                if (null!=fs&&null!=fs.get()){
                    //将各个线程（任务）执行的结果放入list
                    list.add(fs.get());
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }finally{
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                pool.shutdown();
            }
        }
        Collections.sort(list);

        //写文件
        FileUtils.writeFile(Parameters.filePath,list,"UTF-8");

        return "success";
    }

    public String BookSubmit(BlockingQueue<Book> bqueue){
        //创建任务等待队列
        BlockingQueue<Runnable> workQueue  = new ArrayBlockingQueue<Runnable>(bqueue.size());
        //创建线程池，池中保存的线程数为5，允许的最大线程数为10，50秒
        ThreadPoolExecutor pool=new ThreadPoolExecutor(10,20,60,TimeUnit.SECONDS,workQueue);
        //返回对象集合
        List<Future<Book>> resultList = new ArrayList<Future<Book>>();
        List<Book> list = new ArrayList<Book>();
        System.out.println("开始前任务队列数量:"+bqueue.size());
        while (bqueue.size()>0){
            try {
                Book book=bqueue.poll(2, TimeUnit.SECONDS);
                //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
                Future<Book> future = pool.submit(new TaskWithBook(book));
                //将任务执行结果存储到List中
                resultList.add(future);
                System.out.println("------任务数量--->" + pool.getTaskCount()+ "---活动线程数---：" + pool.getActiveCount() + "---线程数---：" + pool.getLargestPoolSize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bqueue数量:"+bqueue.size());
        }
        System.out.println("结束后任务队列数量:"+bqueue.size());
        //遍历任务的结果
        for (Future<Book> fs : resultList){
            try{
                //Future返回如果没有完成，则一直循环等待，直到Future返回完成
                while(!fs.isDone());
                if (null!=fs&&null!=fs.get()){
                    //将各个线程（任务）执行的结果放入list
                    list.add(fs.get());
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }finally{
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                pool.shutdown();
            }
        }
        Collections.sort(list);

        //写文件
        FileUtils.writeFile(Parameters.filePath,list,"UTF-8");

        return "success";
    }
}
