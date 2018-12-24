
package com.meiguo.product.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 商品分类表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 13:16:05
 */
public class CategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//分类ID
	private Long id;
	//分类名称
	private String name;
	//分类状态（0：启用  1：禁用）
	private Integer status;
	
	//创建时间
	private Date createTime;
	//创建者
	private String createBy;
	//更新时间
	private Date updateTime;
	//更新者
	private String updateBy;
	//备注信息
	private String remarks;
    //数据删除标志(0:数据没有被执行删除操作   1：数据执行了删除操作)
	private Integer deleteEnable;
	//0  可以用浆果票兑换   1 不能用浆果票兑换
	private Integer jiangguoFlag;
	public Integer getJiangguoFlag() {
		return jiangguoFlag;
	}
	public void setJiangguoFlag(Integer jiangguoFlag) {
		this.jiangguoFlag = jiangguoFlag;
	}
	public Integer getDeleteEnable() {
		return deleteEnable;
	}
	public void setDeleteEnable(Integer deleteEnable) {
		this.deleteEnable = deleteEnable;
	}

	/**
	 * 设置：分类ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：分类ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
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
	 * 设置：备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置分类状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取分类状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	
}
