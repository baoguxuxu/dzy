package com.meiguo.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订单明细表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 10:49:04
 */
public class OrderProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单商品ID
	private Long orderProductId;
	//订单ID
	private Long orderId;
	//商品id
	private Long goodsId;
	//商品名称
	private String name;
	//展示图片
	private String picImg;
	//商品规格编号
	private Long productSpecId;
	//价格
	private BigDecimal price;
	//积分
	private Integer score;
	//商品数量
	private Integer buyNumber;
	//商品积分
	private Integer productScore;
	//商品总金额
	private BigDecimal productAmount;
	//是否可以用浆果票支付  0 可以用浆果票支付   1 不能用浆果票支付
	private Integer jiangguoFlag;
	public Integer getJiangguoFlag() {
		return jiangguoFlag;
	}
	public void setJiangguoFlag(Integer jiangguoFlag) {
		this.jiangguoFlag = jiangguoFlag;
	}
	//商品规格
	private String spec;
	
    /**
     * 获取 ：商品规格
     */
	public String getSpec() {
		return spec;
	}
	/**
	 *设置：商品规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * 设置：订单商品ID
	 */
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	/**
	 * 获取：订单商品ID
	 */
	public Long getOrderProductId() {
		return orderProductId;
	}
	/**
	 * 设置：订单ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单ID
	 */
	public Long getOrderId() {
		return orderId;
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
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：展示图片
	 */
	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	/**
	 * 获取：展示图片
	 */
	public String getPicImg() {
		return picImg;
	}
	/**
	 * 设置：商品规格编号
	 */
	public void setProductSpecId(Long productSpecId) {
		this.productSpecId = productSpecId;
	}
	/**
	 * 获取：商品规格编号
	 */
	public Long getProductSpecId() {
		return productSpecId;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：积分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * 获取：积分
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * 设置：商品数量
	 */
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}
	/**
	 * 设置：商品积分
	 */
	public void setProductScore(Integer productScore) {
		this.productScore = productScore;
	}
	/**
	 * 获取：商品积分
	 */
	public Integer getProductScore() {
		return productScore;
	}
	/**
	 * 设置：商品总金额
	 */
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * 获取：商品总金额
	 */
	public BigDecimal getProductAmount() {
		return productAmount;
	}
}
