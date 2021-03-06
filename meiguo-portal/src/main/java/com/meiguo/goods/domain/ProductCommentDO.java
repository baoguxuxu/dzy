package com.meiguo.goods.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 评价表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:37:33
 */
public class ProductCommentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//评价ID
	private Long id;
	//商品ID
	private Long productId;
	//用户ID
	private Long userId;
	//昵称
	private String nickname;
	//用户头像
	private String headimgurl;
	//订单ID
	private Long orderId;
	//评论星级：1,2,3,4,5
	private Integer star;
	//评论内容
	private String content;
	//好评数
	private Integer praises;
	//状态：1.显示；0.隐藏
	private Integer status;
	//评论类型：1,优质；0,普通
	private Integer type;
	//创建时间
	private Date createTime;
	//创建者
	private String createBy;
	//更新时间
	private Date updateTime;
	//更新者
	private String updateBy;
	//子订单ID
	private Long orderProductId;
	//评价图片
	private List<OrderCommentImageDO> orderCommentImageDOList = new ArrayList<OrderCommentImageDO>();

	public List<OrderCommentImageDO> getOrderCommentImageDOList() {
		return orderCommentImageDOList;
	}
	public void setOrderCommentImageDOList(List<OrderCommentImageDO> orderCommentImageDOList) {
		this.orderCommentImageDOList = orderCommentImageDOList;
	}
	/**
	 * 设置：评价ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：评价ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：商品ID
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * 获取：商品ID
	 */
	public Long getProductId() {
		return productId;
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
	 * 设置：用户头像
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * 获取：用户头像
	 */
	public String getHeadimgurl() {
		return headimgurl;
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
	 * 设置：评论星级：1,2,3,4,5
	 */
	public void setStar(Integer star) {
		this.star = star;
	}
	/**
	 * 获取：评论星级：1,2,3,4,5
	 */
	public Integer getStar() {
		return star;
	}
	/**
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：好评数
	 */
	public void setPraises(Integer praises) {
		this.praises = praises;
	}
	/**
	 * 获取：好评数
	 */
	public Integer getPraises() {
		return praises;
	}
	/**
	 * 设置：状态：1.显示；0.隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1.显示；0.隐藏
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：评论类型：1,优质；0,普通
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：评论类型：1,优质；0,普通
	 */
	public Integer getType() {
		return type;
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
	 * 设置：创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateBy() {
		return createBy;
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
	 * 设置：更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：子订单ID
	 */
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	/**
	 * 获取：子订单ID
	 */
	public Long getOrderProductId() {
		return orderProductId;
	}
}
