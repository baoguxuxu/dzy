package com.meiguo.product.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 产品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:09:27
 */
public class ProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//产品ID
	private Long id;
	//产品编号
	private Long productNumber;
	//产品分类ID
	private Long categoryId;
	//产品分类名称
	private String categoryName;
	//产品名称
	private String name;
	//创建时间
	private Date createTime;
	//创建者
	private String createBy;
	//更新时间
	private Date updateTime;
	//更新者
	private String updateBy;
	//产品规则页面展示
	private String spec_dis;
	//备注
	private String remarks;
	//产品是否上架(0:上架，1：下架)
	private Integer status;
	//数据删除标志（0：数据没有被执行删除操作  1：数据执行了删除操作）
	private Integer deleteEnable;
	public Integer getDeleteEnable() {
		return deleteEnable;
	}
	public void setDeleteEnable(Integer deleteEnable) {
		this.deleteEnable = deleteEnable;
	}
	//产品具备的规格
	private List<Spec_Product> list = new ArrayList<Spec_Product>();
   /**
    * 设置规格
    */
	public List<Spec_Product> getList() {
		return list;
	}
	/**
	 * 获取规格
	 */
	public void setList(List<Spec_Product> list) {
		this.list = list;
	}
	/**
	 * 设置：产品ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：产品ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：产品编号
	 */
	public void setProductNumber(Long productNumber) {
		this.productNumber = productNumber;
	}
	/**
	 * 获取：产品编号
	 */
	public Long getProductNumber() {
		return productNumber;
	}
	/**
	 * 设置：产品分类
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：产品分类
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：产品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：产品名称
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
	 * 设置：创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateBy() {
		return createBy;
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
	 * 设置：更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 获取：
	 */
	public String getSpec_dis() {
		return spec_dis;
	}
	/**
	 * 获取：
	 */
	public void setSpec_dis(String spec_dis) {
		this.spec_dis = spec_dis;
	}
	/**
	 * 设置分类名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 获取分类名称
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 获取产品上架状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置产品上架状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
