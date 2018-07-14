/**
  * <pre>
  * 系统缩写：news
  * 系统名称：news
  * 组件名称：新闻管理
  * 文件名称：NewsSection.java
  * 作    者：刘广平
  * 创建日期：2016-7-20
  * </pre>
  */

/**
  * <pre>
  * 修改记录：01  
  * 修改日期：2016-7-20
  * 修 改 人：刘广平
  * 修改内容：新建文件
  * </pre>
  */

package com.sky.design.proxy.conf;

import com.sky.design.proxy.dao.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 实体类
 * 数据库表名称：tbl_news_section
 * </pre>
 */
public class NewsSection extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
     * 字段名称：标签名
     * 
     * 数据库字段信息:lableName VARCHAR(10)
     */
    private String lableName;

    /**
     * 字段名称：序号
     * 
     * 数据库字段信息:orderNo INT(10)
     */
    private Long orderNo;

    /**
     * 字段名称：父标签id
     * 
     * 数据库字段信息:parentId BIGINT(19)
     */
    private String parentId;

    /**
     * 字段名称：是否默认版块(0:否，1：是）
     * 
     * 数据库字段信息:isDefault INT(10)
     */
    private String isDefault;

    /**
     * 字段名称：是否推荐版块
     * 
     * 数据库字段信息:isRecommend INT(10)
     */
    private String isRecommend;

    /**
     * 字段名称：是否设为版块(0:否，1：是)
     * 
     * 数据库字段信息:isSection INT(10)
     */
    private String isSection;

    /**
     * 字段名称：是否子节点
     * 
     * 数据库字段信息:isLeaf INT(10)
     */
    private Long isLeaf;

    /**
     * 字段名称：标签编码
     * 
     * 数据库字段信息:code VARCHAR(10)
     */
    private String code;

    /**
     * 字段名称：状态（0：停用，1：启用）
     * 
     * 数据库字段信息:status INT(10)
     */
    private String status;
    private String selectType;//是否选中的状态
    private String attach;//所属标签签
    private Date updateTime;//最后使用时间
    private String updatePeople;//操作人
    private String attachId;//所属标签ID
    /**
     * 字段名称：用户id
     * 
     * 数据库字段信息:userId VARCHARE(40)
     */
    private String userId;
    
    private List<NewsSection> sunSectionCodeList;
    
    
    
    public List<NewsSection> getSunSectionCodeList() {
		return sunSectionCodeList;
	}

	public void setSunSectionCodeList(List<NewsSection> sunSectionCodeList) {
		this.sunSectionCodeList = sunSectionCodeList;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatePeople() {
		return updatePeople;
	}

	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public NewsSection() {
    }	

    public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getLableName() {
        return this.lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

	
    public Long getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

	
    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

	
    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

	
    public String getIsRecommend() {
        return this.isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

	
    public String getIsSection() {
        return this.isSection;
    }

    public void setIsSection(String isSection) {
        this.isSection = isSection;
    }

	
    public Long getIsLeaf() {
        return this.isLeaf;
    }

    public void setIsLeaf(Long isLeaf) {
        this.isLeaf = isLeaf;
    }

	
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}