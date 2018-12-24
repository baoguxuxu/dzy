package com.meiguo.goods.service.impl;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.dao.CartDao;
import com.meiguo.goods.dao.CategoryDao;
import com.meiguo.goods.dao.GoodsDao;
import com.meiguo.goods.dao.ProductDao;
import com.meiguo.goods.domain.CartDO;
import com.meiguo.goods.domain.CategoryDO;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.domain.ProductDO;
import com.meiguo.goods.domain.SpecDO;
import com.meiguo.goods.service.GoodsService;
import com.meiguo.order.dao.AddressDao;
import com.meiguo.order.dao.OrderDao;
import com.meiguo.order.dao.OrderProductDao;
import com.meiguo.order.domain.AddressDO;
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.domain.OrderProductDO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderProductDao orderProductDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private CategoryDao categoryDao;
	@Override
	public GoodsDO get(Long id){
		return goodsDao.get(id);
	}
	
	
	//查询产品分类信息
	@Override
	public List<CategoryDO> listCategory(Map<String, Object> map){
		return goodsDao.listCategory(map);
	}
	
	

	/**
	 * 根据分类名称查询该分类下的商品
	 */
	@Override
	public List<ProductDO> getProductByCategoryName(String name) {
		return goodsDao.getProductByCategoryName( name);
	}
	@Override
	public List<GoodsDO> getGoodsByProductId(Long id) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsByProductId(id);
	}
	@Override
	public List<ImgDO> listGoodsimgAndDetailimg(Long id, int i) {
		return goodsDao.listGoodsimgAndDetailimg(id,i);
	}

	@Override
	public List<GoodsDO> list(Map<String, Object> map) {
		return goodsDao.list(map);
	}
	//订单生成
	@Override
	public String byGoods(List<CartDO> cartDOList) {
		List<OrderProductDO> orderProductDOList = new ArrayList<OrderProductDO>();
		BigDecimal totalamount=new BigDecimal(0);//支付总金额
		for(CartDO cartDO :cartDOList){
			GoodsDO  goodsDO = goodsDao.get(cartDO.getGoodsId());
			if(goodsDO==null)
				return "商品不存在或已下架，生成订单失败";
			if(cartDO.getBuyNumber()>goodsDO.getSurplus())
				return "规格为  "+goodsDO.getSpec()+" 的商品剩余量不足，生成订单失败";
			if(cartDO.getCartId()!=null)
				cartDao.remove(cartDO.getCartId());
			OrderProductDO orderProductDO = new OrderProductDO();
			orderProductDO.setBuyNumber(cartDO.getBuyNumber());
			orderProductDO.setGoodsId(cartDO.getGoodsId());
			BigDecimal price = goodsDO.getVipPrice()==null ?goodsDO.getPayPrice():goodsDO.getVipPrice();
			orderProductDO.setPrice(price);
			
			orderProductDO.setProductAmount(price.multiply(new BigDecimal(cartDO.getBuyNumber())));
			totalamount = totalamount.add(orderProductDO.getProductAmount());
			orderProductDO.setScore(0);//默认积分是0
			orderProductDO.setSpec(goodsDO.getSpec());
			ProductDO productDO = productDao.get(goodsDO.getProductId());
			orderProductDO.setName(productDO.getName());
			orderProductDO.setPicImg(productDO.getUrl());
			CategoryDO categoryDO = categoryDao.get(productDO.getCategoryId());
			orderProductDO.setJiangguoFlag(categoryDO.getJiangguoFlag());
			orderProductDOList.add(orderProductDO);
		}
		OrderDO orderDO  = new OrderDO();
		String orderNo = "lv" + DateFormatUtils.format(new Date(), "yyyyMMdd") + format1( RandomUtils.nextInt(10000), 5);
		orderDO.setOrderNo(orderNo);
		orderDO.setCreateTime(new Date());
		orderDO.setOrderAmount(totalamount);
		orderDO.setOrderStatus(0);
		orderDO.setOrderScore(0);
		orderDO.setPayType(1);//默认为在线支付
		orderDO.setUserId(ShiroUtils.getUserId());
		orderDO.setDeleteEnable(0);//订单未删除标志
	    Map<String,Object> map  = new HashMap<String,Object>();
		map.put("userId",ShiroUtils.getUserId());
		map.put("defaultFlag","1");
		List<AddressDO> list = addressDao.list(map);
		AddressDO addressDO = new AddressDO();
		if(list.size()>0)
			addressDO = list.get(0);
		orderDO.setAddress(addressDO.getAddress());
		orderDO.setMobile(addressDO.getMobile());
		orderDO.setConsignee(addressDO.getConsignee());
		orderDao.save(orderDO);
		for(OrderProductDO orderProductDO : orderProductDOList){
			orderProductDO.setOrderId(orderDO.getId());
			orderProductDao.save(orderProductDO);
		}
		return orderNo;
	}
	
	/**
	 * 订单编号生成
	 */
	public static String format1(Integer value, int minLength) {
		StringBuffer st = new StringBuffer(value.toString());
		if (st.length() < minLength) {
			int len = minLength - st.length();
			for (int i = 0; i < len; i++) {
				st.insert(0, "0");
			}
		}
		return st.toString();
	}

	
}
