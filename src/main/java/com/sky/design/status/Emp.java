package com.sky.design.status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: demo_tset
 * @description: emp
 * @author: suznhg
 * @create: 2018-07-13
 **/
@Setter
@Getter
@ToString(callSuper = true)
public class Emp implements Serializable {

    private String name;
    private int id;

    public Emp(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
