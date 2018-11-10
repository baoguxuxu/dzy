package com.meiguo.order.control;

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
import com.meiguo.order.domain.OrderProductDO;
import com.meiguo.order.service.OrderProductService;


/**
 * 订单明细表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 10:49:04
 */
 
@Controller
@RequestMapping("/system/orderProduct")
public class OrderProductController {
	@Autowired
	private OrderProductService orderProductService;
	
	@GetMapping()
	@RequiresPermissions("system:orderProduct:orderProduct")
	String OrderProduct(){
	    return "system/orderProduct/orderProduct";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:orderProduct:orderProduct")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderProductDO> orderProductList = orderProductService.list(query);
		int total = orderProductService.count(query);
		PageUtils pageUtils = new PageUtils(orderProductList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:orderProduct:add")
	String add(){
	    return "system/orderProduct/add";
	}

	@GetMapping("/edit/{orderProductId}")
	@RequiresPermissions("system:orderProduct:edit")
	String edit(@PathVariable("orderProductId") Long orderProductId,Model model){
		OrderProductDO orderProduct = orderProductService.get(orderProductId);
		model.addAttribute("orderProduct", orderProduct);
	    return "system/orderProduct/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:orderProduct:add")
	public R save( OrderProductDO orderProduct){
		if(orderProductService.save(orderProduct)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:orderProduct:edit")
	public R update( OrderProductDO orderProduct){
		orderProductService.update(orderProduct);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:orderProduct:remove")
	public R remove( Long orderProductId){
		if(orderProductService.remove(orderProductId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:orderProduct:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] orderProductIds){
		orderProductService.batchRemove(orderProductIds);
		return R.ok();
	}
	
}
