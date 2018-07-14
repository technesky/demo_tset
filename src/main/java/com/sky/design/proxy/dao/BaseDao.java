package com.sky.design.proxy.dao;


/**
 * 
 * @描述: 数据访问层基础支撑接口.
 * @作者: luck
 * @创建时间: 2013-7-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param <T>
 */
public interface BaseDao<T> {


	
	public Object getProxy(Object obj);
}
