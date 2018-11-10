package com.meiguo.order.service.impl;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.order.dao.OrderDao;
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.domain.OrderProductDO;
import com.meiguo.order.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDO get(Long id){
		return orderDao.get(id);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
		order.setCreateTime(new Date());
		order.setOrderStatus(0);//订单状态为待支付
		String dateString  = new SimpleDateFormat("yyyyMMddhhMMss").format(new Date());
		Integer orderNo = RandomUtils.nextInt(10000);
		order.setOrderNo("mg"+dateString+format1(orderNo,5));
		order.setUserId(ShiroUtils.getUserId());
		return orderDao.save(order);
	   
	}
	
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
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Long id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderDao.batchRemove(ids);
	}
	
}
