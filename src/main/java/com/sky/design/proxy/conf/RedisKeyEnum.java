package com.sky.design.proxy.conf;

/**
 * 固定操作key，定义
 * @author shb
 *
 */
public class RedisKeyEnum {
	
	//版块系列
	public static final String DEFAULTSECTION = "GUDUO_MOBILE_SECTION_MOREN";//默认版块
    public static final String MYSECTION = "GUDUO_MOBILE_SECTION_MYSECTION_";//我的版块
    public static final String RECOMMENDSECTION ="GUDUO_MOBILE_SECTION_TUIJIAN_";//推荐版块
	
    //热点新闻
    public static final String HOTNEWS ="HOT_NEWS";//热点新闻
    /**
     * 新闻详情缓存-key
     */
    public static final String NEWSDETAIL ="GUDUO_MOBILE_NEWS_DETAIL_";
    
    /**
     * 所有一级爆料缓存-key
     */
    //最热
    public static final String YIJIBROKEHOT ="GUDUO_MOBILE_NEWS_YIJIBROKE_HOT_";
    //最新
    public static final String YIJIBROKENEW ="GUDUO_MOBILE_NEWS_YIJIBROKE_NEW_";
    //最有价值
    public static final String YIJIBROKEWORTH ="GUDUO_MOBILE_NEWS_YIJIBROKE_WORTH_";
}
