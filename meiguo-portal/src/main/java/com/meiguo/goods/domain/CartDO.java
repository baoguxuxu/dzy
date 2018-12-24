package com.meiguo.goods.domain;

import java.io.Serializable;
import java.util.Date;

import com.meiguo.goods.domain.GoodsDO;



/**
 * 购物车表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-09 09:44:50
 */
public class CartDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//购物车ID
	private Long cartId;
	//商品id
	private Long goodsId;
	//用户ID
	private Long userId;
	//购买数量
	private Integer buyNumber;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//购物车状态：0,未选中；1,选中
	private Integer checkStatus;
    //商品
	private GoodsDO goodsDO;
	public GoodsDO getGoodsDO() {
		return goodsDO;
	}
	public void setGoodsDO(GoodsDO goodsDO) {
		this.goodsDO = goodsDO;
	}
	/**
	 * 设置：购物车ID
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	/**
	 * 获取：购物车ID
	 */
	public Long getCartId() {
		return cartId;
	}
	/**
	 * 设置：商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：购买数量
	 */
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	/**
	 * 获取：购买数量
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：购物车状态：0,未选中；1,选中
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：购物车状态：0,未选中；1,选中
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}
}
