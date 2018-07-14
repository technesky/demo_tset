package com.sky.jedis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by sky on 2018/4/17.
 */
public class Test {


    private <T> T execute(Function<BookBase, T> fun) {
        BookBase bookBase = new BookBase();
        try {
            // 从连接池中获取到jedis分片对象
           // shardedJedis = shardedJedisPool.getResource();

            System.out.println("从连接池中获取到jedis分片对象");
            return fun.callBack(bookBase);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bookBase) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                System.out.println("关闭，检测连接是否有效");
            }
        }
        return null;
    }

    /**
     * 执行set方法
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return this.execute(new Function<BookBase, String>() {
            @Override
            public String callBack(BookBase bookBase) {
                System.out.println("-----callBack----");
                return bookBase.setName(key);
            }
        });
    }

    public static void main(String[] args) {
        Test test=new Test();
        System.out.println(test.set("你好","000"));

    }
}
