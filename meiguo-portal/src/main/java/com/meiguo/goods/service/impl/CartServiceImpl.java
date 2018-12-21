package com.meiguo.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.dao.CartDao;
import com.meiguo.goods.domain.CartDO;
import com.meiguo.goods.service.CartService;

import java.util.Date;
import java.util.HashMap;
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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",ShiroUtils.getUserId());
		List<CartDO> list = cartDao.list(map);
		for(CartDO  cartDO :list){
			if(cartDO.getGoodsId()==goodsId)
				return -1;
		}
		CartDO cartDO = new CartDO();
		cartDO.setUserId(ShiroUtils.getUserId());
		cartDO.setBuyNumber(1);//初始化商品数量为1
		cartDO.setCreateTime(new Date());
		cartDO.setGoodsId(goodsId);
		cartDO.setCheckStatus(0);//默认未选中
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
