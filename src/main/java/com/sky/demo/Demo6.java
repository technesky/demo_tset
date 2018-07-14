package com.sky.demo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparable and Comparator
 * Created by sunzhg on 2016/9/22.
 */
class Emp implements Comparable<Emp> {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
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
    public int compareTo(Emp o) {
        if (this.id > o.id) {
            return 1;
        } else if (this.id < o.id) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Emp2 {
    private int id;
    private String name;

    public Emp2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Emp2() {
    }

    @Override
    public String toString() {
        return "Emp2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}

class EmpComparator implements Comparator<Emp2> {

    @Override
    public int compare(Emp2 o1, Emp2 o2) {
        if (o1.getId() < o2.getId()) {
            return 1;
        } else if (o1.getId() > o2.getId()) {
            return -1;
        } else {
            return 0;
        }
    }
}

public class Demo6 {
    public static void main(String[] args) {
        //第一种比较器
        Emp emp[] = new Emp[]{
                new Emp(1, "zhangsan"),
                new Emp(3, "lisi"),
                new Emp(2, "wangwu")
        };
        Arrays.sort(emp);
        System.out.println(Arrays.toString(emp));
        //第二种 挽救比较器  不改变对象类的前提下比较
        Emp2 emp2[] = new Emp2[]{
                new Emp2(1, "zhangsan"),
                new Emp2(3, "lisi"),
                new Emp2(2, "wangwu")
        };
        Arrays.sort(emp2, new EmpComparator());
        System.out.println(Arrays.toString(emp2));
    }
}
