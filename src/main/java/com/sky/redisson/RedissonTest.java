package com.sky.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo_tset
 * @description: RedissonTest
 * @author: sunzhg
 * @create: 2018-07-30
 **/
public class RedissonTest {
    private static RedissonClient redissonClient;
    static{
        Config config = new Config();
        config.useSingleServer().setAddress("39.108.7.26:6379").setPassword(null);
        redissonClient = Redisson.create(config);
    }

    public static RedissonClient getRedisson(){

        return redissonClient;
    }

    public static void main(String[] args) throws InterruptedException {
        RLock fairLock = getRedisson().getLock("TEST_KEY");
        System.out.println(fairLock.toString());
        // 尝试加锁，最多等待10秒，上锁以后10秒自动解锁
        boolean res = fairLock.tryLock(10, 10, TimeUnit.SECONDS);
        fairLock.lock();
        System.out.println(res);
        //fairLock.unlock();


        //有界阻塞队列
//      RBoundedBlockingQueue<JSONObject> queue = getRedisson().getBoundedBlockingQueue("anyQueue");
        // 如果初始容量（边界）设定成功则返回`真（true）`，
        // 如果初始容量（边界）已近存在则返回`假（false）`。
//      System.out.println(queue.trySetCapacity(10));
//      JSONObject o=new JSONObject();
//      o.put("name", 1);
//      if(!queue.contains(o)){
//          queue.offer(o);
//      }

//      JSONObject o2=new JSONObject();
//      o2.put("name", 2);
        // 此时容量已满，下面代码将会被阻塞，直到有空闲为止。

//      if(!queue.contains(o2)){
//          queue.offer(o2);
//      }

//      //  获取但不移除此队列的头；如果此队列为空，则返回 null。
//      JSONObject obj = queue.peek();
//      //获取并移除此队列的头部，在指定的等待时间前等待可用的元素（如果有必要）。
//      JSONObject ob = queue.poll(10, TimeUnit.MINUTES);

        //获取并移除此队列的头，如果此队列为空，则返回 null。
//       Iterator<JSONObject> iterator=queue.iterator();
//       while (iterator.hasNext()){
//            JSONObject i =iterator.next();
//            System.out.println(i.toJSONString());
//            iterator.remove();
//
//        }
//          while(queue.size()>0){
//              JSONObject ob = queue.poll();
//              System.out.println(ob.toJSONString());
//          }
//
//      JSONObject someObj = queue.poll();
//      System.out.println(someObj.toJSONString());
    }

}
