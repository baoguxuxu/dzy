package com.meiguo.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户成就中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-02 09:14:44
 */
public class UserChengjiuMidDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//用户id
	private Long userId;
	//成就id
	private Integer chengjiuId;
	//达成时间
	private Date chengjiuTime;
	//
	private String beiyong1;
	//
	private String beiyong2;
	//
	private Integer beiyong3;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：成就id
	 */
	public void setChengjiuId(Integer chengjiuId) {
		this.chengjiuId = chengjiuId;
	}
	/**
	 * 获取：成就id
	 */
	public Integer getChengjiuId() {
		return chengjiuId;
	}
	/**
	 * 设置：达成时间
	 */
	public void setChengjiuTime(Date chengjiuTime) {
		this.chengjiuTime = chengjiuTime;
	}
	/**
	 * 获取：达成时间
	 */
	public Date getChengjiuTime() {
		return chengjiuTime;
	}
	/**
	 * 设置：
	 */
	public void setBeiyong1(String beiyong1) {
		this.beiyong1 = beiyong1;
	}
	/**
	 * 获取：
	 */
	public String getBeiyong1() {
		return beiyong1;
	}
	/**
	 * 设置：
	 */
	public void setBeiyong2(String beiyong2) {
		this.beiyong2 = beiyong2;
	}
	/**
	 * 获取：
	 */
	public String getBeiyong2() {
		return beiyong2;
	}
	/**
	 * 设置：
	 */
	public void setBeiyong3(Integer beiyong3) {
		this.beiyong3 = beiyong3;
	}
	/**
	 * 获取：
	 */
	public Integer getBeiyong3() {
		return beiyong3;
	}
}
