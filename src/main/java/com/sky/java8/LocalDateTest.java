package com.sky.java8;

import org.junit.Test;

import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: demo_tset
 * @description: LocalDateTest
 * @author: suznhg
 * @create: 2018-08-28
 **/
public class LocalDateTest {

    List<Employee> employees= Arrays.asList(
            new Employee("张三",18,9999.78,LocalDate.of(2018,8,5)),
            new Employee("李四",58,5555.78,LocalDate.of(2018,8,6)),
            new Employee("王五",28,3333.78,LocalDate.of(2018,8,7)),
            new Employee("赵六",36,6666.78,LocalDate.of(2018,8,10)),
            new Employee("田七",12,8888.78,LocalDate.of(2018,8,15)));


    @Test
    public void test1(){
        //.LocalDate转Date
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + date);


    }

    @Test
    public void test2(){
        //Date转LocalDate
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println("Date = " + date);
        System.out.println("LocalDate = " + localDate);
    }


    @Test
    public void test3(){

        List<LocalDate> list=employees.stream().map(Employee::getLocalDate).collect(Collectors.toList());

        LocalDate one=LocalDate.of(2018,8,1);
        LocalDate two=LocalDate.of(2018,8,31);
        int days=Period.between(one,two).getDays();

        for (int i = 0; i <=days ; i++) {

            int finalI = i;
            List<Employee> liste=employees.stream().filter((e)->{
                return e.getLocalDate().compareTo(one.plusDays(finalI))==0;
            }).collect(Collectors.toList());

            liste.forEach((e)->{
                System.out.println(e.getLocalDate()+"==="+e.getName());

            });
/*
            if (list.contains(one.plusDays(i))){
                System.out.println(one.plusDays(i)+"   true");
            }else{
                System.out.println(one.plusDays(i)+"   false");
            }*/

            /*for (Employee e:employees) {
                if (one.plusDays(i).compareTo(e.getLocalDate())!=0){
                    System.out.println(one.plusDays(i)+"---"+e.getLocalDate());
                }else{
                    System.out.println(one.plusDays(i)+"====="+e.getLocalDate());

                }
            }*/

        }



        System.out.println(Period.between(one,two).getDays());
    }

}
