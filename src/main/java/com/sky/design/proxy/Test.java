package com.sky.design.proxy;

import com.sky.design.proxy.dao.NewsSectionDao;
import com.sky.design.proxy.dao.NewsSectionDaoImpl;

/**
 * 代理使用redis缓存
 * Created by sky on 2017/5/10.
 */
public class Test {




    public static void main(String[] args) {

        NewsSectionDao dao=new NewsSectionDaoImpl();
        System.out.println("执行代理开始！");

        NewsSectionDao tt=(NewsSectionDao)dao.getProxy(dao);
        System.out.println("执行代理结束！");
        tt.selectDefaultSection();

    }
}
