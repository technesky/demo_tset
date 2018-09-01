package com.sky.testBean;

import java.io.Serializable;


/**
 * 
 * 
 * @author sunzhg
 * @email 1992lcg@163.com
 * @date 2018-08-24 22:23:57
 */
public class StatisSalaryInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//姓名
	private String name;
	//工资状态
	private Integer status;
	//金额
	private Integer price;

	private  String month;

	private String year;

	private Integer count;

	public StatisSalaryInfoDO() {super();}

	public StatisSalaryInfoDO(String name, Integer status, Integer price, String month, String year, Integer count) {
		super();
		this.name = name;
		this.status = status;
		this.price = price;
		this.month = month;
		this.year = year;
		this.count = count;
	}
    public StatisSalaryInfoDO(String name, Integer price) {
		super();
        this.name = name;
        this.price = price;
    }
	public StatisSalaryInfoDO(String name, String year) {
		super();
		this.name = name;
		this.year = year;
	}
	public StatisSalaryInfoDO(Integer price, String month) {
		super();
		this.price = price;
		this.month = month;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "StatisSalaryInfoDO{" +
				"name='" + name + '\'' +
				", status=" + status +
				", price=" + price +
				", month='" + month + '\'' +
				", year='" + year + '\'' +
				", count=" + count +
				'}';
	}
}
