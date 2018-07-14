package com.sky.design.status;

import java.util.Collections;

/**
 * @program: demo_tset
 * @description: test
 * @author: suznhg
 * @create: 2018-07-13
 **/
public class TestStatus {

    public static void main(String[] args) {
        CommonResponse<Emp> commonResponse=new CommonResponse<>();
        Emp emp=new Emp("abc",1);
        commonResponse.setObj(emp);
        StatusUtil.execto(new ResultInterFace() {
            @Override
            public void success() {
                emp.setName("9999");
                System.out.println("success");
            }

            @Override
            public void fail() {
                System.out.println("fail");

            }
        },commonResponse);

        yyyyy();

        System.out.println(emp.getName());
    }

    private static void yyyyy() {
        String ttt = TestStatus.ttt();
    }


    public static String ttt(){
        System.out.println("3342342");
        return "333";
    }
}
