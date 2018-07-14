package com.sky.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/6/26.
 */
public class StreamAPI2 {

    List<Employee> employees= Arrays.asList(new Employee("张三",18,9999.78),
            new Employee("李四",58,5555.78),
            new Employee("王五",28,3333.78),
            new Employee("赵六",36,6666.78),
            new Employee("田七",12,8888.78));

    @Test
    public void test1(){
        employees.stream()
                .filter( (e) -> {
                    System.out.println("--------------");
                   return e.getAge()>20;
                })
                .limit(2)
                .forEach(System.out::println);

    }

    /***
     * 映射map
     */
    @Test
    public void test2(){
        List<String> list= Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream().map((s) -> s.toUpperCase())
                .forEach(System.out::println);


        //提取名字
        employees.stream().map(Employee :: getName)
                .forEach(System.out::println);

    }

    /***
     * 排序
     */
    @Test
    public void test3(){

        //排序
        employees.stream().sorted((e1,e2) ->{
            if (e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge() - e2.getAge();
            }
        }).forEach(System.out::println);

    }


    /***
     * 归约
     */
    @Test
    public void test4(){
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum=list.stream().reduce(0,(x,y) -> x+y);
        System.out.println(sum);

        System.out.println("---------------------");

        Optional<Double> doubleOptional=employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(doubleOptional.get());

    }

    /***
     * 收集
     */
    @Test
    public void test5(){

        List<String> stringList=employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        stringList.forEach(System.out::println);


    }
}
