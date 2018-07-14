package com.sky.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * ArrayList 交集 并集 差集  对象去重等
 * Created by sky on 2017/2/20.
 */
public class Demo9 {

    public static void main(String[] args) {
        List<String> listA=new ArrayList<>();
        List<String> listB=new ArrayList<>();

        /*for (int i = 0; i <1000000 ; i++) {
            listA.add("test"+i);
            if (i>100 && i<=200){
                listB.add("test"+i);
            }
            if (i>200 && i<=250){
                listB.add("testB"+i);
            }
        }*/
        //实体
        List<Tbean> listC=new ArrayList<>();
        List<Tbean> listD=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Tbean tbean=new Tbean();
            tbean.setId(i);
            tbean.setName("test"+i);
            listC.add(tbean);

            Tbean t2=new Tbean();
            t2.setId(i);
            t2.setName("test"+i);

            if (i>1 && i<=5){
                listD.add(t2);
            }
            if (i>5 && i<=7){

                t2.setName("testB"+i);
                listD.add(t2);
            }
        }
        Long startTime=System.currentTimeMillis();

        System.out.println("A:size="+listA.size()+" B:size="+listB.size());
        //交集
        //listA.retainAll(listB);
        //并集
        //listA.addAll(listB);
        //无重复并集
        //listA.removeAll(listB);
        //listA.addAll(listB);

        System.out.println("A:size="+listA.size()+" B:size="+listB.size());

        System.out.println("C:size="+listC.size()+" D:size="+listD.size());

        //实体测试
        listC.removeAll(listD);
        listC.addAll(listD);

        System.out.println("C:size="+listC.size()+" D:size="+listD.size());

        System.out.println("共用时："+(System.currentTimeMillis()-startTime));

    }


}

class Tbean{
    private int id;
    private String name;
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tbean tbean = (Tbean) o;

        if (id != tbean.id) return false;
        return name.equals(tbean.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

}
