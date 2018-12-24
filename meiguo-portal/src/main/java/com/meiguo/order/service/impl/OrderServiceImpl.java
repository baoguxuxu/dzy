package com.meiguo.order.service.impl;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.order.dao.OrderDao;
import com.meiguo.order.dao.OrderProductDao;
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.domain.OrderProductDO;
import com.meiguo.order.service.OrderService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderProductDao orderProductDao;
	private static RestTemplate restTemplate = new RestTemplate();
	@Override
	public OrderDO get(String orderNo,Long userId){
		return orderDao.get(orderNo,userId);
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
		OrderDO orderDO = new OrderDO();
		orderDO.setId(id);
		orderDO.setDeleteEnable(1);
		return orderDao.update(orderDO);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderDao.batchRemove(ids);
	}

	@Override
	public String scoreExchange(String orderNo) {
		
		return null;
	}

	
	public  Map<String, Object> kuaid100QueryBase(String postid) {
		// TODO Auto-generated method stub
		String autoComNumUri = "http://www.kuaidi100.com/autonumber/autoComNum?resultv2=1&text="
				+ postid;
		String autoComNumJson = restTemplate.getForObject(autoComNumUri,
				String.class, new Object[] {});
		JSONObject result1 = (JSONObject) JSONObject.parse(autoComNumJson);
		JSONArray autoResult = result1.getJSONArray("auto");
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		if (autoResult != null && autoResult.size() > 0) {
			String comCode = autoResult.getJSONObject(0).getString("comCode");
			String queryUri = "http://www.kuaidi100.com/query?type=" + comCode
					+ "&postid=" + postid + "&temp="
					+ System.currentTimeMillis();
			String postResult = restTemplate.getForObject(queryUri,
					String.class, new Object[] {});
			JSONObject postJson = (JSONObject) JSONObject.parse(postResult);
			if ("200".equals(postJson.getString("status"))) {
				
				result.put("com", comCode);
				result.put("nu", postid);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				JSONArray datas = postJson.getJSONArray("data");
				for (int i = 0; i < datas.size(); i++) {
					JSONObject item = datas.getJSONObject(i);
					Map<String, Object> m = new LinkedHashMap<String, Object>();
					try {
						m.put("time",
								DateUtils.parseDate(item.getString("time"),
										new String[] { "yyyy-MM-dd HH:mm:ss" })
										.getTime());
						m.put("ftime",
								DateUtils.parseDate(item.getString("ftime"),
										new String[] { "yyyy-MM-dd HH:mm:ss" })
										.getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					m.put("context", item.getString("context"));
					list.add(m);
				}
				result.put("code", 0);
				result.put("msg", list);
				return result;
			}
			result.put("code", -1);
			result.put("msg", postJson.getString("message"));
			return result;
		}
		result.put("code",-1);
		result.put("msg", "运单号查询失败");
		return result;
	}

	/**
	 * 订单支付
	 */
	@Override
	public R pay(String orderNo, Integer jflag) {
		OrderDO  orderDO= orderDao.get(orderNo,ShiroUtils.getUserId());
		if(orderDO==null)
			return R.error("订单不存在或已支付");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderNo", orderNo);
		map.put("orderStatus", 0);
		List<OrderProductDO> list = orderProductDao.list(map);
		BigDecimal freeMoney = new BigDecimal(0);
		for(OrderProductDO orderProductDO :list){
			if(orderProductDO.getJiangguoFlag()==0)
				freeMoney=	freeMoney.multiply(orderProductDO.getProductAmount());//浆果票购买
		}
		int result = orderDO.getPayAmount().compareTo(freeMoney);
		if(result==1){//发起微信支付
			
		}
		return R.ok();
	}
	
}
