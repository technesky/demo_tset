package com.sky.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Dao层调用代理 m
 * @author shb
 *
 */

public class DaoInvocationHandler implements InvocationHandler{

	private Object instance;
	
	public DaoInvocationHandler(Object instance) {
		this.instance = instance;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		try {
			Object reback = null;
			//缓存开关打开，且方法标注缓存注解
			if(CacheConfig.isCache && method.isAnnotationPresent(RedisCache.class)){
				//获取注解相关信息
				RedisCache cache = method.getAnnotation(RedisCache.class);
				String type = cache.handlerType();//获取操作类型
				String cacheHandlerName = cache.cacheHandlerName();//获取处理类
				String key = cache.key();
				Class<?> cla = Class.forName(cacheHandlerName);
				Method[] methods = cla.getMethods();
				for(Method process:methods){
					if(process.getName().toString().equals("process")){
						reback = process.invoke(cla.newInstance(),args,method,instance,type,key);
						break;
					}
				}
				return reback;
			}else{
				return method.invoke(instance, args);
			}
		} catch (Exception e) {
			return method.invoke(instance, args);
		}
	}
	
}
