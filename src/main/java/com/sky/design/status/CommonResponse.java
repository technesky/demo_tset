package com.sky.design.status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString(callSuper = true)
public class CommonResponse<T> implements Serializable {

    private String org_order_no;
    private String order_no;
    private boolean is_success;
    private T obj;
    private String retmsg = "";
    private String retcode = "0000";//交易失败

}
