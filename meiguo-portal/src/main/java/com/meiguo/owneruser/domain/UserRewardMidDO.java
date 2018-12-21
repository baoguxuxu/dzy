package com.meiguo.owneruser.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
public class UserRewardMidDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//用户id
	private Long userId;
	//奖励id
	private Integer rewardId;
	//获得时间
	private Date winTime;

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
	 * 设置：奖励id
	 */
	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}
	/**
	 * 获取：奖励id
	 */
	public Integer getRewardId() {
		return rewardId;
	}
	/**
	 * 设置：获得时间
	 */
	public void setWinTime(Date winTime) {
		this.winTime = winTime;
	}
	/**
	 * 获取：获得时间
	 */
	public Date getWinTime() {
		return winTime;
	}
}
