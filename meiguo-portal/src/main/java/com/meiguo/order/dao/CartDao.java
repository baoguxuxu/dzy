package com.meiguo.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.order.domain.CartDO;

/**
 * 购物车表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-09 09:44:50
 */
@Mapper
public interface CartDao {

	CartDO get(Long cartId);
	
	List<CartDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CartDO cart);
	
	int update(CartDO cart);
	
	int remove(Long cart_id);
	
	int batchRemove(Long[] cartIds);
}
