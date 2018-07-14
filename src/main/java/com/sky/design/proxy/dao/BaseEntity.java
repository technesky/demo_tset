package com.sky.design.proxy.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @描述: 基础实体类，包含各实体公用属性 .
 * @作者: luck
 * @版本: 1.0 .
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String version;
	
	/**
	 * 创建时间
	 */
	protected Date createTime;
	
	/**
	 * 与userId关联
	 * add by Lee
	 */
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
