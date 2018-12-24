package com.meiguo.order.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.service.GoodsService;
import com.meiguo.order.domain.AddressDO;
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.domain.OrderProductDO;
import com.meiguo.order.service.AddressService;
import com.meiguo.order.service.OrderProductService;
import com.meiguo.order.service.OrderService;



/**
 * 订单表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-02 10:51:27
 */
 
@Controller
@RequestMapping("/system/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
    
	@Autowired
	private OrderProductService orderProductService;
	
	@GetMapping()
	String Order(Model model,String orderNo){
		model.addAttribute("orderNo",orderNo);
	    return "order/order2";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/order/add";
	}

	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
		 //保存订单详情
			for(OrderProductDO o:order.getList()){
				o.setOrderId(order.getId());
				orderProductService.save(o);
			}
		}
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove/{id}")
	@ResponseBody
	public R remove(@PathVariable("id") Long id){
		if(orderService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 确认收货
	 */
	@PostMapping("/confirmReceive/{id}")
	@ResponseBody
	public R confirmReceive(@PathVariable("id") Long id){
		OrderDO orderDO= new OrderDO();
		orderDO.setId(id);
		orderDO.setOrderStatus(3);
		orderService.update(orderDO);
		return R.ok();
	}
	
	
	/**
	 * 确认订单
	 */
	@ResponseBody
	@GetMapping("/confirm/{orderNo}")
	public Map<String,Object> confirmOrder(@PathVariable("orderNo") String orderNo){
		OrderDO orderDO=  orderService.get(orderNo,ShiroUtils.getUserId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId",orderDO.getId());
		List<OrderProductDO> oplist = orderProductService.list(map);
		map.put("oplist",oplist);
		map.put("orderDO", orderDO);
		map.put("jiangguo", 2);//此处为该用户的浆果票数，数据库查询得到
		return map;
	}
	

	
	
	
	/**
	 * 	购买记录
	 */
	@GetMapping("/jilu")
	public String lookJilu(Integer orderStatus,Model model){
		return "order/goumaijilu";
	}
	
	/**
	 * 查看我的购买记录
	 */
	
	@ResponseBody
	@RequestMapping("/checkjilu")
	public List<OrderDO> lookJilu(Integer orderStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",ShiroUtils.getUserId());
		map.put("orderStatus",orderStatus);
		List<OrderDO> list = orderService.list(map);
		for(OrderDO o:list){
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("orderId",o.getId());
			List<OrderProductDO> oList = orderProductService.list(map1);
			o.setList(oList);
		}
		return list;
 	}
	
	/**
	 * 订单支付
	 */
	@ResponseBody
	@RequestMapping("/pay")
	public R pay(String orderNo,Integer jflag){
		
		return R.error();
	}
	
	/**
	 * 查看物流
	 */
	
	@ResponseBody
	@RequestMapping("/kuaid100Query")
	public Map<String,Object> kuaid100Query(String postid){
		return orderService.kuaid100QueryBase(postid);
	}
}
