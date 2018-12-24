package com.meiguo.goods.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;



/**
 * 货品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-17 15:41:03
 */
public class GoodsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//货品ID编号
	private Integer id;
	//货品编号
	private Integer goodsNumber;
	//货品条形码号
	private Integer tgoodsNumber;
	//商品id
	private Integer productId;
	//商品名称
	private String name;
	//货品状态   0：上架   1：下架
	private Integer status;
	// 折扣价
	private BigDecimal discountPrice;
	// 售价
	private BigDecimal payPrice;
	// 会员价
	private BigDecimal vipPrice;
	// 促销价
	private BigDecimal promotionPrice;
	//创建时间
	private Date createTime;
	//创建者
	private String createBy;
	//更新时间
	private Date updateTime;
	//更新者
	private String updateBy;
	//货品详情描述
	private String remarks;
	//货品列表图
	private MultipartFile listimg;
	//所属产品名称
	private String productName;
	//数据删除标志（0：该数据执行了删除操作   1：该数据没有被执行删除操作）
	private Integer deleteEnable;
	//保存图片表中失效的id值
	private String imgids;
	//货品具备的规格参数详情
	private List<GoodsSpec> list = new ArrayList<GoodsSpec>();
	//货品规格
	private String spec;
	//库存总量
	private Long total;
	//库存剩余量
	private Long surplus;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getSurplus() {
		return surplus;
	}
	public void setSurplus(Long surplus) {
		this.surplus = surplus;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public List<GoodsSpec> getList() {
		return list;
	}
	public void setList(List<GoodsSpec> list) {
		this.list = list;
	}
	public String getImgids() {
		return imgids;
	}
	public void setImgids(String imgids) {
		this.imgids = imgids;
	}
	public Integer getDeleteEnable() {
		return deleteEnable;
	}
	public void setDeleteEnable(Integer deleteEnable) {
		this.deleteEnable = deleteEnable;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 设置：货品ID编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：货品ID编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：货品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：货品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：货品编号
	 */
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	/**
	 * 获取：货品编号
	 */
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	/**
	 * 设置：货品条形码号
	 */
	public void setTgoodsNumber(Integer tgoodsNumber) {
		this.tgoodsNumber = tgoodsNumber;
	}
	/**
	 * 获取：货品条形码号
	 */
	public Integer getTgoodsNumber() {
		return tgoodsNumber;
	}
	/**
	 * 设置：所属产品
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：所属产品
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：货品状态   0：上架   1：下架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：货品状态   0：上架   1：下架
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置： 折扣价
	 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * 获取： 折扣价
	 */
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * 设置： 售价
	 */
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	/**
	 * 获取： 售价
	 */
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	/**
	 * 设置： 会员价
	 */
	public void setVipPrice(BigDecimal vipPrice) {
		this.vipPrice = vipPrice;
	}
	/**
	 * 获取： 会员价
	 */
	public BigDecimal getVipPrice() {
		return vipPrice;
	}
	/**
	 * 设置： 促销价
	 */
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	/**
	 * 获取： 促销价
	 */
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
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
	 * 设置：货品详情描述
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：货品详情描述
	 */
	public String getRemarks() {
		return remarks;
	}
	public MultipartFile getListimg() {
		return listimg;
	}
	public void setListimg(MultipartFile listimg) {
		this.listimg = listimg;
	}
	
	/**
	 * 货品具备的规格参数详情
	 */
	public static class GoodsSpec{
		private String specname;
		private String specdetail;
		public String getSpecname() {
			return specname;
		}
		public void setSpecname(String specname) {
			this.specname = specname;
		}
		public String getSpecdetail() {
			return specdetail;
		}
		public void setSpecdetail(String specdetail) {
			this.specdetail = specdetail;
		}
		
	}
}
