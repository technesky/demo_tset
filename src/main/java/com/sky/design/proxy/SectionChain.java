package com.sky.design.proxy;


import com.sky.design.proxy.conf.HandlerEnum;
import com.sky.design.proxy.conf.RedisKeyEnum;

import java.lang.reflect.Method;

/**
 * 版块缓存：默认版块、我的版块
 * 		    推荐版块查询量不大，未缓存
 * @author shb
 *
 */
public class SectionChain implements CacheHandler {
	
	@Override
	public Object process(Object[] params, Method method, Object instance,String handlerType,String key) throws Exception{
		//基础校验
		if(method == null || instance == null ){
			throw new Exception();
		}else{
			//我的版块，需要在key后面加上userId
			if(key.equals(RedisKeyEnum.MYSECTION)){
				key = key+params[0];
			} 
			if(handlerType.equals(HandlerEnum.DAO_SEARCH)){
				/*
				if(PoolUtil.checkIsExit(key)){
					return PoolUtil.getString(key);
				}else{
					Object result = method.invoke(instance, params);
					PoolUtil.insertString(key, result.toString());
					return result;
				}*/
				/*String msg="缓存中查询！！！！";
				System.out.println(msg);
				return msg;*/

				Object result = method.invoke(instance, params);
				return result;
			}else{
				String msg="缓存中更新！！！！";
				System.out.println(msg);
				return msg;
			}
		}
	}


}
