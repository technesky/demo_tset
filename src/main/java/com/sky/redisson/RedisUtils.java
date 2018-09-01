package com.sky.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
 
/***
 * Redis client的辅助工具类
 * 用于连接Redis服务器 创建不同的Redis Server对应的客户端对象
 * @author u1
 *
 */
public class RedisUtils {
	
	private static RedisUtils redisUtils;
	
	private RedisUtils(){}
	
	/**
	 * 提供单例模式
	 * @return
	 */
	public static RedisUtils getInstance(){
		if(redisUtils==null) 
			synchronized (RedisUtils.class) {
				if(redisUtils==null) redisUtils=new RedisUtils();
			}
		return redisUtils;
	}

	/**
	 * 使用ip地址和端口创建Redisson
	 * @param ip
	 * @param port
	 * @return
	 */
	public RedissonClient getRedisson(String ip,String port){
		Config config = new Config();
		config.useSingleServer().setAddress(ip+":"+port).setPassword(null);
		System.out.println("成功连接Redis Server"+"\t"+"连接"+ip+":"+port+"服务器");
		return Redisson.create(config);
	}
	
	/**
	 * 关闭Redisson客户端连接
	 * @param redissonClient
	 */
	public void closeRedisson(RedissonClient redissonClient){
		redissonClient.shutdown();
		System.out.println("成功关闭Redis Client连接");
	}

	/**
	 * 获取字符串对象
	 * @param redisson
	 * @param objectName
	 * @param <T>
	 * @return
	 */
	public <T> RBucket<T> getRBucket(RedissonClient redisson, String objectName){
		RBucket<T> bucket=redisson.getBucket(objectName);
		return bucket;
	}
	
	/**
	 * 获取Map对象
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <K,V> RMap<K, V> getRMap(RedissonClient redisson, String objectName){
		RMap<K, V> map=redisson.getMap(objectName);
		return map;
	}
	
	/**
	 * 获取有序集合
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RSortedSet<V> getRSortedSet(RedissonClient redisson, String objectName){
		RSortedSet<V> sortedSet=redisson.getSortedSet(objectName);
		return sortedSet;
	}
	
	/**
	 * 获取集合
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RSet<V> getRSet(RedissonClient redisson, String objectName){
		RSet<V> rSet=redisson.getSet(objectName);
		return rSet;
	}
	
	/**
	 * 获取列表
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RList<V> getRList(RedissonClient redisson, String objectName){
		RList<V> rList=redisson.getList(objectName);
		return rList;
	}
	
	/**
	 * 获取队列
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RQueue<V> getRQueue(RedissonClient redisson,String objectName){
		RQueue<V> rQueue=redisson.getQueue(objectName);
		return rQueue;
	}
	
	/**
	 * 获取双端队列
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <V> RDeque<V> getRDeque(RedissonClient redisson,String objectName){
		RDeque<V> rDeque=redisson.getDeque(objectName);
		return rDeque;
	}
	
	/**
	 * 此方法不可用在RedissonClient 1.2 中
	 * 在1.2.2版本中 可用
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	/**
	public <V> RBlockingQueue<V> getRBlockingQueue(RedissonClient redisson,String objectName){
		RBlockingQueue rb=redisson.getBlockingQueue(objectName);
		return rb;
	}*/
	
	/**
	 * 获取锁
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public RLock getRLock(RedissonClient redisson,String objectName){
		RLock rLock=redisson.getLock(objectName);
		return rLock;
	}
	
	/**
	 * 获取原子数
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public RAtomicLong getRAtomicLong(RedissonClient redisson,String objectName){
		RAtomicLong rAtomicLong=redisson.getAtomicLong(objectName);
		return rAtomicLong;
	}
	
	/**
	 * 获取记数锁
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public RCountDownLatch getRCountDownLatch(RedissonClient redisson,String objectName){
		RCountDownLatch rCountDownLatch=redisson.getCountDownLatch(objectName);
		return rCountDownLatch;
	}
	
	/**
	 * 获取消息的Topic
	 * @param redisson
	 * @param objectName
	 * @return
	 */
	public <M> RTopic<M> getRTopic(RedissonClient redisson,String objectName){
		 RTopic<M> rTopic=redisson.getTopic(objectName);
		 return rTopic;
	}
	
	
}
