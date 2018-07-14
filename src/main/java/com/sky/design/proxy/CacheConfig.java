package com.sky.design.proxy;


/**
 * 缓存配置文件
 * @author shb
 *
 */
public class CacheConfig {
	
	//缓存开关
	public static boolean isCache=true;

	public static boolean isCache() {
		return isCache;
	}

	public static void setCache(boolean isCache) {
		CacheConfig.isCache = isCache;
	}
	
}
