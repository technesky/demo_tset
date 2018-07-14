package com.sky.design.proxy.dao;



import com.sky.design.proxy.DaoInvocationHandler;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @描述: 数据访问层基础支撑类.
 * @作者: luck
 * @创建时间: 2013-7-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param <T>
 */
public abstract class BaseDaoImpl<T extends BaseEntity>  implements BaseDao<T>{

	
	public Object getProxy(Object obj){
		DaoInvocationHandler handler = new DaoInvocationHandler(obj);
		return Proxy.newProxyInstance(handler.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}

}
