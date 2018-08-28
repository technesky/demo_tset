package com.sky.java8;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/26.
 */
public class Employee {
    private String name;
    private int age;
    private double salary;
    private Date date;
    private LocalDate localDate;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, int age, double salary, Date date) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.date = date;
    }

    public Employee(String name, int age, double salary,  LocalDate localDate) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.localDate = localDate;
    }

    public Employee() {
    }


}
