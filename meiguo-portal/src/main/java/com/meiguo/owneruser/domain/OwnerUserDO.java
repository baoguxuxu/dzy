package com.meiguo.owneruser.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.web.multipart.MultipartFile;

public class OwnerUserDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//id

	private Long id;
	//用户ID
	private Long userId;
	//微信id
	private String openId;
	//昵称
	private String nickname;
    // 用户名
    private String username;
	//密码
	private String password;
	//手机号
	private String phone;
	//头像
	private String heardUrl;
	//头像附件
	private MultipartFile fileImg; 
	//真实姓名
	private String name;
	//身份证号
	private String identityCard;
	//注册时间
	private Date registerTime;
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
	//成就图标
    private String chengjiu_ioc ;

	//成就名称
	private String chengjiu_name; 
	//获取条件
	private String chengjiu_demand;
	//奖励
	private String reward;
	//达成时间
	private Date chengjiu_time;
	// 排名
	private String rank;
	//'0：解锁1：未解锁'
	private Integer chengjiu_flag;
	//开始时间'
	private Date startTime;
	//结束时间
	private Date endTime; 
	//总口数
	private Integer otalkou_num;
	//当天口数
	private Integer daykou_num;
	//性别
	private String sex;
	//年龄
	private Integer age;
	//烟龄
	private String smoke_age;
	//日期
	private Date time;
	//注册码
	private Long zhucema;
	//邀请人Id
	private Long inviterId;
	//注册人数
	private Integer zhucemaNum;
	//登陆次数
	private Integer dengluNum;
	//总积分
	private Integer total_jifen;

	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getZhucema() {
	return zhucema;
	}
	public void setZhucema(Long zhucema) {
		this.zhucema = zhucema;
	}
	public Long getInviterId() {
		return inviterId;
	}
	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}
	public Integer getTotal_jifen() {
		return total_jifen;
	}
	public void setTotal_jifen(Integer total_jifen) {
		this.total_jifen = total_jifen;
	}
	public Integer getZhucemaNum() {
		return zhucemaNum;
	}
	public void setZhucemaNum(Integer zhucemaNum) {
		this.zhucemaNum = zhucemaNum;
	}
	public Integer getDengluNum() {
		return dengluNum;
	}
	public void setDengluNum(Integer dengluNum) {
		this.dengluNum = dengluNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
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
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegisterTime() {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getChengjiu_ioc() {
		return chengjiu_ioc;
	}
	public void setChengjiu_ioc(String chengjiu_ioc) {
		this.chengjiu_ioc = chengjiu_ioc;
	}
	public String getChengjiu_name() {
		return chengjiu_name;
	}
	public void setChengjiu_name(String chengjiu_name) {
		this.chengjiu_name = chengjiu_name;
	}
	public String getChengjiu_demand() {
		return chengjiu_demand;
	}
	public void setChengjiu_demand(String chengjiu_demand) {
		this.chengjiu_demand = chengjiu_demand;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public Date getChengjiu_time() {
		return chengjiu_time;
	}
	public void setChengjiu_time(Date chengjiu_time) {
		this.chengjiu_time = chengjiu_time;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Integer getChengjiu_flag() {
		return chengjiu_flag;
	}
	public void setChengjiu_flag(Integer chengjiu_flag) {
		this.chengjiu_flag = chengjiu_flag;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getOtalkou_num() {
		return otalkou_num;
	}
	public void setOtalkou_num(Integer otalkou_num) {
		this.otalkou_num = otalkou_num;
	}
	public Integer getDaykou_num() {
		return daykou_num;
	}
	public void setDaykou_num(Integer daykou_num) {
		this.daykou_num = daykou_num;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSmoke_age() {
		return smoke_age;
	}
	public void setSmoke_age(String smoke_age) {
		this.smoke_age = smoke_age;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public MultipartFile getFileImg() {
		return fileImg;
	}
	public void setFileImg(MultipartFile fileImg) {
		this.fileImg = fileImg;
	}

	
}
