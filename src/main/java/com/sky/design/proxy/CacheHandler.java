package com.sky.design.proxy;

import java.lang.reflect.Method;

public interface CacheHandler {
	
	/**
	 * 执行缓存相关操作
	 * @param params：方法执行请求参数
	 * @param method：执行的方法体
	 * @param instance：代理对象	
	 * @param handlerType：操作类型（增删该查），参考HandlerEnum
	 * @param key：操作的redis的key
	 * @return
	 * @throws Exception 
	 */
	Object process(Object[] params, Method method, Object instance, String handlerType, String key)  throws Exception;
}
