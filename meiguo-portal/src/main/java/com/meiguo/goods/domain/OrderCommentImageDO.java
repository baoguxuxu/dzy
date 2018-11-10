package com.meiguo.goods.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 评价图片表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:55:12
 */
public class OrderCommentImageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//评价ID
	private Long id;
	//评价ID
	private Long commentId;
	//展示图片
	private String picImg;
	//排序
	private Integer sort;
	//状态：1.显示；0.隐藏
	private Integer status;

	/**
	 * 设置：评价ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：评价ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：评价ID
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：评价ID
	 */
	public Long getCommentId() {
		return commentId;
	}
	/**
	 * 设置：展示图片
	 */
	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	/**
	 * 获取：展示图片
	 */
	public String getPicImg() {
		return picImg;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：状态：1.显示；0.隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1.显示；0.隐藏
	 */
	public Integer getStatus() {
		return status;
	}
}
