package com.meiguo.goods.service;


import java.util.List;
import java.util.Map;

import com.meiguo.goods.domain.CartDO;


/**
 * 购物车表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-09 09:44:50
 */
public interface CartService {
	
	CartDO get(Long cartId);
	
	List<CartDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Long goodsId);
	
	int update(CartDO cart);
	
	int remove(Long cartId);
	
	int batchRemove(Long[] cartIds);
}
