package com.meiguo.order.controller;

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
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.service.OrderService;

/**
 * 订单表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-07 16:39:15
 */
 
@Controller
@RequestMapping("/information/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping()
	@RequiresPermissions("information:order:order")
	String Order(){
	    return "information/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        query.put("orderStatus",params.get("orderStatus"));
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:order:add")
	String add(){
	    return "information/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:order:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "information/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:order:add")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:order:remove")
	public R remove( Long id){
		OrderDO orderDO = new OrderDO();
		orderDO.setId(id);
		orderDO.setDeleteEnable(1);
		orderService.update(orderDO);
		return R.ok();
	
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:order:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
}
