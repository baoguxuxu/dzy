package com.meiguo.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户登陆记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-02 09:14:45
 */
public class UserDengluLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//用户id
	private Integer userid;
	//登录时间
	private Date createTime;

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
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserid() {
		return userid;
	}
	/**
	 * 设置：登录时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：登录时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
