package com.meiguo.information.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;




/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-16 14:41:23
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//微信id
	private String openId;
	//昵称
	private String nickname;
	//密码
	private String password;
	//手机号
	private String phone;
	//头像
	private String heardUrl;
	//真实姓名
	private String name;
	//身份证号
	private String identityCard;
	//注册时间
	private Timestamp registerTime;
	//消费金额
	private Double payNum;
	//服务次数
	private Integer serveNum;
	//余额
	private Double balance;
	//返还
	private Double restitution;
	//缴费日期
	private Date payTime;
	//最后登录时间
	private Date loginTime;
	//添加时间
	private Date addTime;
	//修改时间
	private Date updateTime;
	//0：是；1：否
	private Integer deleteFlag;
	//
	private String personalchengjing;
	//成就图标
	private String chengjiuIoc;
	//成就名称
	private String chengjiuName;
	//获取条件
	private String chengjiuDemand;
	//奖励
	private String reward;
	//达成时间
	private Date chengjiuTime;
	//排名
	private String rank;
	//0：解锁1：未解锁
	private Integer chengjiuFlag;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	

	
	
	

	public String getPersonalchengjing() {
		return personalchengjing;
	}
	public void setPersonalchengjing(String personalchengjing) {
		this.personalchengjing = personalchengjing;
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
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
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
	 * 设置：头像
	 */
	public void setHeardUrl(String heardUrl) {
		this.heardUrl = heardUrl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeardUrl() {
		return heardUrl;
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
	 * 设置：注册时间
	 */
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：消费金额
	 */
	public void setPayNum(Double payNum) {
		this.payNum = payNum;
	}
	/**
	 * 获取：消费金额
	 */
	public Double getPayNum() {
		return payNum;
	}
	/**
	 * 设置：服务次数
	 */
	public void setServeNum(Integer serveNum) {
		this.serveNum = serveNum;
	}
	/**
	 * 获取：服务次数
	 */
	public Integer getServeNum() {
		return serveNum;
	}
	/**
	 * 设置：余额
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * 获取：余额
	 */
	public Double getBalance() {
		return balance;
	}
	/**
	 * 设置：返还
	 */
	public void setRestitution(Double restitution) {
		this.restitution = restitution;
	}
	/**
	 * 获取：返还
	 */
	public Double getRestitution() {
		return restitution;
	}
	/**
	 * 设置：缴费日期
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：缴费日期
	 */
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 设置：最后登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：最后登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
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
	 * 设置：0：是；1：否
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：0：是；1：否
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	
	/**
	 * 设置：成就图标
	 */
	public void setChengjiuIoc(String chengjiuIoc) {
		this.chengjiuIoc = chengjiuIoc;
	}
	/**
	 * 获取：成就图标
	 */
	public String getChengjiuIoc() {
		return chengjiuIoc;
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
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * 获取：排名
	 */
	public String getRank() {
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
