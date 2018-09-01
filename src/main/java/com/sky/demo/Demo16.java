package com.sky.demo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sky.testBean.DataList;
import com.sky.testBean.StatisSalaryInfoDO;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo_tset
 * @description: fastjson
 * @author: suznhg
 * @create: 2018-08-31
 **/
public class Demo16 {
    @Test
    public void test1(){
        List<StatisSalaryInfoDO> list=new ArrayList<>();

        StatisSalaryInfoDO ssi1=new StatisSalaryInfoDO("张三", 1, 341, "2", "2018", 1);
        StatisSalaryInfoDO ssi2=new StatisSalaryInfoDO("李四", 1, 341, "2", "2018", 1);
        StatisSalaryInfoDO ssi3=new StatisSalaryInfoDO("王五", 1, 341, "2", "2018", 1);
        list.add(ssi1);
        list.add(ssi2);
        list.add(ssi3);
        String str=JSON.toJSONString(ssi1);
        byte[] bytes=JSON.toJSONString(list, SerializerFeature.WriteClassName).getBytes(Charset.forName("UTF-8"));
        String str2 = new String(bytes, Charset.forName("UTF-8"));

        System.out.println(str);
        System.out.println(str2);

        List<StatisSalaryInfoDO> m=JSON.parseObject(str2, new TypeReference<List<StatisSalaryInfoDO>>(){});
        m.forEach(System.out::println);

        DataList dataLis=new DataList();
        dataLis.setList(list);
        byte[] bytesdd=JSON.toJSONString(dataLis, SerializerFeature.WriteClassName).getBytes(Charset.forName("UTF-8"));
        String str3 = new String(bytesdd, Charset.forName("UTF-8"));
        System.out.println("sta3=="+str3);

        JSONObject m2= JSON.parseObject(str);

        DataList m3= JSON.parseObject(str3,DataList.class);
        System.out.println(m3.getList().get(2).toString());

    }
}
