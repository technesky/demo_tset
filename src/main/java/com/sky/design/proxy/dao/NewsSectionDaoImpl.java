

package com.sky.design.proxy.dao;


import com.sky.design.proxy.conf.NewsSection;

/**
 * </pre>
 * @描述: 数据访问层基础支撑类.
 * 
 * </pre>
 */
public class NewsSectionDaoImpl extends BaseDaoImpl<NewsSection> implements NewsSectionDao {
	

	
	//默认板块查询
	@Override
	public String selectDefaultSection() {
		String msg="查询数据库！！！";
		System.out.println(msg);
		return msg;
	}


}