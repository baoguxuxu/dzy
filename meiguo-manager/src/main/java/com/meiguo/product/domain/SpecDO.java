package com.meiguo.product.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 商品规格表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 15:18:22
 */
public class SpecDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//规格ID
	private Long id;
				   
	//商品规格编号
	private Long productSpecId;
	//规格名称
	private String name;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//规格创建者
	private String createBy;
	//规格修改者
	private String updateBy;
	//商品规格描述
	private String remark;
	//规格状态：0,启用；1,禁用
	private Integer status;
	//数据删除标志(0:数据没有执行删除操作        1：数据被执行了删除操作)
	private Integer deleteEnable;
	//商品分类id
	private Long categoryId;
	//商品分类名称
	private String categoryName;
	/**
	 * 设置商品分类名称
	 */
    public String getCategoryName() {
		return categoryName;
	}
    /**
     * 获取商品分类名称
     */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
     * 设置商品分类id
     */
	public Long getCategoryId() {
		return categoryId;
	}
	/**
	 * 获取商品分类id
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 获取商品规格的删除状态
	 */
	public Integer getDeleteEnable() {
		return deleteEnable;
	}
	/**
	 * 设置商品规格的删除状态
	 */
	public void setDeleteEnable(Integer deleteEnable) {
		this.deleteEnable = deleteEnable;
	}
	
	/**
	 * 设置：规格ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：规格ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：商品规格编号
	 */
	public void setProductSpecId(Long productSpecId) {
		this.productSpecId = productSpecId;
	}
	/**
	 * 获取：商品规格编号
	 */
	public Long getProductSpecId() {
		return productSpecId;
	}
	/**
	 * 设置：规格名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：规格名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：规格创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：规格创建者
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：规格修改者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：规格修改者
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：商品规格描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：商品规格描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：规格状态：0,启用；1,禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：规格状态：0,启用；1,禁用
	 */
	public Integer getStatus() {
		return status;
	}
}
