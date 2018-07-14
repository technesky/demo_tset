package com.sky.java8;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @Title: StreamAPI
 * @Package: com.sky.java8
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/6/6 20:26
 */
public class StreamAPI {

    /**
     * 操作流步住
     * 1.创建流
     * 2.中间操作
     * 3.终止操作
     */

    /**
     * 流的创建方式
     */
    @Test
    public void test1(){
        //
        List<String> list=new ArrayList<>();
        Stream<String> stringStream=list.stream();

        Integer[] ints=new Integer[5];
        Stream<Integer> integerStream=Arrays.stream(ints);

        Stream<String> stream=Stream.of("aa","bb","cc");

        //4创建无线流
        Stream<Integer> stream1=Stream.iterate(0,(x) -> x+2);


    }

    /**
     * 例如，我们希望统计一个字符串类型数组中，所有长度大于3的元素。
     */
    @Test
    public void test(){
        String[] strArr = { "Java8", "new", "feature", "Stream", "API" };
       /* int count = 0;
        for (String s : strArr) {
            if (s.length() > 3)
                count++;
        }*/

        long count = Stream.of(strArr).filter(w -> w.length() > 3).count();

        System.out.println("count="+count);
    }


    /**
     * 串行排序：
     */
    @Test
    public void testsequential(){

        List<String> list = new ArrayList<String>();
        for(int i=0;i<1000000;i++){
            double d = Math.random()*1000;
            list.add(d+"");
        }
        long start = System.nanoTime();
        //获取系统开始排序的时间点
        int count= (int) ((Stream) list.stream().sequential()).sorted().count();
        long end = System.nanoTime();
        // 获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);
        //得到串行排序所用的时间
        System.out.println(ms+"ms");
    }

    /**
     * 并行排序：
     */
    @Test
    public void testsparallel(){

        List<String> list = new ArrayList<String>();
        for(int i=0;i<1000000;i++){
            double d = Math.random()*1000;
            list.add(d+"");
        }
        long start = System.nanoTime();//获取系统开始排序的时间点
        int count = (int)((Stream) list.stream().parallel()).sorted().count();
        long end = System.nanoTime();
        //获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到并行排序所用的时间
        System.out.println(ms+"ms"+"\t count="+count);
    }

    /**
     * sort
     */
    @Test
    public void testSort(){
        List<Integer>  list = new ArrayList<Integer>();
        list.add(50);
        list.add(18);
        list.add(6);
        list.add(99);
        list.add(32);
        System.out.println(list.toString()+"排序之前");
        Collections.sort(list, (a, b) -> {
            return a-b;
        });
        System.out.println(list.toString()+"排序之后");


        List<String>  stringList = new ArrayList<String>();
        stringList.add("a");
        stringList.add("d");
        stringList.add("c");
        stringList.add("b");
        stringList.add("z");

        System.out.println(stringList.toString()+"排序之前");

        stringList.sort(String.CASE_INSENSITIVE_ORDER);

        System.out.println(stringList.toString()+"排序之后");

        //按字母排序字符串列表
        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );
        System.out.println(cities);
        //[Milan, london, San Francisco, Tokyo, New Delhi]
        //String.CASE_INSENSITIVE_ORDER（返回不区分大小写的比较器）之间的差异。
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(cities);
        //[london, Milan, New Delhi, San Francisco, Tokyo]
        //是为了更好地突出 Comparator.naturalOrder() （返回首先排序大写字母的比较器
        cities.sort(Comparator.naturalOrder());
        System.out.println(cities);
        //[Milan, New Delhi, San Francisco, Tokyo, london]


    }

}