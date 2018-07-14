package com.sky.design.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis缓存
 * @author shb
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCache {
	
	/**
	 * 缓存的key值
	 */
	String key();
	
	/**
	 * 操作类型 :类型参考HandlerType枚举
	 */
	String handlerType();
	
	/**
	 * 缓存执行类
	 * @return
	 */
	String cacheHandlerName();
}
