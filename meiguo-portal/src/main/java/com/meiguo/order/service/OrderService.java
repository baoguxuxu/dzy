package com.meiguo.order.service;


import java.util.List;
import java.util.Map;

import com.meiguo.common.utils.R;
import com.meiguo.order.domain.OrderDO;

/**
 * 订单表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-02 10:51:27
 */
public interface OrderService {
	
	OrderDO get(String  orderNo,Long userId);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	String scoreExchange(String orderNo);

	 Map<String, Object> kuaid100QueryBase(String postid);
	 R pay(String orderNo,Integer jflag);
}
