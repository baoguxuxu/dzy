package com.meiguo.order.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.order.domain.OrderProductDO;

/**
 * 订单明细表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 10:49:04
 */
@Mapper
public interface OrderProductDao {

	OrderProductDO get(Long orderProductId);
	
	List<OrderProductDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrderProductDO orderProduct);
	
	int update(OrderProductDO orderProduct);
	
	int remove(Long order_product_id);
	
	int batchRemove(Long[] orderProductIds);
}
