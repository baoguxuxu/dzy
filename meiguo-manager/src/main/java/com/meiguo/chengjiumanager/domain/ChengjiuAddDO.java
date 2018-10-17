package com.meiguo.chengjiumanager.domain;

import java.io.Serializable;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-12 10:59:41
 */
public class ChengjiuAddDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//成就名称
	private String chengjiuName;
	//成就图标
	private String chengjiuIco;
	//获取条件
	private String chengjiuDemand;
	//奖励
	private String reward;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//0：是1：否
	private Integer deleteFlag;
	//
	private String username;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
		
	private String url;
	
	private MultipartFile imgFile;
	
	private Long userId;
	


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
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
	 * 设置：成就名称
	 */
	public void setChengjiuName(String chengjiuName) {
		this.chengjiuName = chengjiuName;
	}
	/**
	 * 获取：成就名称
	 */
	public String getChengjiuName() {
		return chengjiuName;
	}
	/**
	 * 设置：成就图标
	 */
	public void setChengjiuIco(String chengjiuIco) {
		this.chengjiuIco = chengjiuIco;
	}
	/**
	 * 获取：成就图标
	 */
	public String getChengjiuIco() {
		return chengjiuIco;
	}
	/**
	 * 设置：获取条件
	 */
	public void setChengjiuDemand(String chengjiuDemand) {
		this.chengjiuDemand = chengjiuDemand;
	}
	/**
	 * 获取：获取条件
	 */
	public String getChengjiuDemand() {
		return chengjiuDemand;
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
	 * 设置：0：是1：否
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：0：是1：否
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
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
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
