/**
  * <pre>
  * 系统缩写：news
  * 系统名称：news
  * 组件名称：新闻管理
  * 文件名称：NewsSectionDao.java
  * 作    者：刘广平
  * 创建日期：2016-7-20
  * </pre>
  */

/**
  * <pre>
  * 修改记录：01  
  * 修改日期：2016-7-20
  * 修 改 人：刘广平
  * 修改内容：新建文件
  * </pre>
  */

package com.sky.design.proxy.dao;


/*import com.guduo.model.news.NewsSection;
import com.guduo.model.publish.SectionRelation;
import com.guduo.redis.config.HandlerEnum;
import com.guduo.redis.config.RedisCache;
import com.guduo.redis.config.RedisKeyEnum;
import com.guduo.redis.config.RedisCache;*/

import com.sky.design.proxy.RedisCache;
import com.sky.design.proxy.conf.HandlerEnum;
import com.sky.design.proxy.conf.NewsSection;
import com.sky.design.proxy.conf.RedisKeyEnum;

/**
 * <pre>
 * @描述:数据访问层接口.
 * 表
 * </pre>	
 */
public interface NewsSectionDao extends BaseDao<NewsSection> {

	
	//查询默认板块
	@RedisCache(key= RedisKeyEnum.DEFAULTSECTION,handlerType= HandlerEnum.DAO_SEARCH,cacheHandlerName="com.sky.design.proxy.SectionChain")
	String selectDefaultSection();

    
}