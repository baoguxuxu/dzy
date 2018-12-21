package com.meiguo.reward.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 奖券表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
public class UserCouponDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//奖券名称
	private String couponName;
	//奖券属性   0：一般 1：特殊
	private Integer couponArrt;
	//状态 0：正常 1：异常
	private Integer couponStatus;
	//奖券编号
	private Integer couponId;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//备注
	private String notes;

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
	 * 设置：奖券名称
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	/**
	 * 获取：奖券名称
	 */
	public String getCouponName() {
		return couponName;
	}
	/**
	 * 设置：奖券属性   0：一般 1：特殊
	 */
	public void setCouponArrt(Integer couponArrt) {
		this.couponArrt = couponArrt;
	}
	/**
	 * 获取：奖券属性   0：一般 1：特殊
	 */
	public Integer getCouponArrt() {
		return couponArrt;
	}
	/**
	 * 设置：状态 0：正常 1：异常
	 */
	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}
	/**
	 * 获取：状态 0：正常 1：异常
	 */
	public Integer getCouponStatus() {
		return couponStatus;
	}
	/**
	 * 设置：奖券编号
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：奖券编号
	 */
	public Integer getCouponId() {
		return couponId;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：备注
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * 获取：备注
	 */
	public String getNotes() {
		return notes;
	}
}
