package com.sky.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties工具类
 */
public class PropertiesUtil {

	/*加载配置文件*/
	public  Properties load(String path){

		System.out.println("加载配置文件  "+path);
		InputStream is=this.getClass().getResourceAsStream(path);

		Properties p = new Properties();

		try {
    		p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return p;

	}

}
