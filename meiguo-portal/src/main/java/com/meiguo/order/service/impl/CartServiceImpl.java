package com.meiguo.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.order.dao.CartDao;
import com.meiguo.order.domain.CartDO;
import com.meiguo.order.service.CartService;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;
	
	@Override
	public CartDO get(Long cartId){
		return cartDao.get(cartId);
	}
	
	@Override
	public List<CartDO> list(Map<String, Object> map){
		return cartDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return cartDao.count(map);
	}
	
	@Override
	public int save(Long goodsId){
		CartDO cartDO = new CartDO();
		cartDO.setUserId(ShiroUtils.getUserId());
		cartDO.setBuyNumber(1);
		cartDO.setCreateTime(new Date());
		cartDO.setGoodsId(goodsId);
		cartDO.setCheckStatus(0);
		return cartDao.save(cartDO);
	}
	
	@Override
	public int update(CartDO cart){
		return cartDao.update(cart);
	}
	
	@Override
	public int remove(Long cartId){
		return cartDao.remove(cartId);
	}
	
	@Override
	public int batchRemove(Long[] cartIds){
		return cartDao.batchRemove(cartIds);
	}
	
}
