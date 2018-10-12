package com.meiguo.chengjiumanager.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 成就列表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:54:27
 */
public class ChengjiuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer chengjiuId;
	//外键id
	private Integer id;
	//微信id
	private String openId;
	//昵称
	private String nickname;
	//手机号
	private String phone;
	//真实姓名
	private String name;
	//注册时间
	private Date redisterTime;
	//达成时间
	private Date chengjiuTime;
	//排名
	private Integer rank;
	//0：解锁1：未解锁
	private Integer chengjiuFlag;
	//成就图标
	private String chengjiuIco;
	//图标名称
	private String chengjiuName;
	//获取条件
	private String chengjiuDemand;
	//奖励
	private String reward;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//登录时间
	private Date loginTime;
	//头像
	private String hearcUrl;
	//身份证号
	private String identityCard;
	//0：是1：否
	private Integer deleteFlag;
	//
	private String username;

	/**
	 * 设置：id
	 */
	public void setChengjiuId(Integer chengjiuId) {
		this.chengjiuId = chengjiuId;
	}
	/**
	 * 获取：id
	 */
	public Integer getChengjiuId() {
		return chengjiuId;
	}
	/**
	 * 设置：外键id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：外键id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：微信id
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：微信id
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRedisterTime(Date redisterTime) {
		this.redisterTime = redisterTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRedisterTime() {
		return redisterTime;
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
	 * 设置：排名
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**
	 * 获取：排名
	 */
	public Integer getRank() {
		return rank;
	}
	/**
	 * 设置：0：解锁1：未解锁
	 */
	public void setChengjiuFlag(Integer chengjiuFlag) {
		this.chengjiuFlag = chengjiuFlag;
	}
	/**
	 * 获取：0：解锁1：未解锁
	 */
	public Integer getChengjiuFlag() {
		return chengjiuFlag;
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
	 * 设置：图标名称
	 */
	public void setChengjiuName(String chengjiuName) {
		this.chengjiuName = chengjiuName;
	}
	/**
	 * 获取：图标名称
	 */
	public String getChengjiuName() {
		return chengjiuName;
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
	 * 设置：登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置：头像
	 */
	public void setHearcUrl(String hearcUrl) {
		this.hearcUrl = hearcUrl;
	}
	/**
	 * 获取：头像
	 */
	public String getHearcUrl() {
		return hearcUrl;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdentityCard() {
		return identityCard;
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
}
