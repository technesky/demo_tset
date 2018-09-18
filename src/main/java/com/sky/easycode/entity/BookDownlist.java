package com.sky.easycode.entity;

import java.util.Date;

/**
 * 小说列表(BookDownlist)表实体类
 *
 * @author makejava
 * @since 2018-09-05 17:33:21
 */
public class BookDownlist {
    //编号
    private Long id;
    //书名
    private String name;
    //下载地址
    private String addr;
    //备注信息
    private String remarks;
    //创建时间
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}