package com.meiguo.order.service;


import java.util.List;
import java.util.Map;

import com.meiguo.order.domain.OrderDO;

/**
 * 订单表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-07 16:39:15
 */
public interface OrderService {
	
	OrderDO get(Long id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
