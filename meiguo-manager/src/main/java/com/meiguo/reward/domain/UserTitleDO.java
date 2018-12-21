package com.meiguo.reward.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 称号表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
public class UserTitleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//称号名
	private String titleName;
	//称号图标
	private String titleIco;
	//获得条件
	private String titleDemand;
	//奖励
	private String reward;
	//状态 0:是  1：否
	private Integer status;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;

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
	 * 设置：称号名
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	/**
	 * 获取：称号名
	 */
	public String getTitleName() {
		return titleName;
	}
	/**
	 * 设置：称号图标
	 */
	public void setTitleIco(String titleIco) {
		this.titleIco = titleIco;
	}
	/**
	 * 获取：称号图标
	 */
	public String getTitleIco() {
		return titleIco;
	}
	/**
	 * 设置：获得条件
	 */
	public void setTitleDemand(String titleDemand) {
		this.titleDemand = titleDemand;
	}
	/**
	 * 获取：获得条件
	 */
	public String getTitleDemand() {
		return titleDemand;
	}
	/**
	 * 设置：奖励
	 */
	public void setReward(String reward) {
		this.reward = reward;
	}
	/**
	 * 获取：奖励
	 */
	public String getReward() {
		return reward;
	}
	/**
	 * 设置：状态 0:是  1：否
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0:是  1：否
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
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
}
