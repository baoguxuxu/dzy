package com.meiguo.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.order.dao.OrderProductDao;
import com.meiguo.order.domain.OrderProductDO;
import com.meiguo.order.service.OrderProductService;

import java.util.List;
import java.util.Map;




@Service
public class OrderProductServiceImpl implements OrderProductService {
	@Autowired
	private OrderProductDao orderProductDao;
	
	@Override
	public OrderProductDO get(Long orderProductId){
		return orderProductDao.get(orderProductId);
	}
	
	@Override
	public List<OrderProductDO> list(Map<String, Object> map){
		return orderProductDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderProductDao.count(map);
	}
	
	@Override
	public int save(OrderProductDO orderProduct){
		return orderProductDao.save(orderProduct);
	}
	
	@Override
	public int update(OrderProductDO orderProduct){
		return orderProductDao.update(orderProduct);
	}
	
	@Override
	public int remove(Long orderProductId){
		return orderProductDao.remove(orderProductId);
	}
	
	@Override
	public int batchRemove(Long[] orderProductIds){
		return orderProductDao.batchRemove(orderProductIds);
	}
	
}
